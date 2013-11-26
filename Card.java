package cardgames;
//import java.util.Arrays;

public class Card {	

    private Number numbr;
    private Suit suit;
    
    Card(Number numbr, Suit suit) {
        this.numbr = numbr;
        this.suit = suit;
    }

    public Number numbr() { 
        return numbr; 
    }
    public Suit suit() { 
        return suit; 
    }
	
// pretty printing
    public String toString(){
	return (numbr + " van " + suit + "\n"); 
	}
	
	// vergelijken van  sets en kaartnummers
    public int compareTo(Card card){
    int vergelijkset = this.suit().compareTo(card.suit()) ;
		
    if(vergelijkset == 0){
    int vergelijknummer = this.numbr().compareTo(card.numbr()) ;
    return vergelijknummer;
    }else{
    return vergelijkset;
    }		 
   }
    }
