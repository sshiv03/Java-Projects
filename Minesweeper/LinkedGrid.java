import java.util.Arrays;

public class LinkedGrid<T> {
	private int width;
	private int height;
	private LinearNode<T>[] grid;
	
	public LinkedGrid(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.grid = (LinearNode<T>[]) new LinearNode[width];
		
		for(int x = 0; x < width; x++) {	//Loop through Columns
			LinearNode<T> node = new LinearNode<T>();	//Make a head node
			grid[x] = node;	//Assign node to array
			
			for(int y = 1; y < height; y++) {	//Loop rows
				LinearNode<T> nextNode = new LinearNode<T>();
				node.setNext(nextNode);	//Assign the new node as the next
				node = nextNode;	//Move to the next node
			}
		}
	}
	
	private LinearNode<T> gedNode(int col, int row){
		LinearNode<T> node = null;
		
		if(col < 0 || col >= width || row < 0 || row >= height) {
			throw new LinkedListException("LinkedGrid Out of Bounds");
		} else {
			node = grid[col];	//Gets node at column
			
			for(int y = 1; y <= row; y++)	//Goes to row
				node = node.getNext();	//Gets node at row
		}
		return node;	//Return node at col and row
	}
	
	public void setElement(int col, int row, T data) {
		LinearNode<T> node = gedNode(col, row);	//Gets node at that index
		node.setElement(data);
	}
	
	public T getElement(int col, int row) {
		LinearNode<T> node = gedNode(col, row);	//Gets node at that index
		return node.getElement();
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	
	private String printNode(LinearNode<T> node) {
		String output;
		
		if(node == null || node.getElement() == null)	//Checks if node is null/empty
			output = "null  ";
		else
			output = node.getElement().toString() + "  ";
		
		return output; 
	}

	@Override
	public String toString() {
		String output = "";
		
		for(int y = 0; y < height; y++) {	//Print rows
			for(int x = 0; x < width; x++) {	//Print cols
				output += printNode(gedNode(x, y));
			}
			
			output += "\n";
		}
		
		return output;
	}
}
