package P4;

public class Chicken extends Animal {
	
	//attributes
	private static int count = 1;
	
	//constructors
	public Chicken() {
		setName("Chicken" + count++);
		setMealAmount(5);
	}
	
	public Chicken(double energy) {       //for accurate cloning
		setName("Chicken" + count++);
		setMealAmount(5);
		setEnergy(energy);
	}
	
	//methods
	public void sound() {
		if(getAlive()) 
			System.out.println("Cluck!");
		else
			System.out.println("It can't make sounds if it's dead");
	}
	
	public void swim() {
		if(getAlive())
			System.out.println(getName() + " swimmed a bit");
	}
	
	public Chicken clone() throws CloneNotSupportedException { return new Chicken(getEnergy()); }
}
