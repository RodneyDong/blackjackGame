package blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

	Player player1;
	Player player2;
	Player player3;
	Card heartsA;
	Card hearts4;
	Card diamondsQ;
	Card clubs10;
	Card spades2;
	MainFrame frame;

	
	@BeforeEach
	void setUp() throws Exception {
		frame = new MainFrame();
		heartsA = new BlackjackCard("A", "heart");
		hearts4 = new BlackjackCard("4", "heart");
		diamondsQ = new BlackjackCard("Q", "diamond");
		clubs10 = new BlackjackCard("10", "club");
		spades2 = new BlackjackCard("2", "spade");
		
		player1 = new Player("Rodney", "WEST", frame);
		player1.addCardToHand(heartsA);
		player1.addCardToHand(hearts4);
		player1.addCardToHand(clubs10);
		
		player2 = new Player("Dong", "EAST", frame);
		player2.addCardToHand(spades2);
		player2.addCardToHand(hearts4);
		player2.addCardToHand(clubs10);
		
		player3 = new Player("John", "SOUTH", frame);
		player3.addCardToHand(spades2);
		player3.addCardToHand(hearts4);
		player3.addCardToHand(heartsA);	// 17	
	}

	@Test
	void testToString() {
		assertEquals("John:[[A, heart], [4, heart], [10, club]]", player1.toString());
	}

	@Test
	void testAddCardToHand() {
		Player rodney = new Player("Rodney", "EAST", frame);
		rodney.addCardToHand(clubs10);
		assertEquals(1, rodney.getHandSize()); 
		assertEquals(clubs10, rodney.hand.get(0));
	}
	
	@Test
	void testGetHandValueWithAce() {
		int actual = player3.getHandValue();
		assertEquals(17, actual);
	}
	
	@Test
	void testGetHandValueWithoutAce() {
		int actual = player2.getHandValue();
		assertEquals(16, actual);
	}
	
	@Test 
	void test2Ace(){
		player3.addCardToHand(heartsA);
		int actual = player3.getHandValue();
		assertEquals(18, actual);		
	}
	
	@Test
	void test3Ace() {
		player3.addCardToHand(heartsA);
		player3.addCardToHand(heartsA);
		int actual = player3.getHandValue();
		assertEquals(19, actual);				
	}
	
	@Test
	void test4Ace() {
		player3.addCardToHand(heartsA);
		player3.addCardToHand(heartsA);
		player3.addCardToHand(heartsA);
		int actual = player3.getHandValue();
		assertEquals(20, actual);				
	}
	
	@Test 
	void test1AceBusted(){
		player3.addCardToHand(diamondsQ);
		player3.addCardToHand(diamondsQ);
		int actual = player3.getHandValue();
		assertEquals(27, actual);		
	}
	
	@Test 
	void test2AceBusted(){
		player3.addCardToHand(heartsA);
		player3.addCardToHand(diamondsQ);
		player3.addCardToHand(hearts4);
		int actual = player3.getHandValue();
		assertEquals(22, actual);		
	}
	
	@Test 
	void test3AceBusted(){
		player3.addCardToHand(heartsA);
		player3.addCardToHand(heartsA);
		player3.addCardToHand(hearts4);
		player3.addCardToHand(diamondsQ);
		int actual = player3.getHandValue();
		assertEquals(23, actual);		
	}
	
	@Test 
	void test4AceBusted(){
		player3.addCardToHand(heartsA);
		player3.addCardToHand(heartsA);
		player3.addCardToHand(heartsA);
		player3.addCardToHand(hearts4);
		player3.addCardToHand(diamondsQ);
		int actual = player3.getHandValue();
		assertEquals(24, actual);		
	}
	
	@Test
	void testCleanHand() {
		player1.cleanHand();
		assertEquals(0, player1.getHandSize());
	}
	
	@Test
	void testGetHandSize() {
		int actual = player3.getHandSize();
		assertEquals(3, actual);
	}
}
