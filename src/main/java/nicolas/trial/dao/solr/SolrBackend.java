package nicolas.trial.dao.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
/**
 * This class generic solr specific connections
 * 
 * @author nicolasfontenele
 *
 */
public class SolrBackend {

	private String urlSolr;
	SolrClient solr;

	public SolrBackend(String urlSolr) {
		this.urlSolr = urlSolr;
	}

	/**
	 * Use Http Solr Client
	 * @return
	 */
	protected SolrClient getClient() {
		if (solr == null) {
			solr = new HttpSolrClient(urlSolr);
		}
		return solr;
	}

	/**
	 * Should be called after each solr commit/connection
	 * 
	 * @throws IOException
	 */
	protected void closeResources() throws IOException {
		if (solr != null) {
			solr.close();
			solr = null;
		}
	}

}
