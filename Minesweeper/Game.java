import java.awt.Color;	//Needed for the provided background color code to work

public class Game {
	private LinkedGrid<Character> board;
	private LinkedGrid<GUICell> cells;
	public static int width;
	public static int height;
	private boolean isPlaying;
	private GUI gui;
	
	public Game(int width, int height, boolean fixedRandom, int seed) {
		Game.width = width;
		Game.height = height;
		
		this.board = new LinkedGrid<Character>(width, height);

		//Fill board with '_'
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				board.setElement(x, y, '_');
			}
		}
		
		initializeCells();	//initialize Cells
		
		BombRandomizer.placeBombs(board, fixedRandom, seed);
		determineNumbers();
		
		this.isPlaying = true;
		gui = new GUI(this, cells);
	}

	public Game(LinkedGrid<Character> board) {
		Game.width = board.getWidth();	//Get data from board
		Game.height = board.getHeight();
		
		initializeCells();	//initialize Cells
		
		this.board = board;
		determineNumbers();
		
		this.isPlaying = true;
		gui = new GUI(this, cells);
	}
	
	private void initializeCells() {
		this.cells = new LinkedGrid<GUICell>(width, height);
		GUICell newCell;
		
		//Fill Cells with GUICell Objects
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				newCell = new GUICell(y, x);	//The row and col parameters are flipped in GUICell class
				cells.setElement(x, y, newCell);	
			}
		}
	}

	public static int getWidth() {
		return Game.width;
	}

	public static int getHeight() {
		return Game.height;
	}
	
	public LinkedGrid<GUICell> getCells() {
		return cells;
	}
	
	private int checkBomb(int col, int row) {
		if(col >= 0 && row >= 0 && col < Game.width && row < Game.height)	//Check valid index
			if(board.getElement(col, row) == 'x')	//Check bomb
				return 1;	//Returns if there is a bomb at that index
		
		return 0;	//No bomb or out of bounds
	}
	
	public void determineNumbers() {
		int bombCount;
		
		for(int x = 0; x < Game.width; x++) {
			for(int y = 0; y < Game.height; y++) {
				if(checkBomb(x, y) == 1) {	//If current index is bomb
					bombCount = -1;
				} else {
					bombCount = 0;	//Reset bomb count for that index
					
					//Check neighbors for a bomb
					for(int i = -1; i <= 1; i++) {
						for(int j = -1; j <= 1; j++) {
							if(i == 0 && j == 0)	//Only check neighbors (skip current index)
								continue;
								
							bombCount += checkBomb(x+i, y+j);	//Count the number of bombs
						}
					}
				}
				cells.getElement(x, y).setNumber(bombCount);	//Add the bomb count to cell
			}
		}
	}

	public int processClick(int col, int row) {
		if(!isPlaying)	//Check if the game is lost
			return -10;	//Return Bomb previously  found
		
		GUICell cell = cells.getElement(col, row);
		int cellNumber = cell.getNumber();
		
		if(cellNumber < -1)	{	//Double check if game is lost
			isPlaying = false;	//Set the isPlaying to false to indicate a lost game
			return -10;	//Return Bomb previously found
		}

		if(cellNumber < 0) {	//Check if it is a bomb
			if(cell.isRevealed()) {	//Triple check if game is lost
				isPlaying = false;	//Set the isPlaying to false to indicate a lost game
				cell.setNumber(-10);
				return -10;	//Return Bomb previously found
			}

			if(gui != null)
				cell.setBackground(Color.red);
			
			cell.reveal();	//Reveal the bomb
			cell.setNumber(-10);	//Set bomb to previously found
			isPlaying = false;	//Set the isPlaying to false to indicate a lost game
			
			return -1;	//Bomb found
		} else if(cellNumber == 0) {	//Check if cell contains 0
			return recClear(col, row);	//region clearing starting at current index
		} else if (cellNumber > 0) {	
			if(cell.isRevealed())	//Check if the cell is revealed
				return 0;
			
			if(gui != null)
				cell.setBackground(Color.white);
			
			cell.reveal();
			return 1;
		}
		
		return -21;	//Error happened
	}
	
	//Following provided Algorithm
	private int recClear(int col, int row) {
		if(col < 0 || row < 0 || col >= Game.width || row >= Game.height)	//Check if index is out of bounds
			return 0;
		
		GUICell cell = cells.getElement(col, row);
		int cellNumber = cell.getNumber();
		
		if(cell.isRevealed() || !isPlaying || cellNumber < 0)	//Check if cell is revealed or is a bomb
			return 0;
		
		if(cellNumber > 0) {
			cell.reveal();
			
			if(gui != null)
				cell.setBackground(Color.white);
			
			return 1;
		} else {
			cell.reveal();
			
			if(gui != null)
				cell.setBackground(Color.white);
			int result = 1;
			
			//Check all neighbors
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(i == 0 && j == 0)	//Only check neighbors (skip current index)
						continue;
						
					result += recClear(col+i, row+j);
				}
			}

			return result;
		}
	}
}
