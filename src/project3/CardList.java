/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: CardList.java for creating the deck of cards, getting the first card in the deck, deleting card, inserting new card, and shuffling deck 
 */
package project3;

public class CardList {

    private Card firstcard = null;
    private int numcards = 0;

    //constructor to create a deck of cards with set numcards
    public CardList(int num) {
        numcards = num;
        //load the cards
        for (int i = 0; i < num; i++) {
            Card temp = new Card(i);
            if (firstcard != null) {
                temp.setNext(firstcard);
            }
            firstcard = temp;
        }
    } //end constructor CardList(int)

    //return the first card on top of the deck, first item in linked list
    public Card getFirstCard() {
        return firstcard;
    } //end getFirstCard();

    //remove a specific card, based on their index number, from the deck
    public Card deleteCard(int cardnum) {
        Card target, targetprevious;

        if (cardnum > numcards) {
            return null;
            // not enough cards to delete that one
        } else {
            numcards--;
        }

        target = firstcard;
        targetprevious = null;
        //loop through deck until card is found and set it on top of the deck
        while (cardnum-- > 0) {
            targetprevious = target;
            target = target.getNextCard();
            //error, card not found
            if (target == null) {
                return null;
            }
        }
        //link previous to the targeted card, reassigned it to the top of the deck 
        if (targetprevious != null) {
            targetprevious.setNext(target.getNextCard());
        } else {
            //reassign the first card in the deck to next card
            firstcard = target.getNextCard();
        }
        //return the targetted card
        return target;
    } //end deleteCard(int)

    //insert a card onto the deck
    public void insertCard(Card target) {
        numcards++;
        //insert to the bottom of the deck
        if (firstcard != null) {
            target.setNext(firstcard);
        } else {
            //insert to top of the deck 
            target.setNext(null);
        }
        //first card is the last card in
        firstcard = target;
    } //end insertCard(Card)

    //shuffle the deck of card
    public void shuffle() {
        for (int i = 0; i < numcards; i++) {
            //modular operator to generate random number less than number of cards
            int rand = (int) (Math.random() * 100) % numcards;
            //delete the random card from the organized deck
            Card temp = deleteCard(rand);
            if (temp != null) {
                //insert card into new deck
                insertCard(temp);
            }
        }
    } // end shuffle
} //end class CardList
