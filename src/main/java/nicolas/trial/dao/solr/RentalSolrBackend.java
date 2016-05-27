package nicolas.trial.dao.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import nicolas.trial.dao.RentalBackend;
import nicolas.trial.exceptions.BackendException;
import nicolas.trial.model.Rental;

/**
 * Backend Solr class for performing data manipulation over solr cluster for Rental entities
 * 
 * @author nicolasfontenele
 *
 */
public class RentalSolrBackend extends SolrBackend implements RentalBackend {

	SolrRentalConverter solrRentalConverter;
	
	public RentalSolrBackend(String urlSolr) {
		super(urlSolr);
		solrRentalConverter = new SolrRentalConverter();
	}

	@Override
	public Rental update(Rental entity) throws BackendException {
		throw new UnsupportedOperationException("Out of scope for now");
	}

	@Override
	public void create(Rental entity) throws BackendException {

		try {

			SolrInputDocument document = solrRentalConverter.convertEntityToSolrDocument(entity);
			UpdateResponse response = getClient().add(document);
			getClient().commit();
			closeResources();

			System.out.println(response);
		} catch (SolrServerException | IOException e) {
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public void remove(Rental entity) throws BackendException {
		throw new UnsupportedOperationException("Out of scope for now");
	}

	@Override
	public Rental read(String identifier) throws BackendException {

		SolrQuery query = new SolrQuery();
		query.setQuery("id:" + identifier);

		QueryResponse response = null;
		try {
			response = getClient().query(query);
			closeResources();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			throw new BackendException("Error in Solr reading query", e);
		}
		SolrDocument result = response.getResults().get(0);

		Rental r = new Rental();
		r.setId(result.getFieldValue("id").toString());
		return r;
	}

	@Override
	public List<Rental> list() throws BackendException {
		throw new UnsupportedOperationException("not on scope");
	}

	
	@Override
	public List<Rental> filterByText(String solrQuery, int rows) throws BackendException {
		
		List<Rental> rentals = new ArrayList<Rental>();
		
		SolrQuery query = new SolrQuery();
		query.setQuery(solrQuery);
		query.setRows(rows);

		QueryResponse response = null;
		try {
			response = getClient().query(query);
			closeResources();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			throw new BackendException("Error in Solr reading query", e);
		}
		
		SolrDocumentList results = response.getResults();
		
		for ( SolrDocument doc : results ) {
			Rental r = solrRentalConverter.convertSolrDocToRental(doc);
			rentals.add(r);
		}
		return rentals;
	}
	
}
