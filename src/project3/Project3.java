/* Name: Thach Diep
 * Date: March 17, 2016
 * Project: 3 – Link List Card Games
 * Description: Project3.java just to run the main() can create a disclaimer as well as the GUI to run everything
 */

package project3;

import javax.swing.JOptionPane;

public class Project3 {

    public static void main(String[] args) {
        //disclaimer, read before you grade
        JOptionPane.showMessageDialog(null, "Some Poker hands, which are rare, might be miscalculated because JAVA Swing is Bullsh!t!!"
                + "\nAlso, you lose during push and winning will subtract ANTE back because that's how Casino Ferguson works!!");
        new GUI();
    }
}
