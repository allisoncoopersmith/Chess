/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */


package chess;

/**
 * This class models the Bishop chess piece, which extends from the abstract class of Piece. 
 */
public class Bishop extends Piece {

	/**
	 * This is a constructor that accepts either "black" or "white" and returns an instance of a Bishop object.
	 * @param color		constructor accepts either "black" or "white" as valid colors
	 */
	public Bishop (String color){
		super(color);
		this.type = "Bishop";

	}
	
	/**
	 * This method checks if the move made by a bishop piece is valid. 
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true if inputted move is valid and is able to be made, false otherwise
	 */
	public boolean isValidMove(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
		
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
	 * This method updates the board with the valid move.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return chessboard		which is updated, to be used later.
	 */
	
		public Tile[][] updateBoard(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
			

			board[endRow][endColumn].setOccupyingPiece(null);
			board[endRow][endColumn].setOccupyingPiece(board[startRow][startColumn].getOccupyingPiece());
			board[startRow][startColumn].setOccupyingPiece(null);

			return board;
		}
	}
