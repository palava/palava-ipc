package de.cosmocode.palava.ipc;

/**
 * Register your object with this interface to the public Registry
 * to get triggered when a Session will be removed to cleanup
 * data in it.
 *
 * @author Tobias Sarnowski
 */
public interface IpcEventSessionRemoved {

	/**
	 * Cleanup session data.
	 *
	 * @param ipcSession the session which will be removed
	 */
	void eventIpcSessionRemoved(IpcSession ipcSession);

}
