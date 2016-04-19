package chess;

import java.util.Scanner;

import model.Board;

/**
 * @authors Julia Sutula and Corentin Rejaud
 *
 */
public class Chess {
	
	private static Board game;
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		startGame();
		
	}

	/**
	 * 
	 * startGame()
	 * @return void
	 * 
	 * Initializes new game which takes in user input until game is complete
	 * 
	 */
	public static void startGame() {
		String input;
		boolean isValidInput, isValidMove;
		game = new Board();
		
		while (!game.isDone()) {
			game.drawBoard();
			game.askForInput();
			input = scan.nextLine();
			isValidInput = isValidInput(input);
			isValidMove = true;
			if (isValidInput)
				isValidMove = game.isValidMove(input);
			while(!isValidInput || !isValidMove) {
				if (!isValidInput)
					System.out.println("Invalid Input, please use the format: fileRank fileRank");
				if (!isValidMove)
					System.out.println("Illegal move, try again");
				game.askForInput();
				input = scan.nextLine();
				isValidInput = isValidInput(input);
				isValidMove = true;
				if (isValidInput)
					isValidMove = game.isValidMove(input);

			}
			game.move(input);
			System.out.println();
		}
		
		
	}
	
	/**
	 * 
	 * isValidInput()
	 * @param input
	 * @return boolean
	 * 
	 * Returns if user input is valid
	 * 
	 */
	public static boolean isValidInput(String input) {
		//exits if resignation and declares winner
		if (input.trim().equals("resign"))
		{
			if(game.isWhitesMove()){
				System.out.println("Black wins");
			}
			else{
				System.out.println("White wins");
			}
			
			System.exit(0);
		}
			
		//exits if draw
		if (input.trim().equals("draw")){
			System.out.println("Draw");
			System.exit(0);
		}
		
		//Checks if input is for movement, promotion, or draw
		return input.matches("[abcdefgh][12345678] [abcdefgh][12345678]") 
				|| input.matches("[abcdefgh][12345678] [abcdefgh][12345678] [QNRB]")
				|| input.matches("[abcdefgh][12345678] [abcdefgh][12345678] draw[?]");	
	}
	
}
