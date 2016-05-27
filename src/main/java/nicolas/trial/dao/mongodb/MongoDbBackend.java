package nicolas.trial.dao.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import nicolas.trial.model.Rental;

/**
 * Wrapper class for mongo db client/connection
 * @author nicolasfontenele
 *
 */
public class MongoDbBackend {

	MongoClient client;
	MongoDatabase db;

	public MongoDbBackend() {

	}

	/**
	 * Gets a mongodb table/collection on 'rentals' database
	 * @param collection
	 * @return
	 */
	protected MongoCollection<Document> getCollection(String collection) {

		if (client == null) {
			client = new MongoClient();
		}
		db = client.getDatabase("rentals");

		return db.getCollection(collection);
	}

	/**
	 * make sure everything is close
	 */
	protected void closeResources() {

		if (client != null) {
			client.close();
		}
	}

	/**
	 * rental to json helper method
	 * @param entity
	 * @return
	 */
	protected Document convertEntityToDocument(Rental entity) {

		Document rentalDocument = new Document().append("_id", entity.getId()).append("city", entity.getCity())
				.append("country", entity.getCountry()).append("currency", entity.getCurrency())
				.append("dailyPrice", entity.getDailyPrice()).append("province", entity.getProvince())
				.append("roomsNumber", entity.getRoomsNumber()).append("type", entity.getType())
				.append("zipCode", entity.getZipCode()).append("hasAirCondition", entity.isHasAirCondition())
				.append("hasGarden", entity.isHasGarden()).append("hasPool", entity.isHasPool())
				.append("hasPool", entity.isCloseToBeach());

		return rentalDocument;
	}
}
