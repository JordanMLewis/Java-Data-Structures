
public class TwoStackQueue<E> {
	private Stack<E> stackOne = null;
	private Stack<E> stackTwo = null;
	
	public TwoStackQueue(){
		stackOne = new Stack<>();
		stackTwo = new Stack<>();
	}
	
	public void enqueue(E newData){
		stackOne.push(newData);
	}
	
	public Node<E> dequeue(){
		
		//If stackTwo is empty, pop all from stackOne into it
		if(stackTwo.isEmpty()){
			
			while(!stackOne.isEmpty()){
				stackTwo.push(stackOne.pop().getData());
			}
		}
		
		return stackTwo.pop();
	}
	
	public boolean isEmpty(){
		return (stackOne.isEmpty() && stackTwo.isEmpty());
	}
	
	public void printQueue(){
				
		//If there are elements in stack two and not in stack one
		if(stackOne.isEmpty() && !stackTwo.isEmpty()){
			
			Node<E> currentNode = stackTwo.peek();
			
			if(currentNode != null){
				
				while (currentNode.getNext() != null){
					System.out.println(currentNode.getData());
					currentNode = currentNode.getNext();
				}
				
				System.out.println(currentNode.getData());
			}
			
		//If there are elements in the first stack but not in the second
		} else if (!(stackOne.isEmpty()) && stackTwo.isEmpty()){
			
			Stack<E> tempStack = new Stack<>();
			Node<E> currentNode = stackOne.peek();
			
			if(currentNode != null){
				
				while (currentNode.getNext() != null){
					System.out.println(currentNode.getData());
					tempStack.push(currentNode.getData());
					currentNode = currentNode.getNext();
				}
				
				tempStack.push(currentNode.getData());
				System.out.println(currentNode.getData());
			}
			
			currentNode = tempStack.peek();
			
			while(currentNode.getNext() != null){
				System.out.println(currentNode.getData());
				currentNode = currentNode.getNext();
			}
			
			System.out.println(currentNode.getData());

		//If there are elements in both
		} else if (!(stackOne.isEmpty()) && !(stackTwo.isEmpty())){
			
			Stack<E> tempStack = new Stack<>();
			
			Node<E> currentNode = stackTwo.peek();
			
			if(currentNode != null){
				
				//print all items in stackTwo
				while (currentNode.getNext() != null){
					System.out.println(currentNode.getData());
					currentNode = currentNode.getNext();
				}
				
				System.out.println(currentNode.getData());
			}
			
			//Then move onto stackOne
			currentNode = stackOne.peek();
			
			if(currentNode != null){
				
				while (currentNode.getNext() != null){
					System.out.println(currentNode.getData());
					tempStack.push(currentNode.getData());
					currentNode = currentNode.getNext();
				}
				
				tempStack.push(currentNode.getData());
				System.out.println(currentNode.getData());
			}
			
			currentNode = tempStack.peek();
			
			while(currentNode.getNext() != null){
				System.out.println(currentNode.getData());
				currentNode = currentNode.getNext();
			}
			
			System.out.println(currentNode.getData());
			
			
		}
		
		
	}
}
