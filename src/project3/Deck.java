/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: Deck.java for inserting and maintaining the cards within the current playing deck
 */
package project3;

import java.awt.Image;

public class Deck{

    private CardList theDeck;
    private Card current;
    
    //default constructor the create the new deck from CardList, shuffle the deck, and set the card on top to current
    public Deck() {
        theDeck = new CardList(52);
        theDeck.shuffle();
        current = theDeck.getFirstCard();
    }
    
    //is deck empty?
    public boolean isEmpty() {
        return (current == null);
    }
    
    //get the first card on the deck
    public Card getFirstCard() {
        return current;
    }
    
    //delete the first card from the deck and move to the next one
    public Card deleteFirstCard() {
        Card temp = current;
        current = current.getNextCard();
        return temp;
    }    
    
    //return the card index, an int
    public int getCardIndex() {
        return current.getCardIndex();
    } //end getCardIndex()
    
    //return the image of the card
    public Image getCardImage() {
        return current.getCardImage();
    } //end getCardImage()
}
