public class Stack<E> {
	private Node<E> top;
	private Node<E> temp;
	
	public Stack(){
	
		// We want to initialize our Stack to be empty
		// (ie) Set top as null
		top = null;
	}
	
	public void push(E newData){
	
		// We want to create a node whose data is newData and next node is top
		// Push this new node onto the stack
		// Update top
		
		//Do not let null be pushed to stack
		if (newData != null){
			
			//If there are items on the stack...
			if(!(top == null)){
				
				Node<E> newNode = new Node<E>(newData, top);
				top = newNode;
				
			//If there are no items...
			} else {
				top = new Node<E>(newData, null);
			}
		}
	}
	
	public Node<E> pop(){
	
		// Return the Node that currently represents the top of the stack
		// Update top
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty stack		
		
		//If the stack has has a top, it still has values
		if(!(top == null)){
			
			//Store value of top in temp variable
			temp = top;
			
			//will assign top to null in the case that there is one item left
			top = temp.getNext(); 
			return temp;
			
		//If the stack is empty, return null
		} else {
			return null;
		}
	}
	
	public Node<E> peek(){
		return top;
	}
	
	public boolean isEmpty(){

		//Check if there is a "top" value
		if(top != null){
			return false;
		} else {
			return true;
		}
	}
	
	public void printStack(){
		
		//If there are values in the stack
		if(top != null){
			
			//Start printing from the top
			Node<E> currentNode = top;
			Node<E> next = currentNode.getNext();
			
			//Follow the chain of nodes until
			//we reach the bottom, in which n.getNext() == null
			while(next != null){
				
				System.out.print(currentNode.getData());
				System.out.print("\n");
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