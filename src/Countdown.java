
/*
 * Class Countdown
 * Creates a Countdown Object.
 * @author Luke McCann - U1364096  - 03/04/2016
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Countdown {

	int counter;
	Timer myTimer;

	/*
	 * Constructor for the Countdown class
	 * 
	 * @param JLabel countdownGUI - JLabel for the position of the timer on the
	 * GUI.
	 * 
	 * @param GameController gC - GamerController passed through as a variable
	 * Creates a Timer object and sets it to the JLabel on the GUI.
	 */
	public Countdown(JLabel countdownGUI, GameController gC, int seconds) {
		counter = seconds;

		myTimer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter--;
				// System.out.println(String.valueOf(counter));
				countdownGUI.setText(String.valueOf(counter));
				if (counter == 0) {
					gC.displayMsg("Out Of Time!, Retry?");
					;
					((Timer) e.getSource()).stop();
					System.out.println("out of time!");
				}
			}
		});
	}

	public void setCounter(int seconds) {
		this.counter = seconds;
	}
}
