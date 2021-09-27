public class ArrayBagTest{

	
	public static void main(String[] args) {
		
		String[] contains2 = {"A", "B", "A", "C", "B", "C", "D"};
		String[] contains1 = {"A", "A", "B", "A", "C", "A"};

		//The 2 bas that will be used in this program
		BagInterface<String> bag1 = new ResizableArrayBag<>(contains1);
		BagInterface<String> bag2 = new ResizableArrayBag<>(contains2);
		
		testUnion(bag1, bag2);
		testIntersection(bag1, bag2);
		testDifference(bag1, bag2);
	}
	
	private static void displayBag(BagInterface<String> aBag)
	{
		System.out.println("The bag contains " + aBag.getCurrentSize() +
		                   " string(s), as follows:");		
		Object[] bagArray = aBag.toArray();
		for (int index = 0; index < bagArray.length; index++)
		{
			System.out.print(bagArray[index] + " ");
		} // end for
		
		System.out.println();
	} // end displayBag
		
		public static void testUnion(BagInterface<String> bag1, BagInterface<String> bag2) {
			
			BagInterface<String> bag3 = new ResizableArrayBag<>(bag1.getCurrentSize() + bag2.getCurrentSize());
			
			
			System.out.println("Testing if it will combine the two bags:");
		
			if((bag1 != null) && (bag2 != null)) {
				System.out.println("The bags were combined successfully: ");
				displayBag(bag3.union(bag1, bag2));
			} else {
				System.out.println("The bags were not combined.");
			}
		}
		
	
		
		
		public static void testIntersection(BagInterface<String> bag1, BagInterface<String> bag2) {
			
			BagInterface<String> bag3 = new ResizableArrayBag<>(Math.min(bag1.getCurrentSize(), bag2.getCurrentSize()));
			
			System.out.println("Testing to find repeated objects in both bags.");
			
			if((bag1 != null) && (bag2 != null)) {
				bag3 = bag3.intersection(bag1, bag2);
				System.out.println("The repeated elements were written into one bag: ");
				displayBag(bag3);
			}else {
				System.out.println("This test was not completed successfully.");
			}
			
		}
		
		public static void testDifference(BagInterface<String> bag1, BagInterface<String> bag2) {
			
			BagInterface<String> bag3 = new ResizableArrayBag<>(Math.max(bag1.getCurrentSize(), bag2.getCurrentSize()));
			
			System.out.println("Testing to find the leftover elements.");
			
			if((bag1 != null) && (bag2 != null)){
				bag3 = bag3.difference(bag1, bag2);
				displayBag(bag3);
			} else {
				System.out.println("This test was not completed successfully.");
			}
		}
	

}
