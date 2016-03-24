package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;

public class GUI extends JFrame {

    private static JFrame myFrame;
    private JLabel backgroundPic;
    private JPanel background;
    private JLayeredPane layers;
    private JButton dealButton, foldButton, betButton;
    private TextField bet;
    private Text text;
    private DealingCards theGame, theGame2;
    private Poker pokerGame;
    private int dealClicks;
    private Deck deck;
    private Image[] arrayImage;
    private final int ANTE = 50;
    private int cashMoney = 1000, betValue, potSize = ANTE, winLose;

    //default constructor for the GUI class
    public GUI() {
        //instantiate JFrame and JLayeredPane, plus the dimensions
        myFrame = new JFrame();
        layers = new JLayeredPane();
        Dimension boardSize = new Dimension(800, 540);
        dealClicks = 0;

        //set title, closing operation, resizable, size, visibility, and put it in the middle for JFrame
        setTitle("POKER: TEXAS HOLD'EM");
        setDefaultCloseOperation(myFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(boardSize.width, boardSize.height);
        setVisible(true);
        setLocationRelativeTo(null);

        //set size of JLayeredPane and add it to JFrame
        layers.setPreferredSize(boardSize);
        add(layers);

        //set picture, size, and boundary of background JPanel
        background = new JPanel();
        backgroundPic = new JLabel(new ImageIcon("poker.jpg"));
        background.add(backgroundPic);
        background.setPreferredSize(boardSize);
        background.setBounds(0, 0, boardSize.width, boardSize.height);

        //instantiate and set bounds of Text JPanel, the yellow texts
        text = new Text();
        text.setBounds(0, 0, boardSize.width, boardSize.height);

        arrayImage = new Image[10];
        deck = new Deck();

        //set location, font style of the DEAL button
        dealButton = new JButton("DEAL");
        dealButton.setBounds(190, 460, 100, 30);
        dealButton.setFont(new Font("Verdana", Font.BOLD, 14));

        //set location, font style of the FOLD button
        foldButton = new JButton("FOLD");
        foldButton.setBounds(300, 460, 100, 30);
        foldButton.setFont(new Font("Verdana", Font.BOLD, 14));

        //set location, font style of the BET button
        betButton = new JButton("BET");
        betButton.setBounds(410, 460, 100, 30);
        betButton.setFont(new Font("Verdana", Font.BOLD, 14));

        //create, set location, font style of the bet TextField for inputting bet amount
        bet = new TextField();
        bet.setBounds(520, 460, 100, 30);
        bet.setFont(new Font("Verdana", Font.BOLD, 14));

        //set new ActionListener for the buttons
        dealButton.addActionListener(new ButtonListener());
        foldButton.addActionListener(new ButtonListener());
        betButton.addActionListener(new ButtonListener());

        //set the Dealing Cards JPanel to display the cards
        theGame = new DealingCards();
        theGame.setBounds(0, 0, 800, 540);
        theGame.setOpaque(false);
        //add various JAVA components into the JFrame
        layers.add(background, JLayeredPane.DEFAULT_LAYER);
        layers.add(theGame, new Integer(1));
        layers.add(text, new Integer(1));
        layers.add(dealButton, new Integer(1));
        layers.add(foldButton, new Integer(1));
        layers.add(betButton, new Integer(1));
        layers.add(bet, new Integer(1));
    } //end default constructor GUI()

    //set and get Image from outside source
    public static Image load_picture(String fname) {
        Image image;
        //track image variable
        MediaTracker tracker = new MediaTracker(myFrame);
        //set global Image variable
        image = myFrame.getToolkit().getImage(fname);
        //set location of the Image
        tracker.addImage(image, 0);
        //try catch for if there is a failure to find the image
        try {
            tracker.waitForID(0);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        //if image does not exist
        if (tracker.isErrorID(0)) {
            image = null;
        }
        return image;
    } //end load_picture(String)

    public void imageArray() {
        Image cardimage = GUI.load_picture("card back.jpg");
        arrayImage[0] = cardimage;
        int index = 1;
        for (int i = 4; i > 0; i--) {
            arrayImage[index++] = pokerGame.theStart(i);
        }
        for (int i = 5; i < 10; i++) {
            arrayImage[i] = pokerGame.dealCards();
        }
        if (pokerGame.winner() == 1) {
            winLose = 1;
        } else {
            winLose = 0;
        }
    }

    //implement new ActionListener for the buttons
    class ButtonListener implements ActionListener {

        //overrided actionPerformed(ActionEvent e) method
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == betButton) {
                try {
                    betValue = Integer.parseInt(bet.getText());
                    if (cashMoney >= betValue) {
                        cashMoney -= betValue;
                    } else {
                        betValue = 0;
                    }
                } catch (NumberFormatException ex) {
                }
                dealClicks++;
                repaint();
                bet.setText(null);
            } else if (e.getSource() == dealButton) {
                if (dealClicks == 0) {
                    cashMoney -= ANTE;
                }
                dealClicks++;
                repaint();
            } else if (e.getSource() == foldButton) {
                winLose = 0;
                potSize = ANTE;
                dealClicks = 1;
                repaint();
            }
        } //end actionPerformed(ActionEvent)
    } //end ButtonListener class

    //Class Text using JLabel, to display the yellow text
    class Text extends JLabel {

        //override the paintComponent(Graphics g)
        @Override
        protected void paintComponent(Graphics g) {
            DecimalFormat format = new DecimalFormat();
            format.setGroupingUsed(true);
            format.setGroupingSize(3);
            super.paintComponent(g);
            g.setFont(new Font("Verdana", Font.BOLD, 16));
            g.setColor(Color.YELLOW);
            if (dealClicks == 0) {
                g.drawString("WELCOME TO TEXAS HOLD'EM", 265, 430);
            } else {
                if (dealClicks == 9) {
                    if (winLose == 1) {
                        g.drawString(pokerGame.winningPhrase() + format.format(potSize * 2 - ANTE), 225, 430);
                    } else {
                        g.drawString(pokerGame.winningPhrase() + format.format(potSize), 225, 430);
                    }
                }
                g.drawString("Dealer's Hand", 240, 62);
                g.drawString("Player's Hand", 240, 365);
                g.drawString("The Board", 650, 135);
                g.drawString("Ante: $" + ANTE, 192, 450);
                g.drawString("$" + format.format(cashMoney), 300, 450);
                g.drawString("Pot: $" + format.format(potSize += betValue), 410, 450);
                g.drawString("Discard:", 680, 340);
                betValue = 0;
            }
        } //end paintComponent(Graphics)
    } //end Text class

    //Class TextField to input the bet amount
    class TextField extends JTextField {

        //override the paintComponent(Graphics g)
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty() && !(FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
                g.setFont(new Font("Verdana", Font.PLAIN, 10));
                g.drawString("Place Your Bet", 10, 18);
                g.dispose();
            }
        } //end paintComponent(Graphics)
    } //end TextField class

    //Class DealingCards, to display the cards
    class DealingCards extends JPanel {

        //override the paintComponent(Graphics g) 
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (dealClicks == 0) {
                int start = -1, xpos = 25, ypos = 140;
                while (start < 39) {
                    Card card = new Card(start += 13);
                    g.drawImage(card.getCardImage(), xpos += 130, ypos, this);
                }
            }
            if (cashMoney < 0) {
                Image broke = GUI.load_picture("CashMoney.jpg");
                g.drawImage(broke, 0, 0, this);
            } else {
                if (dealClicks == 1) {
                    pokerGame = new Poker();
                    imageArray();
                    g.drawImage(arrayImage[0], 20, 50, this);
                    g.drawImage(arrayImage[0], 130, 50, this);
                    g.drawImage(arrayImage[0], 680, 345, this);
                    g.drawImage(arrayImage[1], 20, 220, this);
                    g.drawImage(arrayImage[3], 130, 220, this);
                    dealClicks++;
                }
                if (dealClicks != 0) {
                    g.drawImage(arrayImage[0], 680, 345, this);
                }
                if (dealClicks == 3) {
                    g.drawImage(arrayImage[0], 20, 50, this);
                    g.drawImage(arrayImage[0], 130, 50, this);
                    g.drawImage(arrayImage[1], 20, 220, this);
                    g.drawImage(arrayImage[3], 130, 220, this);
                    int xpos = 130;
                    for (int i = 5; i < 8; i++) {
                        g.drawImage(arrayImage[i], xpos += 110, 140, this);
                    }
                    dealClicks++;
                }
                if (dealClicks == 5) {
                    g.drawImage(arrayImage[0], 20, 50, this);
                    g.drawImage(arrayImage[0], 130, 50, this);
                    g.drawImage(arrayImage[1], 20, 220, this);
                    g.drawImage(arrayImage[3], 130, 220, this);
                    int xpos = 130;
                    for (int i = 5; i < 9; i++) {
                        g.drawImage(arrayImage[i], xpos += 110, 140, this);
                    }
                    dealClicks++;
                }
                if (dealClicks == 7) {
                    g.drawImage(arrayImage[0], 20, 50, this);
                    g.drawImage(arrayImage[0], 130, 50, this);
                    g.drawImage(arrayImage[1], 20, 220, this);
                    g.drawImage(arrayImage[3], 130, 220, this);
                    int xpos = 130;
                    for (int i = 5; i < 10; i++) {
                        g.drawImage(arrayImage[i], xpos += 110, 140, this);
                    }
                    dealClicks++;
                }
                if (dealClicks == 9) {
                    g.drawImage(arrayImage[2], 20, 50, this);
                    g.drawImage(arrayImage[4], 130, 50, this);
                    g.drawImage(arrayImage[1], 20, 220, this);
                    g.drawImage(arrayImage[3], 130, 220, this);
                    int xpos = 130;
                    for (int i = 5; i < 10; i++) {
                        g.drawImage(arrayImage[i], xpos += 110, 140, this);
                    }

                    if (winLose == 1) {
                        cashMoney = cashMoney + (potSize * 2);
                    }
                    potSize = ANTE;
                    dealClicks = 0;
                }
            }
        } //end paintComponent(Graphics) 
    } //end DealingCards class
} //end GUI class
