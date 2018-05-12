package dinningPhilosphersProblem;

import java.util.Random;

/**
 * @author somyataneja
 * 
 * fork is available forkAvailable is true and when in use forkAvailable is false;
 *
 */
public class Fork {
	public int forkId;
	private boolean forkAvailable;
	
	Fork(int id){
		this.forkId = forkId;
		this.forkAvailable = true;
	}
	
	public synchronized void free() throws InterruptedException{
		forkAvailable = true;
	}
	
	public synchronized boolean pick(int philId) throws InterruptedException{
		int counter =0;
		int waitUntil = new Random().nextInt(10) +2;
		while(!forkAvailable) {
			Thread.sleep(new Random().nextInt(20) +20);
			counter ++;
			if(counter > waitUntil) {
				return false;
				
			}
		}
		
		forkAvailable = false;
		return true;
		
	}
	
	

}
