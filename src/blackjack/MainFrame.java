package blackjack;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * MainFrame of the game of black jack
 * ICS4U
 * @author Rodney Dong
 * @date January 20 2022
 * Student Number: 350706008
 *
 */

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout; // layout for MainFrame
	private PlayBoardPanel playBoardPnl; // main panel that holds all cards
	private ControlPanel buttonPnl; // for sending command for game
	private List<Player> playerList = new ArrayList<>(); // list of players
	private Player currentPlayer; // current player
	private int index = 0; // The index used to determine the current player in playerList
	private Dealer dealer; // Dealer of game
	
	/**
	 * Creates the main frame, sets CardLayout and Container of cards (CardLayout)
	 */
	public MainFrame() {
		init();
		
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);

		this.add(new ConfigPanel(this));
		this.add(buildBoardPanel());

		buildPlayerList();
	}
	
	/**
	 * Sets initial setup for the MainFrame
	 */
	private void init() {
		setTitle("Blackjack Game");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL url = getClass().getClassLoader().getResource("resources/icon.jpg");
		Image icon = Toolkit.getDefaultToolkit().getImage(url);
		setIconImage(icon);
		setResizable(false); // disable changing frame size
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width-this.getSize().width)/2, (dim.height-this.getSize().height)/2); //Set the location in the middle of the screen
	}
	
	/**
	 * build default player name by seat 
	 * name and seat are the same if no player's name are assigned to the seat
	 */
	private void buildPlayerList() {
		// Default player name, matches seat
		Player westPlayer = new Player("WEST", "WEST", this);
		Player southPlayer = new Player("SOUTH", "SOUTH", this);
		Player eastPlayer = new Player("EAST", "EAST", this);
		dealer = new Dealer(this);
		
		playerList.add(eastPlayer);
		playerList.add(southPlayer);
		playerList.add(westPlayer);
		playerList.add(getDealer());
		currentPlayer = playerList.get(index);
		buttonPnl.setCurrentPlayer(currentPlayer); // set current playing player
		playBoardPnl.setPlayerName(playerList);
		playBoardPnl.setGameResultPanel(playerList);
	}
	
	/**
	 * Set the play board, on top is the control panel and the center is the play board panel
	 * @return the whole board panel
	 */
	private JPanel buildBoardPanel() {
		JPanel mainPnl = new JPanel();

		mainPnl.setLayout(new BorderLayout());
		buttonPnl = new ControlPanel(this);
		mainPnl.add(buttonPnl, BorderLayout.NORTH);

		playBoardPnl = new PlayBoardPanel(this);
		mainPnl.add(playBoardPnl, BorderLayout.CENTER);

		return mainPnl;
	}
	
	/**
	 * Switch Card Layout
	 */
	void switchCard() {
		cardLayout.next(this.getContentPane());
	}
	
	/**
	 * Set Background Color
	 * @param backgroundColor
	 */
	public void setBoardColor(Color backgroundColor) {
		playBoardPnl.setBackground(backgroundColor);
	}
	
	/**
	 * Add a card to a player's hand
	 * @param card
	 * @param player
	 */
	public void addCard(Card card, Player player) {
		playBoardPnl.addCard(card, player);
	}
	
	/**
	 * Dead card to one player
	 */
	public void deal() {
		currentPlayer.addCardToHand(getDealer().deal());
		index++; // changes current player by changing the index
		if(currentPlayer.isDealer() && currentPlayer.getHandSize()==2) { //if dealer has two cards, it means that everybody has two cards
			buttonPnl.setEnabledDealBtn(false); // disable deal button after everyone has two cards
		}
		if (index>playerList.size()-1) { // resets index to 0 if index is out of range or one round has already gone
			index=0;
		}
		currentPlayer = playerList.get(index); // changed current player by changing index
	}
	
	/**
	 * Clears all the card and resets the game to initial state in preparation for next round
	 */
	public void clearBoard() {
		for(Player player: playerList) { // Clears each player's label card list
			player.lblList.clear();
		}
		buttonPnl.setEnabledDealBtn(true);
		playBoardPnl.clearBoard();
		index=0; // set current player back to the first player, which is the player in the east seat
		currentPlayer = playerList.get(index);
		buttonPnl.setCurrentPlayer(currentPlayer);
		playBoardPnl.repaint();
		buttonPnl.setEnabledClearBtn(false);
	}
	
	/**
	 * Set a player's name according to the seat
	 * @param seat
	 * @param name
	 */
	public void setPlayerName(String seat, String name) {
		for(Player player: playerList) { // iterates through the playerList
			if(player.seat.equals(seat)) { // If seat is equal to the seat that the user wants to change the name
				player.setName(name); // changes player name
			}
		}
		playBoardPnl.updatePlayerName(); // update player name on play board
		buttonPnl.updateCurrentPlayerName(); // update player name on control panel
	}
	
	/**
	 * Determine the winner for each round
	 * 3 players are competing again Dealer
	 */
	public void determineWinner() {
		int dealerTotal = dealer.getHandValue();
		for(int i=0; i<playerList.size()-1; i++) { // for each player in player list
			Player player = playerList.get(i);
			int playerTotal = player.getHandValue();
			if(playerTotal>21) { // if player busted, dealer always win
				dealer.win(); // dealer win count plus 1
			}else if(dealerTotal>21) { // if dealer busted and player is not busted, player win
				player.win(); // player win count plus 1
			}else if(playerTotal==dealerTotal) { // if it's a tie, no one wins
				
			}else if(playerTotal>dealerTotal) { // if player's cards are greater than dealer's, player wins
				player.win();
			}else {
				dealer.win(); // The only left case is that dealer's cards are greater than player's so dealer wins
			}
		}
	}
	
	/**
	 * get the dealer 
	 * @return dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
	 * Used by pass button to change current player to next player
	 * @return
	 */
	public Player nextPlayer() {
		index++;
		currentPlayer = playerList.get(index);
		buttonPnl.setCurrentPlayer(currentPlayer);
		return currentPlayer;
	}

	/**
	 * After every other player passed, add cards to dealer until meeting the requirement
	 */
	public void addCardToDealer() {
		playBoardPnl.removeFaceDownCard();
		int value = dealer.getHandValue();
		while(value<17) { // only gets another card if Dealer's value is less than 17, otherwise, dealer will not hit
			dealer.addCardToHand(dealer.deal());
			value = dealer.getHandValue();
		}
		index = 0;
		buttonPnl.setEnabledDealBtn(false);
		buttonPnl.setEnabledHitBtn(false);
		buttonPnl.setEnabledUpdateBtn(true);
	}
	
	/**
	 * Calculate the result including determine winner and update result to score board
	 */
	public void calculateResult() {
		determineWinner();
		playBoardPnl.updateResult();
		buttonPnl.setEnabledUpdateBtn(false);
		buttonPnl.setEnabledClearBtn(true);
	}

	/**
	 * used just for unit test
	 * @return playerList
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	/**
	 * Enable or disable hit button
	 * @param boolean
	 */
	public void setEnabledHitBtn(boolean b) {
		buttonPnl.setEnabledHitBtn(b);
		
	}
	
	/**
	 * Clears the score on score board
	 */
	public void clearScore() {
		for (Player player: playerList) { // go through every player in playerList
			player.setWin(0);
		}
		playBoardPnl.updateResult();
	}
}
