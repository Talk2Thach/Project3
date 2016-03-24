/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: Poker.java for handling the game portion of the TEXAS HOLD'EM, in term of dealing cards and outputting winning hand
 */
package project3;

import java.awt.Image;

public class Poker {

    private Deck theDeck;
    private DealerHand theDealer;
    private PlayerHand thePlayer;
    private PokerScores theScores;
    
    //default constructor the build a new deck, player's hand, dealer's hand, and the poker scoring system
    public Poker() {
        theDeck = new Deck();
        theDealer = new DealerHand();
        thePlayer = new PlayerHand();
        theScores = new PokerScores();
        //removed the first card as how it would be played in real life
        theDeck.deleteFirstCard();
    }
    
    //dealing the first 4 cards, 2 the players and 2 to the dealer and returning the image of dealt cards
    //also set the cards within the dealers hand and players hand object
    public Image theStart(int numCards) {
        if ((numCards % 2) == 0) {
            thePlayer.insertFirstCard(theDeck.deleteFirstCard());
            theScores.fillPlayerHand(thePlayer.getCardIndex());
            return thePlayer.getCardImage();
        } else {
            theDealer.insertFirstCard(theDeck.deleteFirstCard());
            theScores.fillDealerHand(theDealer.getCardIndex());
            return theDealer.getCardImage();
        }
    }
    
    //dealing the remain cards on the table
    public Image dealCards() {
        theDeck.deleteFirstCard();
        theScores.fillPlayerHand(theDeck.getCardIndex());
        theScores.fillDealerHand(theDeck.getCardIndex());
        return theDeck.getCardImage();
    }
    
    //print out to the console the values of the player's cards
    public void playerCardValues() {
        for (int i = 0; i < 7; i++) {
            System.out.println("index: " + theScores.getPlayerValue()[i]);
        }
    }
    
    //print out to the console the values of the dealer's cards
    public void dealerCardValues() {
        for (int i = 0; i < 7; i++) {
            System.out.println("index: " + theScores.getDealerValue()[i]);
        }
    }
    
    //output the string of the how the winner won
    public String resultPhrase(int pokerResult, String winLose) {
        if (pokerResult == 9) {
            return "Straight Flush!! You " + winLose + " $";
        } else if (pokerResult == 8) {
            return "Four of a Kind!! You " + winLose + " $";
        } else if (pokerResult == 7) {
            return "Full House!! You " + winLose + " $";
        } else if (pokerResult == 6) {
            return "Flush!! You " + winLose + " $";
        } else if (pokerResult == 5) {
            return "Straight!! You " + winLose + " $";
        } else if (pokerResult == 4) {
            return "Three of a Kind!! You " + winLose + " $";
        } else if (pokerResult == 3) {
            return "Two Pairs!! You " + winLose + " $";
        } else if (pokerResult == 2) {
            return "A Single Pair!! You " + winLose + " $";
        } else {
            return "High Card!! You " + winLose + " $";
        }
    }
    
    //determining the winning phrase of the winner and set out a string
    public String winningPhrase() {
        if (theScores.winner()) {
            //player won
            return resultPhrase(theScores.playerResult(), "Win");
        } else {
            //dealer won
            return resultPhrase(theScores.dealerResult(), "Lose");
            
        }
    }
    
    //send out and int to identify the winner
    public int winner() {
        if (theScores.winner()) {
            //player won
            return 1;
        } else {
            //dealer won
            return 0;            
        }
    }
}
