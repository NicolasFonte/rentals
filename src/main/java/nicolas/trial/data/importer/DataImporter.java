package nicolas.trial.data.importer;

/**
 * Generic importer definition
 * 
 * @author nicolasfontenele
 *
 */
public interface DataImporter {
	
	/**
	 * 
	 * @param datasetPath - path of the dataset to be imported
	 */
	void importData(String datasetPath);
}
