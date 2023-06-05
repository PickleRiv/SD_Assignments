package interfaces;

public interface AssaultPartyInterface extends Remote{
	public void crawlIn() throws RemoteException;
	public void crawlOut() throws RemoteException;
	public void reverseDirection() throws RemoteException;
}
