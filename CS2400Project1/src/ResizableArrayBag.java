import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
	
	
	private int size;
	private T[] bag;
	private boolean integrityOK = false;
	private int initialCapacity;

	/**This is a constructor that the bag from ArrayBagTest is sent to.
	 * @param initialCapacity The initial size of the bag.
	 */
	public ResizableArrayBag(int initialCapacity)
	{
	  this.initialCapacity = initialCapacity;
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempBag = (T[])new ResizableArrayBag[initialCapacity]; // Unchecked cast
      bag = tempBag;
      size = 0;
      integrityOK = true;
	}
	
	/** This is a constructor used if an array of type T is passed through.
	 * @param b This would be the bag that is passed through.
	 */
	public ResizableArrayBag(T[] b) {
		bag = Arrays.copyOf(b, b.length);
		size = b.length;
	}
    
	/**This checks if the object being passed is secure
	 */
	private void checkintegrity() {
		if(!integrityOK) {
			throw new SecurityException ("Object is corrupt.");
		}
	}
	/** Gets the current size of the bag.
	 * @return returns the size of the bag.
	 */
	public int getCurrentSize() {
		
		return size;
	}


	/** Checks if the bag is empty.
	 * @return returns value 0 to show the bag is empty.
	 */
	public boolean isEmpty() {
		
		return size == 0;
	}

    
	/** This method adds a new entry to the bag.
	 * @return this returns whether you add the bag successfully or
	 * not.
	 */
	public boolean add(T newEntry) {
		
		checkintegrity();
		if(size >= bag.length) {
			int newsize = 2 * bag.length;
			bag = Arrays.copyOf(bag, newsize);
		}else {
			bag[initialCapacity] = newEntry;
			size++;
		}
		return true;
	}

    
	/** Removes an unspecified entry from the bag.
	 * @return The entry that has been removed.
	 */
	public T remove(){
		
		checkintegrity();
		T remove = removeEntry(size - 1);
		return remove;
	}


    /** Removes a specific entry.
     * @param anEntry is used as an index for the array.
     * @return true if the entry is found and removed.
     */
	public boolean remove(T anEntry) {
		
		checkintegrity();
		int index = 0;
		
		for(int i = 0; i < size; i++) {
			if(bag[i] == anEntry) {
				index = i;
				break;
			}else {
				i++;
			}
		}
	    T ind = removeEntry(index);
		return anEntry.equals(ind);
	}
	
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
		}
		return remove;
	}

    
	/** This method clears out the whole bag
	 */
	public void clear() {
		
		for(int i = 0; i < size; i++) {
			bag[i] = null;
		}
		
	}

    /** It counts how many times an entry is in the bag
     * @return Returns the number of how many times an entry is 
     * in the bag.
     */
	public int getFrequencyOf(T anEntry) {
		
		checkintegrity();
		int count = 0;
		
		for(int i = 0; i < size; i++) {
			if(anEntry.equals(bag[i])) {
				count++;
			}
		}
		return count;
	}


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
			}
		}
		return contains;
	}


    /** Turns the bag into an array.
     * @return Result is a new array.
     */
	public T[] toArray() {
		
		checkintegrity();
		@SuppressWarnings("unchecked")
		T[] result = (T[])new ResizableArrayBag[size];
		int i = 0;
		while(i < size && bag[i]!=null) {
			result[i]= bag[i];
			i++;
		}
		return result;
	}


	/** Combines to two bags into one new bag
	 * @param bag1 is a bag that contains entries.
	 * @param bag2 is a bag that contains entries.
	 * @result returns the new bag with the two original bags.
	 */
	public BagInterface<T> union(BagInterface<T> bag1, BagInterface<T> bag2) {
		
		int minCapacity = bag1.getCurrentSize() + bag2.getCurrentSize();
		T array1[] = bag1.toArray();
		T array2[] = bag2.toArray();
		
		BagInterface<T> newbag = new ResizableArrayBag<T>(minCapacity);
		
		
		
		for(int i = 0; i < bag1.getCurrentSize(); i++) {
			newbag.add(array1[i]);
		}
		for(int i = 0; i < bag2.getCurrentSize(); i++) {
			newbag.add(array2[i]);
		}
		
		return newbag;
	}

	/** This method puts objects that are seen in both bags.
	 *  @param bag1 is a bag that contains entries.
	 *  @param bag2 is a bag that contains entries.
	 *  @return Returns a new bag with the repeating elements in each of the
	 *  original bags.
	 */
	public BagInterface<T> intersection(BagInterface<T> bag1, BagInterface<T> bag2){
		
	int capacity = Math.min(bag1.getCurrentSize(), bag2.getCurrentSize()) + 1;
	BagInterface<T> newbag = new ResizableArrayBag<T>(capacity);
	T array[] = bag1.toArray();
	for(int i = 0; i < bag1.getCurrentSize(); i++) {
		if(newbag.contains(array[i])) {
			continue;
		}else {
		int first = bag1.getFrequencyOf(array[i]);
		int second = bag2.getFrequencyOf(array[i]);
		int minNum = Math.min(first, second);
		   
			for(int j = 0; j <= minNum; j++) {
				newbag.add(array[i]);
			}
		
		}
	}
	return newbag;
	
		
	
	}
	
	
	/** Creates a new bag that contains the elements that would be left over in
	 * one bag if all entries were removed from the two bags that were the same.
	 * @param bag1 is a bag that has entries.
	 * @param bag2 is a bag that contains entries.
	 * @return Returns the new bag.
	 */
	public BagInterface<T> difference(BagInterface<T> bag1, BagInterface<T> bag2) {
		
		int returnObject;
		int capacity = Math.max(bag1.getCurrentSize(), bag2.getCurrentSize()) + 1;
		BagInterface<T> newbag = new ResizableArrayBag<T>(capacity);
		T array[] = bag1.toArray();
	    for(int i = 0; i < bag1.getCurrentSize(); i++) {
	    	int first = bag1.getFrequencyOf(array[i]);
	    	int second = bag2.getFrequencyOf(array[i]);
	    	
	    	if((second - first) <= 0) {
	    		returnObject = 0;
	    	}else {
	    		returnObject = second - first;
	    		for(int j = 0; j < returnObject; j++) {
	    			newbag.add(array[i]);
	    		}
	    	}
	    	
	    }
		return newbag;
	}
	
}
