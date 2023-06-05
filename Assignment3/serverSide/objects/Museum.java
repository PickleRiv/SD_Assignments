package serverSide.objects;

import clientSide.entities.*;
import interfaces.*;

public class Museum implements MuseumInterface {

    private Thread Master

    private Thread[] Thief;

    private thievesStates[] thievesStates;

    private masterStates masterState;
	
    
    /**
     *   Number of entity groups requesting the shutdown.
     */
    private int nEntitiesShut;
    
    /**
     * Creates a museum object and initializes the rooms (0..N)
     * @param gp General Repository
     */
    public Museum(GeneralReposInterface gp) throws RemoteException {
        Master = null;
        masterState = null;
        Thief = new Thread[7-1];			 // parametro M 7
        thiefStates = new thievesStates[7-1]; // parametro M
        
        for(int i=0, i< 7-1, i++) {
        	Thief[i] = null;
        	thievesStates[i] = null;
        }
        
        this.nEntitiesShut = 0;
        rooms = new Room[5-1]; // parametro N 5
        for (int i = 0; i< 5; i++) {
        	rooms[i]
        }
    }
    public synchronized ReturnBool rollACanvas(int thiefId) throws RemoteException {
    	Thief[thiefId] = Thread.currentThread();
    	 thievesStates[thiefId] = OrdinaryThievesStates.AT_A_ROOM;
    	return new ReturnBool ();  
    }
    
    public synchronized void endOperation ()
    {
        while (nEntities == 0)
        { /* the master thief waits for the termination of the ordinary thieves */
            try
            { wait ();
            }
            catch (InterruptedException ignored) {}
        }
        if (Master != null)
        	Master.interrupt ();
    }
    
    public synchronized void shutdown ()
    {
        nEntities += 1;
        if (nEntities >= SimulPar.M)
            ServerMuseum.shutdown();
        notifyAll ();                                        // the master thief may now terminate
    }
}