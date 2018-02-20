import java.util.InputMismatchException;
import java.util.Scanner;

public class lab1 {
	public static void main(String[] args){
	
		// Create a Scanner that reads system input
		
		// Loop over the scanner's input
		// For each line of the input, send it to isPalindrome()
		// If isPalindrome returns true, print "This is a Palindrome." 
		// Otherwise print "Not a Palindrome."
		
		// Close the Scanner		
		//Get file via command line
		Scanner scanner = new Scanner(System.in);
		
		String nextNumber;
		
		//First integer will represent the number of integer pairs to expect in the input file
		Integer numProblems = Integer.valueOf((scanner.nextLine()));
		
		for(int i = 0; i < numProblems; ++i){
			
			nextNumber = scanner.nextLine();
			
			if(isPalindromeEC(nextNumber)){
				System.out.println("This is a Palindrome.");
			} else {
				System.out.println("Not a Palindrome.");
			}
		}
		scanner.close();
		System.exit(0);
	}
	
	public static boolean isPalindrome(String s){
	
		// Create a stack and a Queue of chars that represents the passed in string
		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		
		// Compare your Queue and Stack to see if the input String was a Palindrome or not	
		
		Queue<Integer> q = new Queue<>();
		Stack<Integer> stack = new Stack<>();
		int nextNum;
		char[] strArray = s.toCharArray();
		
		for (char c : strArray){
			nextNum = Character.getNumericValue(c);
			stack.push(nextNum);
			q.enqueue(nextNum);
		}
		
		Node<Integer> nodeA = stack.pop();
		Node<Integer> nodeB = q.dequeue();
		boolean palindrome = true;
		
		while(palindrome && nodeA != null && nodeB != null){
			if(nodeA.getData() == nodeB.getData()){
				nodeA = nodeA.getNext();
				nodeB = nodeB.getNext();
			} else {
				palindrome = false;
				break;
			}
		}
		
		return palindrome;
	}
	
	public static boolean isPalindromeEC(String s){
	
		// Implement if you wish to do the extra credit.
		TwoStackQueue<Integer> q = new TwoStackQueue<>();
		Stack<Integer> stack = new Stack<>();
		int nextNum;
		char[] strArray = s.toCharArray();
		
		for (char c : strArray){
			nextNum = Character.getNumericValue(c);
			stack.push(nextNum);
			q.enqueue(nextNum);
		}
		
		Node<Integer> nodeA = stack.pop();
		Node<Integer> nodeB = q.dequeue();
		boolean palindrome = true;
		
		while(palindrome && nodeA != null && nodeB != null){
			if(nodeA.getData() == nodeB.getData()){
				nodeA = nodeA.getNext();
				nodeB = nodeB.getNext();
			} else {
				palindrome = false;
				break;
			}
		}
		
		return palindrome;
	}
}
