package P4;

import java.io.Serializable;

public class AnimalList implements Iterable<Animal>, Serializable {
	
	// attributes
	private int size = 0;
	private AnimalNode head = null, tail = null;

	// common methods
	public boolean isEmpty() { return (size == 0); }
	public int size() { return size; }
	
	public void addFirst(Animal a) {
		AnimalNode newNode = new AnimalNode(a);
		if(isEmpty())
			head = tail = newNode;
		else {
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	public void addLast(Animal a) {
		AnimalNode newNode = new AnimalNode(a);
		if(isEmpty())
			addFirst(a);
		else {
			tail.next = newNode;
			tail = newNode;
			size++;
		}
	}
	
	public void add(int idx, Animal a) {
		checkIdx(idx);
		if(isEmpty())
			addFirst(a);
		else if(idx == size)
			addLast(a);
		else {
			AnimalNode newNode = new AnimalNode(a);
			AnimalNode prev = head;
			for(int i = 0; i < idx - 1; i++)
				prev = prev.next;
			newNode.next = prev.next;
			prev.next = newNode;
			size++;
		}
	}
	
	public Animal removeFirst() {
		if(isEmpty())
			return null;
		AnimalNode temp = head; 
		head = head.next;
		if(head == null)
			tail = null;
		size--;
		return temp.animal;
	}
	
	public Animal removeLast() {
		if(isEmpty())
			return null;
		AnimalNode temp = tail;
		AnimalNode prev = head;
		for(int i = 0; i < size - 2; i++)
			prev = prev.next;
		tail = prev;
		tail.next = null;
		size--;
		return temp.animal;
	}
	
	public Animal remove(int idx) {
		checkIdx(idx);
		if(idx == size - 1)
			return removeLast();
		else if(idx == 0)
			return removeFirst();
		else {
			AnimalNode prev = head;
			for(int i = 0; i < idx - 1; i++)
				prev = prev.next;
			AnimalNode current = prev.next;
			prev.next = current.next;
			size--;
			return current.animal;
		}
	}
	
	// getters / setter
	public Animal getFirst() { return head.animal; }
	public Animal getLast() { return tail.animal; }
	public Animal get(int idx) {
		checkIdx(idx);
		AnimalNode temp = head;
		for(int i = 0; i < idx; i++)
			temp = temp.next;
		return temp.animal;
	}
	
	public Animal set(int idx, Animal a) {
		checkIdx(idx);
		AnimalNode temp = head;
		for(int i = 0; i < idx; i++)
			temp = temp.next;
		Animal save = temp.animal;
		temp.animal = a;
		return save;
	}
	
	public String toString() {
		String s = "";
		AnimalNode temp = head;
		for(int i = 0; i < size; i++) {
			s += temp.animal.toString() + "\n";
			temp = temp.next;
		}
		return s;
	}
	
	// custom methods
	public AnimalList getHungryAnimals() {
		AnimalList hungry = new AnimalList();
		AnimalNode temp = head;
		for(int i = 0; i < size; i++) {
			if(temp.animal.getEnergy() < 50)
				hungry.addLast(temp.animal);
			temp = temp.next;
		}
		if(hungry.size == 0)
			return null;
		else
			return hungry;
	}
	
	public AnimalList getStarvingAnimals() {
		AnimalList starving = new AnimalList();
		AnimalNode temp = head;
		for(int i = 0; i < size; i++) {
			if(temp.animal.getEnergy() < 17)
				starving.addLast(temp.animal);
			temp = temp.next;
		}
		if(starving.size == 0)
			return null;
		else
			return starving;
	}
	
	public AnimalList getAnimalsInBarn() {
		AnimalList inBarn = new AnimalList();
		AnimalNode temp = head;
		for(int i = 0; i < size; i++) {
			int x = (int) temp.animal.getX();
			int y = (int) temp.animal.getY();
			if( (x > 450 && x < 550) && (y > 50 && y < 150) )
				inBarn.addLast(temp.animal);
			temp = temp.next;
		}
		if(inBarn.size == 0)
			return null;
		else
			return inBarn;
	}
	
	public double getRequiredFood() {
		double foodNeeded = 0;
		AnimalNode temp = head;
		for(int i = 0; i < size; i++) {
			foodNeeded += 100 - temp.animal.getEnergy();
			temp = temp.next;
		}
		return foodNeeded;
	}
	
	public AnimalList getByType(Class<?> cls) {  // method is called by .getByType(AnimalType.class)
		AnimalList typeList = new AnimalList();
		AnimalNode temp = head;
		if(cls == Cow.class) {
			for(int i = 0; i < size; i++) {
				if(temp.animal instanceof Cow)
					typeList.addLast(temp.animal);
				temp = temp.next;
			}
		} else if(cls == Chicken.class) {
			for(int i = 0; i < size; i++) {
				if(temp.animal instanceof Chicken)
					typeList.addLast(temp.animal);
				temp = temp.next;
			}
		} else if(cls == Llama.class) {
			for(int i = 0; i < size; i++) {
				if(temp.animal instanceof Llama)
					typeList.addLast(temp.animal);
				temp = temp.next;
			}
		}
		return typeList;
	}
	
	// helper
	public void checkIdx(int idx) {
		if(idx < 0 || idx > size - 1)
			throw new IndexOutOfBoundsException();
	}
	
	// Iterator
	class MyIterator implements java.util.Iterator<Animal> {
		private AnimalNode current = head;
		public boolean hasNext() {
			return (current != null);
		}
		public Animal next() {
			Animal a = current.animal;
			current = current.next;
			return a;
		}
	}
	
	public java.util.Iterator<Animal> iterator() {
		return new MyIterator();
	}
	
	// Node
	class AnimalNode implements Serializable {
		Animal animal;
		AnimalNode next;
		public AnimalNode(Animal a) { animal = a; }
	}
}
