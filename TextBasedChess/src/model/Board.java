/**
 * 
 */
package model;

import java.util.Arrays;

/**
 * @authors Julia Sutula and Corentin Rejaud
 *
 */
public class Board {
	
	private Square[][] board;
	private boolean isDone;
	private boolean isStalemate;
	private boolean isResign;
	private boolean isWhiteWinner;
	private boolean isWhitesMove;
	private boolean isWhiteInCheck;
	private boolean isBlackInCheck;
	private boolean isInCheck;
	private boolean isDrawAvailable;
	/**
	 * 
	 */
	
	public Board() {
		
		board = new Square[8][8];
		
		/*Initialize rank 8*/
		/*board[0][0] = new Square(new Rook("black"),"white");
		board[0][7] = new Square(new Rook("black"),"black");
		board[0][1] = new Square(new Knight("black"),"black");
		board[0][6] = new Square(new Knight("black"),"white");
		board[0][2] = new Square(new Bishop("black"),"white");
		board[0][5] = new Square(new Bishop("black"),"black");
		board[0][3] = new Square(new Queen("black"),"black");
		board[0][4] = new Square(new King("black"),"white");
		*/
		board[0][7] = new Square(new Rook("black"),"black");
		board[7][7] = new Square(new Rook("black"),"white");
		board[1][7] = new Square(new Knight("black"),"white");
		board[6][7] = new Square(new Knight("black"),"black");
		board[2][7] = new Square(new Bishop("black"),"black");
		board[5][7] = new Square(new Bishop("black"),"white");
		board[3][7] = new Square(new Queen("black"),"white");
		board[4][7] = new Square(new King("black"),"black");
		/*Initialize Rank 7*/
		int file, rank;
		/*rank = 1; 
		for(file = 0; file < 8; file++)
		{
			if (file%2 == 0)
				board[rank][file] = new Square(new Pawn("black"),"black");
			else
				board[rank][file] = new Square(new Pawn("black"),"white");	
		}*/
		rank = 6; 
		for(file = 0; file < 8; file++)
		{
			if (file%2 == 0)
				board[file][rank] = new Square(new Pawn("black"),"white");
			else
				board[file][rank] = new Square(new Pawn("black"),"black");	
		}
		
		/*Initialize Ranks 3-6*/
		/*for(rank = 2; rank<=5; rank++){
			for(file = 0; file<8; file++){
				if ((file%2 == 0 && rank%2 == 0) || (file%2 != 0 && rank%2 != 0))
					board[rank][file] = new Square("white");
				else
					board[rank][file] = new Square("black");
			}
		}*/
		for(rank = 5; rank>=2; rank--){
			for(file = 0; file<8; file++){
				if ((file%2 == 0 && rank%2 == 0) || (file%2 != 0 && rank%2 != 0))
					board[file][rank] = new Square("white");
				else
					board[file][rank] = new Square("black");
			}
		}
		/*Initialize Rank 2*/
		/*rank = 6; 
		for(file = 0; file < 8; file++)
		{
			if (file%2 == 0)
				board[rank][file] = new Square(new Pawn("white"),"white");
			else
				board[rank][file] = new Square(new Pawn("white"),"black");	
		}*/
		rank = 1; 
		for(file = 0; file < 8; file++)
		{
			if (file%2 == 0)
				board[file][rank] = new Square(new Pawn("white"),"black");
			else
				board[file][rank] = new Square(new Pawn("white"),"white");	
		}
		
		/*Initialize Rank 1*/
		/*board[7][0] = new Square(new Rook("white"),"black");
		board[7][7] = new Square(new Rook("white"),"white");
		board[7][1] = new Square(new Knight("white"),"white");
		board[7][6] = new Square(new Knight("white"),"black");
		board[7][2] = new Square(new Bishop("white"),"black");
		board[7][5] = new Square(new Bishop("white"),"white");
		board[7][3] = new Square(new Queen("white"),"white");
		board[7][4] = new Square(new King("white"),"black");
		*/
		board[0][0] = new Square(new Rook("white"),"white");
		board[7][0] = new Square(new Rook("white"),"black");
		board[1][0] = new Square(new Knight("white"),"black");
		board[6][0] = new Square(new Knight("white"),"white");
		board[2][0] = new Square(new Bishop("white"),"white");
		board[5][0] = new Square(new Bishop("white"),"black");
		board[3][0] = new Square(new Queen("white"),"black");
		board[4][0] = new Square(new King("white"),"white");
		
		isDone = false;
		isStalemate = false;
		isResign = false;
		isWhiteWinner = false;
		isWhitesMove = true;
		isWhiteInCheck = false;
		isBlackInCheck = false;
		isInCheck = false;
		isDrawAvailable = false;
	}
	
	public void drawBoard()
	{
		int rank, file;
		/*for(rank = 0; rank < 8; rank++)
		{
			for(file = 0; file < 8; file++)
			{
				if(board[rank][file].getPiece() != null){
					System.out.print(board[rank][file] + " ");
				}
				else{
					if(board[rank][file].isSquareBlack())
						System.out.print("## ");
					else
						System.out.print("   ");
				}
				
			}
			System.out.println(" " + (rank+1));
		}
		*/
		for(rank = 7; rank >= 0; rank--)
		{
			for(file = 0; file < 8; file++)
			{
				if(board[file][rank].getPiece() != null){
					System.out.print(board[file][rank] + " ");
				}
				else{
					if(board[file][rank].isSquareBlack())
						System.out.print("## ");
					else
						System.out.print("   ");
				}
				
			}
			System.out.println(" " + (rank+1));
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
	}
	
	/**
	 * move
	 * parse input and move 
	 */
	public void move(String input) {
		String[] args = input.split(" ");
		char initFile = args[0].charAt(0);
		char initRank = args[0].charAt(1);
		char finalFile = args[1].charAt(0);
		char finalRank = args[1].charAt(1);
		
		isDrawAvailable = false;
		
		if (args.length == 3 && args[2].equals("draw?"))
			isDrawAvailable = true;
			
		
		board = board[initFile-'a'][initRank-49].getPiece().move(input, board);
		
		testForCheck(board); //tests for check and checkmate
		
		changePlayer();
	}
	
	/**
	 * isValidMove
	 * @return			true if input is valid, else false
	 */
	public boolean isValidMove(String input) {
		String[] args = input.split(" ");
		String initPos = args[0];
		String finalPos = args[1];
		char initFile = initPos.charAt(0);
		char initRank = initPos.charAt(1);
		char finalFile = finalPos.charAt(0);
		char finalRank = finalPos.charAt(1);
		
		// if the position is the same or there's no piece in the initial file or that piece is not the correct player's piece
		if (initPos.equals(finalPos)
				|| board[initFile-'a'][initRank-49].getPiece() == null
				|| board[initFile-'a'][initRank-49].getPiece().isWhite() != isWhitesMove()
				|| board[finalFile-'a'][finalRank-49].getPiece() instanceof King)
			return false;
		
		// if there's a third argument, and the piece isn't a pawn, return false (for promotion)
		if (args.length == 3 && !(board[initFile-'a'][initRank-49].getPiece() instanceof Pawn))
			return false;
		
		boolean isValidMove = board[initFile-'a'][initRank-49].getPiece().isValidMove(input, board);
		
		// ensures that if a player is in check, their king must be brought out of check
		//if (isValidMove && isInCheck()) {
		//	Square[][] clone = createClone(board);
		//	clone = clone[initFile-'a'][initRank-49].getPiece().move(input, clone);
		//	testForCheck(finalFile, finalRank, clone);
		//	return isInCheck() && !isKingInCheck(clone, isWhitesMove());
		//}
		
		// ensure that the player does not bring themselves into a check
		if (isValidMove) {
			Square[][] clone = createClone(board);
			boolean hasMoved = board[initFile-'a'][initRank-49].getPiece().hasMoved();
			clone = clone[initFile-'a'][initRank-49].getPiece().move(input, clone);
			board[initFile-'a'][initRank-49].getPiece().setHasMoved(hasMoved);
			return !isKingInCheck(clone, isWhitesMove());
		}
		
		return isValidMove;
	}
	
	/**
	 * askForInput
	 * asks for input depending on who's move it is
	 */
	public void askForInput() {
		if (isWhitesMove())
			System.out.print("White's move: ");
		else
			System.out.print("Black's move: ");
	}
	
	/**
	 * changePlayer
	 */
	public void changePlayer() {
		isWhitesMove = !isWhitesMove;
	}
	
	/**
	 * testForCheck
	 * first checks if king is in check
	 * then, if it is in check, check for checkmate
	 */
	public void testForCheck(Square[][] b) {
		if (isKingInCheck(board, !isWhitesMove()))
			setCheck();
		else
			unsetCheck();
			
	}
	
	/**
	 * setCheck
	 * sets isInCheck to true and checks for checkmate
	 */
	public void setCheck() {
		isInCheck = true;
		System.out.println();
		if (!testForCheckmate())
			System.out.println("Check");
	}
	
	/**
	 * unsetCheck
	 * sets isInCheck to false and checks for stalemate
	 */
	public void unsetCheck() {
		isInCheck = false;
		testForStalemate();
	}
	
	/**
	 * isInCheck
	 * @return true if isInCheck, else false
	 */
	public boolean isInCheck() {
		return isInCheck;
	}
	
	/**
	 * isDone Accessor
	 * @return			true if game is done, else false
	 */
	public boolean isDone() {
		return isDone;
	}
	/**
	 * isStalemate Accessor
	 * @return			true if game is stalemate, else false
	 */
	public boolean isStalemate() {
		return isStalemate;
	}
	/**
	 * isResign Accessor
	 * @return			true if game is resign, else false
	 */
	public boolean isResign() {
		return isResign;
	}
	/**
	 * isWhiteWinner Accessor
	 * Might be unnecessary
	 * @return			true if white won, else false
	 */
	public boolean isWhiteWinner() {
		return isWhiteWinner;
	}
	/**
	 * isWhitesMove Accessor
	 * @return			true if it is White's move, else false
	 */
	public boolean isWhitesMove() {
		return isWhitesMove;
	}
	
	/**
	 * isDrawAvailable
	 * @return			true if draw is available, else false
	 */
	public boolean isDrawAvailable() {
		return isDrawAvailable;
	}
	
	/* CHECKMATE STUFF */
	/*public void testForCheckmate()
	{
		String kingsLocation = getEnemyKingLocation(board, isWhitesMove);
		String[] args = kingsLocation.split(" ");
		int kingsFile = args[0].charAt(0) - 'a';
		int kingsRank = Character.getNumericValue(args[1].charAt(0))-1;
		if( !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile, kingsRank, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile+1, kingsRank+1, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile, kingsRank+1, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile+1, kingsRank, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile+1, kingsRank-1, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile-1, kingsRank+1, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile-1, kingsRank-1, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile-1, kingsRank, board)
				&& !((King) board[kingsFile][kingsRank].getPiece()).isSafe(kingsFile, kingsRank-1, board)){
				
				if(isWhitesMove){
					isWhiteWinner = true;
					System.out.println("Checkmate");
					System.out.println("White wins");
					isDone=true;
				}
				else{
					isWhiteWinner = false;
					System.out.println("Checkmate");
					System.out.println("Black wins");
					isDone =true;
				}
				
		}
				
		
		
	}
	
	*/
	
	/**
	 * testForCheckmate
	 * if there are no valid moves from any piece on the board, then declare checkmate and end game
	 * @return true if it is checkmate, else false
	 */
	
	public boolean testForStalemate()
	{
		char initFile, initRank;
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				initFile = (char) f;
				initFile += 'a';
				initRank = (char) r;
				initRank += 49;
				if (board[f][r].getPiece() != null && board[f][r].getPiece().isWhite() != isWhitesMove()) {
					if (hasValidMoves(initFile, initRank)) {
						return false;
					}
				}		
			}
		}
		
		System.out.println("Stalemate");
		isDone = true;
		
		return true;
	}
	
	/**
	 * testForCheckmate
	 * @return true if checkmate, else false
	 */
	public boolean testForCheckmate() 
	{
		char initFile, initRank;
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				initFile = (char) f;
				initFile += 'a';
				initRank = (char) r;
				initRank += 49;
				if (board[f][r].getPiece() != null && board[f][r].getPiece().isWhite() != isWhitesMove()) {
					if (hasValidMoves(initFile, initRank)) {
						return false;
					}
				}		
			}
		}
		
		if(isWhitesMove){
			isWhiteWinner = true;
			System.out.println("Checkmate");
			System.out.println("White wins");
			isDone = true;
		}
		else{
			isWhiteWinner = false;
			System.out.println("Checkmate");
			System.out.println("Black wins");
			isDone = true;
		}
		
		return true;
	}
	
	/**
	 * isKingInCheck
	 * @param b
	 * @param isWhite
	 * @return true if king is in check, false otherwise
	 */
	public boolean isKingInCheck(Square[][] b, boolean isWhite) 
	{
		String kingLoc = getKingLocation(b, isWhite);
		char initFile, initRank;
		String initInput = "";
		String testCheckInput = "";
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				initFile = (char) f;
				initFile += 'a';
				initRank = (char) r;
				initRank += 49;
				initInput = initFile + "" + initRank + "";
				testCheckInput = initInput + " " + kingLoc;
				//System.out.println(testCheckInput);
				/* DO NOT LET isValidMove return true if location is king */
				if (b[f][r].getPiece() != null && !kingLoc.equals(initInput)) {
					if (b[f][r].getPiece().isValidMove(testCheckInput, b)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	

	
	/*getEnemyKingLocation */
	 /* gets fileRank location of opposing players king
	 
	public String getEnemyKingLocation(Square[][] board, boolean isCurrentlyWhite) {
		String location = "";
		char kingFile, kingRank;
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				if (board[f][r].getPiece() != null 
						&& board[f][r].getPiece().isWhite() != isWhitesMove
						&& board[f][r].getPiece() instanceof King) {
					kingFile = (char)(f + 'a');
					kingRank = (char)(r + 49);
					location = "" + kingFile +" " + kingRank;
					//return location;
				}
			}
		}
		return location;
	}
	 */
	
	/**
	 * getKingLocation
	 * @param board
	 * @param isWhite
	 * @return king location of white king if isWhite is true, else black king
	 */
	public String getKingLocation(Square[][] board, boolean isWhite) {
		String location = "";
		char kingFile, kingRank;
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				if (board[f][r].getPiece() != null 
						&& board[f][r].getPiece().isWhite() == isWhite
						&& board[f][r].getPiece() instanceof King) {
					kingFile = (char)(f + 'a');
					kingRank = (char)(r + 49);
					location = "" + kingFile + "" + kingRank;
					//return location;
				}
			}
		}
		return location;
	}
	
	/**
	 * hasValidMoves
	 * @param file
	 * @param rank
	 * @return if piece at that location has valid moves, return true. Else, false
	 */
	public boolean hasValidMoves(char file, char rank) {
		char finalFile, finalRank;
		String finalInput = "";
		String initInput = file + "" + rank + " ";
		
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				if (board[f][r].getPiece() instanceof King)
					continue;
				finalFile = (char) f;
				finalFile += 'a';
				finalRank = (char) r;
				finalRank += 49;
				finalInput = finalFile + "" + finalRank + "";
				if (board[file-'a'][rank-49].getPiece().isValidMove(initInput + finalInput, board)) {
					Square[][] clone = createClone(board);
					clone = clone[file-'a'][rank-49].getPiece().move(initInput + finalInput, clone);
					if (!isKingInCheck(clone, !isWhitesMove()) && !isKingInCheck(clone, isWhitesMove())) {
						//System.out.println(initInput + finalInput);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * createClone
	 * @param board
	 * @return a deep clone of board, for checking moves
	 */
	public Square[][] createClone(Square[][] board) {
		Square[][] clone = new Square[8][8];
		
		for (int f = 0; f < 8; f++) {
			for (int r = 0; r < 8; r++) {
				try {
					clone[f][r] = (Square) board[f][r].clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return clone;
	}

}
