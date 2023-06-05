package interfaces;

public interface ConcentrationSiteInterface {
	public void appraiseSit() throws RemoteException;
	public void prepareAssaultParty() throws RemoteException;
	public void sendAssaultParty() throws RemoteException;
	public void amINeeded() throws RemoteException;
	public void prepareExcursion() throws RemoteException;
}
