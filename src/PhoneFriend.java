
/*
 * Class PhoneFriend
 * @param Gamecontroller gC - passed the GameController as a parameter
 * Creates a PhoneFriend object.
 * @author Luke McCann - U1364096 - 13/04/2016
 */

import java.util.Random;

import javax.swing.JOptionPane;

public class PhoneFriend {

	private int chanceFriendIsIncorrect;
	private String friendsAnswer;

	/*
	 * Generates a random number between 1 and 4. if the answer is higher than 3
	 * you're friend will be correct, otherwise a answer is selected at random.
	 */
	public PhoneFriend(ZillionaireGUI thisJFrame) {
		this.chanceFriendIsIncorrect = 3;

		Random rnd = new Random();
		int random = rnd.nextInt(4);

		if (random < chanceFriendIsIncorrect) {
			String friendsAnswer = thisJFrame.questionOrder.get(random);
			this.friendsAnswer = "Your Friend Thinks The Answer Is: " + friendsAnswer;
			setFriendsAnswer(this.friendsAnswer);
			thisJFrame.setphoneFriend(this.friendsAnswer);
			JOptionPane.showMessageDialog(thisJFrame, this.friendsAnswer);
		} else {
			String friendsAnswer = thisJFrame.gC.qBank.getCorrectAns();
			this.friendsAnswer = "Your Friend Thinks The Answer Is: " + friendsAnswer;
			JOptionPane.showMessageDialog(thisJFrame, this.friendsAnswer);
		}
	}

	/*
	 * @return String - returns friends response
	 */
	public String getFriendsAnswer() {
		return this.friendsAnswer;
	}

	/*
	 * @param friendsAnswer - sets friendsAnswr to the value of Answer.
	 */
	public String setFriendsAnswer(String answer) {
		return this.friendsAnswer = answer;
	}

}