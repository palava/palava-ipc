package de.cosmocode.palava.connector;

/**
 * Will be used by the {@link CommandProvider} and will be given
 * to the Command caller.
 *
 * @author Tobias Sarnowski
 */
public class CommandNotAvailable extends Exception {

	/**
	 *
	 * @param requestedCommand
	 */
	public CommandNotAvailable(Class<Command> requestedCommand) {
		super("command '" + requestedCommand.getName() + "' is not available");
	}

	/**
	 * 
	 * @param requestedCommand
	 * @param throwable
	 */
	public CommandNotAvailable(Class<Command> requestedCommand, Throwable throwable) {
		super("command '" + requestedCommand.getName() + "' is not available", throwable);
	}

}
