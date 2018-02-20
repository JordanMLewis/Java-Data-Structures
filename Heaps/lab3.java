import java.util.NoSuchElementException;
import java.util.Scanner;

public class lab3 {
    public static void main(String[] args) {
    
    	// Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, perform the corresponding function
        //      (ie) insert, maximum, extractMax, isEmpty, or print
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
    	Scanner scanner = new Scanner(System.in);
		
		String command;
		String[] commandArray = new String[2];
		
		//First integer will represent the number of integer pairs to expect in the input file
		Integer numCommands = Integer.valueOf((scanner.nextLine()));
		
		//Priority Queue with size s (using numComands guarantees large enough size)
		pQueue<Integer> pq = new pQueue<Integer>(numCommands);
		try{
		for(int i = 0; i < numCommands; ++i){
			
			commandArray = new String[2];
			command = scanner.nextLine();
			commandArray = command.split(" ");
			
			// Print, isEmpty, maximum, extractMax case
			if(commandArray.length == 1){
				
				//============================================
				if(commandArray[0].equals("maximum")){
					pq.maximum();
					
				//============================================
				} else if (commandArray[0].equals("print")){
					pq.print();
					
				//============================================
				} else if (commandArray[0].equals("isEmpty")){
					if(pq.isEmpty()){
						System.out.println("Empty");
					} else {
						System.out.println("Not Empty");
					}
					
				//============================================
				} else if (commandArray[0].equals("extractMax")){
					pq.extractMax();
				} 
			
			//============================================
			//Insert case
			} else if (commandArray.length == 2){
				//string value comparison, rather than object comparison (==)
				if(commandArray[0].equals("insert")){
					pq.insert(Integer.valueOf(commandArray[1]));
					
				} else if(commandArray[0].equals("build")){
					
					//String array of each integer in input ["1", "2", "3", "4", "5"]
					String[] newArray = commandArray[1].replaceAll("\\]", "")
													 .replaceAll("\\[", "")
													 .replaceAll("\\s", "")
													 .split(",");
					
					Integer[] intArray = new Integer[newArray.length + 1];
					intArray[0] = null;
					int index = 1;
					
					//Covert all strings into ints
					for(String s : newArray){
						intArray[index] = Integer.parseInt(s);
						index++;
					}
					
					pq.build(intArray);
				}//end "build"
			} 
		}//end for loop
		} catch (NoSuchElementException nsee){
			
		}
		scanner.close();
		System.exit(0);
    }
}
