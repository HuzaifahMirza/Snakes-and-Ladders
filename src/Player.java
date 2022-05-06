
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
 *The player class, it manages the players on an individual basis.
 *
 */
public class Player {

	private String name;//player name.
	private int position;//player position on the board.

	/**player constructor without parameters.
	 * 
	 */
	public Player() {
		this.name = "";
		this.position = 0;
	}

	/**players constructor with name parameter.
	 * @param name, sets the name of the player.
	 */
	public Player(String name) {
		this.name = name;
		this.position = 0;
	}

	/**sets the name of a player thats already been declared by a paramenterless constructor.
	 * @param name, the name to be assigned.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**Returns the name of the player.
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**Returns player position.
	 * @return
	 */
	public int getPosition() {
		return this.position;
	}

	/**advances the player by the amount of num.
	 * @param num, the parameter thats used to determine how much to advance the player by.
	 */
	public void advance(int num) {

		this.position += num;

	}

	/**To set the players position to an arbitrary number predetermined by another method.
	 * @param n, the new position to be set.
	 */
	public void setPosition(int n) {
		this.position = n;
	}

	/**places the players position back the by amount of num.
	 * @param num, the parameter thats used to determine how much to retreat the player by.
	 */
	public void retreat(int num) {
		this.position -= num;
	}

	/**returns the first letter of a player and will be used to represent their position on the board.
	 * @return returns a char.
	 */
	public char getChar() {
		return Character.toUpperCase(this.getName().charAt(0));
	}

	/**
	 *A basic toString method to return the name of the player.
	 */
	public String toString() {
		return this.name;
	}

}
