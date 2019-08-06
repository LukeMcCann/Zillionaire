
/*
 * Class FIleHandler
 * Creates a FileHandler Object
 * @param GameController - is passed the GameControler class a a parameter.
 * @author Luke McCann - U1364096 - 20/02/2016
 * @updated 05/04/2015
 */

import java.io.*;
import javax.swing.JOptionPane;

public class FileHandler {

	GameController gC;

	public FileHandler(GameController gC) throws IOException, SecurityException {
		this.gC = gC;
	}

	/*
	 * Saves QuestionBank data to a .dat file.
	 * 
	 * @throws IOException
	 */
	public void saveToFile() throws IOException {
		try {
			FileOutputStream fOs = new FileOutputStream(new File("QuestionData.dat"));
			ObjectOutputStream oOs = new ObjectOutputStream(fOs);
			oOs.writeObject(gC.qBank);
			oOs.flush();
			oOs.close();
			JOptionPane.showMessageDialog(gC.qGUI, "Save SuccessFul!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(gC.qGUI, "Error, Problem Saving Data.");
			System.err.println("Error: " + e);
		}
	}

	/*
	 * Loads the QuestionBank data from QuestionData.dat.
	 * 
	 * @throws IOException
	 * 
	 * @throws ClassNotFoundException
	 */
	public void loadFromFile() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fIs = new FileInputStream("QuestionData.dat");
			ObjectInputStream oIs = new ObjectInputStream(fIs);
			gC.qBank = (QuestionBank) oIs.readObject();
			gC.qGUI.list.setModel(gC.qBank);
			oIs.close();
			JOptionPane.showMessageDialog(gC.qGUI, "Loading Successful!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(gC.qGUI, "File Does Not Exist.");
			System.err.println("Error: " + e);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(gC.qGUI, "Problem Finding QuestionBank.");
			System.err.println("Error: " + e);
		}
	}

	/*
	 * @param String file - file to import from. Imports questions from a
	 * specified file.
	 */
	public void importCustomFile(String file) throws IOException {
		try {
			FileInputStream fIs = new FileInputStream(file);
			ObjectInputStream oIs = new ObjectInputStream(fIs);
			gC.qBank = (QuestionBank) oIs.readObject();
			gC.qGUI.list.setModel(gC.qBank);
			oIs.close();
			JOptionPane.showMessageDialog(gC.mainGUI, "Import Successful!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(gC.qGUI, "File Does Not Exist.");
			System.err.println("Error: " + e);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(gC.qGUI, "Problem Finding QuestionBank.");
			System.err.println("Error: " + e);
		}
	}

	/*
	 * Saves QuestionBank data to a custom file
	 * 
	 * @throws IOException
	 */
	public void exportAsFile(String file) throws IOException {
		try {
			FileOutputStream fOs = new FileOutputStream(new File(file));
			ObjectOutputStream oOs = new ObjectOutputStream(fOs);
			oOs.writeObject(gC.qBank);
			oOs.flush();
			oOs.close();
			JOptionPane.showMessageDialog(gC.mainGUI, "Export Successful!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(gC.qGUI, "Error, Problem Saving Data.");
			System.err.println("Error: " + e);
			
		}
	}
}
