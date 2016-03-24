/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: Card.java for assigning image files to Card object
 */
package project3;

import java.awt.Image;

public class Card extends Link {

    private Image cardimage;
    //global int index of card
    private int cardnum;
    
    //constructor to assign the image of each card
    public Card(int cardnum) {
        this.cardnum = cardnum;
        cardimage = GUI.load_picture("Cards/Card" + cardnum + ".jpg");
        //if assignment failed, terminate program
        if (cardimage == null) {
            System.out.println("Error - image failed to load: Cards/Card" + cardnum + ".jpg");
            System.exit(-1);
        }
    } //end constructor Card(int)

    //return next card in the deck
    public Card getNextCard() {
        return (Card) next;
    } //end getNextCard()

    //return the card index, an int
    public int getCardIndex() {
        return cardnum;
    } //end getCardIndex()
    
    //return the image of the card
    public Image getCardImage() {
        return cardimage;
    } //end getCardImage()
} //end class Card
