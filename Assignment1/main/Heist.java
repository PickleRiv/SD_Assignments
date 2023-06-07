package main;

import sharedRegions.*;
import entities.*;
import main.*;

/**
 *   Simulation of the Problem of the Restaurant.
 */

public class Heist {
	
	/**
	   *    Main method.
	   *    @param args runtime arguments
	   */
	public static void main(String[] args) {
		MasterThief master;
		OrdinaryThief[] thieves = new OrdinaryThief[6];
		CollectionSite ccSite;
		ConcentrationSite conSite;
		Museum museum;
		AssaultParty [] aParty;
		
		ccSite = new CollectionSite();
		conSite = new ConcentrationSite();
		museum = new Museum();
		aParty = new AssaultParty[2];
		
		for (int i = 0; i < 2; i++) {
			aParty[i] = new AssaultParty();
        }
		
		
		master = new MasterThief("Master",1, ccSite, conSite);
		for (int i = 0; i < 6; i++) {
            thieves[i] = new OrdinaryThief("Thief_"+i, i, ccSite, conSite, museum, aParty);
        }
		
		/* start of simulation */
		
		master.start();
		System.out.println("Lauching Master Thread");
		for (int i = 0; i < 6; i++) {
            thieves[i].start();
    		System.out.println("Lauching"+ " Thief_"+i +" Thread");
        }
		
		for (int i = 0; i < 6; i++) {
            try {
                thieves[i].join();
                System.out.println("Thief_"+i+" terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}

		try
		{
			master.join();
		} catch(InterruptedException e) { }
		System.out.println("The Master terminated");
				
		/* end of simulation */
		
		System.out.println("End of Simulation");
	}

}