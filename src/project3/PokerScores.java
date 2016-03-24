/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: PokerScores.java for determing the type of hand a players and dealers had and deciding the winner from said hand
 */
package project3;

import java.util.Arrays;

public class PokerScores {

    private int[] playersCards;
    private int[] dealerCards;
    private int[] test = {1, 2, 3, 5, 4, 1, 7, 100, 100};
    private int arrayCount;

    //deafult constructor that allocate space for the players and dealers hand
    //the last slot is for the value of a paired or tripled cards
    //the second to last slot is for the second paired cards 
    public PokerScores() {
        playersCards = new int[9];
        dealerCards = new int[9];
        arrayCount = 0;
    }

    //fill the int array with the player's hand
    public void fillPlayerHand(int cardIndex) {
        playersCards[arrayCount] = cardIndex;
    }

    //fill the int array with the dealer's hand
    public void fillDealerHand(int cardIndex) {
        dealerCards[arrayCount++] = cardIndex;
    }

    //return of int array of the player's hand
    public int[] getPlayerValue() {
        return playersCards;
    }

    //return of int array of the dealer's hand
    public int[] getDealerValue() {
        return dealerCards;
    }

    //test the int array to see if the hand is a straight flush
    private int isStraightFlush(int[] hand) {
        if (isFlush(hand) == 6 && isStraight(hand) == 5) {
            return 9;
        } else {
            return 0;
        }
    }

    //test the int array to see if the hand is four of a kind
    private int isFourKind(int[] hand) {
        int count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = (i + 1); j < 7; j++) {
                if ((hand[i] % 13) == (hand[j] % 13)) {
                    count++;
                }
            }
            if (count == 3) {
                hand[7] = (hand[i] % 13);
                break;
            } else {
                count = 0;
            }
        }
        return (count == 3) ? 8 : 0;
    }

    //test the int array to see if the hand is full house
    private int isFullHouse(int[] hand) {
        int count = 0;
        //check for tripled cards twice incase of the double triples
        if (isThreeKind(hand) == 4) {
            for (int i = 0; i < 7; i++) {
                for (int j = (i + 1); j < 7; j++) {
                    if ((hand[i] % 13) == (hand[j] % 13) && (hand[i] % 13) != (hand[7] % 13)) {
                        count++;
                    }
                }
                if (count == 2) {
                    if (hand[7] < (hand[i] % 13)) {
                        hand[7] = (hand[i] % 13);
                    }
                    break;
                } else {
                    count = 0;
                }
            }
            //check for the pairs afterward
            count = 2;
            for (int i = 0; i < 7; i++) {
                for (int j = (i + 1); j < 7; j++) {
                    if ((hand[i] % 13) == (hand[j] % 13) && (hand[i] % 13) != (hand[7] % 13)) {
                        count++;
                    }
                }
                if (count >= 3) {
                    hand[8] = (hand[i] % 13);
                    break;
                } else {
                    count = 2;
                }
            }
        }
        return (count == 3) ? 7 : 0;
    }

    //test the int array to see if the hand is flush
    private int isFlush(int[] hand) {
        int count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = (i + 1); j < 7; j++) {
                if ((hand[i] / 13) == (hand[j] / 13)) {
                    count++;
                }
            }
            if (count == 4) {
                hand[7] = (hand[i] % 13);
                break;
            } else {
                count = 0;
            }
        }
        return (count == 4) ? 6 : 0;
    }

    //test the int array to see if the hand is straight, the worst of the bunch for some reason
    private int isStraight(int[] hand) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if ((((hand[i] % 13) + 1) == (hand[i + 1] % 13))) {
                hand[7] = (hand[i + 1] % 13);
                count++;
            }
            if (count == 3 && hand[i] == 12){
                count++;
            }
        }
        return (count >= 4) ? 5 : 0;
    }

    //test the int array to see if the hand is three of a kind
    private int isThreeKind(int[] hand) {
        int count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = (i + 1); j < 7; j++) {
                if ((hand[i] % 13) == (hand[j] % 13)) {
                    count++;
                }
            }
            if (count == 2) {
                hand[7] = (hand[i] % 13);
                break;
            } else {
                count = 0;
            }
        }
        return (count == 2) ? 4 : 0;
    }

    //test the int array to see if the hand is two pair
    private int isTwoPair(int[] hand) {
        int count = 0;
        //check for paired cards twice
        if (isOnePair(hand) == 2) {
            for (int i = 0; i < 7; i++) {
                for (int j = (i + 1); j < 7; j++) {
                    if ((hand[i] % 13) == (hand[j] % 13) && (hand[i] % 13) != (hand[7])) {
                        count++;
                    }
                }
                if (count == 1) {
                    if (hand[7] < (hand[i] % 13)) {
                        hand[8] = hand[7];
                        hand[7] = (hand[i] % 13);
                    } else {
                        hand[8] = (hand[i] % 13);
                    }
                    break;
                } else {
                    count = 0;
                }
            }
        }
        return (count == 1) ? 3 : 0;
    }

    //test the int array to see if the hand is one pair
    private int isOnePair(int[] hand) {
        int count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = (i + 1); j < 7; j++) {
                if ((hand[i] % 13) == (hand[j] % 13)) {
                    count++;
                }
            }
            if (count == 1) {
                hand[7] = (hand[i] % 13);
                break;
            } else {
                count = 0;
            }
        }
        return (count == 1) ? 2 : 0;
    }

    //test the int array to determine the highest card in your hand, avoiding paired cards if necessary
    private int isHighCard(int[] hand) {
        int count = 0;
        for (int i = 2; i < 7; i++) {
            count += hand[i];
        }
        return count;
        /*
         int max = 0, count = 0;
         while ((hand[count] != hand[7]) && (hand[count] != hand[8])) {
         count++;
         }
         max = hand[count] % 13;
         for (int i = count; i < 7; i++) {
         if (hand[i] % 13 > max) {
         max = hand[i] % 13;
         }
         }
         hand[7] = max;
         return max;*/
    }

    //determine what the hand's result would be by assigning it a value of the highest possible hand
    private int pokerResult(int[] hand) {
        if (isStraightFlush(hand) == 9) {
            return 9;
        }
        if (isFourKind(hand) == 8) {
            return 8;
        }
        if (isFullHouse(hand) == 7) {
            return 7;
        }
        if (isFlush(hand) == 6) {
            return 6;
        }
        if (isStraight(hand) == 5) {
            return 5;
        }
        if (isThreeKind(hand) == 4) {
            return 4;
        }
        if (isTwoPair(hand) == 3) {
            return 3;
        }
        if (isOnePair(hand) == 2) {
            return 2;
        }
        isHighCard(hand);
        return 0;
    }

    //call for the player's hand results
    public int playerResult() {
        return pokerResult(playersCards);
    }

    //call for the dealer's hand results
    public int dealerResult() {
        return pokerResult(dealerCards);
    }

    private boolean comparePair(int type) {
        if (playersCards[7] > dealerCards[7]) {
            return true;
        } else if (playersCards[7] == dealerCards[7]) {
            if (playersCards[8] > dealerCards[8]) {
                return true;
            } else if (playersCards[8] == dealerCards[8]) {
                if (type == 1) {
                    if (isHighCard(playersCards) > isHighCard(dealerCards)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //determine who won 
    public boolean winner() {
        //ordered array in numerical values for easier comparison
        playersCards[7] = 100;
        playersCards[8] = 100;
        Arrays.sort(playersCards);
        dealerCards[7] = 100;
        dealerCards[8] = 100;
        Arrays.sort(dealerCards);

        //the value of the player's and dealer's hand
        int playerResult = playerResult();
        int dealerResult = dealerResult();

        if (playerResult > dealerResult) {
            return true;
        } else if (playerResult == dealerResult) {
            if (playerResult == 8 || playerResult == 4 || playerResult == 3 || playerResult == 2) {
                return comparePair(1);
            } else {
                return comparePair(0);
            }
        } else {
            return false;
        }
    }
}
