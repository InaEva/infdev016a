package cardgames;
import java.util.Random;
public class Deck implements Comparable<Deck> {

    public  Card[] cardArray;

 
        Deck() {
                cardArray = new Card[0];
        }
        
        Deck(Card[] cards){
            cardArray = cards;
        }

        public void fill() {
            int suitable = Suit.values().length;  // waardes van de sets
            int numable = Number.values().length; // waardes van de nummers
            cardArray = new Card[suitable * numable];  // sets * nummers = 52
            
            int kaartnr = 0;
            for(int i = 0;i< suitable ; i++){    // toevoegen van de sets
                for(int o = 0; o < numable; o++){  //toevoegen van de nummers
                
                	cardArray[kaartnr] = new Card(Number.values()[o], Suit.values()[i]);  // kaartnummer bestaat uit nummerwaarde en setwaarde
                    kaartnr++; 
                    }
                }
            }
            
     

        
        public void insertAt(Card card, int index) {
            
        Card [] temp = new Card[cardArray.length + 1];  // temp nieuwe array met de waardes  + 1 (die toegevoegd gaat worden)
        for(int i = 0; i < temp.length; i++){
        	if(i < index){
        		temp[i] = cardArray[i]; 
        	} else if (i == index){
        		temp[i] = card; 
        	} else{
        		temp[i] = cardArray[i-1];
        	}
        }
        cardArray = temp;
        }
        
        public void delete(int index){
        	cardArray = delete(cardArray, index);
        }
        
        
        public Card[] delete(Card[] card, int index) {    // verwijderen van 1 kaart 
        Card [] temp = new Card[card.length - 1];      // totale lengte van de set  - 1 kaart 
        
        for(int i = 0; i < card.length; i++){    // loopen door het aantal
            if(i == index) {    // indien i gelijk is aan de index  
                continue;      // go go go  
            }
         if( i < index){      // indien  i kleiner is dan de index 
             temp[i] = card[i];   // temp[i]  wordt card[i] 
         } else {                   
             temp[i-1] = card[i];   // verwijder 1 kaart  
         }
        }
            return temp;   // return statement voor de nieuwe "temp"
            
        }
// 
        public void shuffle() {
        Card temp;
        Random r = new Random();
     
        
        for (int i = 0; i < cardArray.length; i++) {
            temp = cardArray[i];
            for(int z = 0; i< cardArray.length; i++){
            int x = r.nextInt(cardArray.length);
            cardArray[z] = cardArray[x];
            cardArray[x] = temp;
            }
            
            
        }
    }

// swapping them' cards ;D
        private void swap(int leftable, int rightable) {
           Card temp = cardArray[leftable];
           cardArray[leftable] = cardArray[rightable];
           cardArray[rightable] = temp;
            
        }

        /**
         * Een gegeven kaart moet worden opgezocht in de array, en de index ervan
         * moet als return worden teruggegeven. Zie [Hubbard p.30]
         * 
         * @param card
         *            de kaart die gezocht wordt
         * @return De index van de gevonden kaart
         */
        public int sequentialSearch(Card card) {
            for(int i = 0; i < cardArray.length; i++){
                if(cardArray[i].compareTo(card) == 0)
                    return i;
            } 
                int result = -1;
                return result;
        }

        /**
         * Legt de kaarten op volgorde. We nemen aan dat een deck op volgorde ligt,
         * als de volgorde hetzelfde is als na {@link #fillDeck()}
         */
        public void sort() {
          if(cardArray.length == 0)  
              return; 
          qckSort(cardArray, 0, cardArray.length -1);
        }
         
      public void  qckSort(Card [] cardArray, int left, int right){
      Card pivot = cardArray[left + (right - left) / 2];
      int leftable = left; 
      int rightable = right; 

      while (leftable <= rightable){
        while(cardArray[leftable].compareTo(pivot) < 0 ){
          leftable++;
        }

        while(cardArray[rightable].compareTo(pivot) > 0){
          rightable--;
        }

        if(leftable <= rightable){
          swap(leftable,rightable);
          leftable++;
          rightable--;
        }
      }
      if(left < rightable)
        qckSort(cardArray, left, rightable);
      if(leftable < right)
        qckSort(cardArray, leftable, right);

     }

        /**
         * Vertelt of het deck gesorteerd is.
         * @return
         */
        public boolean isSorted(){
               Deck s = new Deck(cardArray);
               s.sort();
               
               return equalsInOrder(s);
        }

        /**
         * Een bepaalde kaart moet worden opgezocht in de gesorteerde array op de
         * binary search manier zoals besproken in [Hubbard p.31].
         * 
         * @param card
         *            de kaart die gezocht wordt
         * @return De index van de gevonden kaart
         */
        public int binarySearch(Card card) {
                return binarySearch(card, 0, cardArray.length);
        }
        
        private int binarySearch(Card card, int start, int end){
            int middle = start + ((end - start) / 2 ); 
            
            if(end < start){ 
                return -1;
            }
            int c = card.compareTo(cardArray[middle]);
            
            if(c < 0)
                return binarySearch(card, start, middle -1); 
            else if (c > 0)
                return binarySearch(card, middle + 1, end);
            else 
                return middle;
        }


        /**
         * Pretty-print het deck.
         */
        @Override
        public String toString() {
                String str = "";
                for(int i = 0; i < cardArray.length; i++)
                    str += String.format("%s",cardArray[i]);

                return str + "\n";
        }
        
    public boolean equalsInContent(Deck d) {
        if (this.cardArray.length != d.cardArray.length)
            return false;
        
        for (int i = 0; i < cardArray.length; i++) {
            if (d.binarySearch(cardArray[i]) == -1) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Compares two decks and returns whether they contain the same order
     * of cards.
     * 
     * @param   deck    deck to compare to
     * @return  boolean whether the two decks contain the same order of cards
     */
    public boolean equalsInOrder(Deck d) {
        if (this.cardArray.length != d.cardArray.length)
            return false;
        
        for (int i = 0; i < cardArray.length; i++) {
            if (cardArray[i].compareTo(d.cardArray[i]) != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public int compareTo(Deck deck) {
        
        if (deck.cardArray.length > cardArray.length)
            return -1;
        else if (deck.cardArray.length < cardArray.length)
            return 1;
        
        int r;
        for (int i = 0; i < cardArray.length; i++)
        {
            r = cardArray[i].compareTo(deck.cardArray[i]);
            if (r != 0) { return r; }
        }
        return 0;
    }

}
