package P4;

public class Llama extends Animal {

	//attributes
	private static int count = 1;
	
	//constructors
	public Llama() {
		setName("Llama" + count++);
		setMealAmount(9);
	}
	
	public Llama(double energy) {      //for accurate cloning
		setName("Llama" + count++);
		setMealAmount(9);
		setEnergy(energy);
	}
	
	//methods
	public void sound() {
		if(getAlive()) 
			System.out.println("Hmmm!");
		else
			System.out.println("It can't make sounds if it's dead");
	}
	
	public void jump() {
		if(getAlive())
			System.out.println(getName() + " jumped");
	}
	
	public Llama clone() throws CloneNotSupportedException { return new Llama(getEnergy()); }
}
