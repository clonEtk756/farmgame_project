package P4;

import java.util.Iterator;
import java.io.*;

public class Farm {
	
	//attributes
	private AnimalList animals;
	private double availableFood;
	
	//constructor
	public Farm(String filename) { load(filename); }
	
	//setters/getters
	public double getAvailableFood() { return availableFood; }
	public void setAvailableFood(int food) { availableFood = food; }
	public AnimalList getAnimals() { 
		return animals;
	}
	
	//IO methods
	public void exit(String filename) {
		
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream animalsOut = new ObjectOutputStream(fos);
				DataOutputStream out = new DataOutputStream(fos);) 
		{
			out.writeDouble(getAvailableFood());
			animalsOut.writeObject(animals);
			
			System.out.println("Data successfully saved to " + filename);
			
		} catch (FileNotFoundException e) {
			System.out.println("Error! File not found. ");
		} catch (IOException e) {
			System.out.println("Error! Unknown IOException.");
		}
	}
	
	public void load(String filename) {
		
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
				ObjectInputStream animalsIn = new ObjectInputStream(bis);
				DataInputStream in = new DataInputStream(bis);)
		{
			setAvailableFood((int)in.readDouble());
			animals = (AnimalList) animalsIn.readObject();
			
			System.out.println("Data succsessfully loaded from " + filename);
			
		} catch (FileNotFoundException e) {
			System.out.println("Error! File not found. \nAll attributes are resetted to default.");
			setAvailableFood(1000);
			animals = new AnimalList();
			add(new Chicken());
			add(new Cow());
			add(new Llama());
			add(new Llama());
		} catch (IOException e) {
			System.out.println("Error! Unknown IOException.");
		} catch (ClassNotFoundException e) {
			System.out.println("Error! Class not found.");
		}
	}
	
	//methods
	public void makeNoise() {
		Iterator<Animal> it = animals.iterator();
		while(it.hasNext())
			it.next().sound();
	}
	
	public void feedAnimals() {
		Iterator<Animal> it = animals.iterator();
		Animal temp;
		while(it.hasNext()) {
			temp = it.next();
			if(availableFood >= temp.getMealAmount())
				availableFood -= temp.eat();
			else {
				System.out.println("Not enough food on the farm.");
				break;
			}
		}
	}
	
	public void add(Animal anim) {
		animals.addLast(anim);
	}
	
	public void addClone(Animal anim) throws CloneNotSupportedException {
		animals.addLast( (Animal) anim.clone() );
	}
	
	public void animSort() {
		System.out.println("Not supported yet.");
	}
	
	public void printAnimals() {
		System.out.println(animals.toString());
	}
	
	public void printSummary() {
		System.out.println("The farm has:");
		System.out.printf("- %d animals (%d Chicken, %d Cows, and %d Llamas) \n", 
				animals.size(), animals.getByType(Chicken.class).size(), 
				animals.getByType(Cow.class).size(), animals.getByType(Llama.class).size());
		System.out.printf("- %.2f units of available food \n", getAvailableFood());
	}
}
