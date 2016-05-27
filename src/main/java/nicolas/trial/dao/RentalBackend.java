package nicolas.trial.dao;

import java.util.List;

import nicolas.trial.exceptions.BackendException;
import nicolas.trial.model.Rental;

/**
 * @author nicolasfontenele
 */
public interface RentalBackend extends CRUDBackend<Rental> {

	List<Rental> filterByText(String properties, int rows) throws BackendException;
	
}
