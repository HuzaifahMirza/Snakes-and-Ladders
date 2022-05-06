
import java.util.Scanner;
/**
 * Huzaifah Mirza 40136913
COMP249 
Assignment #1
February 7, 2022
 */
//-----------------------------------------------------
//Assignment (1)
//Written by: (Huzaifah Mirza, 40136913)
//The purpose of this program is to recreate the game play of snakes and ladders using computer logic and programming skills in java.

//----------------------------------------------------

/**
 * This class runs the whole program.
 *
 */
public class playLaddersAndSnakes {

	/**
	 * Driver Class
	 * 
	 * @param args runs the program
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String btn; // takes place of "press enter" function.

		// welcome messages
		System.out.println("\nThis program was developed by Huzaifah Mirza. No rights reserved.");

		System.out.println("--------------------------------------------------------------");
		System.out.printf("%5s", "From the likes of Candyland, Snakes and Ladders and Chutes and Ladders, "
				+ "we present to you the completely unoriginal, Ladders and Snakes. \r\n"
				+ "We spared every expense to bring this game to an IDE near you. \r\n" + "We hope you enjoy it!\n");
		System.out.println("--------------------------------------------------------------");
		System.out.println("Press enter to begin the game!");

		btn = input.nextLine();
		int numPlayers = LaddersAndSnakes.setNumOfPlayers();
		LaddersAndSnakes game = new LaddersAndSnakes(numPlayers); // constructs the game based on number of players.
		game.setPlayerNames(numPlayers);
		game.whosFirst();
		game.play();
		game.displayWinners();

		System.out.println("Game has terminated. Run program again to play again.");

	}

}
