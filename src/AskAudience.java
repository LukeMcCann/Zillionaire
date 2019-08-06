
/*
 * Class AskAudeience
 * Creates a AskAudience Object
 * simulates an audience voting by generating a random number
 * it adds 25 answers to an array randomly,
 * then randomly selects one from that array.
 * @author Luke McCann - U1364096 - 03/04/2016
 */

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class AskAudience {

	private ArrayList<String> answers = new ArrayList<String>();
	private int chanceIncorrect;
	private int count;

	public AskAudience(ZillionaireGUI thisJFrame) {

		int random = new Random().nextInt(25);

		while (count != 25) {
			count++;
			if (random <= 5) {
				answers.add("A");
			}
			if (random > 5 && random <= 10) {
				answers.add("B");
			}
			if (random > 10 && random <= 15) {
				answers.add("C");
			}
			if (random > 15 && random <= 20) {
				answers.add("D");
			}
		} JOptionPane.showMessageDialog(thisJFrame, "The Audience Have Voted: " +
			answers.get(random).toString());
	}

	/*
	 * @return String answers - returns the value of answers
	 */
	public ArrayList<String> getAnswers() {
		return answers;
	}

	/*
	 * @param String answers - sets the value of answers
	 */
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	/*
	 * @return String chanceIncorrect - returns the value of chanceIncorrect
	 */
	public int getChanceIncorrect() {
		return chanceIncorrect;
	}

	/*
	 * @param chanceIncorrect - sets the value of chanceIncorrect.
	 */
	public void setChanceIncorrect(int chanceIncorrect) {
		this.chanceIncorrect = chanceIncorrect;
	}
}
