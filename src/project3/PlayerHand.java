/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: PlayerHand.java for inserting and maintaining the cards within the Player's hand
 */
package project3;

import java.awt.Image;

public class PlayerHand {

    private Card current;

    //default constructor that set the first call to null
    public PlayerHand() {
        current = null;
    }

    //is player's hand empty?
    public boolean isEmpty() {
        return (current == null);
    }

    //insert new cards into player's hand and move the previous to the next one
    public void insertFirstCard(Card newCard) {
        newCard.setNext(current);
        current = newCard;
    }

    //delete the first card of the player's hand
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
