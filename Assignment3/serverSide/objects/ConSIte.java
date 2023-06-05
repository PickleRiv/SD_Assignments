package serverSide.objects;

package serverSide.objects;
import interfaces.*;

import java.rmi.RemoteException;

public class ConSite implements ConSiteInterface{
	
	private int thievesAvail;
    private final int[] ordThieves;	
	private int nEntitiesShut;
	
    private Thread Master;

    private Thread[] Thief;

    private thievesStates[] thieves_states;

    private masterStates master_state;
	
    public CCsite()
    {
    	 Master = null;
    	 master_state = null;
    	 Thief = new Thread[7-1];			 // parametro M 7
         thiefState = new thievesStates[7-1]; // parametro M
         
         for(int i=0, i< 7-1, i++) {
         	Thief[i] = null;
         	thieves_states[i] = null;
         }
         
         this.nEntitiesShut = 0;
         this.canvasCollected = 0;
         this.partiesReady = false;
         this.collect = false;
         // unfinished
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
        if ( != null)
            MT.interrupt ();
    }
	
	 public synchronized void shutdown ()
	    {
	        nEntities += 1;
	        if (nEntities >= Simul_Par.M)
	            ServerMasterThiefCCS.shutdown();
	        notifyAll ();                                        // the master thief may now terminate
	    }
}