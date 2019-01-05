/**chess 37
 * @author: Allison Coopersmith
 * @author: Kaushal Parikh
 * CS 213
 */

package chess;

import java.util.Scanner;

/**
 * This class will execute the Chess game, housing a method that setups the valid play environment and a main method that calls this setup method. 
 */

public class Chess {

	
	/**
	 * This method sets up an interaction with the console, with scanning and printing methods initialized, and calls other methods in the board class to progress the game of chess.  
	 */

	private static void playGame() {
		Scanner sc= new Scanner(System.in);
		Board game=new Board(); 
		while (!game.gameIsDone) {
			game.printBoard();
			game.promptPlayer();
			String input = sc.nextLine();
			while (!game.readInput(input)) {
				System.out.println("Illegal move, try again");
				game.promptPlayer();
				input = sc.nextLine();
				
			}
			System.out.println();
		}
		sc.close();

	}
	



	/**
	 * This main method calls for the execution of the chess program.
	 * @param args		any arguments, which should be empty 
	 */

	public static void main (String[] args) {
		playGame();
	}

}
