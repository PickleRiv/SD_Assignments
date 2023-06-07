package sharedRegions;

import sharedRegions.*;
import entities.*;
import commInfra.*;
import main.*;

public class Museum {
	
	private int [] canvas;
	private boolean [] notEmpty;
	
	public Museum() {
		this.canvas = new int[5];
		this.notEmpty = new boolean[5];
		for (int i=0; i<5; i++) {
			canvas[i] =  (int) (Math.random() * (16 - 8 + 1) + 8);
			notEmpty[i] =  true;
		}
	}
	
	public synchronized boolean rollACanvas(){
		if(canvas[0]>0) {
			canvas[0] -=1;
			notEmpty[0] = true;
		}else {
			notEmpty[0] = false;
		}		
		return notEmpty[0];
	}

}
