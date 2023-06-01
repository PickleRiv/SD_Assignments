package clientSite.main;

import clientSide.entities.*;
import clientSide.entities.OrdinaryThief;

/**
 * Simulation of the Museum Heist
 */
public class ClientThief {
	public static void main(String[] args) {
	
		OrdinaryThief[] thief = new OrdinaryThief[6];

		for (int i = 0; i < 6; i++) {
			thief[i] = new OrdinaryThief("Thief_" + (i + 1), i);
			System.out.println("Thief_" + i + "Created");
		}
		
		for (int i = 0; i < 6; i++) {
			thief[i].start();
			System.out.println("Thief_" + i + " Started");
		}
		
		for (int i = 0; i < 6; i++) {
			try {
				thief[i].join();
				System.out.println("Thief_" + i + " terminated");
			} catch (InterruptedException e) {
			}
		}

	}
}