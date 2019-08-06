
/*
 * Class PlayerList
 * Creates an ArrayList of "Player" Objects
 * @author Luke McCann - U1364096 - 03/02/2016
 * @updated 15/02/2016
 * @updated 05/04/2015
 */

import javax.swing.DefaultListModel;

public class PlayerList extends DefaultListModel {

	public PlayerList() {
		super();
	}

	/*
	 * @param double credit - sets Player credits.
	 * 
	 * @param int id - finds the Player using their id.
	 */
	public void setCredits(int index) {
		Player p = (Player) super.getElementAt(index);

		p.increasePlayerCreditLevel();
		System.out.println("player's credits: " + p.getPlayerCredits());
	}

	/*
	 * @param String name - sets the value of "name" on Player object
	 */
	@SuppressWarnings("unchecked")
	public void addPlayer(int id) {
		try {
			super.addElement(new Player(id));
		} catch (Exception e) {
			System.err.println("Please ensure only integers between 1 and 4 are entered.");
		}
	}

	/*
	 * @return int - returns the Id from the Player class.
	 */
	public int getId() {
		Player p;
		for (int i = 0; i < super.size(); i++) {
			p = (Player) super.getElementAt(i);
			return p.getPlayerId();
		}
		return 0;
	}

	public int getCredits() {
		Player p;
		for (int i = 0; i < super.size(); i++) {
			p = (Player) super.getElementAt(i);
			return p.getPlayerCredits();
		}
		return 0;
	}

	/*
	 * @param id - allows the user to remove a player.
	 */
	public void removePlayer(int id) {
		Player p;
		for (int i = 0; i > super.size(); i++) {
			p = (Player) super.getElementAt(i);
			if (id == p.getPlayerId()) {
				super.removeElementAt(i);
			}
		}
	}

	/*
	 * Removes all Players from PlayerList.
	 */
	public void removeAll() {
		super.removeAllElements();
	}
}
