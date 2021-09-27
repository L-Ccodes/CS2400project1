import java.util.Arrays;

public class LinkedBagTest { //LinkedBagTest class

	/** Main method to test the three methods.
	 * @param args default parameter.
	 */
	public static void main(String[] args) {
		//Initializing the 2 bags that will be used
		BagInterface <String> bag1 = new LinkedBag < >(0);
		BagInterface <String> bag2 = new LinkedBag < >(0);

		//Adding each of the letters to bag1
		bag1.add("A");
		bag1.add("A");
		bag1.add("B");
		bag1.add("A");
		bag1.add("C");
		bag1.add("A");
	
		//Adding each of the letters to bag2
		bag2.add("A");
		bag2.add("B");
		bag2.add("A");
		bag2.add("C");
		bag2.add("B");
		bag2.add("C");
		bag2.add("D");
	
		//Printing the output for union of bag 1 and 2
		System.out.println(Arrays.toString(bag1.union(bag1, bag2).toArray()));

		//Printing the output for intersection of bag 1 and 2
		System.out.println(Arrays.toString(bag1.intersection(bag1, bag2).toArray()));

		//Printing the output for difference of bag 1 and 2
		System.out.println(Arrays.toString(bag1.difference(bag1, bag2).toArray()));
	}

}