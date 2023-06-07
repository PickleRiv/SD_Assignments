package entities;

import sharedRegions.*;
import entities.*;
import commInfra.*;
import main.*;

public class MasterThief extends Thread {
	private int masterState;
	private int roomsAvailable;
	private int busyParties;
	private int masterId;
	private final CollectionSite ccSite;
	private final ConcentrationSite conSite;
	
	
	public void setMasterState(int masterState) { this.masterState = masterState; }	
	
	public int getMasterState() { return masterState; }
	
	
	public MasterThief(String name,int id, CollectionSite ccSite, ConcentrationSite conSite) {
		super(name);
		this.masterId = id;
		this.ccSite = ccSite;
		this.conSite = conSite;
		this.masterState = MasterStates.PLANNING_THE_HEIST;
		this.roomsAvailable = 8;
		this.busyParties = 0;
	}
	
	@Override
	public void run (){
		startOperations();
		while(roomsAvailable != 0 && busyParties >= 0) {
			appraiseSit();
			if (roomsAvailable > 0 && busyParties<2) {
				prepareAssaultParty();
				roomsAvailable -= 1;
				busyParties += 1;
				sendAssaultParty();
			}
			if (busyParties == 2 || (roomsAvailable ==0 && busyParties > 0)) {
				takeARest();
				busyParties -= 1;
				collectACanvas();
			}
		}
		sumUpResults();
	}
	
	private void startOperations(){
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
	}
	
	private void appraiseSit(){
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			conSite.appraiseSit();
	}
	
	private void prepareAssaultParty(){
			masterState = MasterStates.ASSEMBLING_A_GROUP;
			conSite.prepareAssaultParty();
	}
	
	private void sendAssaultParty(){
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			conSite.sendAssaultParty();
	}
	
	private void takeARest(){
			masterState = MasterStates.WAITING_FOR_ARRIVAL;
			ccSite.takeARest();
	}
	
	private void collectACanvas(){
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			ccSite.collectACanvas();	
	}
	
	private void sumUpResults(){
			masterState = MasterStates.PRESENTING_THE_REPORT;
			ccSite.sumUpResults();	
	}

}
