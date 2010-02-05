package de.cosmocode.palava.ipc;

/**
 * The implementing object manages the source of requested
 * commands.
 *
 * Implementation has to available via Guice with this interface.
 *
 * @author Tobias Sarnowski
 */
public interface CommandProvider {

	/**
	 * Provides a factory for {@link Command}s.
	 *
	 * @param command the requested command type
	 * @return must not be null
	 * @throws CommandNotAvailable will be returned to the caller
	 */
	Command getCommand(Class<Command> command) throws CommandNotAvailable;

}
