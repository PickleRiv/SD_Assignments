package clientSite.main;

import clientSide.entities.*;
/**
 * Simulation of the Museum Heist
 */
public class ClientMaster {
	public static void main(String[] args) {
	
		MasterThief master;
		
		master = new MasterThief("Master",1);
		System.out.println("Master Started");
		master.start();
		
		/* waiting for the end of the simulation */
		try {
			master.join();
		}catch(InterruptedException e) {}
		System.out.println("Master Terminated");
	}

}