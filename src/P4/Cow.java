package P4;

public class Cow extends Animal {
	
	//attributes
	private static int count = 1;
	
	//constructors
	public Cow() {
		setName("Cow" + count++);
		setMealAmount(20);
	}
	
	public Cow(double energy) {       //for accurate cloning
		setName("Cow" + count++);
		setMealAmount(20);
		setEnergy(energy);
	}
	
	//methods
	public void sound() {
		if(getAlive()) 
			System.out.println("Moo!");
		else
			System.out.println("It can't make sounds if it's dead");
	}
	
	public void milk() {
		if(getAlive())
			System.out.println(getName() + " is milked");
	}
	
	public Cow clone() throws CloneNotSupportedException { return new Cow(getEnergy()); }
}
