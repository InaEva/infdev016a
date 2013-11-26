package cardgames;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testCompareTo(){
		Card c1 = new Card(Number.AAS, Suit.HARTEN);
		Card c2 = new Card(Number.TWEE, Suit.SCHOPPEN);
		
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c1.compareTo(c2) > 0);
		assertTrue(c2.compareTo(c1) < 0);
	}
}
