
/*
 * Class Question
 * Creates Objects belonging to the Question class
 * @param question - String the question being held
 * @param type - String the category associated with the question
 * @param difficulty - int difficulty of the question
 * @param correctAns - String the correct answer to the question
 * @author Luke McCann - U1364096 - 01/02/2016
 * @updated 15/02/2016
 * @updated 05/04/2015
 */

import java.io.Serializable;

public class Question implements Serializable {

	private String question;
	private String type;
	private String ansA;
	private String ansB;
	private String ansC;
	private String correctAns;
	private int difficulty;

	/*
	 * Constructor for the Question class.
	 */
	public Question(String question, String type, String ansA, String ansB, String ansC, String correctAns, int difficulty) {
		this.question = question;
		this.type = type;
		this.difficulty = difficulty;
		this.correctAns = correctAns;
		this.ansA = ansA;
		this.ansB = ansB;
		this.ansC = ansC;
	}

	/*
	 * @return returns the value of "question"
	 */
	public String getQuestion() {
		return this.question;
	}

	/*
	 * @param String question - sets the value of "question"
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/*
	 * @return returns the value of "type"
	 */
	public String getType() {
		System.out.println("question: " + this.type);
		return this.type;
	}

	/*
	 * @param String type - sets the value of "type"
	 */
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * @return returns the value of "correctAns"
	 */
	public String getCorrectAns() {
		System.out.println(this.correctAns);
		return this.correctAns;
	}

	/*
	 * @param String correctAns - sets the value of "correctAns"
	 */
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public String getAnsA() {
		return ansA;
	}

	public void setAnsA(String ansA) {
		this.ansA = ansA;
	}

	public String getAnsB() {
		return ansB;
	}

	public void setAnsB(String ansB) {
		this.ansB = ansB;
	}

	public String getAnsC() {
		return ansC;
	}

	public void setAnsC(String ansC) {
		this.ansC = ansC;
	}

	public int getDifficulty() {
		return difficulty;
	}

	/*
	 * @return returns Question data as String
	 */
	@Override
	public String toString() {
		return "Question: " + question + "  type: " + type + "  Answer1: " + correctAns + "  difficulty: "
				+ difficulty + "  Answer2: " + ansA + "  Answer3: " + ansB + "  Answer4: " + ansC;
	}
	
}
