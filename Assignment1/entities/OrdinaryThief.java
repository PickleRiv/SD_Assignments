package entities;

import sharedRegions.*;
import entities.*;
import commInfra.*;
import main.*;

public class OrdinaryThief extends Thread{

	private int thiefState;
	private int thiefId;
	private int partyId;
	private boolean needed;
	private boolean isOver;
	private int roomId;
	private int dist;
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
		this.roomId = -1;
		this.carrying = false;
	}
	
	@Override
	public void run (){
		while(true) {
			
			AmINeeded();
			if (isOver) {
				break;
			}
			
			prepareExcursion();
			if (dist == 0) {
				break;
			}
			crawlIn();

			rollACanvas();
			reverseDirection();
			crawlOut();
			if (carrying) {
				handACanvas();
			}
		}
		
	}
		
	private void AmINeeded(){
		isOver = conSite.AmINeeded(thiefId);
	}
	
	private void prepareExcursion(){
		partyId = conSite.prepareExcursion(thiefId);
		roomId = conSite.getRoomId(partyId);
		dist = museum.getRoomDistance(roomId);
		thiefState = ThiefStates.CRAWLING_INWARDS;			
	}
	
	private void rollACanvas(){
		carrying = museum.rollACanvas();
	}
	
	private void crawlIn(){
		aParty[partyId].crawlIn(dist);
		dist = 0;
		thiefState = ThiefStates.AT_A_ROOM;
	}
	
	private void reverseDirection(){
		aParty[partyId].reverseDirection();
		thiefState = ThiefStates.CRAWLING_OUTWARDS;
	}
	
	private void crawlOut(){
		aParty[partyId].crawlOut();
		thiefState = ThiefStates.COLLECTION_SITE;

	}
	
	private void handACanvas(){
		carrying = false;
		ccSite.handACanvas();
		thiefState = ThiefStates.CONCENTRATION_SITE;
	}
}