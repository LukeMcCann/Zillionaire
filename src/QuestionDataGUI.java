import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class QuestionDataGUI extends JDialog {
	public JList<Object> list = new JList<Object>();
	private final JPanel contentPanel = new JPanel();
	private JTextField answerTf;
	private JTextField questionTf;
	private JTextField categoryTf;
	private JTextField difficultyTf;
	private JTextField answerATf;
	private JTextField answerBTf;
	private JTextField answerCTf;
	
	GameController gC;
	
	public QuestionDataGUI(GameController gC) {
		this.gC = gC;
		//super();
		initGUI();
		
		/*test questions
		gC.qBank.addQuestion("2Difficulty, CorrectAns", "type", "ansA", "ansB", "ansC", "correctAns", 2);
		gC.qBank.addQuestion("5Difficulty", "type2", "ansA22", "ansB", "ansC2", "correctAns2", 5);
		gC.qBank.addQuestion("3Difficulty", "type", "ansA", "ansB", "ansC", "correctAnsddf", 3);
		gC.qBank.addQuestion("1Difficulty", "type", "ansA", "ansB", "ansC", "correctAnsIII", 1);
		gC.qBank.addQuestion("4Difficulty", "test", "ansA", "ansB", "ansC", "orrectAns", 4);
		list.setModel(gC.qBank);*/
	}

	/**
	 * Create the dialog.
	 */
	public void initGUI() {
		setResizable(false);
		setBounds(100, 100, 847, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(150, 23));
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblQuestion = new JLabel("Question:");
				lblQuestion.setPreferredSize(new Dimension(60, 14));
				panel.add(lblQuestion);
			}
			{
				questionTf = new JTextField();
				questionTf.setPreferredSize(new Dimension(140, 20));
				panel.add(questionTf);
				questionTf.setColumns(10);
			}
			{
				JLabel lblAnswer = new JLabel("Answer:");
				lblAnswer.setPreferredSize(new Dimension(60, 14));
				panel.add(lblAnswer);
			}
			{
				answerTf = new JTextField();
				answerTf.setMaximumSize(new Dimension(61, 23));
				panel.add(answerTf);
				answerTf.setColumns(10);
			}
			{
				JLabel lblCategory = new JLabel("Category:");
				lblCategory.setPreferredSize(new Dimension(60, 14));
				panel.add(lblCategory);
			}
			{
				categoryTf = new JTextField();
				panel.add(categoryTf);
				categoryTf.setColumns(10);
			}
			{
				JLabel lblDifficulty = new JLabel("Difficulty:");
				lblDifficulty.setPreferredSize(new Dimension(60, 14));
				panel.add(lblDifficulty);
			}
			{
				difficultyTf = new JTextField();
				panel.add(difficultyTf);
				difficultyTf.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(150, 23));
			contentPanel.add(panel, BorderLayout.EAST);
			{
				JLabel lblAnswer_1 = new JLabel("Answer2:");
				panel.add(lblAnswer_1);
			}
			{
				answerATf = new JTextField();
				panel.add(answerATf);
				answerATf.setColumns(10);
			}
			{
				JLabel lblAnswer_2 = new JLabel("Answer3:");
				panel.add(lblAnswer_2);
			}
			{
				answerBTf = new JTextField();
				panel.add(answerBTf);
				answerBTf.setColumns(10);
			}
			{
				JLabel lblAnswer_3 = new JLabel("Answer4:");
				panel.add(lblAnswer_3);
			}
			{
				answerCTf = new JTextField();
				panel.add(answerCTf);
				answerCTf.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 20));
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblQuestionDatabase = new JLabel("Question Database");
				panel.add(lblQuestionDatabase);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JButton btnRemove = new JButton("Remove...");
				btnRemove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						int index = list.getSelectedIndex();
						if(index != -1) {
							gC.qBank.removeElementAt(index);
						}
					}
				});
				{
					JButton btnAdd = new JButton("Add...");
					btnAdd.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent arg0) {
							try {
							    String q = questionTf.getText().toLowerCase();
							    String a = answerTf.getText().toLowerCase();
							    String c = categoryTf.getText().toLowerCase();
							    String ansA = answerATf.getText().toLowerCase();
							    String ansB = answerBTf.getText().toLowerCase();
							    String ansC = answerCTf.getText().toLowerCase();
							    int d = Integer.parseInt(difficultyTf.getText());
							        if(d <= 5 && d > 0) {
							        	gC.qBank.addQuestion(q, c, ansA, ansB, ansC, a, d);
								        list.setModel(gC.qBank);
							            clearAllTf();
							        } else {
								        JOptionPane.showMessageDialog(gC.mainGUI, "Only Numbers 1-5.");
							        }
							} catch(Exception e){
								JOptionPane.showMessageDialog(gC.mainGUI, "Error: " + e);
								System.err.println("Error: " + e);
							}
						}
					});
					panel.add(btnAdd);
				}
				{
					JButton btnSave = new JButton("Save...");
					btnSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								gC.fileHandler.saveToFile();
							} catch (IOException e) {
								e.printStackTrace();
								System.err.println("An Error Occured Whilst Saving.");
							}
						}
					});
					{
						JLabel label = new JLabel(" ");
						label.setPreferredSize(new Dimension(2, 14));
						panel.add(label);
					}
					panel.add(btnSave);
				}
				{
					JButton btnLoad = new JButton("Load...");
					btnLoad.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								gC.fileHandler.loadFromFile();
							} catch (ClassNotFoundException e) {
								System.err.println("Error: " + e);
								JOptionPane.showMessageDialog(gC.mainGUI, "Problem Loading File.");
								e.printStackTrace();
							} catch (IOException e) {
								JOptionPane.showMessageDialog(gC.mainGUI, "Problem Loading File");
								System.err.println("Error: " + e);
								e.printStackTrace();
							}
						}
					});
					panel.add(btnLoad);
				}
				{
					JLabel label = new JLabel(" ");
					label.setPreferredSize(new Dimension(400, 14));
					panel.add(label);
				}
				
				panel.add(btnRemove);
			}
			{
				JButton btnDone = new JButton("Done...");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				{
					JLabel label = new JLabel("");
					label.setPreferredSize(new Dimension(2, 0));
					panel.add(label);
				}
				panel.add(btnDone);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
				scrollPane.setPreferredSize(new Dimension(520, 190));
				panel.add(scrollPane);
				{
					JList<Object> list2 = new JList<Object>();
					list2.setPreferredSize(new Dimension(20, 50));
					scrollPane.setViewportView(list);
					
					}
				}
			}
	    }
	public void clearAllTf() {
		questionTf.setText("");
		answerTf.setText("");
		categoryTf.setText("");
		difficultyTf.setText("");
		answerATf.setText("");
		answerBTf.setText("");
		answerCTf.setText("");
	}
    }

