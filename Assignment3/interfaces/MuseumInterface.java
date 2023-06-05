package interfaces;

public interface MuseumInterface extends Remote {
	
	/**
	 * Operation roll a canvas
	 * It is called by the ordinary thief to signal that he is taking a canvas from the room
	 * 	@param thiefId id of the thief
	 * 	@return state of the thief
	 * 	@throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void rollACanvas() throws RemoteException;
	
	/**
	 * Operation museum server shutdown
	 * @throws Remote Exception if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void shutdown() throws RemoteException;
}
