package P4;

import java.io.Serializable;

public abstract class Animal implements Serializable, Cloneable, Comparable<Animal> {

	//attributes
	private String name;
	private double energy, mealAmount, x, y, speedX, speedY;
	private boolean alive;
	
	//constructor
	public Animal() {
		setEnergy(100);
		setSpeedX(1);
		setSpeedY(1);
	}
	
	//getters
	public String getName()       { return name; }
	public double getEnergy()     { return energy; }
	public boolean getAlive()     { return alive; }
	public double getMealAmount() { return mealAmount; }
	public double getX()          { return x; }
	public double getY()          { return y; }
	public double getSpeedX()     { return speedX; }
	public double getSpeedY()     { return speedY; }
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setEnergy(double energy) {
		this.energy = energy;
		if(this.energy > 0)
			alive = true;
		if(this.energy > 17 && this.energy < 50)
			System.out.printf("%s is hungry. Energy = %.2f\n", name, this.energy);
		else if(this.energy <= 17)
			System.out.printf("%s is starving. Energy = %.2f\n", name, this.energy);
		else if(this.energy <= 0) {
			this.alive = false;
			System.out.println(name + " is dead");
		}
	}
	public void setMealAmount(double mealAmount) {
		if(mealAmount > 0 && mealAmount < 100)
			this.mealAmount = mealAmount;
		else
			System.out.println("Invalid Meal Amount");
	}
	public void setX(double x)           { this.x = x; }
	public void setY(double y)           { this.y = y; }
	public void setSpeedX(double speedX) { this.speedX = speedX; }
	public void setSpeedY(double speedY) { this.speedY = speedY; }
	
	//methods
	public String toString() {
		String nameF = String.format("%-9s", name);
		String energyF = String.format("%.2f", energy);
		return nameF + ":" + (alive ? " alive " : " dead ") + 
				"at (" + x + ", " + y + ") Energy=" + energyF;
	}
	
	public void move() {
		if(alive) {
			x += speedX;
			y += speedY;
			energy -= 0.1;
		} else
			System.out.println(name + " can't move. It's dead!");
	}
	
	public void speak(String msg) {
		if(getAlive()) System.out.println(name + " says: " + msg);
	}
	
	public abstract void sound();

	public double eat() {
		if(!getAlive()) {
			System.out.println(name + " is dead!");        //alive check
			return 0;
		}
		else if(this.energy >= 100) {                      //full check
			System.out.println(name + " didn't eat as it is full!");         
			return 0;
		}
		else if(this.energy < 100) {                       //animal eats
			
			double temp = energy;
			energy += mealAmount;
			
			if(this.energy < 100) {                        //less than 100 case
				System.out.println(name + " ate " + mealAmount + " units");
				return mealAmount;
				} 
			else if(this.energy > 100) {                   //more than 100 case
				energy = 100;
				System.out.printf("%s ate %.2f units, now it is full!\n", name, (100 - temp));
				return 100 - temp;
				} 
			else {                                         //100 case
				System.out.println(name + " ate " + mealAmount + " units, now it is full!");
				return mealAmount;
				}
		} else
			return mealAmount;                             //dummy code
	}
	public Animal clone() throws CloneNotSupportedException { return (Animal) super.clone(); }
	
	public int compareTo(Animal an) { return (int) (energy - an.energy); }
}
