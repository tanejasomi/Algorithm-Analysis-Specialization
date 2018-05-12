package operatingSystem;

import java.util.concurrent.Semaphore;

/**
 * @author somyataneja
 * Class Ferry is Semaphore which handles critical region.
 * Different Cars (thread) try to enter into Semaphore. 
 * At given time only few (MAX_SLOT) can access the resources 
 * of critical region.
 */
public class Ferry {
	
	static final int MAX_SLOTS = 2;// No of threads in allowed in Semaphore(M)
	static final int MAX_ISLAND_PARKING_SLOTS = 3; // No of slots in Island Parking (N)
	static final Semaphore ferryTicket = new Semaphore(MAX_SLOTS);
	static int islandCounter;
	String carName;
	
	Ferry() {
		islandCounter = 0;
		System.out.println("**Number of availabe ticket for ride per ferry: "+MAX_SLOTS+"**");
		System.out.println("**Total Slots available in Island Parking: "+ this.getAvailableSlot()+" **");
		
	}
	

	//Method to return number of cars present in island parking
	public int getIslandCounter() {
		return islandCounter;
	}
	
	//Metohd to return number of available slots in island parking
	public int getAvailableSlot() {
		return (MAX_ISLAND_PARKING_SLOTS - islandCounter);
	}
	
	// Method getTicket is used to acquire resources of semaphore
	public Object getTicket(String name) throws InterruptedException{
		this.carName=name;	
		//System.out.println("Slots available in Island Parking "+ getAvailableSlot());
		//if(getAvailableSlot() >0) {
		ferryTicket.acquire();
		System.out.println(carName+" got the ticket");	
	//	}
		return ferryTicket;		
	}
	
	
	//When thread die ie leave island parking island counter is decreased.
	public synchronized void markParkingSlotAvailable() {
		islandCounter--;
		System.out.println("Now available in island Parking " + getAvailableSlot());
		
	}
	
	//Method to realease Semaphore once thread(Car) finishes job.
	public void reachedIsland() {		
		ferryTicket.release();
		islandCounter++;				
	}
	

}
