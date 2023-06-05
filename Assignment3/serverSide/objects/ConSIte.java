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

    private thievesStates[] thievesStates;

    private masterStates masterState;
	
    public CCsite()
    {
    	 Master = null;
    	 masterState = null;
    	 Thief = new Thread[7-1];			 // parametro M 7
         thiefStates = new thievesStates[7-1]; // parametro M
         
         for(int i=0, i< 7-1, i++) {
         	Thief[i] = null;
         	thievesStates[i] = null;
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
            Master.interrupt ();
    }
    
    public synchronized ReturnInt amINeeded(int ot_id){
    	   return new ReturnInt(0, OrdinaryThievesStates.CONCENTRATION_SITE);
    }
    
    public synchronized void prepareAssaultParty(){
    	
    }
	
	 public synchronized void shutdown ()
	    {
	        nEntities += 1;
	        if (nEntities >= SimulPar.M)
	            ServerMasterThiefCCS.shutdown();
	        notifyAll ();                                        // the master thief may now terminate
	    }
}