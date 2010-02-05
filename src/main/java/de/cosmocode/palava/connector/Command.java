package de.cosmocode.palava.connector;

import java.util.Map;

/**
 * A command provides an interface to get called by
 * a heterogeneous environment.
 *
 * @author Tobias Sarnowski
 */
public interface Command {

	/**
	 * This method will be called upon request.
	 * 
	 * @param call Contains all given informations.
	 * @param result Can be filled with return values.
	 * @throws CommandExecutionException 
	 */
	void execute(Call call, Map result) throws CommandExecutionException;
}
