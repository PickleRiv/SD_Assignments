package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AssaultPartyInterface extends Remote{
	public void crawlIn() throws RemoteException;
	public void crawlOut() throws RemoteException;
	public void reverseDirection() throws RemoteException;
    /**
     *   Operation server shutdown.
     *
     *   New operation.
     *
     *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry
     *                             service fails
     */

    public void shutdown () throws RemoteException;
}
