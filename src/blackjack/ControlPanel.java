package blackjack;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Control Panel for the black jack game that allows player send a command by clicking the buttons
 * ICS4U
 * @author Rodney Dong
 * @date January 18 2022
 * Student Number: 350706008
 */
public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel playerNameLbl = new JLabel("Player Name: "); // current player name on control panel
	JButton dealCardBtn; // deal card button on control panel
	JButton resultBtn; // result button on control panel
	JButton hitBtn; // hit button on control panel
	JButton passBtn; // pass button on control panel
	JButton configBtn; // configuration button on control panel
	JButton clearBtn; // clear button on control panel
	JButton endBtn; // exit button on control panel
	JButton clearScoreBtn; // clear score button on control panel
	MainFrame parent; // For access to the main frame of the game
	Player player; // current player
	
	/**
	 * Create control panel including two panels, one is West button panel, one is East button panel
	 * @param parent build bi-direction connection between main frame and control panel, 
	 * so that we can pass the command between them @see MainFrame
	 */
	public ControlPanel(MainFrame parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());
		
		add(buildButtonPanel(), BorderLayout.WEST); // Control panel on the left
		add(buildPlayerPanel(), BorderLayout.EAST); // Control panel on the right for each player
	}
	
	/**
	 * Builds the player panel with player's name, hit button and pass button
	 * @return the whole player panel
	 */
	private JPanel buildPlayerPanel() {
		JPanel playerButtonPnl = new JPanel(); 
		playerButtonPnl.add(playerNameLbl);
		// Hit Button
		hitBtn = new JButton("Hit");
		hitBtn.setEnabled(false);
		hitBtn.addActionListener(new ActionListener() { // Hit button action: adds a card to player's hand

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean failed = player.addCardToHand(parent.getDealer().deal());
				if(failed) {
					JOptionPane.showMessageDialog(parent, player.name.concat(", your hand value is greater or equal to 21! You can only do pass."), "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		playerButtonPnl.add(hitBtn);
		// Pass Button
		passBtn = new JButton("Pass");
		passBtn.addActionListener(new ActionListener() { // pass the button control to next player

			@Override
			public void actionPerformed(ActionEvent e) {
				Player player = parent.nextPlayer(); // Simply passes on to next player
				if(player.isDealer()) { // Dealer's actions are dealt by the computer
					parent.addCardToDealer();
				}
			}
			
		});
		passBtn.setEnabled(false);
		playerButtonPnl.add(passBtn);
		return playerButtonPnl;
	}

	/**
	 * Build the button panel on the west side
	 * @return the whole button panel
	 */
	private JPanel buildButtonPanel() {
		JPanel cardButtonPnl = new JPanel();
		
		dealCardBtn = new JButton("Deal Card");
		cardButtonPnl.add(dealCardBtn);
		dealCardBtn.addActionListener(new ActionListener() { // Deal card to one player each time button is clicked

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.deal();
			}
			
		});
		
		resultBtn = new JButton("Update Results");
		resultBtn.setEnabled(false); // Can't use update result before a round the been played
		resultBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // Calculate the result and determine who wins
				parent.calculateResult();			
			}
			
		});
		cardButtonPnl.add(resultBtn);
		
		configBtn = new JButton("Configure");
		cardButtonPnl.add(configBtn);
		configBtn.addActionListener(new ActionListener() { // Switches to the other Card Layout

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.switchCard(); // Switch card layout (there's only two Cards)
			}
			
		});
		
		clearBtn = new JButton("Clear");
		clearBtn.setEnabled(false); // Can't clear after a round is been played
		cardButtonPnl.add(clearBtn);
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // Clears all the cards in every player's hand
				parent.clearBoard();
			}
			
		});
		clearScoreBtn = new JButton("Clear Score");
		cardButtonPnl.add(clearScoreBtn);
		clearScoreBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) { // Resets the score board all to 0 wins
				parent.clearScore();
			}
			
		});
		
		endBtn = new JButton("Exit");
		cardButtonPnl.add(endBtn);
		endBtn.addActionListener(new ActionListener() { // Exit the program
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		
		
		return cardButtonPnl;
	}
	
	/**
	 * set the current player that should be controlling the player buttons
	 * @param player
	 */
	public void setCurrentPlayer(Player player) {
		this.player = player;
		playerNameLbl.setText(this.player.name);
	}
	
	/**
	 * update the current player name 
	 */
	public void updateCurrentPlayerName() {
		playerNameLbl.setText(this.player.name);
	}
	
	/**
	 * Enabled or disable the deal button
	 * @param boolean
	 */
	public void setEnabledDealBtn(boolean b) {
		dealCardBtn.setEnabled(b);
	}
	
	/**
	 * Enable or disable the hit button
	 * @param boolean
	 */
	public void setEnabledHitBtn(boolean b) {
		hitBtn.setEnabled(b);
		passBtn.setEnabled(b);
	}
	
	/**
	 * Enabled or disable the update button
	 * @param boolean
	 */
	public void setEnabledUpdateBtn(boolean b) {
		resultBtn.setEnabled(b);
	}
	
	/**
	 * Enable or disable the clear button
	 * @param boolean
	 */
	public void setEnabledClearBtn(boolean b) {
		clearBtn.setEnabled(b);
	}
	
}
