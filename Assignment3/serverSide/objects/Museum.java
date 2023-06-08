package serverSide.objects;

import clientSide.entities.*;
import interfaces.MuseumInterface;
import interfaces.ReturnBool;
import serverSide.main.ServerMuseum;
import serverSide.main.*;

import java.rmi.RemoteException;

/**
 * Represents the Museum shared region containing information regarding the rooms inside
 */
public class Museum implements MuseumInterface {
	
	private int [] canvas;
	private boolean [] notEmpty;
	private int[] roomDists;
	private int nEntities;
	
	public Museum() {
		this.canvas = new int[5];
		this.notEmpty = new boolean[5];
		this.roomDists = new int[5];
		this.nEntities = 0;
	
		for (int i=0; i<5; i++) {
			canvas[i] =  (int) (Math.random() * (16 - 8 + 1) + 8);
			notEmpty[i] =  true;
			roomDists[i] = (int) (Math.random() * (30 - 15 + 1) + 15);
		}
	}
	
	public synchronized boolean rollACanvas(int roomId){
		if(canvas[roomId]>0) {
			canvas[roomId] -=1;
			notEmpty[roomId] = true;
		}else {
			notEmpty[roomId] = false;
		}		
		return notEmpty[roomId];
	}
	
	public synchronized void reverseDirection(){ 		
	}

	public synchronized int getRoomDistance(int roomNumber) {
	   if(roomNumber != -1) {
		   return roomDists[roomNumber];
	   }
	   return 0;
   }
   
   /**
    *   Operation server shutdown.
    *
    *   New operation.
    */

   public synchronized void shutdown ()
   {
       nEntities += 1;
       if (nEntities >= 1)
           ServerMuseum.shutdown();
       notifyAll ();                  
   }


}
