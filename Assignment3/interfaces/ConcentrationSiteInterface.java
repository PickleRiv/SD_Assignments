package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConcentrationSiteInterface {
	public void appraiseSit() throws RemoteException;
	public void prepareAssaultParty() throws RemoteException;
	public void sendAssaultParty() throws RemoteException;
	public void amINeeded() throws RemoteException;
	public void prepareExcursion() throws RemoteException;
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
