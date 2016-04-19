package model;

/**
 * @authors Julia Sutula and Corentin Rejaud
 *
 */

public class Knight extends Piece {

	public Knight(String color) {
		super(color);
		type = "knight";
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * isValidMove()
	 * @param String
	 * @param Square[][]
	 * @return boolean
	 * @see chess.Piece#isValidMove(java.lang.String, java.lang.String)
	 * 
	 * Returns boolean indicating whether the move indicated for this Knight is valid
	 *
	 * 
	 */
	@Override
	public boolean isValidMove(String input, Square[][] board) {
		// TODO Auto-generated method stub
		String[] args = input.split(" ");
		char initFile = args[0].charAt(0);
		char initRank = args[0].charAt(1);
		char finalFile = args[1].charAt(0);
		char finalRank = args[1].charAt(1);
		
		if (Math.abs(initFile - finalFile) == 1 && Math.abs(initRank - finalRank) == 2 && board[finalFile-'a'][finalRank-49].getPiece() == null)
			return true;
		else if (Math.abs(initFile - finalFile) == 2 && Math.abs(initRank - finalRank) == 1 && board[finalFile-'a'][finalRank-49].getPiece() == null)
			return true;
		else if (Math.abs(initFile - finalFile) == 1 && Math.abs(initRank - finalRank) == 2 
				&& board[finalFile-'a'][finalRank-49].getPiece().isWhite() != board[initFile-'a'][initRank-49].getPiece().isWhite())
			return true;
		else if (Math.abs(initFile - finalFile) == 2 && Math.abs(initRank - finalRank) == 1 
				&& board[finalFile-'a'][finalRank-49].getPiece().isWhite() != board[initFile-'a'][initRank-49].getPiece().isWhite())
			return true;
		else
			return false;
	}

	/**
	 * 
	 * move()
	 * @param input
	 * @param board
	 * @return square
	 * @see chess.Piece#move
	 * 
	 * Returns updated board after moving piece accodring to input
	 *
	 * 
	 */
	@Override
	public Square[][] move(String input, Square[][] board) {
		// TODO Auto-generated method stub
		String[] args = input.split(" ");
		char initFile = args[0].charAt(0);
		char initRank = args[0].charAt(1);
		char finalFile = args[1].charAt(0);
		char finalRank = args[1].charAt(1);
		
		Piece initPiece = board[initFile-'a'][initRank-49].getPiece();
		
		board[finalFile-'a'][finalRank-49].setPiece(initPiece);
		board[initFile-'a'][initRank-49].setPiece(null);
		
		moved(); // set hasMoved to true
		
		return board;
	}
	
}
