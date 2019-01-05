/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */

package chess;

/**
 * This class models the King chess piece, which is an extension of the abstract Piece object.
 */

public class King extends Piece {
	/**
	 * This field is set to true if this King is able to castle on the left-hand side.
	 */
	public boolean leftCastling;
	
	/**
	 * This field is set to true if this King is able to castle on the right-hand side.
	 */
	public boolean rightCastling;

	/**
	 * This is a getter method to return if it is possible for the king to castle on the left.
	 * @return true		if left castling is possible. 
	 */
	public boolean getleftCastling() {
		return this.leftCastling;
	}
	
	/**
	 * This is a getter method to return if it is possible for the king to castle on the right.
	 * @return true		if right castling is possible. 
	 */
	public boolean getrightCastling() {
		return this.rightCastling;
	}

	/**
	 * This is the constructor for the King Piece. It accepts either black or white, and returns an instance of the King object. 
	 * @param color		accepts either "white" or "black" 
	 */
	public King (String color) {
		super (color);
		this.type = "King";
		this.leftCastling=false;
		this.rightCastling=false;
	}

	/**
	 * This method checks to see if the move made by the king is in the correct motion and within the correct amount of allowed spaces.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if move is able to be made.
	 */
	
	public boolean isValidMove(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {

		/*	String[] fileRanks = input.split(" ");
		int startRow= 8 -Integer.parseInt(fileRanks[0].substring(1));
		int startColumn = (fileRanks[0].charAt(0)) -'a';
		int endRow = 8-Integer.parseInt(fileRanks[1].substring(1));
		int endColumn = (fileRanks[1].charAt(0))-'a'; */

		int leftDiagFile = startColumn-1;
		int rightDiagFile = startColumn +1;
		int frontDiagRank = startRow +1;
		int backDiagRank = startRow -1;

		if (whiteCastling(startRow, endRow, startColumn, endColumn, board) || blackCastling(startRow, endRow, startColumn, endColumn, board)) {

			return true;

		}  

		if (board[endRow][endColumn].getOccupyingPiece() ==null || !board[endRow][endColumn].getPieceColor().equals(board[startRow][startColumn].getPieceColor())) { //can't move onto a piece occupied by a piece on your team

			if (Math.abs(startRow-endRow)==1 && startColumn==endColumn) { //moving forward one
				return true;
			}
			if (Math.abs(startColumn-endColumn)==1 && startRow==endRow) { //moving left or right one

				return true;		
			}

			if ((endColumn==leftDiagFile || endColumn==rightDiagFile) && (endRow==frontDiagRank || endRow==backDiagRank)) { //moving diaganol one

				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks to see if castling is able to be done for the white player.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if castling is able to be done.
	 */
	
	public boolean whiteCastling (int startRow, int endRow, int startColumn, int endColumn, Tile[][] board) {

		King king = (King) board[startRow][startColumn].getOccupyingPiece();

		if (!king.getMoved() && king.checkColor()) {
			if (endRow == 7 && endColumn == 6 && board[7][7].getOccupyingPiece() != null && board[7][7].getPieceType().equals("R") &&  board[7][7].getPieceColor().equals("w") &&
					!board[7][7].getOccupyingPiece().getMoved() && board[7][5].getOccupyingPiece()==null && board[7][6].getOccupyingPiece() ==null) {

				return true;
			}
			if (endRow == 7 && endColumn == 2 && board[7][0].getOccupyingPiece() != null && board[7][0].getPieceType().equals("R") &&  board[7][0].getPieceColor().equals("w") &&
					!board[7][0].getOccupyingPiece().getMoved() && board[7][1].getOccupyingPiece()==null && board[7][2].getOccupyingPiece() ==null && board[7][3].getOccupyingPiece() ==null) {
				return true;
			}

		}
		return false;
	}

	/**
	 * This method checks to see if castling is able to be done for the black player.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if castling is able to be done.
	 */
	
	public boolean blackCastling (int startRow, int endRow, int startColumn, int endColumn, Tile[][] board) {	
		King king = (King) board[startRow][startColumn].getOccupyingPiece();
		if (!king.getMoved() && !king.checkColor()) {
			if (endRow == 0 && endColumn == 6 && board[0][7].getOccupyingPiece() != null && board[0][7].getPieceType().equals("R") &&  board[0][7].getPieceColor().equals("b") &&
					!board[0][7].getOccupyingPiece().getMoved() && board[0][5].getOccupyingPiece()==null && board[0][6].getOccupyingPiece() ==null) {
				king.rightCastling = true;
				return true;
			}
			if (endRow == 0 && endColumn == 2 && board[0][0].getOccupyingPiece() != null && board[0][0].getPieceType().equals("R") &&  board[0][0].getPieceColor().equals("b") &&
					!board[0][0].getOccupyingPiece().getMoved() && board[0][1].getOccupyingPiece()==null && board[0][2].getOccupyingPiece() ==null && board[0][3].getOccupyingPiece() ==null) {
				king.leftCastling = true;
				return true;
			}

		}
		return false;
	}

	/**
	 * This method updates the chessboard based on the move that was input. This will also execute castling on either side for either player if it is possible and if it is proposed.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return chessboard		which is updated with the proposed move.
	 */

	public Tile[][] updateBoard (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {


		King king = (King) board[startRow][startColumn].getOccupyingPiece();


		if (king.getrightCastling()) {
			board[startRow][6].setOccupyingPiece(board[startRow][startColumn].getOccupyingPiece());
			board[endRow][5].setOccupyingPiece(board[endRow][7].getOccupyingPiece());
			board [startRow][startColumn].setOccupyingPiece(null);
			board[endRow][7].setOccupyingPiece(null);
			king.rightCastling = false;
			return board;
		}

		if (king.getleftCastling()) {
			board[startRow][2].setOccupyingPiece(board[startRow][startColumn].getOccupyingPiece());
			board[endRow][3].setOccupyingPiece(board[endRow][0].getOccupyingPiece());
			board [startRow][startColumn].setOccupyingPiece(null);
			board[endRow][0].setOccupyingPiece(null);
			king.leftCastling = false;
			return board;			

		}
		board[endRow][endColumn].setOccupyingPiece(null);
		board[endRow][endColumn].setOccupyingPiece(board[startRow][startColumn].getOccupyingPiece());
		board[startRow][startColumn].setOccupyingPiece(null);

		return board;
	}
}
