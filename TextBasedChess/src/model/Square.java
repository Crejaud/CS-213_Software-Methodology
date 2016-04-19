/**
 * 
 */
package model;
/**
 * @authors Julia Sutula and Corentin Rejaud
 *
 *Square class represents a spot on the board, which has a color and an occupying piece
 */

public class Square implements Cloneable {

	
	private Piece occupyingPiece;
	private boolean isBlack;
	
	public Square(String color) {
		this(null, color);
	}
	
	public Square(Piece piece, String color) {
		occupyingPiece = piece;
		isBlack = color.equals("black");
	}
	
	/**
	 * setPiece()
	 * @param Piece
	 * @return void
	 * 
	 * Sets the occupying piece of the square to the passed parameter
	 */
	public void setPiece(Piece piece) {
		occupyingPiece = piece;
	}

	/**
	 * getPiece()
	 * @param none
	 * @return Piece
	 * 
	 * Returns piece occupying the instance of the square on which this method is invoked
	 */
	public Piece getPiece()
	{
		return occupyingPiece;
	}
	
	/**
	 * getPieceType()
	 * @param none
	 * @return String
	 * 
	 * Returns class type of the piece occupying the instance of the square on which this method is invoked
	 */
	public String getPieceType()
	{
		switch(occupyingPiece.type)
		{
			case "rook":
				return "R";
			case "knight":
				return "N";
			case "bishop":
				return "B";
			case "pawn":
				return "p";
			case "king":
				return "K";
			case "queen":
				return "Q";
			default:
				return "";
		}
	}
	
	/**
	 * getPieceColor()
	 * @param none
	 * @return String
	 * 
	 * Returns color of piece occupying the instance of the square on which this method is invoked
	 */
	public String getPieceColor()
	{
		if(occupyingPiece.isWhite)
			return "w";
		else
			return "b";
	}
	
	/**
	 * isSquareBlack()
	 * @param none
	 * @return boolean
	 * 
	 * Returns true if square is black
	 */
	public boolean isSquareBlack()
	{
		return isBlack;
	}
	
	/**
	 * toString()
	 * @param none
	 * @return String
	 * 
	 * Returns string of piece color and type of piece occupying the square
	 */	
	public String toString() {
		return getPieceColor() + getPieceType();
	}
	
	public Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}
}
