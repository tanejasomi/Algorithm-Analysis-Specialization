package operatingSystem;

/**
 * @author somyataneja 
 * Class Car extends Thread and implements run() function. 
 * Each car represent a process which is either waiting 
 * to get into ferry (Semaphore), or going on ferry to island
 * (is working in critical region) or reached island parking
 * (released Semaphore).
 */
public class Car extends Thread {
	String name;
	private Ferry ferry;
	
	Car(Ferry ferry,String name) {

		this.ferry = ferry;
		this.name = name;	
	}
	

	public String getCarName() {
		return this.name;
	}
	
	// Threads call Class ferry (Semaphore methods to acquire and relase resources eventually
	@Override
	public void run() {
		try {
			System.out.println(getCarName() + " is waiting for ferry.");
			while(ferry.getAvailableSlot() < 1) {
				this.wait();
				System.out.println(getCarName()+" is waiting for slot to be vacant in parking.");
			}
			
			ferry.getTicket(getCarName());
			System.out.println(getCarName() + " is going on ferry to island.");
			Thread.sleep(2000);
			ferry.reachedIsland();
			
			System.out.println(getCarName() + " has reached island parking.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

	}
	
}
