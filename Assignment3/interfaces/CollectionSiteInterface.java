package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CollectionSiteInterface {
	public void takeARest() throws RemoteException;
	public void handACanvas() throws RemoteException;
	public void collectACanvas() throws RemoteException;
	public synchronized void sumUpResults() throws RemoteException;

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
