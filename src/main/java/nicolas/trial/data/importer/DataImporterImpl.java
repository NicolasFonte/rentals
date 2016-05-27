package nicolas.trial.data.importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import nicolas.trial.dao.mongodb.RentalMongoDbBackend;
import nicolas.trial.dao.solr.RentalSolrBackend;
import nicolas.trial.exceptions.BackendException;
import nicolas.trial.model.Rental;

/**
 * Imports CSV data over mongodb and solr
 * TODO : design notes - abs factory + interface for generic importers
 * @author nicolasfontenele
 *
 */
public class DataImporterImpl implements DataImporter {

	RentalMongoDbBackend mongoDbBackend;
	RentalSolrBackend solrBackend;

	public DataImporterImpl(String solrPath) {
		mongoDbBackend = new RentalMongoDbBackend();
		solrBackend = new RentalSolrBackend(solrPath);
	}

	@Override
	public void importData(String datasetPath) {

		List<String> allLines = null;
		try {
			allLines = Files.readAllLines(Paths.get(datasetPath));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		int count = 0;
		for (String line : allLines) {

			Rental rental = null;
			try {
				rental = buildRentalFromLine(line);
				mongoDbBackend.create(rental);
				solrBackend.create(rental);
				
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			} catch (BackendException e) {
				System.out.println(e.getMessage());
				return;
			}
			System.out.println("Imported " + (++count) + "documents. Current: " + rental.getId());
		}
	}

	private Rental buildRentalFromLine(String line) {

		String[] values = line.split(",");

		String id = values[0];
		String city = values[1];
		String province = values[2];
		String country = values[3];
		String zipCode = values[4];
		String type = values[5];
		String hasAirCondition = values[6];
		String hasGarden = values[7];
		String hasPool = values[8];
		String isCloseToBeach = values[9];
		String dailyPrice = values[10];
		String currency = values[11];
		String roomsNumber = values[12];

		Rental rental = new Rental();

		rental.setCity(city);
		rental.setCloseToBeach(textToBoolean(isCloseToBeach));
		rental.setCountry(country);
		rental.setCurrency(currency);
		rental.setDailyPrice(textToDouble(dailyPrice));
		rental.setHasAirCondition(textToBoolean(hasAirCondition));
		rental.setHasGarden(textToBoolean(hasGarden));
		rental.setHasPool(textToBoolean(hasPool));
		rental.setId(id);
		rental.setProvince(province);
		rental.setRoomsNumber(textToInteger(roomsNumber));
		rental.setType(type);
		rental.setZipCode(zipCode);
		return rental;
	}

	private boolean textToBoolean(String text) {

		if (text != null && !text.isEmpty()) {
			if (text.equals("false")) {
				return false;
			} else if (text.equals("true")) {
				return true;
			}
			throw new IllegalArgumentException("invalid boolean text");
		}
		throw new IllegalArgumentException("empty boolean text");
	}

	private double textToDouble(String text) {

		if (text != null && !text.isEmpty()) {
			return Double.valueOf(text);
		}
		throw new IllegalArgumentException("empty double text");
	}

	private int textToInteger(String text) {

		if (text != null && !text.isEmpty()) {
			return Integer.valueOf(text);
		}
		throw new IllegalArgumentException("empty integer text");
	}

}
