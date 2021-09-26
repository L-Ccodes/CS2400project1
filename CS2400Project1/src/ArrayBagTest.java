public class ArrayBagTest{

	
	public static void main(String[] args) {
		
		
		
		BagInterface<String> bag1 = new ResizableArrayBag<String>(25);
		BagInterface<String> bag2 = new ResizableArrayBag<String>(25);
		bag1.add("a");
		bag1.add("b");
		bag1.add("a");
		bag2.add("a");
		
		
	}

}
