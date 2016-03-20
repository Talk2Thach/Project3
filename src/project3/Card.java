/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: Card.java for assigning image files to Card object
 */
package project3;

import java.awt.Image;

public class Card extends Link {

    private Image cardimage;                        //image object from Java File 

    public Card(int cardnum) {                      //assign the image of each card
        cardimage = GUI.load_picture("Cards/Card" + cardnum + ".gif");
        if (cardimage == null) {                    //if assignment failed, terminate program
            System.out.println("Error - image failed to load: images/gbCard" + cardnum + ".gif");
            System.exit(-1);
        }
    } //end constructor Card(int)

    public Card getNextCard() {                     //return next card in the deck
        return (Card) next;
    } //end getNextCard()

    public Image getCardImage() {                   //return the image of the card
        return cardimage;
    } //end getCardImage()
} //end class Card
