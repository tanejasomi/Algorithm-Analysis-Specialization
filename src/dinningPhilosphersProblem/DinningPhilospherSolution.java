package dinningPhilosphersProblem;

import java.util.Scanner;

/**
 * @author somyataneja
 * 
 *
 */
public class DinningPhilospherSolution {

	Scanner scan;
	int number;
	Philosopher[] philosophers;
	Fork[] forks;
	Thread[] threads;

	public static void main(String[] args) {
		DinningPhilospherSolution dp = new DinningPhilospherSolution();
		dp.init();
		dp.startThinkingEating();
	}

	public void init() {
		scan = new Scanner(System.in);
		System.out.println("Dinning philosopher problem");
		try {
			System.out.println("Enter the number of philosopher");
			number = scan.nextInt();

		} catch (Exception e) {
			System.out.println("Required only integer");
		}
		philosophers = new Philosopher[number];
		forks = new Fork[number];
		threads = new Thread[number];

		for (int i = 0; i < number; i++) {
			philosophers[i] = new Philosopher(i + 1);
			forks[i] = new Fork(i + 1);
		}
	}

	public void startThinkingEating() {
		for (int i = 0; i < number; i++) {
			final int index = i;
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						philosophers[index].start(forks[index], (index - 1 > 0) ? forks[index - 1] : forks[number - 1]);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threads[i].start();
		}
	}
}
