
public class Node<E extends Comparable<E>> {
    private E data;
    private Node<E> leftChild;
    private Node<E> rightChild;
    private Node<E> parent;

    public Node(E d){
        data = d;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public void setData(E d){
        data = d;
    }

    public void setLeftChild(Node<E> lc){
        leftChild = lc;
    }

    public void setRightChild(Node<E> rc){
        rightChild = rc;
    }

    public void setParent(Node<E> p){
        parent = p;
    }

    public E getData(){
        return data;
    }

    public Node<E> getLeftChild(){
        return leftChild;
    }
    
    public boolean hasLeftChild(){
    	return (leftChild != null);
    }
    
    public boolean hasRightChild(){
    	return (rightChild != null);
    }

    public Node<E> getRightChild(){
        return rightChild;
    }

    public Node<E> getParent(){
        return parent;
    }
    
    public boolean hasChildren(){
    	return (leftChild != null || rightChild != null);
    }
    
    public boolean hasOnlyLeftChild(){
    	return (leftChild != null && rightChild == null);
    }
    
    public boolean hasOnlyRightChild(){
    	return (leftChild == null && rightChild != null);
    }
    
    public Node<E> findMin(){
    	Node<E> node = leftChild;
    	while(node.hasLeftChild()){
    		node = node.getLeftChild();
    	}
    	return node;
    }
    
    public Node<E> findMax(){
    	Node<E> node = rightChild;
    	while(node.hasRightChild()){
    		node = node.getRightChild();
    	}
    	return node;
    }
}