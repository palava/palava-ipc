package de.cosmocode.palava.ipc;

/**
 * The implementing object provides the session management
 * for {@link Command} calls.
 *
 * @author Tobias Sarnowski
 */
public interface SessionProvider {

	/**
	 * The only way to access a session.
	 *
	 * @param sessionId the unique session identifier used by the caller
	 * @param identifier an as unique as possible identifier for the caller determined by someone else than the caller
	 * @return must not be null, provides the requested session or a new one
	 */
	Session getSession(String sessionId, String identifier);

}
