package P4;

public class FarmTest {

	public static void main(String[] args) throws Exception {
		
		Farm myFarm = new Farm("C:/ForJava/P4/farmsave.dat");
		myFarm.getAnimals().addFirst(new Cow());
		myFarm.getAnimals().addLast(new Cow());
		myFarm.getAnimals().add(2, new Cow());
		Animal temp = myFarm.getAnimals().removeFirst();
		System.out.println("removed " + temp.getName());
		myFarm.getAnimals().set(0, new Llama());
		
		
		for(Animal a: myFarm.getAnimals())
			a.setEnergy(10+Math.random()*90);
		System.out.printf("\nAvailable food before feeding: %.2f\n", myFarm.getAvailableFood());
		System.out.println("\nInitial list of animals:\n-------------------------");
		myFarm.printAnimals();
		System.out.println("Adding a clone of the second animal\n----------------");
		
		myFarm.addClone(myFarm.getAnimals().get(0));
		
		myFarm.printAnimals(); 
		System.out.println("List of starving animals:\n------------");
		System.out.println(myFarm.getAnimals().getStarvingAnimals());
		System.out.println("List of hungry animals:\n------------");
		System.out.println(myFarm.getAnimals().getHungryAnimals());
		System.out.printf("Amount of food needed to feed all animals: %.2f\n",
				myFarm.getAnimals().getRequiredFood());
		System.out.println("\nFeeding animals:\n--------------");
		myFarm.feedAnimals();
		System.out.printf("\nAvailable food after feeding: %.2f\n",
		myFarm.getAvailableFood());
		System.out.println("\nFarm summary:\n--------------");
		myFarm.printSummary();
		myFarm.exit("C:/ForJava/P4/farmsave.dat");

	}

}
