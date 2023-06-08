package serverSide.objects;

import clientSide.entities.*;
import interfaces.CollectionSiteInterface;
import serverSide.main.*;

import java.rmi.RemoteException;
public class CollectionSite implements CollectionSiteInterface{
	private int canvas;
    private int nEntities;

	public CollectionSite() {
		this.canvas = 0;
		this.nEntities = 0;

	}
	
	public synchronized void takeARest(){

	}
	public synchronized void handACanvas(boolean carrying){
		
	}
	
	public synchronized void collectACanvas(){
	
	}
	
	public synchronized void sumUpResults(){

	}
	
    public synchronized void shutdown ()
    {
        nEntities += 1;
        if (nEntities >= 1)
            ServerCollectionSite.shutdown();
        notifyAll ();                                        // the master thief may now terminate
    }
}
