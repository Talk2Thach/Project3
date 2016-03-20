package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame {

    private static JFrame myFrame;
    private JLabel backgroundPic;
    private JPanel background;
    private JLayeredPane layers;
    private JButton dealButton, foldButton, betButton;
    private TextField bet;
    private Text moneyText, betText;

    public GUI() {

        myFrame = new JFrame();
        background = new JPanel();
        layers = new JLayeredPane();

        Dimension boardSize = new Dimension(800, 540);
        setTitle("POKER: TEXAS HOLD'EM");
        setDefaultCloseOperation(myFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(boardSize.width, boardSize.height);
        setVisible(true);
        setLocationRelativeTo(null);
        layers.setPreferredSize(boardSize);
        add(layers);

        backgroundPic = new JLabel(new ImageIcon("poker.jpg"));        
        background.add(backgroundPic);
        background.setPreferredSize(boardSize);
        background.setBounds(0, 0, boardSize.width, boardSize.height);
        layers.add(background, JLayeredPane.DEFAULT_LAYER);

        moneyText = new Text();
        moneyText.setBounds(290,430,110,30);
        moneyText.setString("$100");        
        betText = new Text();
        betText.setBounds(400,430,110,30);
        betText.setString("BET: ");
        
        dealButton = new JButton("DEAL");
        dealButton.setFont(new Font("Verdana", Font.BOLD, 14));
        dealButton.setBounds(190, 460, 100, 30);
        foldButton = new JButton("FOLD");
        foldButton.setFont(new Font("Verdana", Font.BOLD, 14));
        foldButton.setBounds(300, 460, 100, 30);
        betButton = new JButton("BET");
        betButton.setFont(new Font("Verdana", Font.BOLD, 14));
        betButton.setBounds(410, 460, 100, 30);

        bet = new TextField();
        bet.setBounds(520, 460, 100, 30);
        bet.setFont(new Font("Verdana", Font.BOLD, 14));
        betButton.addActionListener(new ButtonListener());
        
        layers.add(moneyText, new Integer(1));
        layers.add(betText, new Integer(1));
        layers.add(dealButton, new Integer(1));
        layers.add(foldButton, new Integer(1));
        layers.add(betButton, new Integer(1));
        layers.add(bet, new Integer(1));
        
    }

    public static Image load_picture(String fname) {
        Image image;
        MediaTracker tracker = new MediaTracker(myFrame);
        image = myFrame.getToolkit().getImage(fname);
        tracker.addImage(image, 0);
        try {
            tracker.waitForID(0);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        if (tracker.isErrorID(0)) {
            image = null;
        }
        return image;
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("BET")) {
                bet.setText(null);
            }
        }
    }
    
    class Text extends JLabel{
        private String text;
        protected void setString(String input){
            text = input;
        }
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);            
                g.setFont(new Font("Verdana", Font.BOLD, 16));
                g.setColor(Color.YELLOW);
                g.drawString(text, 10, 18);
                g.dispose();            
        }
    }
    
    class TextField extends JTextField {
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            if (getText().isEmpty() && !(FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setFont(new Font("Verdana", Font.PLAIN, 10));
                g2.drawString("Place Your Bet", 10, 18);
                g2.dispose();
            }
        }
    }
    /*
     class MyPanel extends JPanel {

     public void paintComponent(Graphics g) {

     }
     }*/

}
