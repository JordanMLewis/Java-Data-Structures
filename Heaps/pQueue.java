public class pQueue<E extends Comparable> {
    private MaxHeap myHeap;

    public pQueue (int s) {
        // Build the Constructor
    	//Initialize new MaxHeap of size s
    	myHeap = new MaxHeap<E>(s);
    }

    public void insert(E data){
        myHeap.insert(data);
    }

    public Comparable<E> maximum(){
        return myHeap.maximum();
    }

    public Comparable<E> extractMax(){
        return myHeap.extractMax();
    }

    public boolean isEmpty(){
        // Return true when the priority queue is empty
        // Hint: Do the actual printing in your lab3.java
    	return myHeap.isEmpty();
    }

	public void build(E[] arr){
    	// used for the extra credit
		myHeap.buildHeap(arr);
    }
    
    public void print(){
        // print out Current Queue:
        // followed by each element separated by a comma. 
        // Do not add spaces between your elements.
    	E[] array = (E[]) myHeap.getArray();
    	int heapLength = myHeap.getLength();
    	
    	System.out.print("Current Queue: ");
    	
    	if(array != null){
    		
    		//Print all elements, no comma after last
    		int i = 1;
	    	for(i = 1; i <= heapLength; i++){
	    		
    			System.out.print(array[i]);
    			
    			if(i != heapLength){
    				System.out.print(",");
    			}
	    	}
    	}
    	System.out.print("\n");
    }
}
