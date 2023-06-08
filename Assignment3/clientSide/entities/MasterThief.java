package clientSide.entities;

import interfaces.*;
import clientSide.entities.MasterStates;
import java.rmi.RemoteException;

public class MasterThief extends Thread{

	private int masterState;
	private int roomsAvailable;
	private int busyParties;
	private final int masterId;
	private final CollectionSiteInterface ccSiteIntf;
	private final ConcentrationSiteInterface conSiteIntf;
	private final MuseumInterface museumIntf;
	private final AssaultPartyInterface APIntf;
	
	
	public void setMasterState(int masterState) { this.masterState = masterState; }	
	
	public int getMasterState() { return masterState; }
	
	public MasterThief(String name,int id, CollectionSiteInterface ccSiteStub, ConcentrationSiteInterface conSiteIntf, AssaultPartyInterface APIntf, MuseumInterface MuseumIntf) {
		super(name);
		this.masterId = id;
		this.masterState = MasterStates.PLANNING_THE_HEIST;
		this.roomsAvailable = 8;
		this.busyParties = 0;
		this.ccSiteIntf = ccSiteIntf;
		this.museumIntf = museumIntf;
		this.APIntf = APIntf;
		this.conSiteIntf = conSiteIntf;
	}
	
	@Override
	public void run (){
		startOperations();
		while(roomsAvailable != 0 && busyParties >= 0) {
			appraiseSit();
			System.out.println(masterState);
			if (roomsAvailable > 0 && busyParties<2) {
				prepareAssaultParty();
				System.out.println(masterState);
				roomsAvailable -= 1;
				busyParties += 1;
				sendAssaultParty();
				System.out.println(masterState);
			}
			if (busyParties == 2 || (roomsAvailable ==0 && busyParties > 0)) {
				takeARest();
				System.out.println(masterState);
				busyParties -= 1;
				collectACanvas();
			}
		}
		sumUpResults();
		System.out.println(masterState);
	}
	
	
	private void startOperations(){
		try { 
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			ccSiteIntf.startOperations();	
			}
		catch (RemoteException e){
			System.out.println("Master remote exception on startPreparation: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
	private void appraiseSit(){
		try { 
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			conSiteIntf.appraiseSit();	
			}
		catch (RemoteException e){
			System.out.println("Master remote exception on appraiseSit: " +e.getMessage());
			System.exit(1);
		}
	}
	
	private void prepareAssaultParty(){
		try { 
			masterState = MasterStates.ASSEMBLING_A_GROUP;
			conSiteIntf.prepareAssaultParty();	
			}
		catch (RemoteException e){
			System.out.println("Master remote exception on prepareAssaultParty: " +e.getMessage());
			System.exit(1);
		}
	}
	
	private void sendAssaultParty(){
		try { 
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			conSiteIntf.sendAssaultParty();	
			}
		catch (RemoteException e){
			System.out.println("Master remote exception on sendAssaultParty: " +e.getMessage());
			System.exit(1);
		}
	}
	
	private void takeARest(){
		try { 
			masterState = MasterStates.WAITING_FOR_ARRIVAL;
			ccSiteIntf.takeARest();	
			}
		catch (RemoteException e){
			System.out.println("Master remote exception on takeARest: " +e.getMessage());
			System.exit(1);
		}
	}
	
	private void collectACanvas(){
		try { 
			masterState = MasterStates.DECIDING_WHAT_TO_DO;
			ccSiteIntf.collectACanvas();	
			}
		catch (RemoteException e){
			System.out.println("Master remote exception on collectACanvas: " +e.getMessage());
			System.exit(1);
		}
	}
	
	private void sumUpResults(){
		try { 
			masterState = MasterStates.PRESENTING_THE_REPORT;
			ccSiteIntf.sumUpResults();	
			}
		catch (RemoteException e){
			System.out.println("Master remote exception on sumUpResults: " +e.getMessage());
			System.exit(1);
		}
	}	
}
