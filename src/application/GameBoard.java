package application;

public class GameBoard {
	private int numRows, numCols; // default size
	private int winCondition = 4;
	private byte[][] board;
	private byte playerTurn = 1;

	// default constructor
	public GameBoard() {
		numRows = 6;
		numCols = 7;
		board = new byte[numRows][numCols];
	}

	// constructor with custom size
	public GameBoard(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		board = new byte[numRows][numCols];
	}
	
	public int getNumRows() {
		return numRows;
	}


	public int getNumCols() {
		return numCols;
	}

	// TODO must be >= numRows and numCols
	public void setWinCondition(int connectionNeeded) {
		winCondition = connectionNeeded;
	}

	public int getWinCondition() {
		return winCondition;
	}
	
	public byte[][] getBoard() {
		return board;
	}

	/**
	 * Finds the lowest possible empty position in the specified column and
	 * places a chip of that player there
	 * 
	 * @param playerNumber
	 *            Should be 1 or 2
	 * @param col
	 *            Column of board chip is dropped in
	 */
	public void play(byte playerNumber, int col) {
		int lowestEmpty = numRows;
		for (int i = numRows; i >= 0; i--) { // top to bottom
			if (board[i][col] == 0)
				lowestEmpty = i;
		}
		board[lowestEmpty][col] = playerNumber;
		// change which player's turn it is (should only be 1 or 2)
		if (playerTurn == 1)
			playerTurn++;
		else
			playerTurn--;
	}

	// 0 = empty, 1 = player #1, 2 = player #2
	public boolean checkVictory(byte playerNumber) {

		if (checkVerticalWin(playerNumber))
			return true;

		if (checkHorizontalWin(playerNumber))
			return true;
		
		if (checkDiagonalWin(playerNumber))
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
	 */
	private boolean checkVerticalWin(byte playerNumber) {
		// go up rows
		for (int row = 0; row < numRows - winCondition + 2; row++) {
			// go across columns on board
			for (int col = 0; col < numCols; col++) {
				boolean successful = true;
				for (int shift = 0; shift < winCondition; shift++) {
					if (board[row + shift][col] != playerNumber) {
						successful = false;
						break;
					}
				}
				if (successful) return true;
			}
		}
		// no vertical connections of the required length were found
		return false;
	}

	/**
	 * Used as a helper method for checking victory. Looks at board and returns a
	 * boolean stating if the given player number has won with a horizontal
	 * connection
	 * 
	 * @param playerNumber
	 *            Should be 1 or 2. 0 is used as an empty field.
	 */
	private boolean checkHorizontalWin(byte playerNumber) {
		// going across the columns
		for (int col = 0; col < numCols - winCondition + 2; col++) {
			// going up the rows
			for (int row = 0; row < numRows; row++) {
				boolean successful = true;
				for (int shift = 0; shift < winCondition; shift++) {
					if (board[row][col + shift] != playerNumber) {
						successful = false;
						break;
					}
				}
				if (successful) return true;
			}
		}
		// no horizontal connections of required length were found
		return false;
	}
	
	/**
	 * Used as a helper method for checking victory. Looks at board and returns a
	 * boolean stating if the given player number has won with a diagonal
	 * connection (checks both types of diagonals)
	 * 
	 * @param playerNumber
	 *            Should be 1 or 2. 0 is used as an empty field.
	 */
	private boolean checkDiagonalWin(byte playerNumber) {
		// going up the rows
		for (int row = 0; row < numRows - winCondition + 2; row++) {
			
			// UP-RIGHT direction (going left to right across row)
			for (int col = 0; col < numCols - winCondition + 2; col++) {
				boolean successful = true;
				for (int shift = 0; shift < winCondition; shift++) {
					if (board[row + shift][col + shift] != playerNumber) {
						successful = false;
						break;
					}
				}
				if (successful) return true;
			}
			
			
			//UP-LEFT direction (going right to left across row)
			for (int col = numCols; col > numCols + winCondition - 2; col--) {
				boolean successful = true;
				for (int shift = 0; shift < winCondition; shift++) {
					if (board[row + shift][col - shift] != playerNumber) {
						successful = false;
						break;
					}
				}
				if (successful) return true;
			}
		}
		// no successful connection was found if we reach this point
		return false;
	}
}
