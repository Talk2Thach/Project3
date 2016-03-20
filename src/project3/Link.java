/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: Link.java for setting and getting Link objects, in this case the deck of cards
 */
package project3;

public class Link {
    protected Link next;

    public Link getNext() {
        return next;
    } //end getNext()

    public void setNext(Link newNext) {
        next = newNext;
    } //end setNext(Link)
} //end class Link
