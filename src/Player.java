
/*
 * Class Player
 * Creates objects belonging to the Player class
 * @param credits - int initialized at 0
 * @param id - int id of player
 * @author Luke McCann - U1364096 - 02/02/2016
 * @updated 15/02/2016
 * @updated 05/04/2015
 */

public class Player {

	private int[] scoreValues = new int[] { 0, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000 };

	private int id;
	private int credits;
	private int currentLevel;
	// level 8 wins the game

	/*
	 * @param String name - sets the value of name Initialises the class
	 * incrementing the playerId depending on the number of players created.
	 */
	public Player(int id) {
		this.id = id;
		this.credits = 0;
		this.currentLevel = 0;
	}

	/*
	 * @return returns the value of "id"
	 */
	public int getPlayerId() {
		return this.id;
	}

	/*
	 * @param int id - sets the value of "id"
	 */
	public void setPlayerId(int id) {
		this.id = id;
	}

	/*
	 * @return returns the value of "credits"
	 */
	public int getPlayerCredits() {
		// System.out.println("Received getCredit request");
		return this.credits;

	}

	/*
	 * @param double credits - sets the value of "credits"
	 */
	public void increasePlayerCreditLevel() {
		this.credits = scoreValues[++currentLevel];
	}

}
