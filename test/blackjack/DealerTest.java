package blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {

	Dealer dealer;
	Card heartsA;
	Card hearts4;
	Card diamondsQ;
	Card clubs10;
	Card spades2;
	MainFrame frame;
	
	@BeforeEach
	void setUp() throws Exception {
		heartsA = new BlackjackCard("A", "heart");
		hearts4 = new BlackjackCard("4", "heart");
		diamondsQ = new BlackjackCard("Q", "diamond");
		clubs10 = new BlackjackCard("10", "club");
		spades2 = new BlackjackCard("2", "spade");
		frame = new MainFrame();
		dealer = new Dealer(frame);
	}

	@Test
	void testToString() {
		assertEquals("Dealer:[]", dealer.toString());
	}

	@Test
	void testAddCardToHand() {
		Dealer dealer = new Dealer(frame);
		dealer.addCardToHand(clubs10);
		assertEquals(1, dealer.getHandSize()); 
		assertEquals(clubs10, dealer.hand.get(0));
	}

	@Test
	void testGetHandValueWithAceBust() {
		dealer.addCardToHand(clubs10);
		dealer.addCardToHand(heartsA);
		dealer.addCardToHand(spades2);
		int actual = dealer.getHandValue();
		assertEquals(13, actual);
	}
	
	@Test
	void testGetHandValueWithAce() {
		dealer.addCardToHand(clubs10);
		dealer.addCardToHand(heartsA);
		int actual = dealer.getHandValue();
		assertEquals(21, actual);
	}
	
	@Test
	void testGetHandValueWithoutAce() {
		dealer.addCardToHand(clubs10);
		dealer.addCardToHand(diamondsQ);
		dealer.addCardToHand(spades2);
		int actual = dealer.getHandValue();
		assertEquals(22, actual);
	}
	
	@Test
	void testCleanHand() {
		dealer.cleanHand();
		assertEquals(0, dealer.getHandSize());
	}
	
	
	@Test
	void testGetHandSize() {
		int actual = dealer.getHandSize();
		assertEquals(0, actual);
	}

}
