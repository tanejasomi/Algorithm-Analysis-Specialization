package dinningPhilosphersProblem;

import java.util.Random;

public class Philosopher {
	private int philosopherId;
	private Fork left,right;
	public Philosopher(int philId) {
		this.philosopherId = philId;
		
	}
	public void start(Fork left, Fork right) throws InterruptedException{
		this.left = left;
		this.right = right;
		while(true) {
			if(new Random().nextBoolean()) {
				eat();
			}
			else {
				think();
			}
		}
	}
	
	public void eat() throws InterruptedException {
		boolean rightFork = false;
		boolean leftFork = false;
		System.out.println("Phil "+philosopherId +" wants to eat!");
		System.out.println("Phil "+philosopherId +" is now picking up the left fork "+ left.forkId);
		leftFork =  left.pick(philosopherId);
		if(!leftFork) {
			return;
		}
		System.out.println("Phil "+philosopherId +" is now picking up the right fork "+ right.forkId);
		rightFork =  right.pick(philosopherId);
		if(!rightFork) {
			left.free();
			return;
		}
		
		System.out.println("Philposopher "+ philosopherId+ " is eating");
		Thread.sleep(1000);
		
		left.free();
		right.free();
		System.out.println("Philposopher "+ philosopherId+ " has finished eating");
		
		
	}
	public void think() throws InterruptedException {
		System.out.println("Philosopher "+philosopherId+" is now thinking");
		Thread.sleep(new Random().nextInt(1000) + 100);
		System.out.println("Philosopher "+philosopherId+" has stopped thinking");	
	}
	

}
