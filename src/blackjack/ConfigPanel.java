package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * The configuration panel of the game including configuring background, configuring player name and start button
 * 
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 */
public class ConfigPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private MainFrame parent; // For access to MainFrame's commands/methods
	private Color btnColor = new Color(110, 15, 135); // purple color
	
	/**
	 * Create configuration panel including configure player button, configure background button and start button 
	 * @param MainFrame
	 */
	public ConfigPanel(MainFrame parent) {
		this.parent = parent;
		setLayout(new BorderLayout());
		JPanel buttonPnl = new JPanel();
		buttonPnl.setLayout(new GridBagLayout());
		
		JButton addPlayerBtn = new JButton("Configure Players");
		JButton backgrounBtn = new JButton("Configure Background");
		JButton startBtn = new JButton("Start");
		JLabel emptyLbl = new JLabel("  "); // used to make space so that it looks better
		JLabel empty1Lbl = new JLabel("  ");

		GridBagConstraints gc = new GridBagConstraints();
		
		// changes gridx and gridy for every different button
		gc.gridx=1;
		gc.gridy=1;
		buttonPnl.add(addPlayerBtn, gc); 
		gc.gridx = 2;
		buttonPnl.add(backgrounBtn, gc);
		gc.gridx=1;
		gc.gridwidth=2;
		gc.gridy=2;
		gc.fill = GridBagConstraints.BOTH; // start button takes up two button's spaces
		buttonPnl.add(startBtn, gc);
		// Two empty labels to make two empty spaces at the bottom, just for betting looking, no practical use
		gc.gridy=3;
		buttonPnl.add(emptyLbl, gc);
		gc.gridy=4;
		buttonPnl.add(empty1Lbl, gc);
		
		startBtn.setBackground(btnColor);
		startBtn.setForeground(Color.white);
		
		add(buttonPnl, BorderLayout.SOUTH);
		add(new ImagePanel(), BorderLayout.CENTER);
		
		startBtn.addActionListener(this);
		backgrounBtn.addActionListener(this);
		addPlayerBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch(cmd) {
		case "Start": // start game
			parent.switchCard(); // switch card for card layout
			break;
		case "Configure Background": // for changing background color
			Color[] posibleValues = {new MyBackgroundColor(114, 183, 244,"Blue"), new MyBackgroundColor(244, 215, 114,"Yellow"), new MyBackgroundColor(183, 243, 79,"Green")};
			Color SelectedColor = (Color)JOptionPane.showInputDialog(
					parent, 
					"Choose One Background", // Select message
					"Configure Background", // Dialogue title
					JOptionPane.INFORMATION_MESSAGE,
					null,
					posibleValues, // possible background colors
					posibleValues[2]); // the first one is green
			parent.setBoardColor(SelectedColor);
			break;
		case "Configure Players": // for changing player name or seat
			ConfigPlayerDialog dialog = new ConfigPlayerDialog(parent); // create new dialogue for player configuration
			dialog.setVisible(true);
			break;
		default:
			return;
		}
	}

}
