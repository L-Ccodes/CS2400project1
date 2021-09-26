
class Node<T>{
	
	private T    data; // Entry in bag
	private Node next;
	
	private Node(T data, Node nextNode) {
		
		this.data = data;
		next = nextNode;
	}
	
	private T getData() {
		return data;
	}
	
	private void setData(T newData) {
		data = newData;
	}
	
	private Node getNextNode() {
		
		return next;
	}

	private void setNextNode(Node nextNode) {
		next = nextNode;
	}
}
