/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */


package chess;


/**
 * This class models the abstract Piece object, holding inherent characteristics that all pieces in a chess game should have. All other chess piece classes should be subclasses of this class.
 */

public abstract class Piece {
	/**
	 * This variable holds true if the piece is white.
	 */
	boolean isWhite;
	/**
	 * This variable holds the type of the chess piece.
	 */
	String type;
	/**
	 * This variable holds true if the piece has been moved.
	 */
	boolean hasMoved;
	
	/**
	 * This is the constructor for the Piece object. It accepts either white or black and returns an instance of the Piece object. 
	 * @param color		accepts either "white" or "black" 
	 */
	
	public Piece (String color) {
		this.isWhite = color.toLowerCase().equals("white") || color.toLowerCase().equals("w"); //true if white, false if black
		this.type=null;
		this.hasMoved=false;
		
	}
	
	/**
	 * This is a getter method that will return the color of this Piece object. 
	 * @return color		returns true if white, false if black.  
	 */
	public boolean checkColor() { //true if white, false if black kaushal
		return this.isWhite;
	}
	/**
	 * This is a getter method that will return if the piece has moved or not. 
	 * @return true		if piece has moved.  
	 */
	
	public boolean getMoved() {
		return this.hasMoved;
	}
	
	/**
	 * This is a setter method that will set the value of the hasMoved variable to true when invoked. 
	 */
	public void setMoved() { //update the moved value on a piece
		this.hasMoved = true;
	}

}
