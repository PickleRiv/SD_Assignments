package clientSide.entities;

//import interfaces.*;
import clientSide.entities.ThiefStates;
import java.rmi.RemoteException;
//import genclass.GenericIO;

/**
 *    Chef thread.
 *
 *      It simulates the chef life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on remote calls under Java RMI.
 */
public class OrdinaryThief extends Thread{

	/**
	 *	Chef state 
	 */
	private int thiefState;
	private int thiefId;
	
	
	
	/**
	 * Set a new thief state
	 * @param thiefState new state to be set
	 */
	public void setThiefState(int thiefState) { this.thiefState = thiefState; }	
	
	/**
	 * Get the thief's state
	 * @return thiefState
	 */
	public int getThiefState() { return thiefState; }
	
	public int getThiefId() { return thiefId; }
	
	/**
	 * Instantiation of a thief thread
	 * 	@param name thread name
	 *  @param id thread id
	 */
	public OrdinaryThief(String name,int id) {
		super(name);
		this.thiefState = ThiefStates.CONCENTRATION_SITE;
		this.thiefId = id;
	}
	
	/**
	 * 	Life cycle of the chef
	 */
	@Override
	public void run (){
		System.out.println(thiefState);
	}
	
	
}