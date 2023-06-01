package clientSide.entities;

//import interfaces.*;
import clientSide.entities.MasterStates;
import java.rmi.RemoteException;

public class MasterThief extends Thread{

	private int masterState;
	private int roomsAvailable;
	private int busyParties;
	private int masterId;
	
	
	public void setMasterState(int masterState) { this.masterState = masterState; }	
	
	public int getMasterState() { return masterState; }
	
	
	public MasterThief(String name,int id) {
		super(name);
		this.masterId = id;
		this.masterState = MasterStates.PLANNING_THE_HEIST;
		this.roomsAvailable = 8;
		this.busyParties = 0;
	}
	
	
	
	/**
	 * 	Life cycle of the chef
	 */
	@Override
	public void run (){
		System.out.println(masterState);
		while(roomsAvailable != 0 && busyParties >= 0) {
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			System.out.println(masterState);
			if (roomsAvailable > 0 && busyParties<2) {
				masterState = MasterStates.ASSEMBLING_A_GROUP;
				System.out.println(masterState);
				roomsAvailable -= 1;
				busyParties += 1;
			}
			if (busyParties == 2 || (roomsAvailable ==0 && busyParties > 0)) {
				masterState = MasterStates.WAITING_FOR_ARRIVAL;
				System.out.println(masterState);
				busyParties -= 1;
			}
		}
		masterState = MasterStates.PRESENTING_THE_REPORT;
		System.out.println(masterState);
	}
	
	
}