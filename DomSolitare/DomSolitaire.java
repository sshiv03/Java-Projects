
public class DomSolitaire {
	private StackADT<Domino>[] foundation;
	private StackADT<Domino>[] stack;
	private String name;
	private boolean debug;
	
	public DomSolitaire(int highestNum, int seed, String name) {
		this.name = name;
		
		this.foundation = (StackADT<Domino>[]) new StackADT[highestNum+1];
		this.stack = (StackADT<Domino>[]) new StackADT[highestNum+1];
	
		this.reset(seed);	//Makes the new stacks and adds the Dominos based on the seed
	}

	public String getName() {
		return this.name;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void reset(int seed) {
		int highestNum = foundation.length-1;	//Uses the length of the Foundation array to find the highest number
		int dominoCounter = 0;	//Counter for the Domino we are on for the set
		
		Domino[] newSet = Domino.getSet(highestNum);	//Makes a new Domino set based on highest number
		Domino.shuffle(newSet, seed);	//Shuffles the set
		
		for(int i = 0; i <= highestNum; i++) {	//Makes all the arrays till "F" + highest number 
			foundation[i] = new StackLL<Domino>("F"+Integer.toString(i));	//Makes a new StackLL with the correct name
			stack[i] = new StackLL<Domino>("S"+Integer.toString(i));
			
			for(int j = 0; j < i+1; j++) {	//Loops from zero till the current stack number + 1 (S0 = 1 loop, S3 = 4 loops) 
				stack[i].push(newSet[dominoCounter]);	//Pushs the current Domino from the shuffled set to the current set
				dominoCounter++;	//Gets the next Domino
			}
		}
		
	}
	
	public boolean winner() {
		int highestNum = foundation.length-1;	//Uses the length of the Foundation array to find the highest number
		for(int i = 0; i <= highestNum; i++) {
			if(!stack[i].isEmpty())	//If a stack is not empty
				return false;	//return false
		}
		return true;	//If all stacks are empty
	}
	
	public UnorderedListADT<Move> findSFMoves(){
		UnorderedListADT<Move> possibleMoves = new UnorderedList<Move>();
		Move newMove;
		
		int highestNum = foundation.length-1;
		Domino currentDomino;
		int highValue;
		int lowValue;
		
		for(int i = 0; i <= highestNum; i++) {
			if(stack[i].isEmpty())	//Checks if the From stack is empty
				continue;
			
			currentDomino = stack[i].peek();	//Get Domino at the top of the current Stack
			highValue = currentDomino.getHigh();
			lowValue = currentDomino.getLow();
			
			//Check if Domino is a Double tile or is one less than the top of the foundation
			if(highValue == lowValue || (!foundation[highValue].isEmpty() && currentDomino.isOneDown(foundation[highValue].peek()))) {	//Checks if Foundation is empty before trying to peek
				//newMove = new Move(stack[i], foundation[highValue], "SF");	//Makes a SF move from stack to foundation
				newMove = new Move(stack[i], foundation[highValue]);	//Makes a SF move from stack to foundation
				possibleMoves.addToFront(newMove);	//Adds the Move to the list
			}
		}
		
		//if(debug)System.out.println(this.toString());
		return possibleMoves;
	}
		
		public UnorderedListADT<Move> findSESMoves(){
			UnorderedListADT<Move> possibleMoves = new UnorderedList<Move>();
			Move newMove;
			
			int highestNum = foundation.length-1;
			Domino fromDomino;
			
			for(int i = 0; i <= highestNum; i++) {
				if(stack[i].isEmpty())	//Checks if the From stack is empty
					continue;
				
				for(int j = 0; j <= highestNum; j++) {
					if(!stack[j].isEmpty())	//Checks if the To stack is not empty
						continue;
					
					fromDomino = stack[i].peek();	//Get Domino at the top of the From Stack
					
					if(fromDomino.getLow() == 0) {	//Check if Domino has a blank
						//newMove = new Move(stack[i], stack[j], "SES");	//Makes a SES move from the From stack to the empty stack
						newMove = new Move(stack[i], stack[j]);	//Makes a SES move from the From stack to the empty stack
						possibleMoves.addToFront(newMove);	//Adds the Move to the list
					}
				}
			}
			
			//if(debug)System.out.println(this.toString());
			return possibleMoves;
		}
	
		public UnorderedListADT<Move> findSSMoves(){
			UnorderedListADT<Move> possibleMoves = new UnorderedList<Move>();
			Move newMove;
			
			int highestNum = foundation.length-1;
			Domino fromDomino;
			Domino toDomino;
			
			for(int i = 0; i <= highestNum; i++) {
				if(stack[i].isEmpty())	//Checks if the From stack is empty
					continue;
				
				for(int j = 0; j <= highestNum; j++) {
					if(stack[j].isEmpty())	//Checks if the To stack is empty
						continue;
					
					fromDomino = stack[i].peek();	//Get Domino at the top of the From Stack
					toDomino = stack[j].peek();	//Get Domino at the top of the To Stack
					
					if(fromDomino.hasCommonNumber(toDomino) && fromDomino.isOneUp(toDomino)) {	//Check if the Dominos have a common number and the From tile is one up compared to the To tile
						//newMove = new Move(stack[i], stack[j], "SS");	//Makes a SS move from the From stack to the To stack
						newMove = new Move(stack[i], stack[j]);	//Makes a SS move from the From stack to the To stack
						possibleMoves.addToFront(newMove);	//Adds the Move to the list
					}
				}
			}
			
			//if(debug)System.out.println(possibleMoves);
			return possibleMoves;
		}
		
		public void findMoves(StackADT<Move> st){
			UnorderedListADT<Move> possibleMoves = findSESMoves(); 	//Gets the SES Moves
			this.addFromListToStack(possibleMoves, st);	//Pushes the SES moves first
			
			possibleMoves = findSSMoves();
			this.addFromListToStack(possibleMoves, st);
			
			possibleMoves = findSFMoves();	//Gets the SF Moves
			this.addFromListToStack(possibleMoves, st);	//Pushes the SF moves last
			
		}
		
		private void addFromListToStack(UnorderedListADT<Move> possibleMoves, StackADT<Move> st){
			Move currentMove = possibleMoves.removeFirst();	//Gets the first Move
			
			while(currentMove != null) {	//Gets the moves till the none are left
				st.push(currentMove);	//Push to the stack
				currentMove = possibleMoves.removeFirst();	//Gets the next move
			}
		}
		
		public Move createMove(String from, String to) {
			Move newMove = null;
			
			try {
				int fromIndex = Integer.parseInt(from.substring(1));	//Try to convert the integer
				int toIndex = Integer.parseInt(to.substring(1));
				
				if(from.startsWith("S")) {	//Checks if the from is a valid move from a stack
					if(to.startsWith("S")) {	//Checks if the Domino moves to a stack
						newMove = new Move(stack[fromIndex], stack[toIndex]);	//Creates a move without a name
					} else if(to.startsWith("F")) {	//Checks if the Domino moves to a foundation stack
						newMove = new Move(stack[fromIndex], foundation[toIndex]);	//Creates a move without a name
					}
				}
				
			} catch (NumberFormatException e) {	//If there is a failure converting the integer
				if(debug)System.out.println("DomSolitaire.createMove: Failed to convert Int");
			}
			return newMove;
		}
		
		public String toString(){
			int highestNum = foundation.length-1;
			String output = this.name + " ";	//Adds the DomSolitaire's name

			for(int i = 0; i <= highestNum; i++) {
				
				if(foundation[i] instanceof StackLL) {	//Makes sure the Foundation is a StackLL
					output += ((StackLL) foundation[i]).getName();	//Gets the name of the StackLL
				} else {
					output += "F" + Integer.toString(i);	//Appends the name if it is not a StackLL (error probably happened)
				}
				
				output += " " + Integer.toString(foundation[i].size()) + "/" + Integer.toString(i+1) + "  ";	//Prints the Stack size and the max capacity
			}

			for(int i = 0; i <= highestNum; i++) {
				output += "\n" + stack[i].toString();	//Prints the stack's toString on a new line	
			}
			
			//if(debug)System.out.println(output);
			return output;
		}
		
		public String showNamedContent() {
			int highestNum = foundation.length-1;
			String output = "";
			
			for(int i = 0; i <= highestNum; i++) {
				output += Integer.toString(foundation[i].size());	//Prints the Foundation size
			}
			
			output += "|";
			
			for(int i = 0; i <= highestNum; i++) {
				if(stack[i] instanceof Named) {	//Makes sure the stack has showNamedContent method
					output += ((Named) stack[i]).showNamedContent();	//Gets the name of the StackLL
				} else {
					if(debug)System.out.println("DomSolitaire.showNamedContent: stack is not Named");
				}
			}
			
			//if(debug)System.out.println(output);
			return output;
		}
		
		public StackADT<Move> findSolution(int maxSteps){
			StackADT<Move> completedMoves = new StackLL<Move>("Completed Moves");
			StackADT<Move> possibleMoves = new StackLL<Move>("Move Set");
			Move currentMove;
			
			this.findMoves(possibleMoves);
			if(debug)System.out.println(this);
			
			int steps = 0;
			
			while(!(possibleMoves.isEmpty() || this.winner() || steps > maxSteps)) {	//Breaks the loop is the moves stack is empty, there is a winner, or maxSteps is exceeded
				currentMove = possibleMoves.peek();
				steps++;
				if(debug)System.out.println(possibleMoves);
				if(debug)System.out.println(this);
				
				if(!currentMove.isCompleted()) {	//if move is not completed
					currentMove.doIt();	//Do the move and mark it complete
					this.findMoves(possibleMoves);	//Find the new moves
					completedMoves.push(currentMove);	//Push the move to completed moves stack
				} else {
					if(debug)System.out.println("BACKUP");
					completedMoves.pop().undoIt();	//Undoes the completed move
					possibleMoves.pop();	//Removes the move from possible moves
					
					/* Better Algorithm
					if(completedMoves instanceof StackLL) {	//Makes sure it is StackLL
						while(((StackLL)completedMoves).contains(currentMove)) {	//Backtrack till current move is removed from completed moves
							completedMoves.pop().undoIt();	//Removes all completed moves and undos them till current move is also removed.
						}
					}
					
					if(possibleMoves instanceof StackLL) {	//Makes sure it is StackLL
						while(((StackLL)possibleMoves).contains(currentMove)) {	//Backtrack till current move is removed
							possibleMoves.pop();	//Removes the move completely
						}
					}
					*/
				}
			}
			
			if(steps > maxSteps) {	//Checks if max steps is exceeded
				if(debug)System.out.println("Msx Steps Psseed");
				return null;
			} else if(!this.winner()) {	//Checks if it is not a winner{
				if(debug)System.out.println("Not winner");
				return new StackLL<Move>();
			}
			
			StackADT<Move> solution = new StackLL<Move>();	//New Stack to hold reverse of Completed Moves	
			
			while (completedMoves.size() > 0) {	//Reverses the stack
				solution.push(completedMoves.pop());
			}
			
			if(debug)System.out.println(solution);
			
			return solution;
		}
}
