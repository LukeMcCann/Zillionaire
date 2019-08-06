
/*
 * Class Fiftyfifty
 * Creates a FiftyFifty Object.
 * @author Luke McCann - U1364096 - 09/04/2016
 */
import java.util.Random;

public class Fiftyfifty {

	int selectedNum;

	/*
	 * Constructor for FiftyFifty Creates a Fiftyfifty Object
	 * 
	 * @param ZillionaireGUI thisJFrame - passes ZillionaireGUI as a parameter.
	 * loops through the question buttons twice, finds if one has been selected
	 * if false a random answer is selected, compared to the correct answer and
	 * if it is NOT the correct answer, set enabled to false and another. button
	 * is selected to be false as well.
	 */

	public Fiftyfifty(ZillionaireGUI thisJFrame) {
		for (int i = 0; i < 2; i++) {
			boolean hasOneBeenSelected = false;
			do {
				int random = new Random().nextInt(4);
				System.out.println(random);
				if (thisJFrame.questionOrder.get(random) == thisJFrame.gC.qBank.getCorrectAns()
						|| !thisJFrame.getAnswerButton(random).isEnabled()) {
					hasOneBeenSelected = false;
					System.out.println("no " + random);
				} else {
					System.out.println("yes " + random);
					selectedNum = random;
					hasOneBeenSelected = true;
				}
			} while (!hasOneBeenSelected);
			thisJFrame.getAnswerButton(selectedNum).setEnabled(false);
		}
	}
}
