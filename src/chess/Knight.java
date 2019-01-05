/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */

package chess;

/**
 * This class models the Knight chess piece, which is an extension of the abstract Piece object. 
 */

public class Knight extends Piece {
	
	/**
	 * This is the constructor for the Knight piece. It accepts either white or black and returns an instance of Knight object. 
	 * @param color		accepts either "white" or "black" 
	 */
	
	public Knight (String color) {
		super (color);
		this.type = "Knight";
	}

	/**
	 * This method checks to see if the move made by the knight is in the correct motion and within the correct amount of allowed spaces.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if move is able to be made.
	 */
	
	public boolean isValidMove(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
		/*String[] fileRanks = input.split(" ");
		int startRow= 8 -Integer.parseInt(fileRanks[0].substring(1));
		int startColumn = (fileRanks[0].charAt(0)) -'a';
		int endRow = 8-Integer.parseInt(fileRanks[1].substring(1));
		int endColumn = (fileRanks[1].charAt(0))-'a'; */
		
		if (Math.abs(startRow - endRow)==2 && Math.abs(startColumn - endColumn)==1) {
			return true;
		}
		if (Math.abs(startRow - endRow)==1 && Math.abs(startColumn - endColumn)==2) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method updates the chessboard based on the move that was input by the knight.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return chessboard		which is updated with the proposed move.
	 */
	
	public Tile[][] updateBoard(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
	/*	String[] fileRanks = input.split(" ");
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

