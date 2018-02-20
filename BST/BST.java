
public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){

        // Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
    	//Look for the data to see if it exists
    	
    	//If there is already a root
    	if (root != null){
    		
    		Node<E> temp = root;
    		Node<E> parent = temp;
    		
    		while(true){
	    		//If our data is less than the root 
	    		if(temp.getData().compareTo(data) > 0){
	    			
	    			if(temp.getLeftChild() != null){
	    				temp = temp.getLeftChild();
	    				parent = temp;
	    			} else {
	    				temp.setLeftChild(new Node<E>(data));
	    				temp.getLeftChild().setParent(parent);
	    				break;
	    			}
	    			
	    		} else if (temp.getData().compareTo(data) < 0){
	    			if (temp.getRightChild() != null){
	    				temp = temp.getRightChild();
	    				parent = temp;
	    			} else {
	    				temp.setRightChild(new Node<E>(data));
	    				temp.getRightChild().setParent(parent);
	    				break;
	    			}
	    		} else {
	    			//do nothing
	    			break;
	    		}
    		}
    		
	    //If the root is null, create a new node with data
    	} else {
    		root = new Node<E>(data);
    	}
    		
    }

    public Node<E> find(E data, Node<E> node){

        // Search the tree for a node whose data field is equal to data
    	//Base case, if we find the node
    	if(node == null || node.getData() == data){
    		return node;
    	//If the current node is greater than our input, look in left subtree
    	} else if(node.getData().compareTo(data) > 0) {
    		return find(data, node.getLeftChild());
    	} else {
    		return find(data, node.getRightChild());
    	}
    }
    
    public Node<E> findMin(Node<E> node){
    	while(node.hasLeftChild()){
    		node = node.getLeftChild();
    	}
    	return node;
    }
    
    public Node<E> findMax(Node<E> node){
    	while(node.hasRightChild()){
    		node = node.getRightChild();
    	}
    	return node;
    }

    public void delete(E data){
        // Find the right node to be deleted

        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        // If said node has one child, delete it by making its parent point to its child.
        // If said node has two children, then replace it with its successor,
        //          and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.

        // Hint: You may want to implement a findMin() method to find the successor when there are two children
    	Node<E> target = find(data, root);
    	
    	//if the node was found
    	if (target != null){
    		
	    	//Leaf case, no children
	    	if(target.getLeftChild() == null && target.getRightChild() == null){
	    		
	    		if(target == root){
					root = null;
				} else {
					
					//If the parent's data is greater than the target, we are the left child
					if(target.getParent().getData().compareTo(data) > 0){
						target.getParent().setLeftChild(null);
					} else {
						target.getParent().setRightChild(null);
					}
					
				}
	    		
	    	//One child cases
	    	} else if (target.hasOnlyLeftChild()){
	    		
	    		Node<E> parent = target.getParent();
	    		
	    		//If the node has a parent
	    		if(parent != null){
		    		parent.setLeftChild(target.getLeftChild());
		    		target = null;
		    		
		    	//Otherwise, the node being deleted is the root
		    	//Replace it with the max of the left subtree
	    		} else {
	    			root = target.getLeftChild();
	    			root.setParent(null);
	    		}
	    		
	    	} else if (target.hasOnlyRightChild()){
	    		Node<E> parent = target.getParent();
	    		
	    		//If the node has a parent
	    		if(parent != null){
		    		parent.setRightChild(target.getRightChild());
		    		target = null;
		    		
		    	//Otherwise, the node being deleted is the root
	    		} else {
	    			root = target.getRightChild();
	    			root.setParent(null);
	    		}

	    	//Two children case
	    	} else {
	    		//Look for min in the right subtree
	    		Node<E> minOfRightSubtree = findMin(target.getRightChild());
	    		
	    		//set min's parent's left child to be min's left child
	    		delete(minOfRightSubtree.getData());
	    		
	    		//set node data to min of right subtree
	    		target.setData(minOfRightSubtree.getData());
	    		
	    	}
	    	
	    //If the node is not in the tree
    	} else {
    		//Nothing to delete
    	}
    }

    public void inOrderTraversal(Node<E> top){
    	if (top != null){
    		//left subtree
    		inOrderTraversal(top.getLeftChild());
    		//print node data
    		System.out.print(String.valueOf(top.getData()) + " ");
    		//right subtree
    		inOrderTraversal(top.getRightChild());
    	}
    }
    
    public void preOrderTraversal(Node<E> top){
    	if (top != null){
    		//print node data
    		System.out.print(String.valueOf(top.getData()) + " ");
    		//left subtree
    		preOrderTraversal(top.getLeftChild());
    		//right subtree
    		preOrderTraversal(top.getRightChild());
    	}
    }
    
    public void postOrderTraversal(Node<E> top){
    	if (top != null){
    		//left subtree
    		postOrderTraversal(top.getLeftChild());
    		//right subtree
    		postOrderTraversal(top.getRightChild());
    		//print node data
    		System.out.print(String.valueOf(top.getData()) + " ");
    	}
    }
    
    public boolean hasSameShape(Node<E> root, Node<E> otherRoot){
    	
    	//If both trees are empty, they are the same.
    	if (root == null && otherRoot == null){
    		System.out.println("The trees have the same shape.");
    		return true;
    		
    	//If one tree is empty and the other is not, they are not the same.
    	} else if ((root != null && otherRoot == null) || (root == null && otherRoot != null)){
    		System.out.println("The trees do not have the same shape.");
    		return false;

    	//If the base cases are false, check the children of the roots recursively
    	} else {
    		return hasSameShape(root.getLeftChild(), otherRoot.getLeftChild()) 
    				&& hasSameShape(root.getRightChild(), otherRoot.getRightChild());
    	}
    }
    
    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    // Your Code here
                	preOrderTraversal(top);
                	System.out.println();
                    break;
                case "inorder":
                    // Your Code here
                	//Should be in nondecreasing order
                	inOrderTraversal(top);
                	System.out.println();
                    break;
                case "postorder":
                    // Your Code here
                	postOrderTraversal(top);
                	System.out.println();
                    break;
            }
        }
    }
}
