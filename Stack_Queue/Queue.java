public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;
	private Node<E> temp;
	
	public Queue(){
		
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		head = null;
		tail = null;
	}
	
	public void enqueue(E newData){
	
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail
		// Hint: Think about what's different for the first node added to the Queue
		
		//If the list has items in it...
		if(!(head == null || tail == null)){
			
			temp = new Node<E>(newData, null);
			
			//head.setNext(temp);
			tail.setNext(temp);
			
			tail = temp;
			
		//Else, the list is empty
		} else {
			head = new Node<E>(newData, null);
			tail = head;
		}
	}
	
	public Node<E> dequeue(){
	
		// Return the head of the Queue
		// Update head
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty Queue
		
		// tail, n-1, n-2, ...., 2, 1, head
		//temp = head
		//head = index 1
		
		//If there is an item, return it.
		if (head != null && tail != null){
			
			//keep value of head, and reassign head to the next spot in queue
			Node<E> temp = head;
			head = head.getNext();
			return temp;
			
		} else {
			return null;
		}
	}
	
	public boolean isEmpty(){
		
		return (head.getData() == null || tail.getData() == null);
	}
	
	public void printQueue(){
		//If there are values in the stack
		if(head != null){
			//Start printing from the top
			Node<E> currentNode = head;
			Node<E> next = currentNode.getNext();
			//System.out.print("[");		
			
			//Follow the chain of nodes until
			//we reach the bottom, in which n.getNext() == null
			while(next != null){
				
				System.out.println(currentNode.getData());
				//System.out.print(" ");
				currentNode = next;
				next = currentNode.getNext();
				
			}
			System.out.print(currentNode.getData());
			System.out.print("\n");
			
		//If there are no values in the stack
		} else{
			System.out.println("\n");
		}
	}
}
