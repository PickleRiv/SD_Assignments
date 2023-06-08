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
	private int currParty;
	private int[] rooms;
	private boolean isOver;

	
	public ConcentrationSite() {
		this.thieves = new int[6];
		this.party1 = new int[3];
		this.party2 = new int[3];
		this.currParty = -1;
		this.partyTask = new int[2];
		this.rooms = new int[5];

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
		while (thiefCnt<3) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		isOver = isOver();
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
		notifyAll();
	}

	public synchronized void prepareAssaultParty(){
		int room = getRoom();
		if (room != -1) {
			partyTask[currParty] = room;
			rooms[room] = 1;
		}
		System.out.println("Sending party_"+ currParty + " to room_"+room);
	}
	
	public synchronized void sendAssaultParty(){

	}
	
	// Ordinary thieves activities
	public synchronized boolean AmINeeded(int thiefId){
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
		return isOver;
	}
	
	public synchronized int prepareExcursion(int thiefId){
		while (partyTask[currParty] == -1) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
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
}
