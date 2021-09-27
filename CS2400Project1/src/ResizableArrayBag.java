import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {//ResizableArrayBag class
	
	//private data fields
	private int size; // How many entries in a bag
	private T[] bag; //creates new bag
	private boolean integrityOK = false; //checks integrity
	
	/** Constructor that the bag from ArrayBagTest is passed through.
	 * 
	 */
	public ResizableArrayBag() {
		integrityOK = true;
	}//end constructor

	/**This is a constructor that the bag from ArrayBagTest is sent to.
	 * @param initialCapacity The initial size of the bag.
	 */
	public ResizableArrayBag(int initialCapacity)
	{
       //The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempBag = (T[])new Object[initialCapacity]; // Unchecked cast
      bag = tempBag;
      size = 0;
      integrityOK = true;
	}//end constructor
	
	/** This is a constructor used if an array of type T is passed through.
	 * @param b This would be the bag that is passed through.
	 */
	public ResizableArrayBag(T[] b) {
		bag = Arrays.copyOf(b, b.length);
		size = b.length;
		integrityOK = true;
	}//end constructor
    
	/** This checks if the object being passed is secure.
	 */
	private void checkintegrity() {
		if(!integrityOK) { //Checks integrity.
			throw new SecurityException ("Object is corrupt."); //throws if bag is not secure.
		}
	}
	/** Gets the current size of the bag.
	 * @return returns the size of the bag.
	 */
	public int getCurrentSize() {
		
		return size;
	}// end getCurrentSize


	/** Checks if the bag is empty.
	 * @return returns value 0 to show the bag is empty.
	 */
	public boolean isEmpty() {
		
		return size == 0;
	}// end isEmpty

    
	/** This method adds a new entry to the bag.
	 * @return this returns whether you add the bag successfully or
	 * not.
	 */
	public boolean add(T newEntry) {
		
		checkintegrity();
		if(size >= bag.length) { //Checks to see if the length of the bag should be increased.
			int newsize = 2 * bag.length;
			bag = Arrays.copyOf(bag, newsize);
		}else { //if bag doesn't need to be increased.
			bag[size] = newEntry;
			size++;
		}
		return true;
	}//end add

    
	/** Removes an unspecified entry from the bag.
	 * @return The entry that has been removed.
	 */
	public T remove(){
		
		checkintegrity();
		T remove = removeEntry(size - 1); //calls removeEntry method.
		return remove;
	}//end remove


    /** Removes a specific entry.
     * @param anEntry is used as an index for the array.
     * @return true if the entry is found and removed.
     */
	public boolean remove(T anEntry) {
		
		checkintegrity();
		int index = 0;
		
		for(int i = 0; i < size; i++) { //finds the entry that needs to be removed.
			if(bag[i] == anEntry) {//if entry is found, it saves the index.
				index = i;
				break;
			}else {
				i++;
			}
		}//end for loop
	    T ind = removeEntry(index);//Calls removeEntry method.
	    
		return anEntry.equals(ind);
	}//end remove
	
	/** It removes the entry at a specific entry.
	 * @param index is used to represent the index of the bag.
	 * @return This returns object removed.
	 */
	private T removeEntry(int index) {
		
		T remove = null; 
		if((size !=0) && (index >= 0)) { 
		remove = bag[index];
		bag[index] = bag[size - 1];
		bag[size - 1] = null;
		size--;
		}//end if
		return remove;
	}//end removeEntry

    
	/** This method clears out the whole bag
	 */
	public void clear() {
		
		for(int i = 0; i < size; i++) {// for loop to clear every entry.
			bag[i] = null;
		}// end for loop.
		
	}// end clear

    /** It counts how many times an entry is in the bag
     * @return Returns the number of how many times an entry is 
     * in the bag.
     */
	public int getFrequencyOf(T anEntry) {
		
		checkintegrity();
		int count = 0;
		
		for(int i = 0; i < size; i++) {// finds the certain entry in the bag.
			if(bag[i].equals(anEntry)) {
				count++;
			}//end if
		}// end for loop
		return count;
	}// end getFrequencyOf


    /** Checks to see if anEntry is in the bag.
     * @return true if the entry is in the bag.
     */
	public boolean contains(T anEntry) {
		
		checkintegrity();
		boolean contains = false;
		for(int i = 0; i < size; i++) {
			if(bag[i].equals(anEntry)) {
				contains = true;
				break;
			}//end if
		}//end for loop
		return contains;
	}


    /** Turns the bag into an array.
     * @return Result is a new array.
     */
	public T[] toArray() {
		
		checkintegrity();
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[size];
		int i;
		
		for(i = 0; i < size; i++) {// copies bag entries into a new array
			result[i] = bag[i];
		}
		return result;
	}// end toArray


	/** Combines to two bags into one new bag
	 * @param bag1 is a bag that contains entries.
	 * @param bag2 is a bag that contains entries.
	 * @result returns the new bag with the two original bags.
	 */
	public BagInterface<T> union(BagInterface<T> bag1, BagInterface<T> bag2) {
		
		int minCapacity = bag1.getCurrentSize() + bag2.getCurrentSize();
		T array1[] = bag1.toArray();
		T array2[] = bag2.toArray();
		
		//new bag that all contents of bag 1 and 2 will go in.
		BagInterface<T> newbag = new ResizableArrayBag<>(minCapacity);
		
		// Adds all elements from bag1.
		for(int i = 0; i < bag1.getCurrentSize(); i++) {
			
			newbag.add(array1[i]);
		}// end for loop
		
		//adds all elements from bag2.
		for(int i = 0; i < bag2.getCurrentSize(); i++) {
			newbag.add(array2[i]);
		}// end for loop
		
		return newbag;
	}// end union

	/** This method puts objects that are seen in both bags.
	 *  @param bag1 is a bag that contains entries.
	 *  @param bag2 is a bag that contains entries.
	 *  @return Returns a new bag with the repeating elements in each of the
	 *  original bags.
	 */
	public BagInterface<T> intersection(BagInterface<T> bag1, BagInterface<T> bag2){
		
	int capacity = Math.min(bag1.getCurrentSize(), bag2.getCurrentSize()) + 1;
	
	//New bag that contains repeated objects
	BagInterface<T> newbag = new ResizableArrayBag<>(capacity);

	T array[] = bag1.toArray();
	
	//Gets the number of times an element is in both bags.
	for(int i = 0; i < Math.min(bag1.getCurrentSize(), bag2.getCurrentSize()); i++) {
		if(newbag.contains(array[i])) {
			continue;
		}else {
		int first = bag1.getFrequencyOf(array[i]);
		int second = bag2.getFrequencyOf(array[i]);
		int minNum = Math.min(first, second);
		   
			//Adds all contents in newbag.
			for(int j = 0; j < minNum; j++) {
				newbag.add(array[i]);
			}//end for loop
		
		}
	}// end for loop
	return newbag;
	
		
	
	}//end intersection.
	
	
	/** Creates a new bag that contains the elements that would be left over in
	 * one bag if all entries were removed from the two bags that were the same.
	 * @param bag1 is a bag that has entries.
	 * @param bag2 is a bag that contains entries.
	 * @return Returns the new bag.
	 */
	public BagInterface<T> difference(BagInterface<T> bag1, BagInterface<T> bag2) {
		
		int returnObject;
		int capacity = Math.max(bag1.getCurrentSize(), bag2.getCurrentSize()) + 1;
		
		//A new bag that contains leftover objects.
		BagInterface<T>newbag = new ResizableArrayBag<>(capacity);
		T array[] = bag2.toArray();
		
		//Gets number of times an element is in each bag.
	    for(int i = 0; i < bag2.getCurrentSize(); i++) {
	    	
	    	if(newbag.contains(array[i])) {
	    		continue;
	    	}//end if
	    	int first = bag1.getFrequencyOf(array[i]);
	    	int second = bag2.getFrequencyOf(array[i]);
	    	
	    	if((second - first) <= 0) {
	    		returnObject = 0;
	    	}else {
	    		returnObject = second - first;
	    		for(int j = 0; j < returnObject; j++) {
	    			newbag.add(array[i]);
	    		}//end for loop
	    	}
	    	
	    }//end for loop
		return newbag;
	}//end difference 
	
}// end class
