package nicolas.trial.rest;

import javax.ws.rs.core.Context;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import nicolas.trial.dao.RentalBackend;
import nicolas.trial.dao.mongodb.RentalMongoDbBackend;
import nicolas.trial.dao.solr.RentalSolrBackend;
import nicolas.trial.exceptions.BackendException;
import nicolas.trial.model.Rental;


/**
 * 
 * @author nicolasfontenele
 *
 * Rest resource for Rental operations
 *
 */
@Path("/")
public class RentalResource {

	private RentalBackend dbBackend;
	private RentalBackend solrBackend;
	
	@Context 
	private ServletContext context;

	public RentalResource() {
		
	}
	
	/**
	 * Note: There are few dependency injection we can use here for the backend.
	 * I think this is out of scope for now since here.
	 * If so - consider to use: https://github.com/google/guice
	 * @return
	 */
	public RentalBackend getDbBackend() {
		if ( dbBackend == null ) {
			dbBackend = new RentalMongoDbBackend();
		}
		return dbBackend;
	}
	
	/**
	 * Note: There are few dependency injection we can use here for the backend.
	 * I think this is out of scope for now since here.
	 * If so - consider to use: https://github.com/google/guice
	 * @return
	 */
	public RentalBackend getSolrBackend() {
		if ( solrBackend == null ) {
			String solrUrl = context.getInitParameter("solrUrl");
			solrBackend = new RentalSolrBackend(solrUrl);
		}
		return solrBackend;
	}

	/**
	 * 
	 * Creating by sending json
	 * 
	 * @param rental
	 * @return
	 */
	@POST
	@Path("/create/json")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addRental(Rental rental) {
		
		System.out.println("Rental: " + rental.getId());
		
		dbBackend = getDbBackend();
		solrBackend = getSolrBackend();
		
		try {
			dbBackend.create(rental);
			solrBackend.create(rental);
		} catch (BackendException ex) {
			return Response.ok("Exception in the backend: " + ex.getMessage()).build();
		}
		return Response.ok("created successfuly").build();
	}
	
	@POST
	@Path("/create/form")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addRental(MultivaluedMap<String, String> formParams) {
		
		Rental rental = formParamsToRental(formParams);
		System.out.println("Rental: " + rental.getId());
		
		dbBackend = getDbBackend();
		solrBackend = getSolrBackend();
		
		try {
			dbBackend.create(rental);
			solrBackend.create(rental);
		} catch (BackendException ex) {
			return Response.ok("Exception in the backend: " + ex.getMessage()).build();
		}
		return Response.ok("created successfuly").build();
	}
	
	/**
	 * Just a converter from form params to Rental objects
	 * TODO: add all parameters
	 * @param formParams
	 * @return
	 */
	private Rental formParamsToRental(MultivaluedMap<String, String> formParams) {
		
		Rental r = new Rental();
		
		r.setId(formParams.getFirst("form-id"));
		r.setCountry(formParams.getFirst("form-country"));
		r.setProvince(formParams.getFirst("form-province"));
		r.setCity(formParams.getFirst("form-city"));
		r.setRoomsNumber(Integer.valueOf(formParams.getFirst("form-roomsNumber")));
		r.setDailyPrice(Double.valueOf(formParams.getFirst("form-dailyPrice")));
		
		return r;
	}

	/**
	 * REST API for search in Solr
	 * 
	 * @param query
	 * @param rows
	 * @return
	 */
	@GET
	@Path("{solrQuery}/{rowNumber}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response listRentals(@PathParam("solrQuery") String query, @PathParam("rowNumber") Integer rows) {
		
		solrBackend = getSolrBackend();
		
		List<Rental> rentals = null;
		try {
			rentals = solrBackend.filterByText(query, rows);
			if (rentals == null) {
				return Response.ok("Rentals by solr query " + query + " was not found").build();
			}
		} catch (BackendException ex) {
			return Response.ok("Exception in the backend: " + ex.getMessage()).build();
		}
		return Response.ok(rentals).build();
	}
}
