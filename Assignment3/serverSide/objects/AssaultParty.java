package serverSide.objects;

import clientSide.entities.*;
import interfaces.AssaultPartyInterface;
import interfaces.ReturnBool;
import interfaces.ReturnInt;
import serverSide.main.*;

import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Represents the AssaultParty shared region
 */
public class AssaultParty implements AssaultPartyInterface {
	 private int[] positions;
	 private int nextThief;
	 private boolean in;
	 private int goalDistance;
	 private int numThieves;
	 private int nEntities;


   public AssaultParty() {
     positions = new int[3];
     nextThief = 0;
     in = true;
     goalDistance = 0;
  	 numThieves = 0;
  	 this.nEntities = 0;
   }

   public synchronized void crawlIn(int dist) {
//   	 int threadIndex = numThieves;
//   	numThieves++;
//       positions[threadIndex] = 0;
//       goalDistance = dist;
//       in = true;
//       notifyAll();
//       
//       while (!allThreadsAtGoal(goalDistance, in)) {
//       	if (nextThief != threadIndex) {
//       		try {
//                   wait();
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
//       	}else {
//       		positions[threadIndex] = generateNewPosition(threadIndex, goalDistance, in);
//       		if (nextThief == 2) {
//       			nextThief = 0;
//       		}else { 
//       			nextThief++;
//       		}
//       		notifyAll();
//       	}
//       }
//		numThieves--;
//       positions[threadIndex] = goalDistance;
//		System.out.println("Thread " + (threadIndex) + " is at a Room");
   }
	
   public synchronized void crawlOut(){
//   	int threadIndex = numThieves;
//   	numThieves++;
//   	goalDistance = 0;
//   	in = false;
//		notifyAll();
//	   
//		while (!allThreadsAtGoal(goalDistance, in)) {
//			if (nextThief != threadIndex) {
//				try {
//					wait();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}else {
//				positions[threadIndex] = generateNewPosition(threadIndex, goalDistance, in);
//				if (nextThief == 2) {
//					nextThief = 0;
//				}else { 
//					nextThief++;
//				}
//				notifyAll();
//			}
//		}
//		System.out.println("Thread " + (threadIndex) + " is at site ");
//		numThieves--;
//		positions[threadIndex] = goalDistance;
	}

	public synchronized boolean allThreadsAtGoal(int goalDist, boolean dir) {
       for (int position : positions) {
           if ((dir == true && position < goalDist) || (dir == false && position > goalDist)) {
               return false;
           }
       }
       return true;
   }

   public synchronized int generateNewPosition(int threadIndex, int goal, boolean dir) {
       int newPosition;
       int thief_1 = positions[(threadIndex + 1) % 3];
       int thief_2 = positions[(threadIndex + 2) % 3];
       int disp = (int) (Math.random() * (6 - 2 + 1) + 2);
       
       if(dir == true) {
	       newPosition = positions[threadIndex] + disp;
	        if (Math.abs(newPosition - thief_1) > 3 || Math.abs(newPosition - thief_2) > 3 || newPosition > goal) {
	            return positions[threadIndex];
	        }
       }else {
       	newPosition = positions[threadIndex] - disp;
	        if (Math.abs(newPosition - thief_1) > 3 || Math.abs(newPosition - thief_2) > 3 || newPosition < goal) {
	            return positions[threadIndex];
	        }
       }
       return newPosition;
   }

    /**
     *   Operation server shutdown.
     *
     *   New operation.
     */

    public synchronized void shutdown () throws RemoteException
    {
        nEntities += 1;
        if (nEntities >= 1)
            if(getId() == 0)
                ServerAssaultParty.shutdown();
        notifyAll ();                                        // the master thief may now terminate
    }
}