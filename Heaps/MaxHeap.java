public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    @SuppressWarnings("unchecked")
	public MaxHeap(int s){
    	// Build the constructor
    	// Create an array of Comparable size s, and cast it to E 
    	myArray = (E[]) new Comparable[s+1];
    	length = 0;
    	maxSize = s;
    }

	// helper functions
    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
    	if (newArray.length > maxSize){
    		return;
    	}
    	//set all elements to null
    	for(int i = 1; i < myArray.length; i++){
    		myArray[i] = null;
    	}
    	//copy elements over
    	for(int i = 1; i < newArray.length; i++){
    		myArray[i] = newArray[i];
    	}
        //myArray = newArray;
        length = newArray.length-1;
        //setMaxSize(newArray.length-1);
    }

    public int getMaxSize(){
        return maxSize;
    }

    public int getLength(){
        return length;
    }
    
    public void setMaxSize(int ms){
        maxSize = ms;
    }
    
    public void setLength(int len){
        length = len;
    }

    private int parent(int position){
    	return java.lang.Math.floorDiv(position, 2);
    }
    
    private int leftChild(int k){
    	return (2*k);
    }
    
    private int rightChild(int k){
    	return (2*k) + 1;
    }
    
    private boolean isRoot(int k){
    	//Root is at index one in our array
    	return (k == 1);
    }

    // Other Methods
    public void insert(E data){
    
    	// Insert an element into your heap.
    	
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)
    	
    	if (length < maxSize){
    		myArray[length+1] = data;
    		length++;
    		
    		int current = length;
    		int parent = parent(current);
    		
    		//Bubble up using while loop
    		//While the current element is greater than it's parent
    		while(isRoot(current) == false && myArray[current].compareTo(myArray[parent]) > 0){
    			swap(current, parent);
    			current = parent;
    			parent = parent(current);
    		}
    	}
    }

    public Comparable<E> maximum(){
        // return the minimum value in the heap
    	E max = myArray[1];
    	if(max != null){
    		System.out.println(max);
    	}
    	return max;
    }

	public Comparable<E> extractMax(){
    	
    	if (length >= 1){ 
    	
	    	Comparable<E> max = myArray[1];
	    	//Make new root the last element in the heap, and remove last
	    	myArray[1] = myArray[length];
	    	myArray[length] = null;
	    	length--;
	    	
	    	//maintain heap property by heapify
	    	heapify(1);
	    	
	    	System.out.println(max);
	    	return max;
    	} else {
    		return null;
    	}
    }
    
	public void heapify(int nodeIndex){
    	// helper function for reshaping the array
    	// Code credits to "Introduction to Algorithms" 3rd Edition
    	int LC = leftChild(nodeIndex);
    	int RC = rightChild(nodeIndex);
    	int largest;
    	
    	if(LC <= length && myArray[LC].compareTo(myArray[nodeIndex]) > 0){
    		largest = LC;
    	} else {
    		largest = nodeIndex;
    	}
    	if (RC <= length && myArray[RC].compareTo(myArray[largest]) > 0){
    		largest = RC;
    	} 
    	if(largest != nodeIndex){
    		swap(nodeIndex, largest);
    		heapify(largest);
    	}
    	
		//compareTo returns negative int if less than, 0 if equal, and positive if greater than
		//If the index is less than either the left child or the right child
    }
    
    private void swap(int i, int k){
    	E temp = myArray[i];
    	myArray[i] = myArray[k];
    	myArray[k] = temp;
    }
    
    public boolean isEmpty(){
    	return length == 0;
    }
    
    
    public void buildHeap(E[] newArr){
    	
		// used for the extra credit
    	setArray(newArr);
    	
    	for(int i = java.lang.Math.floorDiv(length, 2); i >= 1; i--){
    		heapify(i);
    	}
	}
}
