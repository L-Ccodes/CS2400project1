public class ArrayBagTest{ //ArrayBagTest class

	
	public static void main(String[] args) {// main method
		
		String[] contains2 = {"B", "B", "D", "E"};//contents of bag 2
		String[] contains1 = {"A","B","C"};//contents of bag 1

		//The 2 bags that will be used in this program
		BagInterface<String> bag1 = new ResizableArrayBag<>(contains1);
		BagInterface<String> bag2 = new ResizableArrayBag<>(contains2);
		
		//the methods called to test
		testUnion(bag1, bag2);
		testIntersection(bag1, bag2);
		testDifference(bag1, bag2);
	}// end main

	/** This method displays the new bag as an array.
	 * @param bag Is the new bag from a certain method.
	 */
	private static void display(BagInterface<String> bag)
	{

	    Object[] bagArray = bag.toArray(); //makes bag into new array.
	    for (int index = 0; index < bagArray.length; index++)// Prints contents in new bag
	    {
	        System.out.print(bagArray[index] + " ");
	    } // end for loop 

	    System.out.println();
	} // end displayBag
	
		/** This tests the union method of resizableArrayBag.
		 * @param bag1 is the first bag used.
		 * @param bag2 is the second bag used.
		 */
		public static void testUnion(BagInterface<String> bag1, BagInterface<String> bag2) {
			
			//new bag that all contents will go in.
			BagInterface<String> bag3 = new ResizableArrayBag<>(bag1.getCurrentSize() + bag2.getCurrentSize());
			
			
			System.out.println("Testing if it will combine the two bags:");
		
			if((bag1 != null) && (bag2 != null)) { // Checks to see if either bag is null.
				System.out.println("The bags were combined successfully: ");
				bag3 = bag3.union(bag1, bag2);
				display(bag3);//displayBag method.
			} else {
				System.out.println("The bags were not combined.");
			}
		}// end testUnion
		
	
		/** This method tests the intersection method that is implemented.
		 * @param bag1 is the first bag used.
		 * @param bag2 is the second bag used.
		 */ 
		public static void testIntersection(BagInterface<String> bag1, BagInterface<String> bag2) {
			
			//New bag that will contain the repeated objects.
			BagInterface<String> bag3 = new ResizableArrayBag<>(Math.min(bag1.getCurrentSize(), bag2.getCurrentSize()));
			
			System.out.println("Testing to find repeated objects in both bags.");
			
			//checks to see if either bag is null. 
			if((bag1 != null) && (bag2 != null)) {
				bag3 = bag3.intersection(bag1, bag2);
				System.out.println("The repeated elements were written into one bag: ");
				display(bag3.intersection(bag1, bag2));//displayBag method.
			}else {
				System.out.println("This test was not completed successfully.");
			}
			
		}//end testIntersection
		
		/** This method tests the difference method that is implemented.
		 * @param bag1 is the first bag used.
		 * @param bag2 is the second bag used.
		 */
		public static void testDifference(BagInterface<String> bag1, BagInterface<String> bag2) {
			
			//new bag that will contain left over objects.
			BagInterface<String> bag3 = new ResizableArrayBag<>(Math.max(bag1.getCurrentSize(), bag2.getCurrentSize()));
			
			System.out.println("Testing to find the leftover elements.");
			
			//checks if either bag is null.
			if((bag1 != null) && (bag2 != null)){
				bag3 = bag3.difference(bag1, bag2);
				display(bag3);
			} else {
				System.out.println("This test was not completed successfully.");
			}
		}// end testDifference.
	

}//end class
