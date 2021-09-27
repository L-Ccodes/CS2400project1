public class LinkedBag<T> implements BagInterface<T>{
	
	//Initialize private nodeOne and size
	private Node nodeOne;
	private int size = 0;
	
	/** Constructor where a new linked bag is passed through
	 */
	public LinkedBag(int capacity) {
	   nodeOne = null;
	   size = capacity;
	}//end constructor
    
	/** Gets the current number of entries that is in the bag.
	 * @return returns the number of entries that is in the bag.
	 */
	public int getCurrentSize() {
		
		return size;
	}//end getCurrentSize

    /** Checks to see if the bag is empty.
     *  @return size = 0, if the bag is empty.
     */
	public boolean isEmpty() {
		
		return size == 0;
	}//end isEmpty

	/** Adds a new entry to the bag (node).
	 * @param newEntry Is the new entry that is to be added.
	 * @return true when the new entry is successfully added.
	 */
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.next = nodeOne;
		
		nodeOne = newNode;
		size++;
		return true;
	}//end add

	/** Removes a non specific entry from the bag
	 *  @return remove when the entry was removed from the bag
	 */
	public T remove() {
		//removes random entry
		T remove = null;
		if(nodeOne != null) {
			remove = nodeOne.getData();
			nodeOne = nodeOne.getNextNode();
		}	
				
		return remove;
	}//end remove
	
	/** It gets the reference of the node that contains anEntry.
	 * @param anEntry is the entry where the desired reference is.
	 * @return the reference of anEntry.
	 */
	private Node getReferenceTo(T anEntry){
		//gets reference to an entry
		boolean found = false;
		Node currentNode = nodeOne;

		while (!found && (currentNode != null)){
		if (anEntry.equals(currentNode.getData()))
		found = true;
		else
		currentNode = currentNode.getNextNode();
		} // end while
	     
		return currentNode;
	} // end getReferenceTo


	/** Removes a specific node from the linked bag.
	 * @param anEntry is the entry that is to be removed.
	 * @return remove is returned when the entry is removed from the bag.
	 */
	public boolean remove(T anEntry) {
		//removes a specific entry
		boolean remove = false;
		Node desiredNode = getReferenceTo(anEntry);
			
			if(desiredNode != null) {
				desiredNode.setData(nodeOne.getData());
				nodeOne = nodeOne.getNextNode();
				
				size--;
			}//end if
		return remove;
	}//end remove

	/** Clears every node that is in the bag.
	 */
	public void clear() {
		
		while(!isEmpty()) {
			remove();
		}//end while
	}

	/** Counts how many times a certain entry is within a bag.
	 * @param anEntry is the entry that the method will check how may times 
	 * its included.
	 * @return the number of times the entry was found in the bag.
	 */
	public int getFrequencyOf(T anEntry) {
		//declare frequency and count
		int frequency = 0;
		int count = 0;
		
		Node currentNode = nodeOne;
		while((count < size) && currentNode != null) {
			if(anEntry.equals(currentNode.getData())) {
				frequency++;
			}//end if
			count++;
			currentNode = currentNode.getNextNode();
		}//end while
		return frequency;
	}

	/** Checks to see if the bag contains a certain entry
	 * @param anEntry is the entry that the method will try to find
	 * @return true if the entry is found.
	 */
	public boolean contains(T anEntry) {
		//checks if entry is contained
		boolean contains = false;
		Node currentNode = nodeOne;
		
		while((!contains) && (currentNode != null)) {
			if(anEntry.equals(currentNode.getData())) {
				contains = true;
			} else {
				currentNode = currentNode.getNextNode();
			}//end if else
		}
		return contains;
	}

	/** Turns the bag into an array.
	 *  @return Returns the new array.
	 */
	public T[] toArray() {
		//converts to array
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		
		int i = 0;
		Node currentNode = nodeOne;
		while((i < size) && (currentNode != null)) {
			array[i] = currentNode.getData();
			i++;
			currentNode = currentNode.getNextNode();
		}//end while
		return array;
	}

	/** Creates a new bag that combines both bags into a new bag
	 *  @param bag1 is a bag that contains entries.
	 *  @param bag2 is a bag that contains entries. 
	 *  @return the new bag that contains entries from both of the
	 *  original bags.
	 */
	public BagInterface<T> union(BagInterface<T> bag1, BagInterface<T> bag2) {
		
		//int minCapacity = bag1.getCurrentSize() + bag2.getCurrentSize();
		BagInterface<T> newBag = new LinkedBag<>(0);
		T[] array1 = bag1.toArray();
		T[] array2 = bag2.toArray();
		
		for(int i = 0; i < bag1.getCurrentSize(); i++) {
			newBag.add(array1[i]);
		}//end for
		for(int i = 0; i < bag2.getCurrentSize(); i++) {
 			newBag.add(array2[i]);
		}//end for
		
		return newBag;
	}

	/** Creates a new bag that contains the objects that are repeated in both bags
	 * @param bag1 is a bag that contains entries.
	 * @param bag2 is a bag that contains entries.
	 * @return a newBag that contains the repeated elements
	 */
	public BagInterface<T> intersection(BagInterface<T> bag1, BagInterface<T> bag2) {
		
		//int minCapacity = Math.min(bag1.getCurrentSize(), bag2.getCurrentSize()) + 1;
		BagInterface<T> newBag = new LinkedBag<>(0);
		
		T[] array = bag1.toArray();
		
		for(int i = 0; i < bag1.getCurrentSize(); i++) {
			if(newBag.contains(array[i])) {
				continue;
			}else {
				int first = bag1.getFrequencyOf(array[i]);
				int second = bag2.getFrequencyOf(array[i]);
				int minNum = Math.min(first, second);
				
				for(int j = 0; j < minNum; j++) {
					newBag.add(array[j]);
				}//end for
			}//end if else
		}//end for
		return newBag;
	}

	/** Creates a new bag that puts entries that would be left in second bag if
	 * all of the entries in the first bag were removed.
	 * @param bag1 is a bag that contains entries.
	 * @param bag2 is a bag that contains entries.
	 * @return newBag that has all of the leftover entries.
	 */
	public BagInterface<T> difference(BagInterface<T> bag1, BagInterface<T> bag2) {
		
		int returnObject;
		int minCapacity = Math.max(bag1.getCurrentSize(), bag2.getCurrentSize());
		BagInterface<T>	newBag = new LinkedBag<>(0);
		
		T[] array = bag1.toArray();
		
		for(int i = 0; i < bag1.getCurrentSize(); i++) {
			int first = bag1.getFrequencyOf(array[i]);
			int second = bag2.getFrequencyOf(array[i]);
			
			if(newBag.contains(array[i])) {
				continue;
			} else if((second - first) <= 0) {
				returnObject = 0;
			} else {
				returnObject = second - first;
				for(int j = 0; j < returnObject; j++) {
					newBag.add(array[i]);
				}//end for
			}//end if else
		}//end for
			
		
		return newBag;
	}
	
	private class Node{
		//Initialize data and next
		private T    data; // Entry in bag
		private Node next;
		
		/** A constructor to create a node.
		 * @param data is what will be in the node.
		 */
		private Node(T data){
			this(data, null);
		}//end constructor
		/** A constructor that creates a new node.
		 * @param data is what will be inside the array.
		 * @param nextNode is the reference to the next node.
		 */
		private Node(T data, Node nextNode) {
			
			this.data = data;
			next = nextNode;
		}//end constructor
		
		/** Used to get what is inside of a node.
		 * @return the data inside the node.
		 */
		private T getData() {
			return data;
		}//end getData
		
		/** This is used to enter data into a node.
		 * @param newData is what will be entered into the node.
		 */
		private void setData(T newData) {
			data = newData;
		}//end setData
		
		/** Used to refer to the next node.
		 * @return the next node.
		 */
		private Node getNextNode() {
			
			return next;
		}//end getNextNode

	}


}