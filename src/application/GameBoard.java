package application;

public class GameBoard {
	private int numRows, numCols; // default size
	private int winCondition = 4;
	private byte[][] board;

	// default constructor
	public GameBoard() {
		numRows = 6;
		numCols = 7;
		board = new byte[numRows][numCols];
	}

	public int getNumRows() {
		return numRows;
	}


	public int getNumCols() {
		return numCols;
	}

	// constructor with custom size
	public GameBoard(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		board = new byte[numRows][numCols];
	}

	public void setWinCondition(int connectionNeeded) {
		winCondition = connectionNeeded;
	}

	public int getWinCondition() {
		return winCondition;
	}
	
	public byte[][] getBoard() {
		return board;
	}

	// 0 = empty, 1 = player #1, 2 = player #2
	public boolean checkVictory(byte playerNumber) {
		int rowToStop = numRows;
		outerLoop:
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (board[row][col] == playerNumber) {
					rowToStop = row;
					break outerLoop;
				}
			}
		}
		
		
		return true;
	}
}
