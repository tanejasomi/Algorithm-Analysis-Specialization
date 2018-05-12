package operatingSystem;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Semaphore sem = new Semaphore(4);
		sem.acquire();
		System.out.println("Available Permits: "+sem.availablePermits());
		sem.release();
		
		
	}

}
