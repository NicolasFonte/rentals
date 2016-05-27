package nicolas.trial.data.importer;

/**
 * Importer data tool. Takes the dataset path and the solr url and import on
 * both infrastructure.
 * 
 * @author nicolasfontenele
 *
 */
public class ImporterApp {
	public static void main(String[] args) {

		String datasetPath = args[0];
		String solrUrl = args[1];

		DataImporterImpl importer = new DataImporterImpl(solrUrl);
		importer.importData(datasetPath);
	}
}
