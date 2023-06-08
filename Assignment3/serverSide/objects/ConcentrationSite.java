package serverSide.objects;

import clientSide.entities.*;
import interfaces.ConcentrationSiteInterface;
import serverSide.main.*;

/**
 * Represents the concentration site shared region of the ordinary thieves
 */
public class ConcentrationSite implements ConcentrationSiteInterface {
	private int thiefCnt;
	private int[] thieves;
	private int readyThieves;
	private int[] party1;
	private int[] party2;
	private int[] partyTask;
	private int currParty;
	private boolean sendParty;
	private int[] rooms;
	private boolean isOver;
	private int nEntities;

	
	public ConcentrationSite() {
		this.thieves = new int[6];
		this.party1 = new int[3];
		this.party2 = new int[3];
		this.currParty = -1;
		this.partyTask = new int[2];
		this.rooms = new int[5];
		this.readyThieves = 0;
		this.sendParty = false;
        this.nEntities = 0;

		isOver = false;
		thiefCnt = 0;
		for (int i =0; i<6;i++) {
			thieves[i]=-1;
		}
		for (int i =0; i<3;i++) {
			party1[i]=-1;
			party2[i]=-1;
		}
		for(int i = 0; i < 2; i++) {
			partyTask[i] = -1;
		}
		for(int i = 0; i < 5; i++) {
			rooms[i] = 0;
		}
	}
	
	// Master Thief activities
	public synchronized void appraiseSit(){
		sendParty = false;
		isOver = isOver();
		currParty = getEmptyParty();
		while ((thiefCnt<3 && !isOver) || currParty == -1) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		if(!isOver) {
				currParty = getEmptyParty();
				if (currParty == 0){
					for(int i = 0; i < 3; i++) {
						party1[i] = getThiefId();
					}
				} else if(currParty == 1){
					for(int i = 0; i < 3; i++) {
						party2[i] = getThiefId();
					}
				}
		}
		System.out.println("Party_"+currParty);
		notifyAll();
	}

	public synchronized boolean prepareAssaultParty(){
		int room = getRoom();
		if (room != -1) {
			partyTask[currParty] = room;
			rooms[room] = 1;
		}
		if (room == 4) {
			return true;
		}
		return false;
	}
	
	public synchronized void sendAssaultParty(){
		notifyAll();
		while(readyThieves < 3) {
			System.out.println("waiting");
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Sent Party_"+ currParty);
		sendParty = true;
		notifyAll();
		}
	
	// Ordinary thieves activities
	public synchronized boolean AmINeeded(int thiefId){
		thiefCnt++;
		thieves[thiefId] = thiefId;
		if(inParty(thiefId)) {
			partyTask[getPartyId(thiefId)] = -1;
			if (getPartyId(thiefId)==0) {
				for (int i =0; i<3;i++) {
					party1[i]=-1;
				}
			}
			if(getPartyId(thiefId)==1) {
				for (int i =0; i<3;i++) {
					party2[i]=-1;
				}
			}
			System.out.println("reset");
		}
		notifyAll();
		while (!inParty(thiefId) && !isOver) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		thiefCnt--;
		thieves[thiefId] = -1;
		return isOver;
	}
	
	public synchronized int prepareExcursion(int thiefId){
		System.out.println("Prepping Party_"+getPartyId(thiefId));
		readyThieves++;
		notifyAll();
		while (!sendParty) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		readyThieves--;
		return getPartyId(thiefId);
	}
	
	// Assisting functions
	public synchronized boolean isOver() {
		int num = 0;
		for(int i = 0; i<5;i++) {
			if(rooms[i]==2) {
				num++;
			}
		}
		if (num==5) {
			return true;
		}
		return false;
	}
	
	public synchronized int getRoomId(int partyId){
		return partyTask[partyId];
	}
	
	public synchronized boolean inParty(int thiefId){
		for(int i = 0; i < 3; i++) {if (party1[i] == thiefId || party2[i] == thiefId) {return true;}}
		return false;
	}
	
	public synchronized int getPartyId(int thiefId){
		for(int i = 0; i < 3; i++) {if (party1[i] == thiefId) {return 0;}}
		for(int i = 0; i < 3; i++) {if (party2[i] == thiefId) {return 1;}}
		return -1;
	}

	public synchronized int getEmptyParty(){
		for(int i = 0; i < 3; i++) {if (party1[i] == -1) {return 0;}}
		for(int i = 0; i < 3; i++) {if (party2[i] == -1) {return 1;}}
		return -1;
	}
	
	public synchronized int getRoom(){
		for(int i = 0; i < 5; i++) {if (rooms[i] == 0) {return i;}}
		return -1;
	}
	
	public synchronized int getThiefId(){
		for(int i = 0; i < 6; i++) {if (thieves[i] != -1 && !inParty(thieves[i])) {return thieves[i];}}
		return -1;
	}
	
	public synchronized void shutdown ()
    {
        nEntities += 1;
        if (nEntities >= 1)
            ServerConcentrationSite.shutdown();
        notifyAll ();                                        // the master thief may now terminate
    }
}