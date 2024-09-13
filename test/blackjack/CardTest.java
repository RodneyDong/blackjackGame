package blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {

	Card heartsA;
	Card hearts4;
	Card diamondsQ;
	Card clubs10;
	
	@BeforeEach
	void setUp() throws Exception {
		heartsA = new Card("A", "heart");
		hearts4 = new Card("4", "heart");
		diamondsQ = new Card("Q", "diamond");
		clubs10 = new Card("10", "club");
	}

	@Test
	void testIsPictured() {
		assertTrue(heartsA.isPictured());
		assertFalse(hearts4.isPictured());
		assertFalse(clubs10.isPictured());
	}
	
	@Test
	void testGetValue() {
		assertTrue(hearts4.getValue()==4);
		assertTrue(heartsA.getValue()==1);
		assertTrue(diamondsQ.getValue()==12);
		assertTrue(clubs10.getValue()==10);
	}
	
	@Test
	void testToString() {
		String expected = "[4, heart]";
		String actual = hearts4.toString();
		assertEquals(actual, expected);
		
		expected = "[Q, diamond]";
		actual = diamondsQ.toString();
		assertEquals(actual, expected);
	}

}
