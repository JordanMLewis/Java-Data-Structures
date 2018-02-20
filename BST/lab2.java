import java.util.Scanner;

public class lab2 {
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
		Integer numProblems = Integer.valueOf((scanner.nextLine()));
		
		BST<Integer> bst = new BST<Integer>();
		
		for(int i = 0; i < numProblems; ++i){
			
			commandArray = new String[2];
			command = scanner.nextLine();
			commandArray = command.split(" ");
			
			//Traversal case
			if(commandArray.length == 1){
				//Index 0 will be preorder, inorder, or postorder
				bst.traverse(commandArray[0], bst.getRoot());
			
			//Find, insert, delete case
			} else if (commandArray.length == 2){
				
				//string value comparison, rather than object comparison (==)
				if(commandArray[0].equals("insert")){
					bst.insert(Integer.valueOf(commandArray[1]));
					
				} else if (commandArray[0].equals("delete")){
					bst.delete(Integer.valueOf(commandArray[1]));
					
				} else if (commandArray[0].equals("find")){
					bst.find(Integer.valueOf(commandArray[1]), bst.getRoot());
				}
			} else {
				//do nothing
			}
		}
		scanner.close();
		System.exit(0);
    }
}