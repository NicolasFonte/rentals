package nicolas.trial.dao.mongodb;

import java.util.List;

import org.bson.Document;

import nicolas.trial.dao.RentalBackend;
import nicolas.trial.exceptions.BackendException;
import nicolas.trial.model.Rental;

public class RentalMongoDbBackend extends MongoDbBackend implements RentalBackend {

	private static String RENTAL_COLLECTION = "rental";

	@Override
	public Rental update(Rental entity) throws BackendException {
		return null;
	}

	@Override
	public void create(Rental entity) throws BackendException {

		Document rentalJson = new Document();
		if (entity != null) {
			rentalJson = convertEntityToDocument(entity);
			getCollection(RENTAL_COLLECTION).insertOne(rentalJson);
			// closeResources();
		} else {
			throw new BackendException("Rental to be inserted is null");
		}
	}

	@Override
	public void remove(Rental entity) throws BackendException {
	}

	@Override
	public Rental read(String identifier) throws BackendException {
		return null;
	}

	@Override
	public List<Rental> list() throws BackendException {
		return null;
	}

	@Override
	public List<Rental> filterByText(String text, int rows) {
		return null;
	}
}
