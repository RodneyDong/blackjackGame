package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainFrameTest {

	List<Player> playerList;
	Dealer dealer;
	Card heartsA;
	Card hearts4;
	Card diamondsQ;
	Card clubs10;
	Card spades2;
	MainFrame frame;
	Player east, south, west;

	
	@BeforeEach
	void setUp() throws Exception {
		frame = new MainFrame();
		playerList = frame.getPlayerList();
		east = playerList.get(0);
		south = playerList.get(1);
		west = playerList.get(2);
		dealer = (Dealer)playerList.get(3);
		heartsA = new BlackjackCard("A", "heart");
		hearts4 = new BlackjackCard("4", "heart");
		diamondsQ = new BlackjackCard("Q", "diamond");
		clubs10 = new BlackjackCard("10", "club");
		spades2 = new BlackjackCard("2", "spade");
	}

	@Test
	void testDetermineWinner() {
		dealer.addCardToHand(clubs10);
		dealer.addCardToHand(diamondsQ);//dealer: 20
		east.addCardToHand(clubs10);
		east.addCardToHand(heartsA); //east: 21
		south.addCardToHand(spades2);
		south.addCardToHand(heartsA); //south: 13
		west.addCardToHand(clubs10);
		west.addCardToHand(heartsA); //east: 21
		frame.determineWinner();
		assertEquals(1, dealer.getWin());
		assertEquals(0, south.getWin());
		assertEquals(1, east.getWin());
		assertEquals(1, west.getWin());
	}

}
