package blackjack;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Single card for regular card game with face, suit and cardImage.
 * ICS4U
 * @author Rodney Dong
 * Student Number: 350706008
 * @date January 18 2022
 */
public class Card {
	static final String RESOURCE_PREFIX = "resources/"; // resource prefix
	static final int CARD_WIDTH = 125; // card image width
	static final int CARD_HEIGHT = 170; // card image length
	
	protected String face; // AKQJ12345678910
	protected String suit; // Heart, Diamond, Spades, Clubs
	ImageIcon cardImage;
	
	/**
	 *  Constructor for creating Card
	 *  Receives a face and suit
	 * @param face
	 * @param suit
	 */
	public Card(String face, String suit) {
		this.face = face;
		this.suit = suit;
		URL url = getClass().getClassLoader().getResource(RESOURCE_PREFIX.concat(suit).concat(face).concat(".gif")); //get specific card with given face and suit
		cardImage = new ImageIcon(url);
	}
	
	/**
	 * empty constructor
	 */
	public Card() { 
		
	}
	
	/**
	 * returns True if face is not a number
	 * returns False if face is a number
	 * @return boolean
	 */
	public boolean isPictured() {
		return "AJQK".contains(face);
	}
	
	/**
	 * @return card face value
	 */
	public int getValue() {
		int value = 0; // card value
		if(!isPictured()) { // if the face value is already a integer
			return Integer.parseInt(face); 
		}
		switch(face) { // gets card value based on face
		case "A": value=1; break;
		case "J": value=11; break;
		case "Q": value=12; break;
		case "K": value=13; break;
		}
		return value;
	}
	
	/**
	 * get the face in String
	 * @return face
	 */
	public String getFace() {
		return face;
	}
	
	/**
	 * get the card image
	 * @return image of card
	 */
	public ImageIcon getCardImage() {
		return cardImage;
	}
	
	/**
	 * represents the card in our own way
	 */
	@Override
	public String toString() {
		return "[" + face + ", " + suit + "]";
	}
	
	
}
