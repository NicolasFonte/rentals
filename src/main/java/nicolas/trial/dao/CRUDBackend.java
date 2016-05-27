package nicolas.trial.dao;

import java.util.List;

import nicolas.trial.exceptions.BackendException;

/**
 * Generic CRUD interface
 * 
 * @author nicolasfontenele
 *
 * @param <T> - entity class
 */
public interface CRUDBackend<T extends Object> {

	T update(T entity) throws BackendException;

	void create(T entity) throws BackendException;

	void remove(T entity) throws BackendException;

	T read(String identifier) throws BackendException;

	List<T> list() throws BackendException;
}