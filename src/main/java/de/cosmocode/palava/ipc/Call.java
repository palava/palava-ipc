package de.cosmocode.palava.ipc;

import java.util.Map;

/**
 * A call symbolizes the request to execute a {@link Command} with
 * all given informations.
 *
 * @author Tobias Sarnowski
 */
public interface Call {

	/**
	 * A request related session.
	 *
	 * @return must not be null
	 */
	Session getSession();

	/**
	 * All given arguments for the request.
	 * 
	 * @return must not be null
	 */
	Map<String,Object> getArguments();

}
