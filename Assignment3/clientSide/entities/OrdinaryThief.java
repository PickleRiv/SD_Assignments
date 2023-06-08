package clientSide.entities;

import interfaces.*;
import clientSide.entities.ThiefStates;
import java.rmi.RemoteException;

public class OrdinaryThief extends Thread{

	private int thiefState;
	private int thiefId;
	private boolean needed;
	private boolean isOver;
	private int rooms;
	private boolean carrying;
	private final CollectionSiteInterface ccSiteIntf;
	private final ConcentrationSiteInterface conSiteIntf;
	private final MuseumInterface museumIntf;
	private final AssaultPartyInterface apIntf;
	
	
	public void setThiefState(int thiefState) { this.thiefState = thiefState; }	

	public int getThiefState() { return thiefState; }
	
	public int getThiefId() { return thiefId; }

	public OrdinaryThief(String name,int id) {
		super(name);
		this.thiefId = id;
		this.needed = false;
		this.rooms = 8;
		this.carrying = false;
	}
	
	@Override
	public void run (){
		while(rooms > 0) {
			needed = false;
			thiefState = ThiefStates.CONCENTRATION_SITE;
			System.out.println(thiefState);
			int dist = 30;
			
			if (!needed) {
				AmINeeded();
			}
			
			prepareExcursion();
			System.out.println(thiefState);
			
			crawlIn(dist);
			System.out.println(thiefState);
			
			rollACanvas();
			reverseDirection();
			System.out.println(thiefState);
			
			crawlOut(dist);
			System.out.println(thiefState);
			
			if (carrying) {
				handACanvas();
			}
			
		}
		
	}
		
	private void AmINeeded(){
		try { 
			thiefState = ThiefStates.CONCENTRATION_SITE;
			needed = !needed;
			ccSiteIntf.AmINeeded();
			}
		catch (RemoteException e){
			System.out.println("Thief_" + thiefId +"remote exception on AmINeeded: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
	private void prepareExcursion(){
		try { 
			thiefState = ThiefStates.CRAWLING_INWARDS;			
			ccSiteIntf.prepareExcursion();
			}
		catch (RemoteException e){
			System.out.println("Thief_" + thiefId +"remote exception on prepareExcursion: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
	private void crawlIn(int dist){
		try {
			while(dist>0) {
				dist -= 1;
			}
			thiefState = ThiefStates.AT_A_ROOM;
			apIntf.crawlIn();
			}
		catch (RemoteException e){
			System.out.println("Thief_" + thiefId +"remote exception on crawlIn: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
	private void rollACanvas(){
		try {
			carrying = !carrying;
			thiefState = ThiefStates.AT_A_ROOM;
			museumIntf.rollACanvas();
			}
		catch (RemoteException e){
			System.out.println("Thief_" + thiefId +"remote exception on rollACanvas: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
	private void reverseDirection(){
		try {
			thiefState = ThiefStates.CRAWLING_OUTWARDS;
			apIntf.reverseDirection();
			}
		catch (RemoteException e){
			System.out.println("Thief_" + thiefId +"remote exception on reverseDirection: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
	private void crawlOut(int dist){
		try {
			while(dist<30) {
				dist += 1;
			}
			thiefState = ThiefStates.COLLECTION_SITE;
			apIntf.crawlOut();
			}
		catch (RemoteException e){
			System.out.println("Thief_" + thiefId +"remote exception on crawlOut: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
	private void handACanvas(){
		try {
			carrying = !carrying;
			rooms -= 1;
			thiefState = ThiefStates.CONCENTRATION_SITE;
			apIntf.handACanvas();
			}
		catch (RemoteException e){
			System.out.println("Thief_" + thiefId +"remote exception on handACanvas: " +e.getMessage());
			System.exit(1);
		}    		
	}
	
//	@Override
//	public void run (){
//		while(true) {
//			
//			System.out.println("AmINeeded?");
//			AmINeeded();
//			if (isOver) {
//				break;
//			}
//			System.out.println("prepareExcursion?");
//			prepareExcursion();
//			System.out.println("crawlIn?");
//			crawlIn();
//			System.out.println("rollACanvas?");
//			rollACanvas();
//			System.out.println("reverseDirection?");
//			reverseDirection();
//			System.out.println("crawlOut?");
//			crawlOut();
//			System.out.println("handACanvas?");
//			handACanvas();
//			System.out.println("finished?");
//		}
//		
//	}
//		
//	private void AmINeeded(){
//		isOver = conSite.AmINeeded(thiefId);
//	}
//	
//	private void prepareExcursion(){
//		partyId = conSite.prepareExcursion(thiefId);
//		roomId = conSite.getRoomId(partyId);
//		thiefState = ThiefStates.CRAWLING_INWARDS;			
//	}
//	
//	private void rollACanvas(){
//		carrying = museum.rollACanvas(roomId);
//	}
//	
//	private void reverseDirection(){
//		museum.reverseDirection();
//		thiefState = ThiefStates.CRAWLING_OUTWARDS;
//	}
//	
//	private void crawlIn(){
//		aParty[partyId].crawlIn(museum.getRoomDistance(roomId));
//		thiefState = ThiefStates.AT_A_ROOM;
//	}
//	
//	private void crawlOut(){
//		aParty[partyId].crawlOut();
//		thiefState = ThiefStates.COLLECTION_SITE;
//	}
//	
//	private void handACanvas(){
//		ccSite.handACanvas(carrying);
//		carrying = false;
//		thiefState = ThiefStates.CONCENTRATION_SITE;
//	}
}