package nicolas.trial.exceptions;

/**
 * Simple Generic backend exception for any problem in the backend
 * 
 * @author nicolasfontenele
 */
public class BackendException extends Exception {

	private static final long serialVersionUID = 8454576074694018441L;

	public BackendException() {
		super();
	}

	public BackendException(String message) {
		super(message);
	}

	public BackendException(String message, Throwable cause) {
		super(message, cause);
	}

	public BackendException(Throwable cause) {
		super(cause);
	}
	
}
