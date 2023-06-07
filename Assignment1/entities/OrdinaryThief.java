package entities;

import sharedRegions.*;
import entities.*;
import commInfra.*;
import main.*;

public class OrdinaryThief extends Thread{

	private int thiefState;
	private int thiefId;
	private boolean needed;
	private boolean isOver;
	private int rooms;
	private boolean carrying;
	private final CollectionSite ccSite;
	private final ConcentrationSite conSite;
	private final Museum museum;
	private final AssaultParty[] aParty;
	
	
	public void setThiefState(int thiefState) { this.thiefState = thiefState; }	

	public int getThiefState() { return thiefState; }
	
	public int getThiefId() { return thiefId; }

	public OrdinaryThief(String name,int id, CollectionSite ccSite, ConcentrationSite conSite, Museum museum, AssaultParty[] aParty) {
		super(name);
		this.thiefId = id;
		this.thiefState = ThiefStates.CONCENTRATION_SITE;
		this.ccSite = ccSite;
		this.conSite = conSite;
		this.museum = museum;
		this.aParty = aParty;
		this.rooms = 8;
		this.carrying = false;
	}
	
	@Override
	public void run (){
		while(true) {
			int dist = 30;
			
			AmINeeded();
			
			prepareExcursion();
			
			crawlIn(dist);
			
			carrying = rollACanvas();
			reverseDirection();
			
			crawlOut(dist);
			if (carrying == false) {
				break;
			}
			if (carrying) {
				handACanvas();
			}
		}
		
	}
		
	private void AmINeeded(){
		conSite.AmINeeded(thiefId); 		
	}
	
	private void prepareExcursion(){
		conSite.prepareExcursion();
		thiefState = ThiefStates.CRAWLING_INWARDS;			
	}
	
	private boolean rollACanvas(){
		boolean ret = museum.rollACanvas();
		
		return ret;
	}
	
	private void crawlIn(int dist){
		while(dist>0) {
			dist -= 1;
		}
		aParty[0].crawlIn(); 		
		thiefState = ThiefStates.AT_A_ROOM;
	}
	
	private void reverseDirection(){
		aParty[0].reverseDirection();
		thiefState = ThiefStates.CRAWLING_OUTWARDS;
	}
	
	private void crawlOut(int dist){
		aParty[0].crawlOut();
		thiefState = ThiefStates.COLLECTION_SITE;

	}
	
	private void handACanvas(){
		carrying = false;
		rooms -= 1;
		ccSite.handACanvas();
		thiefState = ThiefStates.CONCENTRATION_SITE;
	}
}