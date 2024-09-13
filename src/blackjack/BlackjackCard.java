package blackjack;

/**
 * Single card instance for Blackjack card game, which has face, suit and cardImage. 
 * Inherits from Card override getValue() method.
 * 
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 * @see Card
 *
 */
public class BlackjackCard extends Card {
	
	/**
	 * Creates black jack cards
	 * @param face
	 * @param suit
	 */
	BlackjackCard(String face, String suit){
		super(face, suit);
	}
	
	/**
	 * gets a card's value in the game of black jack
	 */
	@Override // method overridden
	public int getValue() {
		int value = 0;
		if(!isPictured()) { // if card is already a number, just return the number in integer
			return Integer.parseInt(face);
		}
		switch(face) { // determines card value based on face
		case "A": value=11; break; // A is initially worth 11, but it can be 1 if total hand value exceeds 21
		case "J": // JQK are both worth 10 in the game of black jack
		case "Q": 
		case "K": value=10; break;
		}
		return value;
	}
	
	
}
