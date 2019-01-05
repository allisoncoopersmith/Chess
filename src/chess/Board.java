/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */

package chess;

import java.util.ArrayList;

/**
 * This class maintains the chess board throughout the execution of the program. It contains methods for initializing the board with the correct format, maintaining the current player's turn, and other functions and rules that need to be enforced throughout a round of play. 
 */
public class Board {
	/**
	 * This is a global variable that maintains the state of the chess board. 
	 */
	public Tile[][] chessBoard;
	/**
	 * This is a boolean to hold the current color's turn. 
	 */
	public boolean whitesMove;
	/**
	 * This is a boolean to maintain if the white player has resigned. 
	 */
	public boolean whiteResigns;
	/**
	 * This is a boolean to maintain if the black player has resigned. 
	 */
	public boolean blackResigns;
	/**
	 * This is a boolean to hold true if white has requested a draw. 
	 */
	public boolean whiteWantsDraw;
	/**
	 * This is a boolean to hold true if black has requested a draw. 
	 */
	public boolean blackWantsDraw;
	/**
	 * This is a boolean to remain false until the game is over. 
	 */
	public boolean gameIsDone;


	/**
	 * This is a constructor that accepts no parameters and returns an instance of a Board object, or in other words, a chess board.
	 * @return board		an instance of a board object, that will maintain the state of a chessboard throughout play. 
	 */
	public Board() {
		whitesMove = true;

		chessBoard = new Tile[8][8];	

		// black pieces
		chessBoard [0][0] = new Tile( "White", new Rook("Black"));
		chessBoard [0][1] = new Tile( "Black", new Knight("Black"));
		chessBoard [0][2] = new Tile( "White", new Bishop("Black"));
		chessBoard [0][3] = new Tile("Black", new Queen("Black"));
		chessBoard [0][4] = new Tile("White", new King ("Black"));
		chessBoard [0][5] = new Tile("Black", new Bishop("Black"));
		chessBoard [0][6]= new Tile( "White", new Knight("Black"));
		chessBoard [0][7]= new Tile( "Black", new Rook("Black"));
		for (int y = 0; y<=7; y++) { //black pawns
			if (y % 2 == 0) {
				chessBoard [1][y] = new Tile( "Black", new Pawn("Black"));
			}
			else {
				chessBoard [1][y] = new Tile("White", new Pawn("Black"));
			}

		}
		//white pieces
		chessBoard [7][0] = new Tile( "Black", new Rook("White"));
		chessBoard [7][1] = new Tile("White", new Knight("White"));
		chessBoard [7][2] = new Tile("Black", new Bishop("White"));
		chessBoard [7][3] = new Tile("White", new Queen("White"));
		chessBoard [7][4] = new Tile( "Black", new King ("White"));
		chessBoard [7][5] = new Tile( "White", new Bishop("White"));
		chessBoard [7][6]= new Tile( "Black", new Knight("White"));
		chessBoard [7][7]= new Tile("White", new Rook("White"));
		for (int y = 0; y<=7; y++) { //white pawns
			if (y % 2 == 0) {
				chessBoard [6][y] = new Tile("White", new Pawn("White"));
			}
			else {
				chessBoard [6][y] = new Tile( "Black", new Pawn("White"));
			}

			//all the other tiles 
			for (int j= 2; j<= 5; j++) {
				for (int k = 0; k<=7; k++) {
					if (j % 2 ==0) {
						if (k % 2 ==0) {
							chessBoard [j][k] = new Tile("White", null);
						}
						else {
							chessBoard [j][k] = new Tile("Black", null);
						}
					}
					else {
						if (k% 2 == 0) {
							chessBoard [j][k] = new Tile( "Black", null);
						}
						else {
							chessBoard [j][k] = new Tile("White", null);
						}
					}
				}
			}

		}

	}


	/**
	 * This is a getter method that will return the boolean that holds true if it is white's turn.
	 * @return true			if it is indeed white's turn, false otherwise (meaning it is black's turn). 
	 */
	public boolean isWhitesMove() {
		return this.whitesMove;
	}

	/**
	 * This method sets the boolean that holds true if it is white's turn to its inverse, symbolically switching players.
	 */
	public void switchPlayer() {
		whitesMove = !whitesMove;
	}
	
	/**
	 * This method prompts for a player's move, either black's or white's.
	 */
	public void promptPlayer() {
		if (isWhitesMove())
			System.out.print("White's move: ");
		else
			System.out.print("Black's move: ");
	}
	
	/**
	 * This method checks to see if the correct filerank input format is followed, and returns true if so.
	 * @param input		is the inputted string move from the player whose turn it is. 
	 * @return true 	if input is in the form "FileRank FileRank", where the first file (column) and rank (row) are the coordinates of the piece to be moved, and the second file and rank are the coordinates of where it should end up.
	 */
	public boolean checkInputFormat (String input) {

		String[] fileRanks = input.split(" ");


		if (fileRanks.length==1 && fileRanks[0].toLowerCase().equals("draw")) {
			if ((whitesMove && blackWantsDraw) || (!whitesMove && whiteWantsDraw)) {
				System.exit(0);

			}
		}	
		if (fileRanks.length==1 && fileRanks[0].toLowerCase().equals("resign")) {
			if (whitesMove) {
				System.out.println("Black wins");
				gameIsDone = true;
				System.exit(0);

			}
			else {
				System.out.println("White wins");
				gameIsDone=true;
				System.exit(0);
			}
		}

		if (fileRanks.length <2) {
			return false;
		}
		if (fileRanks[0].length() !=2 || fileRanks[1].length() != 2) {
			return false;
		}

		char a = fileRanks[0].charAt(0);
		char b =fileRanks[0].charAt(1);
		char c = fileRanks[1].charAt(0);
		char d = fileRanks[1].charAt(1);

		if (!(Character.isLetter(a) && Character.isLetter(c))) {
			return false;
		}
		if (!(Character.isDigit(b) && Character.isDigit(d))) {
			return false;
		}
		return true;

	}

	/**
	 * This method is meant to interpret the user input, i.e. whether or not the player wants to resign, or asks for a draw, etc, and will also check if the move being requested by a player is valid (whether a piece can move to a certain board location or not, depending on the current board layout).
	 * @param input		is the inputted string move from the player whose turn it is. 
	 * @return true 	if input is valid for either requesting a draw, resigning, executing a move/special move, or any other variation that would require a change in the board or one of the pieces, false if input is incorrect. 
	 */
	
	public boolean readInput (String input) {

		resetCastling();

		resetEnpassant(); //enpassant expires after one move

		if (whitesMove && whiteWantsDraw) { //draw only good for one move
			whiteWantsDraw=false;

		}
		if (!whitesMove && blackWantsDraw) { //draw only good for one move
			blackWantsDraw = false;

		}

		if (!checkInputFormat(input)) {
			return false;
		}

		String[] fileRanks = input.split(" ");
		int startRow= 8-Integer.parseInt(fileRanks[0].substring(1));
		int startColumn = (fileRanks[0].charAt(0)) -'a';
		int endRow = 8-Integer.parseInt(fileRanks[1].substring(1));
		int endColumn = (fileRanks[1].charAt(0))-'a';

		Piece startingPiece = chessBoard[startRow][startColumn].getOccupyingPiece();
		Piece endingPiece = chessBoard[endRow][endColumn].getOccupyingPiece();

		if (!checkValidInput(startRow,startColumn,endRow, endColumn, chessBoard) || !isValidMove(startRow,startColumn,endRow, endColumn, chessBoard)) {
			return false;
		}
		Piece starting= chessBoard[startRow][startColumn].getOccupyingPiece();
		Piece ending = chessBoard[endRow][endColumn].getOccupyingPiece();

		updateBoard(startRow, startColumn, endRow, endColumn, input);

		ArrayList<ArrayList<Integer>> whiteMoves = getValidWhiteMoves(chessBoard);
		ArrayList<ArrayList<Integer>> blackMoves = getValidBlackMoves(chessBoard);

		if (whiteInCheck(chessBoard)) { 
			if (whitesMove) {
				chessBoard[startRow][startColumn].setOccupyingPiece(starting);
				chessBoard[endRow][endColumn].setOccupyingPiece(ending);	
				return false;
			}
			dealWithWhiteCheck(input, whiteMoves, startRow, startColumn, endRow, endColumn);

		}

		if (blackInCheck(chessBoard)) {

			if (!whitesMove) {
				chessBoard[startRow][startColumn].setOccupyingPiece(starting);
				chessBoard[endRow][endColumn].setOccupyingPiece(ending);	
				return false;

			}
			dealWithBlackCheck(input, blackMoves, startRow, startColumn, endRow, endColumn);

		}

		if (whitesMove && blackCheckMate(input, blackMoves)) {
			printBoard();
			System.out.println("Stalemate");
			System.out.println("draw");
			System.exit(0);

		}
		if (!whitesMove && whiteCheckMate(input, whiteMoves)) {
			printBoard();
			System.out.println("Stalemate");
			System.out.println("draw");
			System.exit(0);
		}  
		checkForEnpassant(startRow, startColumn, endRow, endColumn, chessBoard);

		if (whitesMove && startRow == 1 && endRow == 0 && chessBoard[endRow][endColumn].getOccupyingPiece() != null && chessBoard[endRow][endColumn].getPieceType().equals("p")) {
			Pawn pawn = (Pawn) chessBoard[endRow][endColumn].getOccupyingPiece();
			pawn.checkForPromotion(input, chessBoard);
		}

		if (!whitesMove && startRow == 6 && endRow == 7 && chessBoard[endRow][endColumn].getOccupyingPiece() != null && chessBoard[endRow][endColumn].getPieceType().equals("p")) {
			Pawn pawn = (Pawn) chessBoard[endRow][endColumn].getOccupyingPiece();
			pawn.checkForPromotion(input, chessBoard);
		}

		startingPiece.setMoved();
		checkForDraw(input);
		switchPlayer();



		return true;
	}
	
	/**
	 * This method will reset the possibility for a player to perform 'castling' between a king and rook if it meets the specific requisites, detailed in the official chess rules. 
	 */

	public void resetCastling() { 
		for (int x=0; x<chessBoard.length; x++) {
			for (int y=0; y<chessBoard.length; y++) {
				Tile curr = chessBoard[x][y];
				if (curr.getOccupyingPiece() != null && curr.getPieceType().equals("K")) {
					King king = (King) chessBoard[x][y].getOccupyingPiece();
					king.leftCastling=false;
					king.rightCastling=false;
				}
			}
		}

	} 

	/**
	 * This method will check the board for pawns that can be captured via enpassant, and set the fields to true within the respective pawn objects if true. 
	 * @param startRow 		basic parameters for system to see current layout of board
	 * @param startColumn 
	 * @param endRow 
	 * @param endColumn
	 * @param chessboard	 
	 */

	public void checkForEnpassant(int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {
		if (Math.abs(startRow-endRow)==2 && board[endRow][endColumn].getPieceType().equals("p")){
			Pawn pawn = (Pawn) board[endRow][endColumn].getOccupyingPiece();
			pawn.setCanBeEnpassanted(true);
		}


	}

	
	/**
	 * This method will reset the pawn fields for being able to be enpassanted to false if the board sees that the next player made a move and did not capitalize on the enpassantable pawn. 
	 */
	public void resetEnpassant() {
		if (whitesMove) {
			for (int x=0; x<chessBoard.length; x++) {
				for (int y=0; y<chessBoard.length; y++) {
					Tile curr = chessBoard[x][y];
					if (curr.getOccupyingPiece() != null && curr.getPieceColor().equals("w") && curr.getPieceType().equals("p")) {
						Pawn pawn = (Pawn) chessBoard[x][y].getOccupyingPiece();
						pawn.setCanBeEnpassanted(false);
						pawn.setWillEnpassant(false);
					}


				}
			}
		}

		if (!whitesMove) {
			for (int x=0; x<chessBoard.length; x++) {
				for (int y=0; y<chessBoard.length; y++) {
					Tile curr = chessBoard[x][y];
					if (curr.getOccupyingPiece() != null && curr.getPieceColor().equals("w") && curr.getPieceType().equals("b")) {
						Pawn pawn = (Pawn) chessBoard[x][y].getOccupyingPiece();
						pawn.setCanBeEnpassanted(false);
						pawn.setWillEnpassant(false);

					}


				}
			}
		}
	}


	/**
	 * This method will check if the white player is in check or checkmate, and will call on the whiteCheckMate method to do so.
	 * @param input 		basic parameters for system to see state of board & to understand what moves the white player can make. 
	 * @param whiteMoves 
	 * @param startRow
	 * @param startColumn
	 * @param endRow 
	 * @param endColumn
	 * @param chessboard	
	 */
	
	public void dealWithWhiteCheck (String input, ArrayList<ArrayList<Integer>> whiteMoves, int startRow, int startColumn, int endRow, int endColumn) {


		if (whiteCheckMate(input, whiteMoves)) {
			System.out.println();
			printBoard();
			System.out.println("Checkmate");
			System.out.println("Black wins");
			gameIsDone=true;
			System.exit(0);
		}

		System.out.println("Check");
	}

	
	/**
	 * This method will check if the black player is in check or checkmate, and will call on the blackCheckMate method to do so.
	 * @param input 		basic parameters for system to see state of board & to understand what moves the black player can make. 
	 * @param whiteMoves 
	 * @param startRow
	 * @param startColumn
	 * @param endRow 
	 * @param endColumn
	 * @param chessboard	
	 */

	public void dealWithBlackCheck (String input, ArrayList<ArrayList<Integer>> blackMoves, int startRow, int startColumn, int endRow, int endColumn) {
		if (blackCheckMate(input, blackMoves)) {
			System.out.println();
			printBoard();
			gameIsDone=true;
			System.out.println("Checkmate");
			System.out.println("White wins");
			System.exit(0);
		}
		System.out.println("Check");



	}

	/**
	 * This method will check if the black player is in checkmate.
	 * @param input 		basic parameters for system to see state of board & to understand if the black player has lost. 
	 * @param blackMoves
	 * @return true 		if white player is in checkmate and has lost. 	
	 */

	public boolean blackCheckMate (String input, ArrayList<ArrayList<Integer>> blackMoves) {

		for (int x=0; x< blackMoves.size(); x++) {
			ArrayList<Integer> temp = blackMoves.get(x);
			Piece starting= chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece();
			Piece ending = chessBoard[temp.get(2)][temp.get(3)].getOccupyingPiece();
			Tile[][] tempBoard = chessBoard;
			updateBoard (temp.get(0), temp.get(1), temp.get(2), temp.get(3), input);

			if (blackInCheck(chessBoard)) {
				blackMoves.remove(x);
				x--;
			}

			else if (Math.abs(temp.get(1) - temp.get(3)) > 1 && chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece() != null
					&& chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece().equals("K")) {
				blackMoves.remove(x);
				x--;
			}
			chessBoard=tempBoard;

			chessBoard[temp.get(0)][temp.get(1)].setOccupyingPiece(starting);
			chessBoard[temp.get(2)][temp.get(3)].setOccupyingPiece(ending);

			if (temp.get(0)==0 && temp.get(1)==4 && temp.get(2)==0 && temp.get(3)==2) {
				chessBoard[0][0].setOccupyingPiece(new Rook ("Black"));
				chessBoard[0][3].setOccupyingPiece(null);

			} 

			if (temp.get(0)==4 && chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece() != null && chessBoard[temp.get(0)][temp.get(1)].getPieceType().equals("p")) {
				if (temp.get(1) != temp.get(3) && chessBoard[temp.get(2)][temp.get(3)].getOccupyingPiece()==null) {
					chessBoard[temp.get(1)][temp.get(3)].setOccupyingPiece(new Pawn("White"));


				}
			}


		}
		if (blackMoves.size()==0) {
			return true;
		}
		return false;
	}

	/**
	 * This method will check if the white player is in checkmate.
	 * @param input 		basic parameters for system to see state of board & to understand if the white player has lost. 
	 * @param whiteMoves
	 * @return true 		if white player is in checkmate and has lost. 	
	 */
	
	public boolean whiteCheckMate (String input, ArrayList<ArrayList<Integer>> whiteMoves) {

		for (int x=0; x< whiteMoves.size(); x++) {
			ArrayList<Integer> temp = whiteMoves.get(x);
			Piece starting= chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece();
			Piece ending = chessBoard[temp.get(2)][temp.get(3)].getOccupyingPiece();
			updateBoard(temp.get(0), temp.get(1), temp.get(2), temp.get(3), input);
			if (whiteInCheck(chessBoard) ) {
				whiteMoves.remove(x);
				x--;
			}
			else if (Math.abs(temp.get(1) - temp.get(3)) > 1 && chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece() != null
					&& chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece().equals("K")) {
				whiteMoves.remove(x);
				x--;
			}
			chessBoard[temp.get(0)][temp.get(1)].setOccupyingPiece(starting);
			chessBoard[temp.get(2)][temp.get(3)].setOccupyingPiece(ending);

			if (temp.get(0)==7 && temp.get(1)==4 && temp.get(2)==7 && temp.get(3)==2) {
				chessBoard[0][0].setOccupyingPiece(new Rook ("White"));
				chessBoard[0][3].setOccupyingPiece(null);

			}  

			if (temp.get(0)==3 && chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece() != null && chessBoard[temp.get(0)][temp.get(1)].getPieceType().equals("p")
					&& chessBoard[temp.get(0)][temp.get(1)].getPieceColor().equals("w")) {
				if (temp.get(1) != temp.get(3) && chessBoard[temp.get(2)][temp.get(3)].getOccupyingPiece()==null) {
					chessBoard[temp.get(1)][temp.get(3)].setOccupyingPiece(new Pawn("Black"));

				}
			}
			if (temp.get(0)==4 && chessBoard[temp.get(0)][temp.get(1)].getOccupyingPiece() != null && chessBoard[temp.get(0)][temp.get(1)].getPieceType().equals("p")) {
				if (temp.get(1) != temp.get(3) && chessBoard[temp.get(2)][temp.get(3)].getOccupyingPiece()==null) {
					chessBoard[4][temp.get(3)].setOccupyingPiece(new Pawn("Black"));


				}
			} 
		}


		if (whiteMoves.size()==0) {
			return true;
		}
		return false;
	}

	/**
	 * This method finds the location of the white king.
	 * @param board		so that it can search for the king.
	 * @return location		of white king in terms of the board matrix.
	 */

	public ArrayList<Integer> whiteKingAddress (Tile[][] board) {
		ArrayList<Integer> whiteAddress = new ArrayList<Integer>();
		int kingsRow=-1;
		int kingsColumn=-1;
		for (int x=0; x< board.length; x++) {
			for (int y=0; y<board.length; y++) {
				if (board[x][y].getOccupyingPiece() != null && board[x][y].getPieceType().equals("K") && board[x][y].getPieceColor().equals("w")) {
					kingsRow = x;
					kingsColumn = y;
					whiteAddress.add(x); whiteAddress.add(y);
				}
			}
		}
		return whiteAddress;
	}

	/**
	 * This method finds the location of the black king.
	 * @param board		so that it can search for the king.
	 * @return location		of black king in terms of the board matrix.
	 */
	
	public ArrayList<Integer> blackKingAddress (Tile[][] board) {
		ArrayList<Integer> blackAddress = new ArrayList<Integer>();
		int kingsRow=-1;
		int kingsColumn=-1;
		for (int x=0; x< board.length; x++) {
			for (int y=0; y<board.length; y++) {
				if (board[x][y].getOccupyingPiece() != null && board[x][y].getPieceType().equals("K") && board[x][y].getPieceColor().equals("b")) {
					kingsRow = x;
					kingsColumn = y;
					blackAddress.add(x); blackAddress.add(y);
				}
			}
		}
		return blackAddress;
	}

	/**
	 * This method will see if the move made by the player puts the king in check.
	 * @param startRow basic parameters for system to see state of board & to understand what moves the player can make to avoid putting the king in check. 
	 * @param startColumn
	 * @param endRow 
	 * @param endColumn
	 * @param chessboard	
	 * @return true if this move is able to be made. 
	 */
	
	public boolean hypothValidInput (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) { //just checking to see if they're valid moves on the board
		Tile startingPosition = board[startRow][startColumn];
		Tile endingPosition = board[endRow][endColumn];


		if (startRow > 7 || startColumn > 7 || endRow > 7 || endColumn > 7 || startRow < 0 || startColumn < 0 || endRow < 0 || endColumn < 0 ) {
			return false;
		}

		if (startingPosition.getOccupyingPiece() == null) {
			return false;
		}


		if (endingPosition.getOccupyingPiece() != null && startingPosition.getPieceColor().equals(endingPosition.getPieceColor())) { //trying to attack your own piece
			if (startingPosition.getPieceType().equals("K") && endingPosition.getPieceType().equals("R")) { //exception: castling
				return true;
			}
			return false;

		}

		return true; 
	}

	/**
	 * This method will see if the move made by the player is on a valid tile that can be moved to and occupied by the incoming piece. 
	 * @param startRow basic parameters for system to see state of board & to understand what moves the player can make. 
	 * @param startColumn
	 * @param endRow 
	 * @param endColumn
	 * @param chessboard	
	 * @return true if this move is able to be made. 
	 */
	
	public boolean checkValidInput (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) { //just checking to see if they're valid moves on the board
		Tile startingPosition = board[startRow][startColumn];
		Tile endingPosition = board[endRow][endColumn];


		if (startRow > 7 || startColumn > 7 || endRow > 7 || endColumn > 7 || startRow < 0 || startColumn < 0 || endRow < 0 || endColumn < 0 ) {
			return false;
		}

		if (startingPosition.getOccupyingPiece() == null) {
			return false;
		}


		if ((isWhitesMove() && startingPosition.getPieceColor().equals("b"))  || (!isWhitesMove() && startingPosition.getPieceColor().equals("w"))) { //selecting the wrong color piece
			return false; 
		}

		if (endingPosition.getOccupyingPiece() != null && startingPosition.getPieceColor().equals(endingPosition.getPieceColor())) { //trying to attack your own piece
			if (startingPosition.getPieceType().equals("K") && endingPosition.getPieceType().equals("R")) { //exception: castling
				return true;
			}
			return false;

		}

		return true; 
	}
	
	/**
	 * This method will call the native update methods in each respective Piece class in order to update the overall board
	 * @param startRow basic parameters for system to see state of board & to understand what moves the player can make. 
	 * @param startColumn
	 * @param endRow 
	 * @param endColumn
	 * @param input	
	 * @return true if this move is able to be made and board was able to be updated, false if otherwise.  
	 */
	
	public boolean updateBoard (int startRow, int startColumn, int endRow, int endColumn, String input) {
		Tile startingPosition = chessBoard[startRow][startColumn];



		Pawn pawn = new Pawn("white");
		Rook rook = new Rook("white");
		Bishop bishop = new Bishop("white");
		Knight knight = new Knight("white");
		Queen queen = new Queen("white");
		King king = new King("white");


		if (startingPosition.getPieceType().equals("p")){
			chessBoard = pawn.updateBoard(startRow, startColumn, endRow, endColumn, chessBoard);
			return true;

		}
		if (startingPosition.getPieceType().equals("R"))  {
			chessBoard = rook.updateBoard(startRow, startColumn, endRow, endColumn, chessBoard);
			return true;

		}
		if (startingPosition.getPieceType().equals("B")) {
			chessBoard = bishop.updateBoard(startRow, startColumn, endRow, endColumn, chessBoard);
			return true;

		}
		if (startingPosition.getPieceType().equals("N")) {
			chessBoard = knight.updateBoard(startRow, startColumn, endRow, endColumn, chessBoard);
			return true;

		}
		if (startingPosition.getPieceType().equals("Q")) {
			chessBoard = queen.updateBoard(startRow, startColumn, endRow, endColumn, chessBoard);
			return true;

		}
		if (startingPosition.getPieceType().equals("K")) {
			chessBoard = king.updateBoard(startRow, startColumn, endRow, endColumn, chessBoard);
			return true;

		}
		return false;

	}

	/**
	 * This method will check if the move involving the piece is valid, meaning the piece can move in such a fashion. 
	 * @param startRow basic parameters for system to see state of board & to understand what is valid. 
	 * @param startColumn
	 * @param endRow 
	 * @param endColumn
	 * @param board	
	 * @return true if this move is able to be made and piece can move in such a fashion, false if otherwise.  
	 */
	
	public boolean isValidMove (int startRow, int startColumn, int endRow, int endColumn, Tile[][] board) {

		Tile startingPosition = board[startRow][startColumn];

		//	Tile[][] tempBoard;

		Pawn pawn = new Pawn("white");
		Rook rook = new Rook("white");
		Bishop bishop = new Bishop("white");
		Knight knight = new Knight("white");
		Queen queen = new Queen("white");
		King king = new King("white");


		if (startingPosition.getPieceType().equals("p") && pawn.isValidMove(startRow, startColumn, endRow, endColumn, board)) {
			return true;

		}
		if (startingPosition.getPieceType().equals("R") && rook.isValidMove(startRow, startColumn, endRow, endColumn, board)) {
			return true;


		}
		if (startingPosition.getPieceType().equals("B") && bishop.isValidMove(startRow, startColumn, endRow, endColumn, board)) {
			return true;

		}
		if (startingPosition.getPieceType().equals("N") && knight.isValidMove(startRow, startColumn, endRow, endColumn, board)) {
			return true;


		}
		if (startingPosition.getPieceType().equals("Q") && queen.isValidMove(startRow, startColumn, endRow, endColumn,board)) {
			return true;


		}
		if (startingPosition.getPieceType().equals("K") && king.isValidMove(startRow, startColumn, endRow, endColumn, board)) {
			return true;


		}
		return false;


	}

	/**
	 * This method finds all the moves any white piece can make at its turn during any portion of the game. This will help in eventually determining if a player is in check/checkmate or can evade it. 
	 * @param board		in order to see current state of board and traverse across it
	 * @return whitemovelist	which will detail all the moves that any white piece can make at any moment in the game
	 */
	public ArrayList<ArrayList<Integer>> getValidWhiteMoves(Tile[][]board) {
		ArrayList<ArrayList<Integer>> whiteMoves = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for (int w = 0; w< board.length; w++) {
			for (int x = 0; x<board[w].length; x++) {
				if (board[w][x].getOccupyingPiece() != null && board[w][x].getPieceColor().equals("w")) {
					temp = findPieceMovesWhite(w, x, board);
					whiteMoves.addAll(temp);
				}
			}
		}

		return whiteMoves;
	} 

	/**
	 * This method finds all the moves any black piece can make at its turn during any portion of the game. This will help in eventually determining if a player is in check/checkmate or can evade it. 
	 * @param board		in order to see current state of board and traverse across it
	 * @return blackmovelist	which will detail all the moves that any black piece can make at any moment in the game
	 */
	
	public ArrayList<ArrayList<Integer>> getValidBlackMoves(Tile[][]board) {
		ArrayList<ArrayList<Integer>> blackMoves = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for (int w = 0; w< board.length; w++) {
			for (int x = 0; x<board[w].length; x++) {
				if (board[w][x].getOccupyingPiece() != null && board[w][x].getPieceColor().equals("b")) {
					temp = findPieceMovesBlack(w, x, board);
					blackMoves.addAll(temp);

				}
			}
		}

		return blackMoves;
	} 

	/**
	 * This method finds all the valid moves any white piece can make that doesn't put its king in check. 
	 * @param w		in order to see current state of board and traverse across movable pieces
	 * @param x
	 * @param board
	 * @return validwhitemovelist	which will detail all the moves that any white piece can make that are valid and will complete their turn.
	 */
	
	public ArrayList<ArrayList<Integer>> findPieceMovesWhite(int w, int x, Tile[][] board) { 
		ArrayList<ArrayList<Integer>> pieceMoves = new ArrayList<ArrayList<Integer>>();
		for (int y = 0; y< board.length; y++) {
			for (int z= 0; z<board[y].length; z++) {
				if (hypothValidInput(w,x,y,z, board) && isValidMove(w,x,y,z, board)) {
					ArrayList<Integer> temp = new ArrayList<Integer>();
					temp.add(w); temp.add(x); temp.add(y); temp.add(z);
					pieceMoves.add(temp);	
				}
			}

		}


		return pieceMoves;
	}

	/**
	 * This method finds all the valid moves any black piece can make that doesn't put its king in check. 
	 * @param w		in order to see current state of board and traverse across movable pieces
	 * @param x
	 * @param board
	 * @return validblackmovelist	which will detail all the moves that any black piece can make that are valid and will complete their turn.
	 */
	
	public ArrayList<ArrayList<Integer>> findPieceMovesBlack(int w, int x, Tile[][] board) { 
		ArrayList<ArrayList<Integer>> pieceMoves = new ArrayList<ArrayList<Integer>>();
		for (int y = 0; y< board.length; y++) {
			for (int z= 0; z<board[y].length; z++) {
				if (hypothValidInput(w,x,y,z, board) && isValidMove(w,x,y,z, board)) {
					ArrayList<Integer> temp = new ArrayList<Integer>();
					temp.add(w); temp.add(x); temp.add(y); temp.add(z);
					pieceMoves.add(temp);	
				}
			}

		}


		return pieceMoves;
	}


	/**
	 * This method checks if the white player is in check.
	 * @param board		for the system to understand the current layout of the board and make the appropriate conclusions.
	 * @return true		if white player is in check. 
	 */

	public boolean whiteInCheck (Tile[][] board) { //returns true if white is in check
		int kingsRow=-1;
		int kingsColumn=-1;
		for (int x=0; x< board.length; x++) {
			for (int y=0; y<board.length; y++) {
				if (board[x][y].getOccupyingPiece() != null && board[x][y].getPieceType().equals("K") && board[x][y].getPieceColor().equals("w")) {
					kingsRow = x;
					kingsColumn = y;	
					break;
				}
			}
		}
		ArrayList<ArrayList<Integer>> blackMoves = getValidBlackMoves(board);
		for (int x=0; x<blackMoves.size(); x++) {
			ArrayList<Integer> temp = blackMoves.get(x);

			if (temp.get(2) == kingsRow && temp.get(3) == kingsColumn) {
				return true;
			}

		}
		return false; 
	} 

	/**
	 * This method checks if the black player is in check.
	 * @param board		for the system to understand the current layout of the board and make the appropriate conclusions.
	 * @return true		if black player is in check. 
	 */
	
	public boolean blackInCheck (Tile[][] board) { //returns true if black is in check
		int kingsRow=-1;
		int kingsColumn=-1;
		for (int x=0; x< board.length; x++) {
			for (int y=0; y<board.length; y++) {
				if (board[x][y].getOccupyingPiece() != null && board[x][y].getPieceType().equals("K") && board[x][y].getPieceColor().equals("b")) {
					kingsRow = x;
					kingsColumn = y;
					break;
				}
			}
		}

		ArrayList<ArrayList<Integer>> whiteMoves = getValidWhiteMoves(board);
		for (int x=0; x<whiteMoves.size(); x++) {
			ArrayList<Integer> temp = whiteMoves.get(x);

			if (temp.get(2) == kingsRow && temp.get(3) == kingsColumn) {
				return true;
			}

		}
		return false; 

	}
	
	/**
	 * This method checks for a draw, and prints out draw in the case that there is.
	 * @param input		incase one player asks for a draw, and the other agrees. 
	 */

	public void checkForDraw(String input) {
		String[] fileRanks = input.split(" ");
		if (fileRanks.length >2 && fileRanks[2].equals("draw?")) {
			if (whitesMove) {
				whiteWantsDraw = true;
			}
			else {
				blackWantsDraw=true;
			}
		}

	}

	/**
	 * This method prints the chess board.
	 */
	
	public void printBoard() {
		int count =8;
		for (int x=0; x< chessBoard.length; x++) {
			for (int y=0; y<chessBoard[x].length; y++) {
				System.out.print(chessBoard[x][y] + " ");
			}
			System.out.print(count);
			count--;
			System.out.println();

		}
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
	}
}
