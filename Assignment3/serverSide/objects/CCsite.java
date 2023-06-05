package serverSide.objects;

package serverSide.objects;
import interfaces.*;

import java.rmi.RemoteException;

public class CCsite implements CCsiteInterface{
	
	private int canvasCollected;
    private boolean partiesReady;
    private int back;
    private boolean collect;
    private Party[] parties;	
	private int nEntitiesShut;
	
    private Thread Master;

    private Thread[] Thief;

    private thievesStates[] thievesStates;

    private masterState masterState;
	
    public CCsite()
    {
    	 Master = null;
    	 masterState = null;
    	 Thief = new Thread[7-1];			 // parametro M 7
    	 thievesStates = new thievesStates[7-1]; // parametro M
         
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
    
    public synchronized ReturnInt startOperations(){
        System.out.println("Starting operations...");
        Master = Thread.currentThread();
        masterState = MasterThiefStates.DECIDING_WHAT_TO_DO;

        return new ReturnInt(0, masterState);
    }
	
    public synchronized ReturnBool appraiseSit() {
    	return ReturnBool(true, masterState);
    }
    
	public synchronized ReturnInt takeARest(){
		
		System.out.println("Thieves are back! Master thief waking up! \n");
        return new ReturnInt(0, masterState);
	}
	
	 public synchronized ReturnInt prepareExcursion(int thief_id) throws RemoteException {
		 return new ReturnInt(idx, thievesStates[thief_id]);
	 }
	 
	 public synchronized ReturnInt sendAssaultParty(){
		 return new ReturnInt(0, masterState);
	 }
	 
	  public synchronized ReturnInt collectACanvas(){
		  return new ReturnInt(0, masterState);
	  }
	  
	  public synchronized int sumUpResults(){
	        return getCollected_canvas();
	    }
	  
	  public synchronized ReturnInt handACanvas(int thief_id, boolean isHoldingCanvas){
		  return new ReturnInt(0, thievesStates[thief_id]);
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
	            ServerMasterThiefCCS.shutdown();
	        notifyAll ();                                        // the master thief may now terminate
	    }
}
