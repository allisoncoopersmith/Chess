/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */

package chess;

/**
 * This class models a Tile Object, or the squares of the chessboard.
 */
public class Tile {

	/**
	 * This variable holds the current fileRank of the piece.
	 */
	public String fileRank;
	/**
	 * This variable holds the current object that is currently on the tile.
	 */
	public Piece occupyingPiece;
	/**
	 * This variable holds true if the tile is black.
	 */
	public boolean tileIsBlack;

	/**
	 * This is the constructor for the Tile piece. It accepts either white or black, any piece that is or should be on it, and returns an instance of Tile object. 
	 * @param color		accepts either "white" or "black" 
	 * @param piece		whichever piece is occupying it
	 */
	public Tile (String color, Piece piece) {
		this.occupyingPiece = piece;
		this.tileIsBlack = (color != null && color.equals("Black"));
	}
	


	/**
	 * This method gets the current piece that is occupying this tile.
	 * @return piece	a string that denotes which specific piece is on it, null if no piece occupies it.
	 */
	public String getPieceType () {
		if (occupyingPiece == null) {
			return null;
		}
		if (occupyingPiece.type.equals("Bishop")){
			return "B";
		}
		if (occupyingPiece.type.equals("King")){
			return "K";
		}
		if (occupyingPiece.type.equals("Knight")){
			return "N";
		}
		if (occupyingPiece.type.equals("Pawn")) {
			return "p";
		}
		if (occupyingPiece.type.equals("Queen")){
			return "Q";
		}
		return "R"; //rook
	}

	/**
	 * This method gets the tile's fileRank.
	 * @return fileRank		which denotes the location on the chessboard of the tile. 
	 */
	public String getFileRank () {
		return fileRank;
	}

	/**
	 * This method gets the tile's color.
	 * @return "b"		which denotes black, if the tile is black. Returns "w" if the tile is white.
	 */
	public String getPieceColor () {
		if (occupyingPiece.checkColor()) {
			return "w";
		}
		return "b";

	}

	/**
	 * This method converts the tile to a string by printing out "  " if it is a white tile or "##" if it is black.
	 */
	public String toString() {
		if (fileRank != null) {
			return fileRank;
		}
		if (occupyingPiece ==null) {
			if (tileIsBlack) {
				return "##";
			}
			return "  ";
		}
		return getPieceColor() + getPieceType();


	}
	/**
	 * This method sets the tile's occupying piece variable to whichever piece has moved onto it.
	 * @param p		that moved onto this tile.
	 */
	public void setOccupyingPiece(Piece p) {
		this.occupyingPiece = p;
	}

	/**
	 * This method returns the type of chesspiece that is occupying this tile.
	 * @return piece type that is occupying tile.
	 */
	public Piece getOccupyingPiece() {
		return this.occupyingPiece;
		
	}
}
