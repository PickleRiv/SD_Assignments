package serverSide.objects;

import clientSide.entities.*;
import interfaces.*;

public class Museum implements MuseumInterface {

    private Thread Master

    private Thread[] Thief;

    private thievesStates[] thieves_states;

    private masterStates master_state;
	
    
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
        master_state = null;
        Thief = new Thread[7-1];			 // parametro M 7
        thiefState = new thievesStates[7-1]; // parametro M
        
        for(int i=0, i< 7-1, i++) {
        	Thief[i] = null;
        	thieves_states[i] = null;
        }
        
        this.nEntitiesShut = 0;
        rooms = new Room[5-1]; // parametro N 5
        for (int i = 0; i< 5; i++) {
        	rooms[i]
        }
    }
    public synchronized ReturnBoolean rollACanvas(int thiefId) throws RemoteException {
    	Thief[thiefId] = Thread.currentThread();
    	
    	return new ReturnBoolean true;  
    }
    
    public synchronized void shutdown ()
    {
        nEntities += 1;
        if (nEntities >= Simul_Par.M)
            ServerMuseum.shutdown();
        notifyAll ();                                        // the master thief may now terminate
    }
}