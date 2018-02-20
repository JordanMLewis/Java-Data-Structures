import java.util.InputMismatchException;
import java.util.Scanner;

public class TreeCompare {
	public static void main(String[] args) {

        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
    	Scanner scanner = new Scanner(System.in);
		
		String command;
		String[] commandArray = new String[2];
		
		//First integer will represent the number of integer pairs to expect in the input file
		Integer n1 = Integer.valueOf((scanner.nextLine()));
		
		BST<Integer> bst1 = new BST<Integer>();
		
		for(int i = 0; i < n1; ++i){
			commandArray = new String[2];
			command = scanner.nextLine();
			commandArray = command.split(" ");
			//Traversal case
			if(commandArray.length == 1){
				//Index 0 will be preorder, inorder, or postorder
				bst1.traverse(commandArray[0], bst1.getRoot());
			//Find, insert, delete case
			} else if (commandArray.length == 2){
				//string value comparison, rather than object comparison (==)
				if(commandArray[0].equals("insert")){
					bst1.insert(Integer.valueOf(commandArray[1]));
				} else if (commandArray[0].equals("delete")){
					bst1.delete(Integer.valueOf(commandArray[1]));
				} else if (commandArray[0].equals("find")){
					bst1.find(Integer.valueOf(commandArray[1]), bst1.getRoot());
				}
			} else {
				//do nothing
			}
		} //end for loop 1 (bst1)
		
		Integer n2 = Integer.valueOf((scanner.nextLine()));
		BST<Integer> bst2 = new BST<Integer>();
		
		for(int i = 0; i < n2; ++i){
			commandArray = new String[2];
			command = scanner.nextLine();
			commandArray = command.split(" ");
			//Traversal case
			if(commandArray.length == 1){
				//Index 0 will be preorder, inorder, or postorder
				bst2.traverse(commandArray[0], bst2.getRoot());
			//Find, insert, delete case
			} else if (commandArray.length == 2){
				//string value comparison, rather than object comparison (==)
				if(commandArray[0].equals("insert")){
					bst2.insert(Integer.valueOf(commandArray[1]));
				} else if (commandArray[0].equals("delete")){
					bst2.delete(Integer.valueOf(commandArray[1]));
				} else if (commandArray[0].equals("find")){
					bst2.find(Integer.valueOf(commandArray[1]), bst2.getRoot());
				}
			} else {
				//do nothing
			}
		} //end for loop 1 (bst2)
		
		//Now, compare the trees to see if they are the same:
		bst1.hasSameShape(bst1.getRoot(), bst2.getRoot());
		
		scanner.close();
		System.exit(0);
    }
}
