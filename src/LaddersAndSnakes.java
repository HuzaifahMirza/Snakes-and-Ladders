
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Huzaifah Mirza 40136913 COMP249 Assignment #1 February 7, 2022
 */
//-----------------------------------------------------
//Assignment (1)
//Written by: (Huzaifah Mirza, 40136913)
//The purpose of this program is to recreate the game play of snakes and ladders using computer logic and programming skills in java.

//----------------------------------------------------

/**
 * The class that runs the entire game. handles manipulating the players and
 * thier positions.
 *
 *
 */
public class LaddersAndSnakes {
	private char[][] board; // the array on which the players positions will be saved.
	private ArrayList<Player> players;// the array that saves the players.
	private ArrayList<Player> winners;// the array that saves the winners as they win.

	/**
	 * This is the constructor for this class, it intializes all the variables.
	 * 
	 * @param num, takes an int and declares the number of players.
	 */
	public LaddersAndSnakes(int num) {
		this.board = new char[100][4];
		this.winners = new ArrayList<>(num);
		this.players = new ArrayList<>();
		for (int i = 0; i < num; i++) {

		}
	}

	/**
	 * this method rolls a virtual dice within the range of 1 to n.
	 * 
	 * @param n intializes the upperlimit of the dice roll.
	 * @return returns the final dice roll number.
	 */
	public static int flipDice(int n) {
		Random rand = new Random();
		int num = 1 + rand.nextInt(n);
		return num;

	}

	/**
	 * Positions the player on the board based on their current position.
	 * 
	 * @param p, the current player who's position is being set.
	 */
	public void setPosition(Player p) {
		int n = p.getPosition() - 1;
		for (int i = 0; i < 4; i++) {
			if (!(this.getBoard()[n][i] >= 'A' && this.getBoard()[n][i] <= 'Z')) {
				this.getBoard()[n][i] = p.getChar();
				break;
			}

		}
	}

	/**
	 * Prints out the first letter of the player on the board to create a visual
	 * representation of where the players are, thereby creating a visual interface.
	 * 
	 * @param n, indicates the position on the board where the player will be
	 *           positioned. Its also important to note that this method is alos
	 *           able to determine how many people are on this position and will
	 *           print all of them.
	 * @return returns the first letter of all the players who might be on that
	 *         position.
	 */
	public String printChars(int n) {
		String name = "";
		for (int i = 0; i < 4; i++) {
			if (!(this.board[n][i] == 0)) {
				name += String.valueOf(this.board[n][i]);
			}
		}
		return name;
	}

	/**
	 * Sets the player names and also catches any invalid inputs like numbers.
	 * 
	 * @param n, limits the loop to the amount of players.
	 */
	public void setPlayerNames(int n) {
		String name = "";
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < n; i++) {
			do {
				System.out.print("please enter a valid name for player " + (i + 1) + ": ");
				name = input.nextLine();

				if (isNotName(name) || name.equals("")) {
					System.out.println("Invalid name! Try again!");
				} else {
					this.setPlayers(name);
				}

			} while ((isNotName(name)) || name.equals(""));

		}
		System.out.println("");
	}

	/**
	 * Declares new players and adds them to the arraylist that stores all the
	 * players.
	 * 
	 * @param name, constructs the player with this string.
	 */
	public void setPlayers(String name) {
		this.players.add(new Player(name));
	}

	/**
	 * returns the arraylist containing the players.
	 * 
	 * @return returns list with the players.
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Replaces the original list of players with a new list.
	 * 
	 * @param p, replaces the original list with this one.
	 */
	public void setPlayers(ArrayList<Player> p) {
		this.players = p;
	}

	/**
	 * A method to determine the number of players that are playing the game. This
	 * method also prevents the user from inputting the wrong input.
	 * 
	 * @return returns the number of players determined by the method.
	 */
	public static int setNumOfPlayers() {
		Scanner input = new Scanner(System.in);
		int x = 1;
		String n = "";
		int num;
		System.out
				.print("Enter the # of players for your game – Number must be between 2 \r\n" + "and 4 inclusively: ");
		while (true) {
			n = input.nextLine();

			// conditional statement to determine if the input is correct and to prompt the
			// user to input the right number if incorrect.
			if (isNotName(n)) {
				num = Integer.parseInt(n);
				if (num >= 2 && num <= 4) {
					System.out.print("\nAwesome! Lets give them some names.");
					break;
				}

			} else if (x < 3) {
				System.out.print("\nBad Attempt " + x
						+ " - Invalid # of players or input was not number. Please enter a # between 2 and \r\n"
						+ "4 inclusively:");

			}
			if (x == 3) {
				System.out.print("\nBad Attempt " + x
						+ " - Invalid # of players or input was not number. Please enter a # between 2 and \r\n"
						+ "4 inclusively. This is your last attempt:");
			}
			if (x == 4) {

				System.out.println(
						"\nBad Attempt 4! You have exhausted all your chances. Program will \r\n" + "terminate! ");
				System.exit(0);
			}
			x++;

		}
		System.out.println("");
		return num;
	}

	/**
	 * Declares a new board, the purpose of this is to erase the old positions, so
	 * that the new positions can be set. if this was not done, then the board would
	 * save all the positions of all the players throughout the duration of the
	 * game.
	 * 
	 * @param list, replaces the board with this new 2d array.
	 */
	public void setBoard(char[][] list) {
		this.board = list;
	}

	/**
	 * runs the bulk of the game, mainly handles the rolling of the dice and
	 * displaying of the board with the updated positions.
	 * 
	 * @return
	 */
	public void play() {
		Scanner input = new Scanner(System.in);
		String btn;

		System.out.println("--------------------------------------------------------------");
		System.out.println("Lets Begin the game! When its your turn, press enter to roll!\n");

		// starts a while loop that runs until all the players have reached the end of
		// the game.

		while (winners.size() < this.getPlayers().size()) {
			// prompts the players to roll and prints out their positions based on where
			// they land, be it a snake ,ladder or none.
			char[][] playerPos = new char[100][4];
			this.setBoard(playerPos);

			int x = 5;
			int pos = 100;

			for (int i = 0; i < this.getPlayers().size(); i++) {

				Player p = this.getPlayers().get(i);
				int num = p.getPosition();
				// skips this player if they've already reached 100.
				if (num == 100) {
					this.setPosition(p);
					continue;
				}
				System.out.println("----------------------------\n");
				System.out.println("Its your turn, " + this.getPlayers().get(i).getName() + ".\n");

				int n = 0;
				btn = input.nextLine();
				n = LaddersAndSnakes.flipDice(6);
				int position = n + p.getPosition();

				// the series of conditional statements compute the new position of the player
				// based on where they landed and sets it on the board.
				if (position == 100 || position == 80) {
					p.advance(n);
					System.out.println("You rolled a " + n + ", advance to " + p.getPosition() + ".\n");
					if (p.getPosition() == 80) {
						System.out.println("you landed on a ladder! proceed to 100.\n");
						p.setPosition(100);
					}
					this.winners.add(p);
					System.out.println("Congratulations " + p.getName() + "! you made it to 100! you are the #"
							+ this.winners.size() + " winner!\n");
					this.setPosition(p);

				} else if (position > 100) {
					p.retreat(n);
					System.out.println("You rolled a " + n + ".\n");
					System.out.println("oh no! go back " + n + " spaces.\n");
					this.ladderOrSnake(p);
					System.out.println();
					this.setPosition(p);

				} else {
					p.advance(n);
					System.out.println("You rolled a " + n + ", advance to " + p.getPosition() + ".\n");
					this.ladderOrSnake(p);
					System.out.println();
					if (p.getPosition() == 80) {
						winners.add(p);
						System.out.println("Congratulations " + p.getName() + "! you made it to 100! you are the #"
								+ winners.size() + " winner!\n");
					}
					this.setPosition(p);
				}

			}
			System.out.println("----------------------------\n");
			System.out.println("Press enter to display the board!");
			btn = input.nextLine();

			// this while loop iterates through the board array and prints out the numbers
			// and letters of the players. it is able to distinguish if a particular
			// position has a player on it or not and displays it accordingly.
			while (x > 0) {

				for (int i = 0; i < 10; i++) {

					if ((playerPos[pos - 1][0] == 0)) {
						System.out.printf("%5s", pos);

					}

					else {
						System.out.printf("%5s", "|" + this.printChars(pos - 1) + "|");
					}

					pos--;
				}
				System.out.println("\n");

				for (int i = 1; i <= 10; i++) {

					if ((playerPos[pos - 11 + i][0] == 0)) {
						System.out.printf("%5s", pos - 10 + i);

					} else {
						System.out.printf("%5s", "|" + this.printChars(pos - 11 + i) + "|");
					}

				}
				System.out.println("\n");

				pos -= 10;

				x--;

			}

		}

		// these statements are printed after the game has ended.
		System.out.println("--------------------------------------------------\n");
		System.out.println("                     GAME OVER");
		System.out.println("--------------------------------------------------\n");

	}

	/**
	 * Returns the board. Is a basic get method.
	 * 
	 * @return
	 */
	public char[][] getBoard() {
		return this.board;
	}

	/**
	 * this method determines the order of the players based on who rolls a 6, and
	 * then a 5 etc, until only 1 player remains which it automatically sets as the
	 * last player.
	 * 
	 */
	public void whosFirst() {
		int x = 0; // iterates through the list of players
		int y = 6;// decrements the dice roll each time a player is chosen, so that whoever rolls
					// a 6 first is player 1, and whoever rolls a 5 is player 2...etc
		int num; // saves the dice roll.
		int order = 1; // increments the order of the players to display which player is in which
						// order.
		ArrayList<Player> oldlist = this.getPlayers();
		ArrayList<Player> list = new ArrayList<Player>();
		System.out.println("Awesome! Whos gonna start the game? Lets roll and find out!\n");
		// while loop continues until all the players are set.
		while (true) {
			Player p = oldlist.get(x);
			Scanner input = new Scanner(System.in);
			System.out.println("Whoever rolls a " + y + " will be number " + order + ", " + oldlist.get(x).getName()
					+ " will roll now.");
			String btn = input.nextLine();
			num = LaddersAndSnakes.flipDice(y);
			System.out.print("you rolled a " + num + "!");
			if (num == y) {
				System.out.println(" Congrats! " + oldlist.get(x).getName() + " is player " + order + "\n");
				list.add(p);
				oldlist.remove(p);
				y--;
				order++;
				x = 0;

			} else {
				System.out.println(" sorry! next turn.\n");
				x++;
				if (x == oldlist.size()) {
					x = 0;
				}

			}

			if (oldlist.size() == 1) {
				list.add(oldlist.get(0));
				System.out.println(oldlist.get(0).getName() + " will go last!\n");
				break;
			}

		}

		// sets the new list so that the game plays in the established order.
		this.setPlayers(list);

	}

	/**
	 * This switch case determines where a player goes if they land on a ladder or
	 * snake.
	 * 
	 * @param p takes the players position and sets it to the new position based on
	 *          where they landed.
	 */
	public void ladderOrSnake(Player p) {
		int n = p.getPosition();
		switch (n) {
		case 1:
			p.setPosition(38);

			System.out.println("you landed on a ladder! proceed to 38.");
			break;

		case 4:
			p.setPosition(14);

			System.out.println("you landed on a ladder! proceed to 14.");
			break;

		case 9:
			p.setPosition(31);

			System.out.println("you landed on a ladder! proceed to 31.");
			break;

		case 28:
			p.setPosition(84);

			System.out.println("you landed on a ladder! proceed to 84.");
			break;

		case 21:
			p.setPosition(42);

			System.out.println("you landed on a ladder! proceed to 42.");
			break;

		case 36:
			p.setPosition(44);

			System.out.println("you landed on a ladder! proceed to 44.");
			break;
		case 51:
			p.setPosition(67);

			System.out.println("you landed on a ladder! proceed to 67.");
			break;

		case 80:
			p.setPosition(100);

			System.out.println("you landed on a ladder! proceed to 100.");
			break;
		case 71:
			p.setPosition(91);

			System.out.println("you landed on a ladder! proceed to 91.");
			break;

		case 16:
			p.setPosition(6);

			System.out.println("oh no! You landed on a snake! retreat to 6.");
			break;

		case 48:
			p.setPosition(30);

			System.out.println("oh no! You landed on a snake! retreat to 30.");
			break;

		case 64:
			p.setPosition(60);
			System.out.println("oh no! You landed on a snake! retreat to 60.");
			break;

		case 79:
			p.setPosition(19);

			System.out.println("oh no! You landed on a snake! retreat to 19.");
			break;

		case 93:
			p.setPosition(68);

			System.out.println("oh no! You landed on a snake! retreat to 68.");
			break;
		case 95:
			p.setPosition(24);

			System.out.println("oh no! You landed on a snake! retreat to 24.");
			break;
		case 98:
			p.setPosition(78);

			System.out.println("oh no! You landed on a snake! retreat to 78.");
			break;

		case 97:
			p.setPosition(76);

			System.out.println("oh no! You landed on a snake! retreat to 76.");
			break;

		}

	}

	/**
	 * used to catch input errors, this method determines whether or not an input
	 * was a string or a number.
	 * 
	 * @param name, the string to be analyzed.
	 * @return returns true or false based one the analysis.
	 */
	public static boolean isNotName(String name) {

		try {
			Integer.parseInt(name);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	/**
	 * used to catch input errors, this method determines whether or not an input
	 * was a string or a number.
	 * 
	 * @param num the number to be analyzed.
	 * @return returns true or false based on the analysis.
	 */
	public static boolean isNum(int num) {
		String n = Integer.toString(num);
		try {
			Integer.parseInt(n);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	/**
	 * displays the players a the end of the game based on who was first, second
	 * third...etc
	 * 
	 */
	public void displayWinners() {
		System.out.println("--------------------------------------------------");
		System.out.println("                    LeaderBoard");
		System.out.println("--------------------------------------------------\n");

		for (int i = 1; i <= winners.size(); i++) {
			System.out.println("                        " + i + "." + this.winners.get(i - 1) + "\n");
		}
	}
}
