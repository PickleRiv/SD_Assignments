package clientSide.entities;

//import interfaces.*;
import clientSide.entities.ThiefStates;
import java.rmi.RemoteException;

public class OrdinaryThief extends Thread{

	private int thiefState;
	private int thiefId;
	private boolean needed;
	private boolean isOver;
	private int rooms;
	private boolean carrying;
	
	
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
			
			System.out.println(thiefState);
			if (!needed) {
				needed = AmINeeded(needed);
			}
			
			//prepareExcursion();
			thiefState = ThiefStates.CRAWLING_INWARDS;
			System.out.println(thiefState);
			
			// crawlIn()
			if (dist > 0) {
				System.out.println(thiefState);
				dist -= 1;
			}
			thiefState = ThiefStates.AT_A_ROOM;
			System.out.println(thiefState);
			
			// rollACanvas();
			carrying = true;
			
			// reverseDirection();
			thiefState = ThiefStates.CRAWLING_OUTWARDS;
			System.out.println(thiefState);
			
			// crawlOut()
			if (dist < 30) {
				System.out.println(thiefState);
				dist += 1;
			}
			
			thiefState = ThiefStates.COLLECTION_SITE;
			System.out.println(thiefState);
			if (carrying) {
				//handACanvas();
				carrying = false;
				rooms -= 1;
			}
			
		}
		
	}
	
	public boolean AmINeeded(boolean val) {return !val; }
}