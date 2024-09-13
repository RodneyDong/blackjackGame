package blackjack;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JLabel;

/**
 * Includes all the actions (methods)  a player do in the game of Blackjack
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 */
public class Player {
	public static Hashtable<String, Position> seats = new Hashtable<>(); // Different positions according to each seat are arranged into a Hashtable to easy access
	static {
		seats.put("WEST", new Position(80, 250)); // the first card position of player
		seats.put("SOUTH", new Position(300, 500)); 
		seats.put("EAST", new Position(600, 250));
		seats.put("NORTH", new Position(300, 30));
	};

	protected String name; // default = seat name
	protected String seat; // player's seat name
	protected List<Card> hand = new ArrayList<>(); // Cards in player's "hand" are an ArrayList
	protected List<JLabel> lblList = new ArrayList<>(); // To display cards in a expected way
	protected int winCount = 0; // Player's win count
	protected int cardX; // position in x coordinate
	protected int cardY; // position in y coordinate
	protected MainFrame frame; // Player contains an empty frame so main frame received from methods can be assigned to
	
	/**
	 * Constructor of Player Class
	 * @param name of player
	 * @param seat of player
	 * @param main GUI frame
	 */
	public Player(String name, String seat, MainFrame frame) {
		this.frame = frame;
		this.name = name;
		this.seat = seat;
		Position pos = seats.get(seat); // get seat position from the hashtable that already created new positions
		cardX = pos.getX(); // set position
		cardY = pos.getY();
	}
	
	/**
	 * pluses on win count to the player
	 */
	public void win() {
		winCount++;
	}
	
	/**
	 * adds a card to player's hand, if player card exceed 21, then this method will return False
	 * @param card
	 * @return boolean
	 */
	public boolean addCardToHand(Card card) {
		boolean failed = false; 
		int value = getHandValue();
		if (value >= 21) {
			failed = true; // if handValue exceeds 21, player is busted, so no longer can addCardToHand
		} else { // if not busted
			hand.add(card); // add card to hand ArrayList
			frame.addCard(card, this); // draw card on board
		}
		return failed;
	}
	
	/**
	 * empties the player's hand (ArrayList)
	 */
	public void cleanHand() {
		hand.clear();
	}
	
	/**
	 * Get one player's cards' total value
	 * @return integer
	 */
	public int getHandValue() {
		int value = 0;
		int aceCount = 0;
		for (Card card : hand) { // for every card in hand
			value += card.getValue(); // adds each card's value to value
			if (card.face == "A") { // adds Ace count if there's an Ace
				aceCount++;
			}
		}
		while (value > 21 && aceCount > 0) { // if busted because Ace equals 11
			value -= 10; // correct my Ace from 11 to 1
			aceCount--; // adjust aceCount
		}
		return value; // returns total value
	}
	
	/**
	 * represent the player with the player's cards
	 */
	@Override
	public String toString() {
		return name + ":" + hand;
	}
	/**
	 * gets the number of cards in a player's hand
	 * @return Integer
	 */
	public Integer getHandSize() {
		return hand.size();
	}
	
	/**
	 * get player's name
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get current x coordinate position
	 * @return integer
	 */
	public int getCardX() {
		return cardX;
	}
	
	/**
	 * get current y coordinate position
	 * @return integer
	 */
	public int getCardY() {
		return cardY;
	}
	
	/**
	 * set player Name 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get the number of win count of a player
	 * @return integer
	 */
	public int getWin() {
		return winCount;
	}

	
	/**
	 * returns True or False if the player is dealer
	 * @return boolean
	 */
	public boolean isDealer() {
		return name.equals("Dealer");
	}
	
	/**
	 * Set win count
	 * @param i
	 */
	public void setWin(int i) {
		this.winCount = i;
	}
}
