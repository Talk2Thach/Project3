/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: DealerHand.java for inserting and maintaining the cards within the Dealer's hand
 */
package project3;

import java.awt.Image;

public class DealerHand {
    
    //the first card within the dealer's hand
    private Card current;
    
    //default constructor to make the first card null
    public DealerHand() {
        current = null;
    }
    
    //is the dealer's hand empty?
    public boolean isEmpty() {
        return (current == null);
    }
    
    //insert a new card into the first slot of the link list and move previous one to next one
    public void insertFirstCard(Card newCard) {
        newCard.setNext(current);
        current = newCard;
    }
    //delete the first card from the link list
    public Card deleteFirstCard() {
        if (!isEmpty()) {
            Card temp = current;
            current = current.getNextCard();
            return temp;
        } else {
            return null;
        }
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
