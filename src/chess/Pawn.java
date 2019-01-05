/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */


package chess;

/**
 * This class models the pawn chess piece, which is an extension of the abstract Piece object. 
 */

public class Pawn extends Piece {
	/**
	 * This variable holds true if this pawn piece can be enpassanted. 
	 */
	boolean canBeEnpassanted;
	/**
	 * This variable holds true if this pawn can perform enpassant. 
	 */
	boolean willEnpassant;
	/**
	 * This variable holds true if this pawn has advanced to the end of the board and can be promoted to any piece of the player's choosing. 
	 */
	boolean toBePromoted;

	/**
	 * This is the constructor for the Pawn piece. It accepts either white or black and returns an instance of Pawn object. 
	 * @param color		accepts either "white" or "black" 
	 */
	
	public Pawn (String color) {
		super (color); 
		this.type = "Pawn";
		this.canBeEnpassanted = false;
		this.willEnpassant = false;
	}

	/**
	 * This is a setter method to set the value of the canBeEnpassanted variable. 
	 * @param b		if this pawn piece can be enpassanted and the canBeEnpassanted state variable should be set to true. 
	 */
	
	public void setCanBeEnpassanted(boolean b) {
		this.canBeEnpassanted = b;
	}

	/**
	 * This is a setter method to set the value of the willEnpassant variable. 
	 * @param b		if this pawn piece can perform enpassant and the willEnpassant state variable should be set to true. 
	 */
	
	public void setWillEnpassant (boolean b) {
		this.willEnpassant = b;
	}
	
	/**
	 * This is a getter method to return the current value of the canBeEnpassanted variable. 
	 * @return true		if this pawn piece can be enpassanted.
	 */
	
	public boolean getCanBeEnpassanted() {
		return this.canBeEnpassanted;
	}
	
	/**
	 * This is a getter method to return the current value of the willEnpassant variable. 
	 * @return true		if this pawn piece can perform enpassant. 
	 */
	
	public boolean getWillEnpassant () {
		return this.willEnpassant;
	}

	/**
	 * This method checks to see if the move made by the pawn is in the correct motion and within the correct amount of allowed spaces.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if move is able to be made.
	 */

	public boolean isValidMove (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) { //put in enpassant 

		if (board[startRow][startColumn].getPieceColor().equals("w") && whiteValid(startRow, endRow, startColumn,endColumn, board)) {
		
			return true;
		}
		if (board[startRow][startColumn].getPieceColor().equals("b") && blackValid(startRow, endRow, startColumn,endColumn, board)) {
			return true;
		}

		return false;

	}
	
	/**
	 * This method checks if the black player is able to enpassant.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if black player can enpassant.
	 */
	public boolean blackEnpassant(int startRow, int endRow, int startColumn, int endColumn, Tile[][] board) {
		int leftDiagFile = startColumn-1;
		int rightDiagFile = startColumn +1;
		int diagRank = startRow +1;

		if ((endRow == 5 && endColumn == leftDiagFile || endColumn == rightDiagFile) && diagRank==endRow  && board[endRow][endColumn].getOccupyingPiece()==null) {
			if (board[endRow-1][endColumn].getOccupyingPiece() != null && board[endRow-1][endColumn].getPieceColor().equals("w") && board[endRow-1][endColumn].getPieceType().equals("p")) {		
				Pawn checkEnpass = (Pawn) board[endRow-1][endColumn].getOccupyingPiece();
				if (checkEnpass.getCanBeEnpassanted()) {
					Pawn willEnpass = (Pawn) board[startRow][startColumn].getOccupyingPiece();		
					willEnpass.setWillEnpassant(true);
					board[startRow][startColumn].setOccupyingPiece(willEnpass);
					return true;
				}
			}
		}
		return false;

	}
	
	/**
	 * This method checks if the black player is making a valid move.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if black player can make move since move is valid.
	 */
	public boolean blackValid(int startRow, int endRow, int startColumn, int endColumn, Tile[][] board) {

		if (blackEnpassant (startRow, endRow, startColumn,endColumn, board)) {
			return true;
		}

		Tile startingPosition = board[startRow][startColumn];
		Tile endingPosition = board[endRow][endColumn];

		int leftDiagFile = startColumn-1;
		int rightDiagFile = startColumn +1;
		int diagRank = startRow +1;

		/*if (blackEnpassant(int startRow, int endRow, int startColumn, int endColumn, Tile[][] board) ) {
			return true;
		} */


		if (!startingPosition.getOccupyingPiece().getMoved() && endColumn==startColumn && endRow-startRow==2 && endingPosition.getOccupyingPiece()==null) { //if the pawn hasn't moved and it wants to move 2 spaces fwd
			if (board[startRow+1][endColumn].getOccupyingPiece()== null) {	//can't be a piece btwn the start and the end

				return true ;
			}

		}



		if (startColumn==endColumn && endingPosition.getOccupyingPiece()==null && endRow-startRow==1) { //moving forward one if black

			return true;

		}

		if ((endColumn == leftDiagFile|| endColumn == rightDiagFile) && diagRank==endRow && 
				endingPosition.getOccupyingPiece()!= null && endingPosition.getPieceColor().equals("w")) { //black moving diagonally into a white piece

			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the white player is able to enpassant.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if white player can enpassant.
	 */
	public boolean whiteEnpassant(int startRow, int endRow, int startColumn, int endColumn, Tile[][] board) {
		int leftDiagFile = startColumn-1;
		int rightDiagFile = startColumn +1;
		int diagRank = startRow -1;


		if ((endRow == 2 && endColumn == leftDiagFile || endColumn == rightDiagFile) && diagRank==endRow  && board[endRow][endColumn].getOccupyingPiece()==null) {
			if (board[endRow+1][endColumn].getOccupyingPiece() != null && board[endRow+1][endColumn].getPieceColor().equals("b") && board[endRow+1][endColumn].getPieceType().equals("p")) {
				Pawn checkEnpass = (Pawn) board[endRow+1][endColumn].getOccupyingPiece();

				if (checkEnpass.getCanBeEnpassanted()) {				
					Pawn willEnpass = (Pawn) board[startRow][startColumn].getOccupyingPiece();
					willEnpass.setWillEnpassant(true);
					board[startRow][startColumn].setOccupyingPiece(willEnpass);
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * This method checks if the white player is making a valid move.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return true		if white player can make move since move is valid.
	 */
	public boolean whiteValid(int startRow, int endRow, int startColumn, int endColumn, Tile[][] board) {

		Tile startingPosition = board[startRow][startColumn];
		Tile endingPosition = board[endRow][endColumn];
		//file is column, rank is row


		int leftDiagFile = startColumn-1;
		int rightDiagFile = startColumn +1;
		int diagRank = startRow -1;

		if (whiteEnpassant (startRow, endRow, startColumn,endColumn, board)) {
			return true;
		}

		if (!startingPosition.getOccupyingPiece().getMoved() && endColumn==startColumn && endRow-startRow==-2 && endingPosition.getOccupyingPiece()==null) { //if the pawn hasn't moved and it wants to move 2 spaces fwd
			if (board[startRow-1][endColumn].getOccupyingPiece()== null) {	//can't be a piece btwn the start and the end
				return true;
			}


		}

		if (startColumn==endColumn && endingPosition.getOccupyingPiece()==null && endRow-startRow == -1) { //moving forward one if white

			return true;

		}



		if ((endColumn == leftDiagFile || endColumn == rightDiagFile) && diagRank==endRow  && 
				endingPosition.getOccupyingPiece()!= null && endingPosition.getPieceColor().equals("b")) { //white moving diagonally into an black piece

			return true;
		}
		return false;

	}


	/**
	 * This method updates the chessboard based on the move that was input by the pawn.
	 * @param startRow 		Row before move
	 * @param startColumn 		Column before move
	 * @param endRow 		Row after move
	 * @param endColumn		Column after move
	 * @param board			board after move
	 * @return chessboard		which is updated with the proposed move.
	 */

	public Tile[][] updateBoard (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
		Pawn pawn = (Pawn) board[startRow][startColumn].getOccupyingPiece();


		if (pawn.getWillEnpassant() && pawn.checkColor()) {
			board[startRow][startColumn].setOccupyingPiece(null);
			board[endRow][endColumn].setOccupyingPiece(pawn);
			board[endRow+1][endColumn].setOccupyingPiece(null);


			return board;


		}
		if (pawn.getWillEnpassant() && !pawn.checkColor()) {
			board[startRow][startColumn].setOccupyingPiece(null);
			board[endRow][endColumn].setOccupyingPiece(pawn);
			board[endRow-1][endColumn].setOccupyingPiece(null);
			return board;


		}



		board[endRow][endColumn].setOccupyingPiece(null);
		board[endRow][endColumn].setOccupyingPiece(board[startRow][startColumn].getOccupyingPiece());
		board[startRow][startColumn].setOccupyingPiece(null);

		return board;
	}


	/**
	 * This method checks if moving pawn is eligible for promotion.
	 * @param input		basic parameters for method to understand board layout and see if propomotion is due.
	 * @param board		board before promotion
	 * @return chessboard		which is updated with the promoted/unpromoted pawn moves.
	 */
	
	public Tile[][] checkForPromotion (String input, Tile[][] board) {

		input = input.toLowerCase();

		String[] fileRanks = input.split(" ");


		int startRow = 8-Integer.parseInt(fileRanks[0].substring(1));
		int startColumn = (fileRanks[0].charAt(0)) -'a';
		int endRow = 8-Integer.parseInt(fileRanks[1].substring(1));
		int endColumn = (fileRanks[1].charAt(0))-'a'; 

		if (board[startRow][startColumn].getOccupyingPiece() != null && !board[startRow][startColumn].getPieceType().equals("p")) {
			return board;
		}



		//check for promotion: black
		if (endRow==7 && board[endRow][endColumn].getPieceColor().equals("b")) {
			if (fileRanks.length < 3 || (fileRanks.length >= 3 && fileRanks[2].equals("q"))) {
				board[endRow][endColumn].setOccupyingPiece(new Queen("Black"));

			}
			else if (fileRanks[2].equals("n")) {
				board[endRow][endColumn].setOccupyingPiece(new Knight("Black"));
			}
			else if (fileRanks[2].equals("r")) {
				board[endRow][endColumn].setOccupyingPiece(new Rook("Black"));

			}
			else if (fileRanks[2].equals("b")) {
				board[endRow][endColumn].setOccupyingPiece(new Bishop("Black"));

			}
		}

		//check for promotion: white
		if (endRow==0 && board[endRow][endColumn].getPieceColor().equals("w")) {
			if (fileRanks.length < 3 || (fileRanks.length >= 3 && fileRanks[2].equals("q"))) {
				board[endRow][endColumn].setOccupyingPiece(new Queen("White"));

			}
			else if (fileRanks[2].equals("n")) {
				board[endRow][endColumn].setOccupyingPiece(new Knight("White"));
			}
			else if (fileRanks[2].equals("r")) {
				board[endRow][endColumn].setOccupyingPiece(new Rook("White"));

			}
			else if (fileRanks[2].equals("b")) {
				board[endRow][endColumn].setOccupyingPiece(new Bishop("White"));

			}
		}

		return board;


	}


}