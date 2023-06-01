package clientSide.entities;

//import interfaces.*;
import clientSide.entities.MasterStates;
import java.rmi.RemoteException;
//import genclass.GenericIO;

/**
 *    Chef thread.
 *
 *      It simulates the chef life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on remote calls under Java RMI.
 */
public class MasterThief extends Thread{

	/**
	 *	Chef state 
	 */
	private int masterState;
	
	
	
	/**
	 * Set a new chef state
	 * @param chefState new state to be set
	 */
	public void setMasterState(int masterState) { this.masterState = masterState; }	
	
	/**
	 * Get the chef's state
	 * @return chef state
	 */
	public int getMasterState() { return masterState; }
	
	
	
	/**
	 * Instantiation of a Chef thread
	 * 	@param name thread name
	 * 	@param kitStub reference to the kitchen interface
	 * 	@param barStub reference to the bar interface
	 */
	public MasterThief(String name) {
		super(name);
		this.masterState = MasterStates.PLANNING_THE_HEIST;
	}
	
	
	
	/**
	 * 	Life cycle of the chef
	 */
	@Override
	public void run (){
		System.out.println(masterState);
	}
	
	
}