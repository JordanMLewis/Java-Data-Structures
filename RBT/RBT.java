public class RBT<E extends Comparable<E>> {
    private Node<E> root;
    private Node<E> nil;

    public RBT(){
        nil = new Node<E>(null);
        nil.setColor('b');
        root = nil;
        root.setLeftChild(nil);
        root.setRightChild(nil);
        root.setParent(nil);
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an RBT tree
    	
	    	Node<E> y = nil;
	    	Node<E> x = root;
	    	Node<E> z = new Node<E>(data);
	    	
	    	//find the correct place in the tree
	    	while(!isNil(x)){
	    		y = x;
	    		//if data < node x data
	    		if(z.getData().compareTo(x.getData()) < 0){
	    			x = x.getLeftChild();
	    		} else {
	    			x = x.getRightChild();
	    		}
	    	}
	    	
	    	//attach new node to tree
	    	z.setParent(y);
	    	
	    	if(isNil(y)){
	    		root = z;
	    	} else if (z.getData().compareTo(y.getData()) < 0){
	    		y.setLeftChild(z);
	    	} else {
	    		y.setRightChild(z);
	    	}
	    	
	    	z.setLeftChild(nil);
	    	z.setRightChild(nil);
	    	z.setColor('r');
	    	fixInsert(z);
    }
    
    public void  fixInsert(Node<E> z){
    	
    	Node<E> y = nil;
    	
    	while(z.getParent().getColor() == 'r'){
    		
    		//If z's parent is the left child of grandparent
    		if(z.getParent() == z.getParent().getParent().getLeftChild()){
    			
    			y = z.getParent().getParent().getRightChild();
    			
    			//case 1: y is red, re-color
    			if(y.getColor() == 'r'){
    				z.getParent().setColor('b');
    				y.setColor('b');
    				z.getParent().getParent().setColor('r');
    				z = z.getParent().getParent();
    				
    			//case 2
    			} else if (z == z.getParent().getRightChild()){
    				z = z.getParent();
    				leftRotate(z);
    			//case 3
    			} else {
    				z.getParent().setColor('b');
    				z.getParent().getParent().setColor('r');
    				rightRotate(z.getParent().getParent());
    			}
    		//If z's parent is the right child of grandparent
    		} else {
    			y = z.getParent().getParent().getLeftChild();
    			//case 1
    			if(y.getColor() == 'r'){
    				z.getParent().setColor('b');
    				y.setColor('b');
    				z.getParent().getParent().setColor('r');
    				z = z.getParent().getParent();
    			//case 2
    			} else if (z == z.getParent().getLeftChild()){
    				z = z.getParent();
    				rightRotate(z);
    			//case 3
    			} else {
    				z.getParent().setColor('b');
    				z.getParent().getParent().setColor('r');
    				leftRotate(z.getParent().getParent());
    			}
    		}
    	}
    	root.setColor('b');
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
    	boolean done = false;
        Node<E> temp = root;
        while(!done){
            if (temp == nil){
                return null;
            }
            if(temp.getData().compareTo(data) == 0){
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) < 0){
                temp = temp.getRightChild();
            }
        }
        return temp;
//        Node<E> temp = root;
//        while(temp != nil){
//            if(temp.getData().equals(data)){
//                return temp;
//                
//            } else if (temp.getData().compareTo(data) > 0){
//                temp = temp.getLeftChild();
//                
//            } else if (temp.getData().compareTo(data) < 0){
//                temp = temp.getRightChild();
//            }
//        }
//        return temp;
    }

    public void delete(E data){
    	// Preform a regular delete
    	// Check to make sure the tree remains an RBT tree
//    	if(root == nil){
//    		return;
//    	}
    	Node<E> z = search(data);
    	//node was not found
//    	if(z == nil){
//    		return;
//    	}
    	
    	Node<E> y = z;
    	Node<E> x;
    	
    	char originalColor = y.getColor();
    	
    	//If there is no left child
    	if(z.getLeftChild() == nil){
    		x = z.getRightChild();
    		transplant(z, x);
    	
    	//If there is no right child	
    	} else if (z.getRightChild() == nil){
    		x = z.getLeftChild();
    		transplant(z, x);
    	
    	//If the node we are deleting had two children, find successor
    	} else {
    		y = findMin(z.getRightChild());
    		originalColor = y.getColor();
    		x = y.getRightChild();
    		
    		if(y.getParent() == z){
    			x.setParent(y);
    		} else {
    			transplant(y, y.getRightChild());
    			//transplant(y, x);
    			y.setRightChild(z.getRightChild());
    			y.getRightChild().setParent(y);
    		}
    		transplant(z, y);
    		y.setLeftChild(z.getLeftChild());
    		y.getLeftChild().setParent(y);
    		y.setColor(z.getColor());
    	}
    	
    	if(originalColor == 'b'){
    		fixDelete(x);
    	}
    	
    }
    
    public void fixDelete(Node<E> x){
    	Node<E> w;
    	while(x != root && x.getColor() == 'b'){
    		
    		//If input node is the left child of a parent
    		if(x == x.getParent().getLeftChild()){
    			
    			w = x.getParent().getRightChild();
    			
    			//case 1: w is red
    			if(w.getColor() == 'r'){
    				w.setColor('b');
    				x.getParent().setColor('r');
    				leftRotate(x.getParent());
    				w = x.getParent().getRightChild();
    			}
    			
    			//case 2: both of w's children are black
    			if(w.getLeftChild().getColor() == 'b' && w.getRightChild().getColor() == 'b'){
    				w.setColor('r');
    				x = x.getParent();
    			//case 3 / 4
    			} else { 
    				if (w.getRightChild().getColor() == 'b'){
	    				w.getLeftChild().setColor('b');
	    				w.setColor('r');
	    				rightRotate(w);
	    				w = x.getParent().getRightChild();
    				}
	    			w.setColor(x.getParent().getColor());
	    			x.getParent().setColor('b');
	    			w.getRightChild().setColor('b');
	    			leftRotate(x.getParent());
	    			x = root;
    			}
    		//x is it's parent right child
    		} else {
    			w = x.getParent().getLeftChild();
    			
    			//case 1: w is red
    			if(w.getColor() == 'r'){
    				w.setColor('b');
    				x.getParent().setColor('r');
    				rightRotate(x.getParent());
    				w = x.getParent().getLeftChild();
    			}
    			
    			//case 2: w's children are black
    			if(w.getRightChild().getColor() == 'b' && w.getLeftChild().getColor() == 'b'){
    				w.setColor('r');
    				x = x.getParent();
    				
    			//case 3 / 4
    			} else {
    				
    				if (w.getLeftChild().getColor() == 'b'){
    				w.getRightChild().setColor('b');
    				w.setColor('r');
    				leftRotate(w);
    				w = x.getParent().getLeftChild();
    			}
    			
    			w.setColor(x.getParent().getColor());
    			x.getParent().setColor('b');
    			w.getLeftChild().setColor('b');
    			rightRotate(x.getParent());
    			x = root;
    			}
    		}
    	}
    	x.setColor('b');
    }
    
    //Utilized in delete method
    public void transplant(Node<E> u, Node<E> v){
    	if(u.getParent() == nil){
    		root = v;
    	} else if (u == u.getParent().getLeftChild()){
    		u.getParent().setLeftChild(v);
    	} else {
    		u.getParent().setRightChild(v);
    	}
    	v.setParent(u.getParent());
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
    	if (top != nil){
    		//print node data
    		System.out.print(String.valueOf(top.getData()) + " ");
    		//left subtree
    		traverse(order, top.getLeftChild());
    		//right subtree
    		traverse(order, top.getRightChild());
    	}
    }

    public void rightRotate(Node<E> y){
    	/*
    	If x is the root of the tree to rotate with left child subtree T1 and right child y, 
    	where T2 and T3 are the left and right children of y:
			x becomes left child of y and T3 as its right child of y
			T1 becomes left child of x and T2 becomes right child of x
		*/
    	
    	Node<E> x = y.getLeftChild();
    	y.setLeftChild(x.getRightChild());
    	
    	if(!isNil(x.getRightChild())){
    		x.getRightChild().setParent(y);
    	}
    	
    	x.setParent(y.getParent());
    	
    	if(isNil(y.getParent())){
    		root = x;
    	} else if (y.getParent().getRightChild() == y){
    		y.getParent().setRightChild(x);
    	} else {
    		y.getParent().setLeftChild(x);
    	}
    	
    	x.setRightChild(y);
    	y.setParent(x);
    	/*
    	Node<E> x = y.getLeftChild();
    	y.setLeftChild(x.getRightChild());
    	
    	if(x.getRightChild() != nil){
    		x.getRightChild().setParent(y);
    	}
    	x.setParent(y.getParent());
    	
    	if(y.getParent() == nil){
    		root = x;
    	} else if (y.getParent().getRightChild() == y){
    		y.getParent().setRightChild(x);
    	} else {
    		y.getParent().setLeftChild(x);
    	}
    	x.setRightChild(y);
    	
    	y.setParent(x);
    	*/
    }

    public void leftRotate(Node<E> x){
    	/*
    	If y is the root of the tree to rotate with right child subtree T3 and left child x, 
    	where T1 and T2 are the left and right children of x:
			y becomes right child of x and T1 as its left child of x
			T2 becomes left child of y and T3 becomes right child of y
		*/
    	
    	Node<E> y;
    	y = x.getRightChild();
    	x.setRightChild(y.getLeftChild());
    	
    	if(!isNil(y.getLeftChild())){
    		y.getLeftChild().setParent(x);
    	}
    	
    	y.setParent(x.getParent());
    	
    	if(isNil(x.getParent())){
    		root = y;
    	} else if (x.getParent().getLeftChild() == x){
    		x.getParent().setLeftChild(y);
    	} else {
    		x.getParent().setRightChild(y);
    	}
    	
    	y.setLeftChild(x);
    	x.setParent(y);
    	
    	/*
    	Node<E> y = x.getRightChild();			//y = x.right
    	x.setRightChild(y.getLeftChild());		//x.right = y.left
    	
    	if(y.getLeftChild() != nil){
    		y.getLeftChild().setParent(x);
    	}
    	y.setParent(x.getParent());
    	
    	if(x.getParent() == nil){
    		root = y;
    	} else if (x == x.getParent().getLeftChild()){
    		x.getParent().setLeftChild(y);
    	} else {
    		x.getParent().setRightChild(y);
    	}
    	y.setLeftChild(x);
    	x.setParent(y);
    	*/
    }
    
    public Node<E> findMin(Node<E> node){
    	while(node.getLeftChild() != nil){
    		node = node.getLeftChild();
    	}
    	return node;
    }
    
    public boolean isNil(Node<E> node){
    	return node == nil;
    }
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
}
