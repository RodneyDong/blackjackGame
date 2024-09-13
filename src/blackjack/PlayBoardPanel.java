package blackjack;

import java.awt.Color;
import java.awt.GridLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 * The purpose of the class is to create a play board to display the card for each player and the game result
 * ICS4U
 * @author Rodney Dong
 * @date January 21 2022
 * Student Number: 350706008
 */
public class PlayBoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static Color blue; // background colors for play board
	static Color yellow;
	static Color green;
	static { 
		blue = new Color(114, 183, 244);
		yellow = new Color(244, 215, 114);
		green = new Color(183, 243, 79);		
	}
	
	private Color bkColor; //background color
	private List<JLabel> cardList = new ArrayList<>(); // for removing all cards from the Panel
	private List<JLabel> playerNameLabelList = new ArrayList<>(); // for resigning player name label
	private List<JLabel> resultNameLabelList = new ArrayList<>(); // for changing player name on result panel
	private List<JLabel> resultValueLabelList = new ArrayList<>(); // for changing result for each player on result panel
	private List<Player> playerList; // holds all players in the game
	private ImageIcon faceDownImg; // To cover up dealer's second card
	private JLabel faceDownLbl; // for remove face down label
	private MainFrame parent; // for access MainFrame's commands/methods
	
	/**
	 * Creates the play board panel, set background, set layer, and get image
	 * @param parent
	 */
	public PlayBoardPanel(MainFrame parent) {
		this.parent = parent;
		bkColor = green; // default green background
		this.setBackground(bkColor); 
		setLayout(null);
		URL url = getClass().getClassLoader().getResource("resources/backR.gif");
		faceDownImg = new ImageIcon(url);
	}
	
	/**
	 * add card to panel, not to hand
	 * @param card
	 * @param player
	 */
	public void addCard(Card card, Player player) {
		if(player.isDealer() && player.getHandSize()==2) { // if the dealer has two cards already
			faceDownLbl = new JLabel(faceDownImg); // cover the second card with face down card
			faceDownLbl.setLocation(player.getCardX()+30, player.getCardY());
			faceDownLbl.setSize(Card.CARD_WIDTH, Card.CARD_HEIGHT);
			add(faceDownLbl);
			parent.setEnabledHitBtn(true);
		}
		JLabel label = new JLabel(card.getCardImage());
		label.setLocation(player.getCardX(), player.getCardY());
		label.setSize(Card.CARD_WIDTH, Card.CARD_HEIGHT);
		cardList.add(label);
		player.lblList.add(label);
		// Every time when a card is added, a player's label on panel gets cleared and repainted
		// In order to make the second card go on top of the first card
		for (JLabel lbl: player.lblList) { // for every player's lblList (cardImages)
			this.remove(lbl);
		}
		for (int i = player.lblList.size()-1; i>=0; i--) { // for every label in from the last card to first card
			int currentXPosition = player.getCardX()+i*30; // prints the cards from the last to first card so that following cards are shown on top of each card
			JLabel label1 = player.lblList.get(i);
			label1.setLocation(currentXPosition, player.getCardY());
			label1.setSize(Card.CARD_WIDTH, Card.CARD_HEIGHT);
			add(label1);
		}
		repaint(); // repaint the whole panel 
	}
	/**
	 * Clears the play board and clears all the card in player's hand
	 */
	public void clearBoard() {
		for(JLabel card: cardList) { // remove every label in card list
			remove(card);
		}
		remove(faceDownLbl); // second card of dealer is covered by a face down card, so need to be removed
		for(Player player: playerList) { // remove all the cards in each player's hand
			player.cleanHand();
		}
	}
	
	/**
	 * sets player's name on the play board
	 * @param playerList
	 */
	public void setPlayerName(List<Player> playerList) {
		for (int i=0; i<playerList.size(); i++) { // Set every player's name according to playerList
			Player player = playerList.get(i);
			JLabel nameLbl = new JLabel(player.getName());
			nameLbl.setLocation(player.getCardX()-60, player.getCardY());
			nameLbl.setSize(70, 30);
			add(nameLbl);
			playerNameLabelList.add(nameLbl);	
		}
	}
	
	/**
	 * Set the game result and score panel on play board
	 * @param playerList
	 */
	public void setGameResultPanel(List<Player> playerList) {
		this.playerList = playerList;
		// put label into JPanel, put the JPanel to specific location.
		Border borderLine = BorderFactory.createTitledBorder("Game Result");
		JPanel resultPnl = new JPanel();
		resultPnl.setBorder(borderLine);
		resultPnl.setLayout(new GridLayout(4,2));
		resultPnl.setBackground(Color.yellow);
		for (int i=0; i<playerList.size(); i++) { // for i based on size of Player's list to access playerList by index
			Player player = playerList.get(i);
			JLabel resultNameLbl = new JLabel(player.getName());
			resultPnl.add(resultNameLbl);
			resultNameLabelList.add(resultNameLbl);
			
			JLabel resultValueLbl = new JLabel("" + player.getWin());
			resultPnl.add(resultValueLbl);
			resultValueLabelList.add(resultValueLbl);
			
			resultPnl.setLocation(800,20);
			resultPnl.setSize(200,200);
			add(resultPnl);
		}
	}
	
	/**
	 * Update every player's name, no matter if the name was changed or not
	 */
	public void updatePlayerName() {
		for(int i=0; i<3; i++) { // for every player's name, excluding dealer so there's only 3 players
			String name = playerList.get(i).getName();
			playerNameLabelList.get(i).setText(name); // update player name for name label
			resultNameLabelList.get(i).setText(name); // update player name on result score board
		}
	}
	
	/**
	 * Remove face down card that is covering the second card of the dealer
	 */
	public void removeFaceDownCard() {
		remove(faceDownLbl);
		repaint();
	}
	
	/**
	 * Update each player's result in label
	 */
	public void updateResult() {
		for(int i=0; i<4; i++) { // Update result for every player including dealer
			Player player = playerList.get(i);
			int win = player.getWin();
			JLabel label = resultValueLabelList.get(i);
			label.setText(""+ win);
		}
		
	}
}
