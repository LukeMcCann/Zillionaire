/*
 * Class Validator 
 * Creates a Validator which holds 
 * validation methods to check if answers are correct.
 * @author Luke McCann 10/03/2016
 */

public class Validator {

	ZillionaireGUI mainGUI;

	public Validator(ZillionaireGUI mainGUI) {
		this.mainGUI = mainGUI;
	}

	/*
	 * @return boolean - checks the answer is correct, returns true if the
	 * answer is the correct answer returns false otherwise.
	 */
	public boolean checkCorrect(String selected) {
		return (selected.equals(mainGUI.gC.qBank.getCorrectAns()));

	}

}
