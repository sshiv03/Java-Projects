public class Move {
	private StackADT<Domino> from;
	private StackADT<Domino> to;
	private boolean completed;
	private String name;
	
	public Move(StackADT<Domino> from, StackADT<Domino> to) {
		this(from, to, "m");	//Calls the other constructor
	}
	
	public Move(StackADT<Domino> from, StackADT<Domino> to, String name) {
		this.from = from;
		this.to = to;
		this.name = name;
		this.completed = false;
	}
	
	public void doIt() {
		to.push(from.pop());	//Pops from the From stack and push into To
		this.completed = true;
	}
	
	public void undoIt() {
		from.push(to.pop());	//Pops from the To stack and push into From
		this.completed = false;
	}
	
	public boolean isCompleted() {
		return this.completed;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Move){	//Checks if the Object is a move
			if(((Move)obj).from == this.from && ((Move)obj).to == this.to) {	//Checks the input Move's from and to have the same memory location
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String output = this.name;
		
		if(this.from instanceof Named)	//Checks if Named is implemented
			output += ((Named) from).getName();	//Uses the Named's method
		else
			output += from.toString();	//Else uses toString
			
		output += "->";
		
		
		if(this.to instanceof Named)	//Same logic as from
			output += ((Named) to).getName();
		else
			output += to.toString();
		
		if(this.completed)	//Checks if completed
			output += "!";	//completed = true;
		else
			output += "?";
		
		return output;
	}
}
