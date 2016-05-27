package nicolas.trial.dao.solr;

import java.util.ArrayList;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import nicolas.trial.model.Rental;

/**
 * TODO: interface it and make a factory of converters!
 * Solr utility for converting entity objects to solr documents and vice-versa
 * 
 * @author nicolasfontenele
 *
 */
public class SolrRentalConverter {
	
	protected SolrInputDocument convertEntityToSolrDocument(Rental entity) {

		SolrInputDocument rentalDocument = new SolrInputDocument();
		rentalDocument.addField("id", entity.getId());
		rentalDocument.addField("city", entity.getCity());
		rentalDocument.addField("country", entity.getCountry());
		rentalDocument.addField("currency", entity.getCurrency());
		rentalDocument.addField("dailyPrice", entity.getDailyPrice());
		rentalDocument.addField("province", entity.getProvince());
		rentalDocument.addField("roomsNumber", entity.getRoomsNumber());
		rentalDocument.addField("type", entity.getType());
		rentalDocument.addField("hasAirCondition", entity.getZipCode());
		rentalDocument.addField("hasGarden", entity.getZipCode());
		rentalDocument.addField("hasPool", entity.getZipCode());
		rentalDocument.addField("isCloseToBeach", entity.getZipCode());
		rentalDocument.addField("zipCode", entity.getZipCode());

		return rentalDocument;
	}

	/**
	 * TODO : Refactor this huge method!
	 * 
	 * @param doc - solr doc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Rental convertSolrDocToRental(SolrDocument doc) {

		// TODO Multivalued fields - need to use arraylist for now.

		Rental r = new Rental();

		r.setId((String) doc.getFieldValue("id"));

		String city = doc.getFieldValue("city") != null ? ((ArrayList<String>) doc.getFieldValue("city")).get(0) : "";
		r.setCity(city);

		boolean isCloseToBeach = doc.getFieldValue("isCloseToBeach") != null
				? ((ArrayList<Boolean>) doc.getFieldValue("isCloseToBeach")).get(0) : false;
		r.setCloseToBeach(isCloseToBeach);

		String country = doc.getFieldValue("country") != null
				? ((ArrayList<String>) doc.getFieldValue("country")).get(0) : "";
		r.setCountry(country);

		String currency = doc.getFieldValue("currency") != null
				? ((ArrayList<String>) doc.getFieldValue("currency")).get(0) : "";
		r.setCurrency(currency);

		r.setDailyPrice(((ArrayList<Double>) doc.getFieldValue("dailyPrice")).get(0));

		boolean hasAirCondition = doc.getFieldValue("hasAirCondition") != null
				? ((ArrayList<Boolean>) doc.getFieldValue("hasAirCondition")).get(0) : false;
		r.setHasAirCondition(hasAirCondition);

		boolean hasGarden = doc.getFieldValue("hasGarden") != null
				? ((ArrayList<Boolean>) doc.getFieldValue("hasGarden")).get(0) : false;
		r.setHasGarden(hasGarden);

		boolean hasPool = doc.getFieldValue("hasPool") != null
				? ((ArrayList<Boolean>) doc.getFieldValue("hasPool")).get(0) : false;
		r.setHasPool(hasPool);

		String province = doc.getFieldValue("province") != null
				? ((ArrayList<String>) doc.getFieldValue("province")).get(0) : "";
		r.setProvince(province);

		long roomsNumber = ((ArrayList<Long>) doc.getFieldValue("roomsNumber")).get(0);

		r.setRoomsNumber((int) roomsNumber);

		String type = doc.getFieldValue("type") != null ? ((ArrayList<String>) doc.getFieldValue("type")).get(0) : "";
		r.setType(type);

		String zipCode = doc.getFieldValue("zipCode") != null
				? ((ArrayList<String>) doc.getFieldValue("zipCode")).get(0) : "";
		r.setZipCode(zipCode);

		return r;
	}
}
