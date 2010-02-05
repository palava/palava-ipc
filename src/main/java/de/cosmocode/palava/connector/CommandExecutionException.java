package de.cosmocode.palava.connector;

/**
 * Should be thrown if something went wrong during the
 * execution of a {@link Command}.
 *
 * @author Tobias Sarnowski
 */
public class CommandExecutionException extends Exception {

	/**
	 * This is object is just a wrapper and as a consequence,
	 * you can only nest other exceptions.
	 *
	 * @param cause the real exception
	 */
	public CommandExecutionException(Throwable cause) {
		super(cause);
	}

}
