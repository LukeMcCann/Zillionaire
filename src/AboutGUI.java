
/*
 * Class AboutGUI
 * A blank Dialog to hold informaiton about the Application
 * in text form.
 * @author Luke McCann - U1364096 - 04/02/2016 
 * @updated 23/02/2016
 * @updated 12/04/2016
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutGUI() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblHelp = new JLabel("About");
				panel.add(lblHelp);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JLabel lblDeveloperLukeMccann = new JLabel("Developer: Luke McCann - U1364096");
				panel.add(lblDeveloperLukeMccann);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JLabel lblZillionaireIsA = new JLabel("Zillionaire is a turn based quiz game for up to 4 Players");
				panel.add(lblZillionaireIsA);
			}
			{
				JLabel lblBasedOnThe = new JLabel("based on the popular quiz shows \"Who Wants To Be A Millionaire\" ");
				panel.add(lblBasedOnThe);
			}
			{
				JLabel lblNewLabel = new JLabel("and \"MasterMind\". Players compete accross cateorical questions");
				panel.add(lblNewLabel);
			}
			{
				JLabel lblIfOnePlayer = new JLabel(
						"if one player cannot answer a question it is passed to the next player");
				panel.add(lblIfOnePlayer);
			}
			{
				JLabel lblHoweverIfNo = new JLabel("however if no player can answer it is \"Game Over\". ");
				panel.add(lblHoweverIfNo);
			}
			{
				JLabel lblThePlayerWith = new JLabel(
						"The Player with the most credits, or who reaches a Zillion first, Wins.");
				panel.add(lblThePlayerWith);
			}
			{
				JLabel lblLifelinesAreUseful = new JLabel(
						"Lifelines are useful, but can only be used once, so be wary!");
				panel.add(lblLifelinesAreUseful);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
