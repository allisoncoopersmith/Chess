/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */

package chess;

/**
 * This class models the Queen chess piece, which is an extension of the abstract Piece object. 
 */

public class Queen extends Piece {

	/**
	 * This is the constructor for the Queen piece. It accepts either white or black and returns an instance of Queen object. 
	 * @param color		accepts either "white" or "black" 
	 */
	
	public Queen (String color) {
		super (color);
		this.type = "Queen";
	}

	/**
	 * This method checks to see if the move made by the queen is in the correct motion.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if move by Queen is able to be made.
	 */
	public boolean isValidMove(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {

		if (validRook(startRow, startColumn, endRow, endColumn, board) || validBish(startRow, startColumn, endRow, endColumn, board)) {
			return true;
		}

		return false;
	}

	
	/**
	 * This checks to see if the queen has moved in the motion of a rook.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if Queen has emulated rook.
	 */
	public boolean validRook (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
		
		int distanceMovedUpDown =endRow-startRow; 
		int distanceMovedLeftRight = endColumn-startColumn;

		if (distanceMovedUpDown !=0 && distanceMovedLeftRight != 0) { //have to stay in the same column or row to be valid
			return false;
		}


		if (startRow == endRow) { //moving left or right 
			if (Math.abs(distanceMovedLeftRight) > 1) { //checking if there's pieces btwn start and end if moving more than 1
				if (distanceMovedLeftRight > 0) {//moving to the right 
					int x=startColumn + 1;
					while (x < endColumn) {
						if (board[startRow][x].getOccupyingPiece() != null) {
							return false;
						}
						x++;
					}	
				}
				if (distanceMovedLeftRight < 0) {//moving to the left
					int x = startColumn -1;
					while (x > endColumn) {
						if (board[startRow][x].getOccupyingPiece() != null) {
							return false;
						}
						x--;
					}

				}

			}
			return true;

		}
		if (startColumn == endColumn) { //moving up or down
			if (Math.abs(distanceMovedUpDown) > 1) { //checking if there's pieces btwn start and end if moving more than 1
				if (distanceMovedUpDown > 0) {//moving up the array
					int x=startRow + 1;
					while (x < endRow) {
						if (board[x][startColumn].getOccupyingPiece() != null) {
							return false;
						}
						x++;
					}	
				}
				if (distanceMovedUpDown < 0) {//moving down the array
					int x = startRow -1;
					while (x > endRow) {
						if (board[x][startColumn].getOccupyingPiece() != null) {
							return false;
						}
						x--;
					}

				}
			}
			return true;
		}
		return false;

	}

	
	/**
	 * This checks to see if the queen has moved in the motion of a bishop.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if Queen has emulated bishop.
	 */

	public boolean validBish (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
		

		if (Math.abs(startRow-endRow)==Math.abs(startColumn-endColumn)) {
			int distanceMovedRows = startRow-endRow;
			int distanceMovedCols =startColumn-endColumn;
			if (distanceMovedRows > 0 && distanceMovedCols < 0) {

				int x=startRow-1;
				int y = startColumn+1;
				while (x > endRow && y < endColumn) { 
					if (board[x][y].getOccupyingPiece() != null) {
						return false;
					}
					x--; y++;
				}
			} 
			if (distanceMovedRows > 0 && distanceMovedCols > 0) { 

				int x=startRow-1;
				int y = startColumn-1;
				while (x > endRow && y > endColumn) {
					if (board[x][y].getOccupyingPiece() != null) {
						return false;
					}
					x--; y--;
				}
			}
			if (distanceMovedRows < 0 && distanceMovedCols < 0) {

				int x=startRow+1;
				int y = startColumn+1;

				while (x < endRow && y < endColumn) {
					if (board[x][y].getOccupyingPiece() != null) {
						return false;
					}
					x++; y++;

				}
			}
			if (distanceMovedRows < 0 && distanceMovedCols > 0) {

				int x=startRow+1;
				int y = startColumn-1;

				while (x < endRow && y > endColumn) {
					if (board[x][y].getOccupyingPiece() != null) {
						return false;
					}
					x++; y--;

				}
			}

			return true;
		}

		return false;
	}

	/**
	 * This method updates the chessboard based on the move that was input by the Queen.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return chessboard		which is updated with the proposed move.
	 */

	public Tile[][] updateBoard(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
		/*String[] fileRanks = input.split(" ");
		int startRow= 8 -Integer.parseInt(fileRanks[0].substring(1));
		int startColumn = (fileRanks[0].charAt(0)) -'a';
		int endRow = 8-Integer.parseInt(fileRanks[1].substring(1));
		int endColumn = (fileRanks[1].charAt(0))-'a'; */

		board[endRow][endColumn].setOccupyingPiece(null);
		board[endRow][endColumn].setOccupyingPiece(board[startRow][startColumn].getOccupyingPiece());
		board[startRow][startColumn].setOccupyingPiece(null);

		return board;
	}
}


