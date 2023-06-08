package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MuseumInterface extends Remote {
	
	public boolean rollACanvas() throws RemoteException;
	
	public void reverseDirection() throws RemoteException;
	
	public int getDistanceToRoom(int roomNumber) throws RemoteException;
	
	/**
	 * Operation museum server shutdown
	 * @throws Remote Exception if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void shutdown() throws RemoteException;
}
