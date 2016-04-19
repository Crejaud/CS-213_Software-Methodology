package model;

import java.util.Scanner;

/**
 * @authors Julia Sutula and Corentin Rejaud
 *
 * Class that represents instances of a Pawn in the game of chess
 */

public class Pawn extends Piece {

	private boolean enpassant;
	private boolean willEnpassant;
	
	public Pawn(String color) {
		super(color);
		type = "pawn";
		enpassant = true;
		willEnpassant = false;
		// TODO Auto-generated constructor stub
	}

	/**
	 * isValidMove()
	 * @param input
	 * @param board
	 * @return boolean
	 * 
	 * Returns boolean indicating whether the move indicated for this Pawn is valid
	 */
	@Override
	public boolean isValidMove(String input, Square[][] board) {
		// TODO Auto-generated method stub
		String[] args = input.split(" ");
		char initFile = args[0].charAt(0);
		char initRank = args[0].charAt(1);
		char finalFile = args[1].charAt(0);
		char finalRank = args[1].charAt(1);
		
		
		
		
		
		willEnpassant = false;
		
		// If pawn is going straight
		if (initFile == finalFile) {
			if (isWhite && initRank+1 == finalRank && board[finalFile-'a'][finalRank-49].getPiece() == null){
				enpassant = false;
				return true;
			}
			
			// is white and it's the first turn, then can move up to 2 spots
			if (isWhite && initRank == '2' && initRank+2 == finalRank && board[finalFile-'a'][finalRank-49].getPiece() == null){
			//	System.out.println("Is now enpassant eligible");
				enpassant = true;
				return true;
			}
			
			if (!isWhite && initRank-1 == finalRank && board[finalFile-'a'][finalRank-49].getPiece() == null)
			{
				enpassant = false;
				return true;
			}
			
			// is black and it's the first turn, then can move up to 2 spots
			if (!isWhite && initRank == '7' && initRank-2 == finalRank && board[finalFile-'a'][finalRank-49].getPiece() == null){
				//System.out.println("Is now enpassant eligible");
				enpassant = true;
				return true;
			}
		}
		
		
		//If pawn is going diagonal into spot of opponent
		if (isWhite && initRank+1 == finalRank && initFile+1 == finalFile && board[initFile-'a'+1][initRank-48].getPiece() != null && !board[initFile-'a'+1][initRank-48].getPiece().isWhite())
		{
			enpassant = false;
			return true;
		}
		if (isWhite && initRank+1 == finalRank && initFile-1 == finalFile && board[initFile-'a'-1][initRank-48].getPiece() != null && !board[initFile-'a'-1][initRank-48].getPiece().isWhite())
		{
			enpassant = false;
			return true;
		}
		
		if (!isWhite && initRank-1 == finalRank && initFile+1 == finalFile && board[initFile-'a'+1][initRank-50].getPiece() != null && board[initFile-'a'+1][initRank-50].getPiece().isWhite())
		{
			enpassant = false;
			return true;
		}
		if (!isWhite && initRank-1 == finalRank && initFile-1 == finalFile && board[initFile-'a'-1][initRank-50].getPiece() != null && board[initFile-'a'-1][initRank-50].getPiece().isWhite())
		{
			enpassant = false;
			return true;
		}
		
		//If pawn is going diagonal for 
		if (isWhite && initRank == '5' && finalRank =='6' && initFile+1 == finalFile && board[initFile-'a'+1][initRank-49].getPiece() != null && !board[initFile-'a'+1][initRank-49].getPiece().isWhite() && canEnpassant(initFile-'a'+1, initRank-49,board) && board[finalFile-'a'][finalRank-49].getPiece() == null)
		{
			enpassant = false;
			willEnpassant = true;
			return true;
		}
		if (isWhite && initRank == '5' && finalRank =='6' && initFile-1 == finalFile && board[initFile-'a'-1][initRank-49].getPiece() != null && !board[initFile-'a'-1][initRank-49].getPiece().isWhite() && canEnpassant(initFile-'a'-1, initRank-49,board) && board[finalFile-'a'][finalRank-49].getPiece() == null)
		{
			enpassant = false;
			willEnpassant = true;
			return true;
		}
		if (!isWhite && initRank == '4' && finalRank =='3'&& initFile+1 == finalFile && board[initFile-'a'+1][initRank-49].getPiece() != null && board[initFile-'a'+1][initRank-49].getPiece().isWhite() && canEnpassant(initFile-'a'+1, initRank-49,board) && board[finalFile-'a'][finalRank-49].getPiece() == null)
		{
			enpassant = false;
			willEnpassant = true;
			return true;
		}
		if (!isWhite && initRank == '4' && finalRank =='3'&& initFile-1 == finalFile && board[initFile-'a'-1][initRank-49].getPiece() != null && board[initFile-'a'-1][initRank-49].getPiece().isWhite() && canEnpassant(initFile-'a'-1, initRank-49, board) && board[finalFile-'a'][finalRank-49].getPiece() == null)
		{
			
			enpassant = false;
			willEnpassant = true;
			return true;
		}
		
		return false;
	}

	/**
	 * move()
	 * @param input
	 * @param board
	 * @return Square[][]
	 * 
	 * Returns updated board after moving pawn accordingly
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
		
		//if pawn is going diagonal for enpassant remove piece it is passing
		if (initFile != finalFile && willEnpassant) { 
			if (isWhite && initRank+1 == finalRank && initFile+1 == finalFile)
				board[initFile-'a'+1][initRank-49].setPiece(null);
			if (isWhite && initRank+1 == finalRank && initFile-1 == finalFile)
				board[initFile-'a'-1][initRank-49].setPiece(null);
			
			if (!isWhite && initRank-1 == finalRank && initFile+1 == finalFile)
				board[initFile-'a'+1][initRank-49].setPiece(null);
			if (!isWhite && initRank-1 == finalRank && initFile-1 == finalFile)
				board[initFile-'a'-1][initRank-49].setPiece(null);
		}
				
		// check for promotion
		if (isWhite && finalRank == '8')
			promotion(input, finalFile, finalRank, board, "white");
		if (!isWhite && finalRank == '1')
			promotion(input, finalFile, finalRank, board, "black");
		
		moved(); // set hasMoved to true
		
		return board;
	}
	
	/**
	 * promotion
	 * Changes pawn into queen, knight, rook or bishop
	 * @param input
	 * @param finalFile
	 * @param finalRank
	 * @param board
	 * @param pieceColor
	 */
	public void promotion(String input, char finalFile, char finalRank, Square[][] board, String pieceColor) {
		//Scanner scan = new Scanner(System.in);
		//String input;
		//System.out.print("Choose promotion [Q, N, R, B]: ");
		String[] args = input.split(" ");
		if (args.length == 3) {
			switch(args[2])
			{
				case "Q":
					board[finalFile-'a'][finalRank-49].setPiece(new Queen(pieceColor));
					return;
				case "N":
					board[finalFile-'a'][finalRank-49].setPiece(new Knight(pieceColor));
					return;
				case "R":
					board[finalFile-'a'][finalRank-49].setPiece(new Rook(pieceColor));
					return;
				case "B":
					board[finalFile-'a'][finalRank-49].setPiece(new Bishop(pieceColor));
					return;
			}
		}
		
		// if there is no third argument, create a Queen
		board[finalFile-'a'][finalRank-49].setPiece(new Queen(pieceColor));
	}
	
	/**
	 * canEnpassant
	 * Indicates whether pawn can perform enpassant
	 * @param file
	 * @param rank
	 * @param board
	 * @return boolean
	 */
	public boolean canEnpassant(int file, int rank, Square[][] board)
	{
		boolean wouldBeUnderAttack = false;
		boolean isWhite = board[file][rank].getPiece().isWhite();

		//Check for different locations to see if piece would have been under attack if it only moved one space up instead of two
		if(isWhite && rank!=0 && file!=7 && board[file + 1][rank].getPiece()!= null && !board[file + 1][rank].getPiece().isWhite()){
			wouldBeUnderAttack = true;
		}
		else if(isWhite && rank!=0 && file!=0 && board[file - 1][rank].getPiece()!= null && !board[file -1][rank].getPiece().isWhite()){
			wouldBeUnderAttack = true;
		}
		else if(!isWhite && rank!=7 && file!=7 && board[file + 1][rank].getPiece()!= null && board[file+1][rank].getPiece().isWhite()){
			wouldBeUnderAttack = true;
		}
		else if(!isWhite && rank!=7 && file!=0 &&board[file - 1][rank].getPiece()!= null && board[file-1][rank].getPiece().isWhite()){
			wouldBeUnderAttack = true;
		}

		return board[file][rank].getPiece().getEnpassant() && wouldBeUnderAttack;
	}
	
	
	/**
	 * canEnpassant
	 * Returns enpassant indicating whether piece is eligible to be captured  by enpassant
	 * @return boolean
	 */
	public boolean getEnpassant()
	{
		return enpassant;
	}
}
