package blackjack;

/**
 * Dealer in blackjack game that is responsible for deal cards to all players
 * 
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 */
public class Dealer extends Player {
	private Deck deck; // deck for black jack game
	
	/**
	 * Creates new Dealer player, new deck and shuffles the deck
	 * @param mainFrame
	 */
	public Dealer(MainFrame mainFrame) { // needs main frame to create new player object
		super("Dealer", "NORTH", mainFrame); // super class is player
		this.deck = new Deck();
		this.deck.shuffle();
	}
	
	/**
	 * Shuffle's the deck , because it's the Dealer's role to do that in the game
	 */
	public void shuffle() {
		deck.shuffle();
	}
	
	/**
	 * Deals one card to one player
	 * @return nextCard in deck
	 */
	public Card deal() {
		return deck.nextCard();
	}
	
}
