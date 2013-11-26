package cardgames;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DeckTest {
	Deck deck;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		deck = new Deck();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFillDeck() {
		deck.fill();

		String result = "leeg";
		try{
		 result = new Scanner(new File("C:\\Users\\Saint\\workspace\\cardgames\\src\\cardgames\\Deck.txt")).useDelimiter("\\Z")
				.next();
		}catch(Exception e){
			System.err.println("error: " + e);
		}
		
		assertEquals("result", result, this.deck.toString());
	}

	@Test
	public void testInsertAt() {
		Deck deck = new Deck();
		Card card1 = new Card(Number.AAS, Suit.RUITEN);
		Card card2 = new Card(Number.TWEE, Suit.RUITEN);
		Card card3 = new Card(Number.TWEE, Suit.KLAVEREN);
		
		deck.insertAt(card1, 0);
		deck.insertAt(card2, 1);
		deck.insertAt(card3, 2);
		
		
		Deck result = new Deck();
		Card[] temp = {card1, card2, card3};
		result.cardArray = temp;
		
//		assertEquals("InsertAt error:", result, deck);
		assertEquals("InsertAt error:", 0, result.compareTo(deck));
	}

	@Test
	public void testDelete() {
		Deck deck = new Deck();
		Card card1 = new Card(Number.AAS, Suit.RUITEN);
		Card card2 = new Card(Number.TWEE, Suit.RUITEN);
		Card card3 = new Card(Number.TWEE, Suit.KLAVEREN);
		
		Card[] temp = {card1, card2, card3};
		deck.cardArray = temp;		
		deck.delete(0);
		
		Deck deck2 = new Deck();
		Card[] temp2 = {card2, card3};
		deck2.cardArray = temp2;
		
//		assertEquals("result\n", deck, deck2);
		assertEquals("result\n", 0, deck.compareTo(deck2));
	}


	@Test
	public void testSequentialSearch() {
		Deck deck = new Deck();
		Card card1 = new Card(Number.AAS, Suit.RUITEN);
		Card card2 = new Card(Number.TWEE, Suit.RUITEN);
		Card card3 = new Card(Number.TWEE, Suit.KLAVEREN);
		
		deck.insertAt(card1, 0);
		deck.insertAt(card2, 1);
		deck.insertAt(card3, 2);
		
		
		assertEquals("result", deck.sequentialSearch(card1), 0);
	}

	@Test
	public void testSort() {
		Deck deck = new Deck();
		deck.fill();
		deck.shuffle();
		
		
		Deck deck2 = new Deck();
		deck2.fill();

		
		deck.sort();
//		assertEquals("result", deck, deck2);
		assertEquals("result", -1, deck.compareTo(deck2));
	}

	@Test
	public void testIsSorted() {
		Deck d= new Deck();
		d.fill();
		
		assertTrue("Gesorteerd", d.isSorted());
		
		//Pas op! het volgende is een op de 
		//80658175170943878571660636856403766975289505440883277824000000000000 
		//keer niet geldig
		d.shuffle();
		assertFalse("Ongesorteerd",d.isSorted());
	}

	@Test
	public void testBinarySearchCard() {
		Deck d = new Deck();
		d.fill();
		
		assertEquals("0", 0, d.binarySearch(new Card(Number.TWEE, Suit.KLAVEREN)));
		
		d.delete(0);
		assertEquals("1", -1, d.binarySearch(new Card(Number.TWEE, Suit.KLAVEREN)));
	}

	@Test
	public void testCompareTo() {
		Deck deck = new Deck();
		Deck deck2 = new Deck();
		Card card1 = new Card(Number.TWEE, Suit.RUITEN);
		Card card2 = new Card(Number.AAS, Suit.RUITEN);
		Card card3 = new Card(Number.TWEE, Suit.KLAVEREN);
		
		deck.insertAt(card1, 0);
		deck.insertAt(card2, 1);
		deck.insertAt(card3, 2);
		
		deck2.insertAt(card1, 0);
		deck2.insertAt(card2, 1);
		deck2.insertAt(card3, 2);
		assertEquals("equals", 0, 	deck.compareTo( deck2 ) 	);
		
		
		//Unequal because of size
		deck2 = new Deck();
		deck2.insertAt(card1, 0);
		deck2.insertAt(card2, 1);
		assertTrue("Size is less than", 	deck2.compareTo( deck ) < 0	);

		deck2 = new Deck();
		deck2.insertAt(card1, 0);
		deck2.insertAt(card2, 1);
		deck2.insertAt(card2, 2);
		deck2.insertAt(card2, 3);
		assertTrue("Size is greather than", deck2.compareTo( deck ) > 0 );
		
		//Unequals because of card order
		deck2 = new Deck();
		deck2.insertAt(card3, 0);
		deck2.insertAt(card1, 1);
		deck2.insertAt(card2, 2);
		assertTrue("Card order is less than", 	deck2.compareTo( deck ) < 0	);
		
		deck2 = new Deck();
		deck2.insertAt(card2, 0);
		deck2.insertAt(card1, 1);
		deck2.insertAt(card3, 2);
		assertTrue("Card order is greather than", 	deck2.compareTo( deck ) > 0	);
	}
	
}
