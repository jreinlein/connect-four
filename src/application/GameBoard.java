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
		// getting first empty row (from the bottom going up), this is where we can stop checking
		outerLoop:
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (board[row][col] == playerNumber) {
					rowToStop = row;
					break outerLoop;
				}
			}
		}

		// VERTICAL ============================================
		// game pieces have reached sufficient height to allow vertical victory
		if (rowToStop >= winCondition - 1)
			if (checkVerticalWin(playerNumber, rowToStop))
				return true;

		// HORIZONTAL ==========================================
		if (checkHorizontalWin(playerNumber, rowToStop))
			return true;

		return false;
	}

	/**
	 * Used as a helper method for checking victory. Looks at board and returns
	 * a boolean stating if the given player number has won with a vertical
	 * connection
	 * 
	 * @param playerNumber
	 *            Should be 1 or 2. 0 is used as an empty field.
	 * @param rowToStop
	 *            First empty row (starting from bottom)
	 */
	private boolean checkVerticalWin(byte playerNumber, int rowToStop) {
		int connected = 0;
		// go up rows
		for (int row = 0; row < rowToStop - winCondition + 2; row++) {
			// go across columns on board
			for (int col = 0; col < numCols; col++) {
				// check if required # is all connected
				for (int i = 0; i < winCondition; i++) {
					if (board[row][col] == playerNumber)
						connected++;
				}
				if (connected == winCondition)
					return true;
				connected = 0;
			}
		}
		// no vertical connections of the required length were found
		return false;
	}

	/**
	 * Used as a hlper method for checking victory. Looks at board and returns a
	 * boolean stating if the given player number has won with a horizontal
	 * connection
	 * 
	 * @param playerNumber
	 *            Should be 1 or 2. 0 is used as an empty field.
	 * @param rowToStop
	 *            First empty row (starting from bottom)
	 */
	private boolean checkHorizontalWin(byte playerNumber, int rowToStop) {
		int connected = 0;
		// going up the columns
		for (int col = 0; col < numCols - winCondition + 2; col++) {
			// going across the row
			for (int row = 0; row < rowToStop; row++) {
				// check if required # of connections is present
				for (int i = 0; i < winCondition; i++) {
					if (board[row][col] == playerNumber)
						connected++;
				}
				if (connected == winCondition)
					return true;
				connected = 0;
			}
		}
		// no horizontal connections of required length were found
		return false;
	}
}
