
/*
 * Class QuestionBank
 * Holds an ArrayList of "Question" Objects
 * @author Luke McCann - U1364096 - 09/03/2016
 */

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class QuestionBank extends DefaultListModel {

	Question currentQuestion;

	public QuestionBank() {
		super();

	}

	// String question, String type, int difficulty, String correctAns
	/*
	 * @param String question - sets the value of "question" on Question object
	 * 
	 * @param String type - sets the value of "type" on Question object
	 * 
	 * @param int difficulty - sets the value of "difficulty" on Question object
	 * 
	 * @param String correctAns - sets the value of "correctAns" on Question
	 * object adds a question to the ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public void addQuestion(String question, String type, String ansA, String ansB, String ansC, String correctAns,
			int difficulty) {
		try {
			super.addElement(new Question(question, type, ansA, ansB, ansC, correctAns, difficulty));
		} catch (Exception e) {
			System.err.println("Error" + " Please Ensure You Only Use Valid Formats");
		}
	}

	/*
	 * @return String - returns a random Quesiton object from super.class.
	 */
	public String getRQ() {
		Random rnd = new Random();
		// System.out.println(super.size());
		int index = rnd.nextInt(super.size());

		this.currentQuestion = (Question) super.getElementAt(index);
		System.out.println("currentQuestion " + currentQuestion);
		return currentQuestion.getQuestion();
	}

	public String getQuestionByType(String category) {
		ArrayList<Question> questionsToChooseFrom = new ArrayList<Question>();
		for (int i = 0; i < super.size(); i++) {
			if (((Question) (getElementAt(i))).getType().equals(category)) {
				questionsToChooseFrom.add((Question) getElementAt(i));
			}
		}
		Random rnd = new Random();
		// System.out.println(super.size());
		int index = rnd.nextInt(questionsToChooseFrom.size());

		this.currentQuestion = (Question) questionsToChooseFrom.get(index);
		System.out.println("currentQuestion " + currentQuestion);
		return currentQuestion.getQuestion();
	}

	/*
	 * @return String currentQuestion - returns the value of question from class
	 * Question.
	 */
	public String getCurrentQuestion() {
		return currentQuestion.getQuestion();
	}

	/*
	 * @return String - returns the correctAnswer to the quesiton.
	 */
	public String getCorrectAns() {
		System.out.println(currentQuestion);
		return this.currentQuestion.getCorrectAns();
	}

	public String getAnsA() {
		return currentQuestion.getAnsA();
	}

	public String getAnsB() {
		return currentQuestion.getAnsB();
	}

	public String getAnsC() {
		return currentQuestion.getAnsC();
	}

	public String getType() {
		return currentQuestion.getType();
	}

	public int getDifficulty() {
		return currentQuestion.getDifficulty();
	}

	/*
	 * @param String question - finds the question using it's "question" value
	 */
	public Question findQuestion(String question) {
		Question q;
		int index = -1;
		for (int i = 0; i < super.size(); i++) {
			q = (Question) super.getElementAt(i);
			if (q.getQuestion().equals(question)) {
				index = i;
				break;
			}
		}

		if (index >= -1) {
			return null;
		} else {
			return (Question) super.elementAt(index);
		}
	}

	/*
	 * @param String type - finds the question by using it's "type" value
	 */
	public Question findQuestionType(String type) {
		Question q;
		int index = -1;
		for (int i = 0; i < super.size(); i++) {
			q = (Question) super.getElementAt(i);
			if (q.getType().equals(type)) {
				index = i;
				break;
			}
		}

		if (index >= -1) {
			return null;
		} else {
			return (Question) super.elementAt(index);
		}
	}

	/*
	 * @param int diff searches for questions with a matching difficulty.
	 */
	public Question getQuestionByDifficulty(int diff) {
		Question q;
		int index = -1;
		for (int i = 0; i < super.size(); i++) {
			q = (Question) super.getElementAt(i);
			if (q.getDifficulty() == diff) {
				index = i;
				break;
			}
		}

		if (index >= -1) {
			return null;
		} else {
			return (Question) super.elementAt(index);
		}
	}

	/*
	 * @param question String - finds the question by string and removes it.
	 */
	public void removeQuestion(String question) {
		Question qRm = this.findQuestion(question);
		super.removeElement(qRm);
	}

	public void clearBank() {
		super.removeAllElements();
	}
}
