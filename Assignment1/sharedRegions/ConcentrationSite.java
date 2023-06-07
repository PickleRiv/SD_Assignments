package sharedRegions;

import sharedRegions.*;
import entities.*;
import commInfra.*;
import main.*;

public class ConcentrationSite {
	private int thiefCnt;
	private int[] thieves;
	private int[] party1;
	private int[] party2;
	private int[] partyTask;
	private int[] emptyRooms;
	private int[] roomDists;
	private boolean isOver;

	
	public ConcentrationSite() {
		this.thieves = new int[6];
		this.party1 = new int[3];
		this.party2 = new int[3];
		this.partyTask = new int[2];
		this.emptyRooms = new int[5];
		this.roomDists = new int[5];

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
			emptyRooms[i] = 0;
			roomDists[i] = (int) (Math.random() * (30 - 15 + 1) + 15);
		}
	}
	
	// Master Thief activities
	public synchronized void appraiseSit(){
		while (thiefCnt<3) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		isOver = isOver();
		if(!isOver) {
			if (getEmptyParty()==0){
				for(int i = 0; i < 3; i++) {
					party1[i] = getThiefId();
					System.out.println(party1[i]);
				}
			} else if(getEmptyParty()==1){
				for(int i = 0; i < 3; i++) {
					party2[i] = getThiefId();
				}
			}
		}
		notifyAll();
	}

	public synchronized void prepareAssaultParty(){

	}
	
	public synchronized void sendAssaultParty(){

	}
	
	// Ordinary thieves activities
	public synchronized void AmINeeded(int thiefId){
		thiefCnt++;
		thieves[thiefId] = thiefId;
		
		notifyAll();
		while (!inParty(thiefId) || isOver) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}

		thiefCnt--;
		thieves[thiefId] = -1;
	}
	
	public synchronized void prepareExcursion(){
//		while (true) {
//            try {
//                wait();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//		}
	}
	
	// Assisting functions
	public synchronized boolean isOver() {
		int num = 0;
		for(int i = 0; i<5;i++) {
			if(emptyRooms[i]==1) {
				num++;
			}
		}
		if (num==5) {
			return true;
		}
		return false;
	}
	
	public synchronized boolean inParty(int thiefId){
		for(int i = 0; i < 3; i++) {if (party1[i] == thiefId || party2[i] == thiefId) {return true;}}
		return false;
	}

	public synchronized int getEmptyParty(){
		for(int i = 0; i < 3; i++) {if (party1[i] == -1) {return 0;}}
		for(int i = 0; i < 3; i++) {if (party2[i] == -1) {return 1;}}
		return -1;
	}
	
	public synchronized int getThiefId(){
		for(int i = 0; i < 6; i++) {if (thieves[i] != -1 && !inParty(thieves[i])) {return thieves[i];}}
		return -1;
	}
}
