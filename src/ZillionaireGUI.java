
/*
 * Class ZillionaireGUI
 * Creates a ZillionaireGUI object
 * main GUI for the Zillionaire game.
 * @author Luke McCann 23/02/2016
 * @updated Luke McCann 12/04/2016
 */

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ZillionaireGUI extends JFrame {
	GameController gC;
	ZillionaireGUI thisJFrame = this;
	Fiftyfifty fiftyFifty;
	private ArrayList<String> askNew;

	private JButton answerButtons[] = new JButton[4];
	private String[] categoryComboBoxStrings = { "General Knowledge", "Science", "Music" };
	private JPanel contentPane;
	private JTextField playerNumTf;
	JComboBox<String> categoryComboBox;
	ArrayList<String> questionOrder = new ArrayList<String>();
	private String sendDialog;
	private JButton fiftyBtn;
	private JButton askPublicBtn;
	private JButton phoneBtn;
	private JButton startBtn;
	private JButton answerA;
	private JButton answerB;
	private JButton answerC;
	private JButton answerD;
	private JLabel lblPlayer;
	private JLabel timerLbl;
	private JLabel lbl_500;
	private JLabel lbl_1000;
	private JLabel lbl_5000;
	private JLabel lbl_10000;
	private JLabel lbl_50000;
	private JLabel lbl_100000;
	private JLabel lbl_500000;
	private JLabel lbl_1000000;
	private JLabel lblCredits;
	String phoneFriend;
	int selected;
	int difficulty;
	String answer;

	/*
	 * @return JLabel - returns the value of lblPlayer
	 */
	public JLabel getPlayerLbl() {
		return this.lblPlayer;
	}

	/*
	 * @param String string - string to set playerLabel to.
	 */
	public void setPlayerLbl(String string) {
		this.lblPlayer.setText(string);
	}

	/*
	 * @param credits - setter for the credits label.
	 */
	public void setLblCredits(String credits) {
		this.lblCredits.setText(credits);
	}

	/*
	 * @return JLabel - returns timerLbl value
	 */
	public JLabel getTimerLbl() {
		return timerLbl;
	}

	/*
	 * @return JButton - returns the value of answerButton.
	 * 
	 * @param buttonIndex - the index value of the button.
	 */
	public JButton getAnswerButton(int buttonIndex) {
		return this.answerButtons[buttonIndex];
	}

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZillionaireGUI frame = new ZillionaireGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JButton getStartBtn() {
		return startBtn;
	}

	/**
	 * Create the frame.
	 */
	public ZillionaireGUI() {

		this.sendDialog = phoneFriend;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 983, 502);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem newGameBtn = new JMenuItem("New Game...");
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gC.resetGUI();
			}
		});
		mnFile.add(newGameBtn);

		JMenuItem restartBtn = new JMenuItem("Restart...");
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gC.resetGUI();
			}
		});
		mnFile.add(restartBtn);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem qDatabaseBtn = new JMenuItem("Question Database...");
		qDatabaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				gC.createQGUI();
			}
		});

		mnFile.add(qDatabaseBtn);

		JSeparator separator_3 = new JSeparator();
		mnFile.add(separator_3);

		JMenu importMenu = new JMenu("Import...");
		mnFile.add(importMenu);

		JMenuItem mntmQuestionsFromFile = new JMenuItem("Questions from file...");
		mntmQuestionsFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = JOptionPane
						.showInputDialog("Enter A File Name, Note It Must Be Readable By The Programme.");
				try {
					gC.fileHandler.importCustomFile(file);
				} catch (IOException e1) {
					System.err.println("Error: " + e);
					e1.printStackTrace();
				}
			}
		});
		importMenu.add(mntmQuestionsFromFile);

		JMenu exportMenu = new JMenu("Export...");
		mnFile.add(exportMenu);

		JMenuItem questionToFileBtn = new JMenuItem("Questions to file...");
		questionToFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String file = JOptionPane.showInputDialog("Enter A File Name.");
				try {
					gC.fileHandler.exportAsFile(file);
				} catch (IOException e) {
					System.err.println("Error: " + e);
					JOptionPane.showMessageDialog(gC.qGUI, "An Error Occured While Creating You're FIle.");
					e.printStackTrace();
				}
			}
		});
		exportMenu.add(questionToFileBtn);

		JSeparator separator_7 = new JSeparator();
		mnFile.add(separator_7);

		JMenuItem exitBtn = new JMenuItem("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(gC.qGUI, "Do You Really Want To Quit?");
				if (reply == JOptionPane.YES_OPTION) {
					gC.timer.myTimer.stop();
					System.exit(0);
				} else {
					// close JOptionPane
				}
			}
		});
		mnFile.add(exitBtn);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem setDifficultyBtn = new JMenuItem("Difficulty...");
		setDifficultyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int difficulty = Integer.parseInt(JOptionPane.showInputDialog("Enter Difficulty..."));

			}
		});
		mnEdit.add(setDifficultyBtn);

		JSeparator separator_1 = new JSeparator();
		mnEdit.add(separator_1);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help...");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				HelpGUI hg1 = new HelpGUI();
				hg1.setModal(true);
				hg1.setVisible(true);
			}
		});
		mnHelp.add(mntmHelp);

		JSeparator separator_9 = new JSeparator();
		mnHelp.add(separator_9);

		JMenuItem mntmAbout = new JMenuItem("About...");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutGUI newAbout = new AboutGUI();
				newAbout.setModal(true);
				newAbout.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		contentPane.add(titlePanel, BorderLayout.NORTH);

		JLabel label_9 = new JLabel(" ");
		label_9.setPreferredSize(new Dimension(10, 14));
		titlePanel.add(label_9);

		JLabel titleLbl = new JLabel("Who Wants To Be A Zillionaire?");
		titleLbl.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		titlePanel.add(titleLbl);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel belowTitlePanel = new JPanel();
		panel_4.add(belowTitlePanel, BorderLayout.NORTH);

		JLabel label_10 = new JLabel(" ");
		label_10.setPreferredSize(new Dimension(10, 14));
		belowTitlePanel.add(label_10);

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(200, 23));
		panel_4.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label_12 = new JLabel(" ");
		label_12.setPreferredSize(new Dimension(100, 40));
		leftPanel.add(label_12);

		JPanel comboPanel = new JPanel();
		leftPanel.add(comboPanel);

		categoryComboBox = new JComboBox(categoryComboBoxStrings);
		categoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = categoryComboBox.getSelectedIndex();
			}
		});
		comboPanel.add(categoryComboBox);

		JLabel label_2 = new JLabel("                        ");
		label_2.setPreferredSize(new Dimension(100, 20));
		leftPanel.add(label_2);

		fiftyBtn = new JButton("50/50");
		fiftyBtn.setEnabled(false);
		fiftyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fiftyFifty = new Fiftyfifty(thisJFrame);
				thisJFrame.fiftyBtn.setEnabled(false);
			}
		});

		fiftyBtn.setPreferredSize(new Dimension(190, 23));
		leftPanel.add(fiftyBtn);

		JLabel label = new JLabel("             ");
		label.setPreferredSize(new Dimension(39, 5));
		leftPanel.add(label);

		phoneBtn = new JButton("Phone Friend");
		phoneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				phoneBtn.setEnabled(false);
				PhoneFriend phone = new PhoneFriend(thisJFrame);
			}
		});
		phoneBtn.setPreferredSize(new Dimension(190, 23));
		phoneBtn.setEnabled(false);
		leftPanel.add(phoneBtn);

		JLabel label_1 = new JLabel("                         ");
		label_1.setPreferredSize(new Dimension(75, 5));
		leftPanel.add(label_1);

		askPublicBtn = new JButton("Ask Public");
		askPublicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AskAudience askNew = new AskAudience(thisJFrame);
			}
		});
		askPublicBtn.setEnabled(false);
		askPublicBtn.setPreferredSize(new Dimension(190, 23));
		leftPanel.add(askPublicBtn);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(120, 20));
		leftPanel.add(lblNewLabel);

		JSeparator separator_8 = new JSeparator();
		separator_8.setPreferredSize(new Dimension(120, 2));
		leftPanel.add(separator_8);

		JLabel label_21 = new JLabel("");
		label_21.setPreferredSize(new Dimension(120, 5));
		leftPanel.add(label_21);

		JLabel playersLbl = new JLabel("Player");
		playersLbl.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		playersLbl.setPreferredSize(new Dimension(190, 20));
		leftPanel.add(playersLbl);

		lblPlayer = new JLabel("Player:");
		lblPlayer.setVisible(false);
		leftPanel.add(lblPlayer);

		lblCredits = new JLabel("Credits");
		lblCredits.setPreferredSize(new Dimension(120, 14));
		lblCredits.setVisible(false);
		leftPanel.add(lblCredits);

		JLabel lblCredits_1 = new JLabel("");
		lblCredits_1.setVisible(false);
		lblCredits_1.setPreferredSize(new Dimension(120, 14));
		leftPanel.add(lblCredits_1);

		JLabel lblCredits_2 = new JLabel("");
		lblCredits_2.setVisible(false);
		lblCredits_2.setPreferredSize(new Dimension(120, 14));
		leftPanel.add(lblCredits_2);

		JLabel lblCredits_3 = new JLabel("");
		lblCredits_3.setPreferredSize(new Dimension(120, 14));
		lblCredits_3.setVisible(false);
		leftPanel.add(lblCredits_3);

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.SOUTH);

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(100, 100));
		panel_4.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		timerLbl = new JLabel("30");
		timerLbl.setFont(new Font("Franklin Gothic Book", Font.BOLD, 30));
		timerLbl.setBackground(UIManager.getColor("Button.focus"));
		timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		timerLbl.setPreferredSize(new Dimension(80, 80));
		timerLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightPanel.add(timerLbl);

		JLabel label_19 = new JLabel(" ");
		label_19.setPreferredSize(new Dimension(130, 40));
		rightPanel.add(label_19);

		JLabel zilLbl = new JLabel("Zillionaire!");
		zilLbl.setHorizontalAlignment(SwingConstants.CENTER);
		zilLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		zilLbl.setPreferredSize(new Dimension(125, 14));
		rightPanel.add(zilLbl);

		JLabel label_20 = new JLabel(" ");
		rightPanel.add(label_20);

		lbl_1000000 = new JLabel("\u00A31,000,000");
		lbl_1000000.setPreferredSize(new Dimension(70, 14));
		rightPanel.add(lbl_1000000);

		lbl_500000 = new JLabel("\u00A3500,000");
		lbl_500000.setPreferredSize(new Dimension(60, 14));
		rightPanel.add(lbl_500000);

		JSeparator separator_4 = new JSeparator();
		separator_4.setPreferredSize(new Dimension(130, 2));
		rightPanel.add(separator_4);

		lbl_100000 = new JLabel("\u00A3100,000");
		lbl_100000.setPreferredSize(new Dimension(60, 14));
		rightPanel.add(lbl_100000);

		lbl_50000 = new JLabel("\u00A350,000");
		lbl_50000.setPreferredSize(new Dimension(60, 14));
		rightPanel.add(lbl_50000);

		lbl_10000 = new JLabel("\u00A310,000");
		lbl_10000.setPreferredSize(new Dimension(60, 14));
		rightPanel.add(lbl_10000);

		lbl_5000 = new JLabel("\u00A35000");
		lbl_5000.setPreferredSize(new Dimension(60, 14));
		rightPanel.add(lbl_5000);

		JSeparator separator_5 = new JSeparator();
		separator_5.setPreferredSize(new Dimension(130, 2));
		rightPanel.add(separator_5);

		lbl_1000 = new JLabel("\u00A31000");
		lbl_1000.setPreferredSize(new Dimension(60, 14));
		rightPanel.add(lbl_1000);

		lbl_500 = new JLabel("\u00A3500");
		lbl_500.setPreferredSize(new Dimension(60, 14));
		rightPanel.add(lbl_500);

		JPanel mainPanel = new JPanel();
		panel_4.add(mainPanel, BorderLayout.CENTER);

		JLabel label_8 = new JLabel(" ");
		label_8.setPreferredSize(new Dimension(300, 30));
		mainPanel.add(label_8);

		JLabel label_4 = new JLabel(" ");
		label_4.setPreferredSize(new Dimension(20, 15));
		mainPanel.add(label_4);

		startBtn = new JButton("Press Start To Begin!");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		startBtn.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 16));
		startBtn.setPreferredSize(new Dimension(600, 40));
		mainPanel.add(startBtn);

		JLabel label_3 = new JLabel("                                                    ");
		label_3.setPreferredSize(new Dimension(500, 70));
		mainPanel.add(label_3);

		answerA = new JButton("A)");
		answerA.setEnabled(false);
		answerA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				answerButtonPressed(0);
			}
		});
		answerButtons[0] = answerA;

		answerA.setHorizontalAlignment(SwingConstants.LEFT);
		answerA.setPreferredSize(new Dimension(300, 40));
		mainPanel.add(answerA);

		JLabel label_6 = new JLabel(" ");
		label_6.setPreferredSize(new Dimension(10, 14));
		mainPanel.add(label_6);

		answerB = new JButton("B)");
		answerB.setEnabled(false);
		answerB.setHorizontalAlignment(SwingConstants.LEFT);
		answerB.setPreferredSize(new Dimension(300, 40));
		mainPanel.add(answerB);

		answerB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				answerButtonPressed(1);
			}
		});
		answerButtons[1] = answerB;

		JLabel label_5 = new JLabel("           ");
		label_5.setPreferredSize(new Dimension(350, 14));
		mainPanel.add(label_5);

		answerC = new JButton("C)");
		answerC.setEnabled(false);
		answerC.setHorizontalAlignment(SwingConstants.LEFT);
		answerC.setPreferredSize(new Dimension(300, 40));
		mainPanel.add(answerC);

		answerC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				answerButtonPressed(2);
			}
		});
		answerButtons[2] = answerC;

		JLabel label_7 = new JLabel(" ");
		label_7.setPreferredSize(new Dimension(10, 14));
		label_7.setOpaque(true);
		mainPanel.add(label_7);

		answerD = new JButton("D)");
		answerD.setEnabled(false);
		answerD.setHorizontalAlignment(SwingConstants.LEFT);
		answerD.setPreferredSize(new Dimension(300, 40));
		mainPanel.add(answerD);

		answerD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				answerButtonPressed(3);
			}
		});
		answerButtons[3] = answerD;

		JLabel label_11 = new JLabel(" ");
		label_11.setPreferredSize(new Dimension(580, 50));
		mainPanel.add(label_11);

		gC = new GameController(this);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gC.playStart();
					int plNum = Integer.parseInt(playerNumTf.getText());
					lblPlayer.setOpaque(true);
					lblPlayer.setBackground(Color.YELLOW);
					gC.createPlayer(plNum);
					if (plNum > 0) {
						lblPlayer.setVisible(true);
						lblCredits.setVisible(true);
						lblPlayer.setText("Player1");
						lblCredits.setText("Credits: " + "\u00A3" + gC.getCredit(0));
					}
					if (plNum > 1) {
						lblPlayer.setText("Team");
						lblCredits.setText("Credits: " + "\u00A3" + gC.getCredit(0));
					}
					if (plNum > 4) {
						playerNumTf.setText("4");
					}
					btnStart.setEnabled(false);

					playerNumTf.setEditable(false);
				} catch (Exception a) {
					System.err.println("Something went wrong");
					playerNumTf.setEditable(true);
					playerNumTf.setText("Please only use numbers 1-4");
					btnStart.setEnabled(true);
				}
				// System.out.println("set question text");
				if (gC.qBank.size() <= 0) {
					System.out.println("Please Ensure Questions Exist.");
					playerNumTf.setEditable(true);
					JOptionPane.showMessageDialog(thisJFrame, "Please Ensure Questions Exist.");

				} else {
					fiftyBtn.setEnabled(true);
					phoneBtn.setEnabled(true);
					askPublicBtn.setEnabled(true);

					gC.setAButtonsEnabled();
					System.out.println("SEleceted: " + selected);
					if (selected == 2) {
						gC.setTheQuestionByCategory("music");
					} else if (selected == 1) {
						gC.setTheQuestionByCategory("science");
					} else {
						gC.setTheQuestionByCategory(null);
					}
				}
			}
		});

		playerNumTf = new JTextField();
		playerNumTf.setText("Enter Players");
		playerNumTf.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				playerNumTf.setText("");
			}

			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		mainPanel.add(playerNumTf);
		playerNumTf.setColumns(10);
		mainPanel.add(btnStart);

	}

	/*
	 * getsPlayerCredits to define which level they are on. Highlights the
	 * amount of Credits winnable in the round, Shows what level the player is
	 * up to.
	 */

	public void incrementLv() {
		int credit = gC.getCredit(0);
		switch (credit) {
		case 500:
			lbl_500.setOpaque(true);
			lbl_500.setBackground(Color.YELLOW);
			gC.setByDifficulty(1);
			break;
		case 1000:
			lbl_1000.setOpaque(true);
			lbl_1000.setBackground(Color.YELLOW);
			gC.qBank.getQuestionByDifficulty(2);
			break;
		case 5000:
			lbl_5000.setOpaque(true);
			lbl_5000.setBackground(Color.YELLOW);
			gC.qBank.getQuestionByDifficulty(3);
			break;
		case 10000:
			lbl_10000.setOpaque(true);
			lbl_10000.setBackground(Color.YELLOW);
			gC.qBank.getQuestionByDifficulty(3);
			break;
		case 50000:
			lbl_50000.setOpaque(true);
			lbl_50000.setBackground(Color.YELLOW);
			gC.qBank.getQuestionByDifficulty(4);
			break;
		case 100000:
			lbl_100000.setOpaque(true);
			lbl_100000.setBackground(Color.YELLOW);

			break;
		case 500000:
			lbl_500000.setOpaque(true);
			lbl_500000.setBackground(Color.YELLOW);
			gC.qBank.getQuestionByDifficulty(5);
			break;
		case 1000000:
			lbl_1000000.setOpaque(true);
			lbl_1000000.setBackground(Color.YELLOW);

		default:
			break;
		}
	}

	/*
	 * @param buttonNum int - index of the button pressed performs a list of
	 * actions when the button is pressed for validation and level increase.
	 */
	public void answerButtonPressed(int buttonNum) {
		// System.out.println(gC.qBank.getCorrAns());
		int plNum = Integer.parseInt(playerNumTf.getText());
		String a = questionOrder.get(buttonNum);
		// System.out.println(a);
		if (gC.v.checkCorrect(a)) {
			gC.updateUIAction();
			gC.playCorrect();
			if (gC.hasPlayerWon(0)) {
				gC.playWon();
				gC.displayMsg("Congratulations! You Won: " + "\u00A3" + gC.getCredit(0));

			} else {
				gC.setAButtonsEnabled();
				gC.setTheQuestionByCategory(null);
			}
		} else {
			gC.incorrectAction();
		}
	}

	public void setphoneFriend(String friendsAnswer) {
		this.phoneFriend = friendsAnswer;
	}

}
