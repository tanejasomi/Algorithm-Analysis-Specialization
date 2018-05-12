package operatingSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author somyataneja(gd2987)
 * This program implements ferry island parking lot system.
 * Where each ferry can take up to M cars at a time to island with
 * N slots. Here each ferry represent semaphore and individual
 * car represents threads. Class Mainland contains the main() 
 * function of program. ExecutorServive library is used to generate 
 * multiple threads (car). 
 */
public class MainLand {

	public static void main(String arg[]) {

		final String[] carName = { "BMW", "Civic", "Audi", "Nissan", "Chevrolet", "Toyota", "Subaru" };
		final int carCount = 7;
		Ferry ferry = new Ferry();
		final ExecutorService exService = Executors.newFixedThreadPool(6);
		for (int i = 0; i < carCount; i++) {
			Car car = new Car(ferry, carName[i]);
			exService.execute(car);

		}
		exService.shutdown();

	}

}
/****************************************
 * public static void main(String args[]) { final int islandCount = 7; Ferry
 * ferry = new Ferry(); Car[] cars = { new Car(ferry,"BMW"), new
 * Car(ferry,"CIVIC"), new Car(ferry,"Audi"), new Car(ferry,"Nissan"), new
 * Car(ferry,"Chevrolet"), new Car(ferry,"Toyota"), new Car(ferry,"Subaru"), new
 * Car(ferry,"Kia")}; /*Car car = new Car(ferry,"Honda"); Car car1 = new
 * Car(ferry, "Civic"); Car car2 = new Car(ferry,"BMW"); Car car4 = new
 * Car(ferry,"Toyota"); car.run(); car4.run(); car2.run(); car1.run();
 * car1.stop(); car.stop(); Car car3 = new Car(ferry, "Tesla"); Car car5 = new
 * Car(ferry, "Nissan"); car5.run(); car3.run(); car5.stop(); car3.stop();
 * car.stop(); car2.stop();
 ****************/
