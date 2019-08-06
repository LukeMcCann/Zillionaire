
/*
 * Class GameController
 * Creates Object GameController
 * @param ZillionaireGUI - passed an instance of ZillionaireGUI on creation.
 * @author Luke McCann - U1364096 - 15/02/2016
 * @upated 26/02/2016
 * @updated 05/03/2016
 */

import java.awt.Color;
import sun.audio.*;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class GameController {
	PlayerList pL;
	QuestionBank qBank;
	Validator v;
	QuestionDataGUI qGUI;
	ZillionaireGUI mainGUI;
	ArrayList<String> answers = new ArrayList();
	Question question;

	Countdown timer;
	FileHandler fileHandler;
	String questionTxt;
	String currentQuestion;
	String answer;

	public GameController(ZillionaireGUI mainGUI) {
		pL = new PlayerList();
		qBank = new QuestionBank();
		v = new Validator(mainGUI);
		qGUI = new QuestionDataGUI(this);
		this.mainGUI = mainGUI;
		this.timer = new Countdown(mainGUI.getTimerLbl(), this, 30);

		try {
			playBackground();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

		try {
			this.fileHandler = new FileHandler(this);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		try {
			fileHandler.loadFromFile();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Error :" + e);
			e.printStackTrace();
		}
	}

	/*
	 * @param int seconds - amount of seconds to set on timer.
	 */
	public void setTimer(int seconds) {
		this.setTimer(seconds);
	}

	/*
	 * @param int numPlayers - the number of players entered. adds a player to
	 * PlayerList.
	 */
	public void createPlayer(int numPlayers) {
		do {
			this.pL.addPlayer(1);
		} while (numPlayers != pL.size());
	}

	/*
	 * @param int index - the index number of the player
	 * 
	 * @return int id - returns the id of the player at index.
	 */
	public int getId(int index) {
		for (int i = 0; i < pL.size(); i++) {
			pL.getElementAt(index);
			return pL.getId();
		}
		return 0;

	}

	/*
	 * @param int index - the index number of the player
	 * 
	 * @return int credits - returns the credits of the player at index.
	 */
	public int getCredit(int index) {
		for (int i = 0; i < pL.size(); i++) {
			pL.getElementAt(index);
			return pL.getCredits();
		}
		return 0;
	}

	/*
	 * @param int index - the index of the player gets the player at index sets
	 * the credits to that
	 */
	public void updateCredit(int index) {
		for (int i = 0; i < pL.size(); i++) {
			pL.getElementAt(index);
			pL.setCredits(index);
		}
	}

	/*
	 * Sets modality and visibility of QuestionDataGUI to true
	 */
	public void createQGUI() {
		qGUI.setModal(true);
		qGUI.setVisible(true);
	}

	/*
	 * Adds a Question Object to the QuestionBank.
	 */
	public void addQ(String question, String type, String ansA, String ansB, String ansC, String correctAns,
			int difficulty) {
		qBank.addQuestion(question, type, ansA, ansB, ansC, correctAns, difficulty);
	}

	/*
	 * @param int index - index of the player Checks player credits if equal to
	 * 1000000 returns true.
	 */
	public boolean hasPlayerWon(int index) {
		return getCredit(index) == 1000000;
	}

	/*
	 * Starts timer in class Countdown.
	 */
	public void startClock() {
		this.timer.myTimer.start();
	}

	/*
	 * Checks if answerButtons are disabled, and enables them if they are.
	 */
	public void setAButtonsEnabled() {
		for (int i = 0; i < 4;) {
			if (!mainGUI.getAnswerButton(i).isEnabled()) {
				mainGUI.getAnswerButton(i).setEnabled(true);
				i++;
			} else {
				i++;
			}
		}
	}

	/*
	 * @param String message - message to be displayed Displays a confirm dialog
	 * with a custom message to the user. if reply is equal to YES_OPTION -
	 * timer is stopped and the GUI is disposed of.
	 */
	public void displayMsg(String message) {
		int reply = JOptionPane.showConfirmDialog(mainGUI, message);
		if (reply == JOptionPane.YES_OPTION) {
			timer.myTimer.stop();
			mainGUI.dispose();
			ZillionaireGUI newGUI = new ZillionaireGUI();
			newGUI.setVisible(true);
		} else {
			System.exit(0);
		}
	}

	/*
	 * generates the next question and sets the text on the buttons. randomises
	 * the order at which the answers will appear. sets the answerButton text.
	 */
	public void setQuestion() {
		mainGUI.getStartBtn().setText(questionTxt);
		mainGUI.getAnswerButton(0).setText("A) " + mainGUI.questionOrder.get(0));
		mainGUI.getAnswerButton(1).setText("B) " + mainGUI.questionOrder.get(1));
		mainGUI.getAnswerButton(2).setText("C) " + mainGUI.questionOrder.get(2));
		mainGUI.getAnswerButton(3).setText("D) " + mainGUI.questionOrder.get(3));
		startClock();
		timer.setCounter(30);
	}

	/*
	 * sets all buttons to enabled Shuffles the order the questions appear sets
	 * Quesiton text.
	 */
	public void shuffleQBank() {
		mainGUI.questionOrder = new ArrayList<String>();
		mainGUI.questionOrder.add(qBank.getCorrectAns());
		mainGUI.questionOrder.add(qBank.getAnsA());
		mainGUI.questionOrder.add(qBank.getAnsB());
		mainGUI.questionOrder.add(qBank.getAnsC());
		Collections.shuffle(mainGUI.questionOrder);
	}

	/*
	 * Generates a new Question and Answers and sets them to buttons.
	 */
	public void setTheQuestionByCategory(String category) {
		if (category == null) {
			this.questionTxt = qBank.getRQ();
		} else {
			this.questionTxt = qBank.getQuestionByType(category);
		}
		shuffleQBank();
		setQuestion();
	}

	/*
	 * Resets the GUI to it's default state.
	 */
	public void resetGUI() {
		int reply = JOptionPane.showConfirmDialog(mainGUI, "Restart?, Are You Sure?");
		if (reply == JOptionPane.YES_OPTION) {
			timer.myTimer.stop();
			mainGUI.dispose();
			ZillionaireGUI newGUI = new ZillionaireGUI();
			newGUI.setVisible(true);
		}
	}

	/*
	 * actions to perform if incorrect answer.
	 */
	public void incorrectAction() {
		playIncorrect();
		timer.myTimer.stop();
		displayMsg("Game Over!, Try Again?");
	}

	public void updateUIAction() {
		updateCredit(0);
		mainGUI.setLblCredits("Credits: " + "\u00A3" + getCredit(0));
		mainGUI.incrementLv();
	}

	public void setByDifficulty(int difficulty) {
		if (mainGUI.difficulty == 0) {
			this.questionTxt = qBank.getRQ();
		} else if (mainGUI.difficulty != question.getDifficulty()) {
			setByDifficulty(difficulty);
		}
		shuffleQBank();
		setQuestion();

	}

	public void playBackground() {
		try {
			File backgroundMusic = new File("millionaire.wav");
			Clip backgroundClip = AudioSystem.getClip();
			backgroundClip.open(AudioSystem.getAudioInputStream(backgroundMusic));
			backgroundClip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Plays the background music
	 */
	public void playStart() {
		try {
			File startMusic = new File("start.wav");
			Clip startClip = AudioSystem.getClip();
			startClip.open(AudioSystem.getAudioInputStream(startMusic));
			startClip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Plays the correct sound
	 */
	public void playCorrect() {
		try {
			File correctMusic = new File("correct.wav");
			Clip correctClip = AudioSystem.getClip();
			correctClip.open(AudioSystem.getAudioInputStream(correctMusic));
			correctClip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Plays the incorrect sound
	 */
	public void playIncorrect() {
		try {
			File failMusic = new File("incorrect.wav");
			Clip failClip = AudioSystem.getClip();
			failClip.open(AudioSystem.getAudioInputStream(failMusic));
			failClip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * Plays the winning sound
	 */
	public void playWon() {
		try {
			File winMusic = new File("win.wav");
			Clip winClip = AudioSystem.getClip();
			winClip.open(AudioSystem.getAudioInputStream(winMusic));
			winClip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
