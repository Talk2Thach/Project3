/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: CardList.java for creating the deck of cards, getting the first card in the deck, deleting card, inserting new card, and shuffling deck 
 */
package project3;

public class CardList {

    private Card firstcard = null;
    private int numcards = 0;

    public CardList(int num) {                  //create a deck of cards
        numcards = num;                         //set numcards in the deck
        for (int i = 0; i < num; i++) {         //load the cards
            Card temp = new Card(i);
            if (firstcard != null) {
                temp.setNext(firstcard);
            }
            firstcard = temp;
        }
    } //end constructor CardList(int)

    public Card getFirstCard() {                //return the first card on top of the deck, first item in linked list
        return firstcard;
    } //end getFirstCard();

    public Card deleteCard(int cardnum) {       //remove a specific card, based on their index number, from the deck
        Card target, targetprevious;

        if (cardnum > numcards) {
            return null;                        // not enough cards to delete that one
        } else {
            numcards--;
        }

        target = firstcard;
        targetprevious = null;
        while (cardnum-- > 0) {                 //loop through deck until card is found and set it on top of the deck
            targetprevious = target;
            target = target.getNextCard();
            if (target == null) {
                return null;                    //error, card not found
            }
        }
        if (targetprevious != null) {           //link previous to the targeted card, reassigned it to the top of the deck 
            targetprevious.setNext(target.getNextCard());
        } else {
            firstcard = target.getNextCard();   //reassign the first card in the deck to next card
        }
        return target;                          //return the targetted card
    } //end deleteCard(int)

    public void insertCard(Card target) {       //insert a card onto the deck
        numcards++;
        if (firstcard != null) {                //insert to the bottom of the deck
            target.setNext(firstcard);
        } else {
            target.setNext(null);               //insert to top of the deck 
        }
        firstcard = target;                     //first card is the last card in
    } //end insertCard(Card)

    public void shuffle() {                     //shuffle the deck of card
        for (int i = 0; i < numcards; i++) {
            int rand = (int) (Math.random() * 100) % numcards; //modular operator to generate random number less than number of cards
            Card temp = deleteCard(rand);       //delete the random card from the organized deck
            if (temp != null) {
                insertCard(temp);               //insert card into new deck
            }
        }  
    } // end shuffle
} //end class CardList
