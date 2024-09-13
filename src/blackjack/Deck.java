package blackjack;

import java.util.ArrayList;
import java.util.Collections;
/**
 * A deck of cards for any card game
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 *
 */
public class Deck {
	static final String[] FACES = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"}; // faces of cards
	static final String[] SUITS = {"spade","club","diamond","heart"}; // suits of cards
	
	private ArrayList<Card> stackOfCards = new ArrayList<>(); // or a deck of cards
	private int currentIndex; // index of the card on top
	
	/**
	 * Creates the a deck using cards by creating objects of Card
	 */
	public Deck(){
		for(String face: FACES) {
			for (String suit : SUITS) {
				Card card = new BlackjackCard(face, suit);
				stackOfCards.add(card);
			}
		}
		currentIndex = 51; // The top of the card is numbered is at index 51
	}
	
	/**
	 * shuffles the cards
	 */
	public void shuffle() {
		Collections.shuffle(stackOfCards);
	}
	
	/**
	 * returns the next card in Deck
	 * @return next card
	 */
	public Card nextCard() {
		int index = currentIndex;
		currentIndex--;
		if(currentIndex<0) { // if index is negative, means that the deck has no cards left
			index=51; // index back to the top of the card
			shuffle(); // re shuffles the deck so the deck can be re-used
		}
		return stackOfCards.get(index);
	}
	
	/**
	 * get the current index of the card
	 * @return currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	/**
	 * gets the stack of cards
	 * @return stack of cards or deck
	 */
	public ArrayList<Card> getStackOfCards() {
		return stackOfCards;
	}

}
