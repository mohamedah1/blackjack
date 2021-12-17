package blackjack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameView extends JFrame {
    private JPanel panel;
    private JLabel firstTwoCards;
    private JLabel[] nextCards;
    private JLabel dlrFirstCard;
    private JLabel[] dlrNext;
    private JButton btnHit;
    private JButton btnStand;
    private GameController controller;
    private JLabel playerTotal;
    private JLabel dealerTotal;

    public GameView(GameController gc) {
        setController(gc);
        setTitle("Blackjack");
        setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10,10,1280,720);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(panel);
        panel.setLayout(null);

        int plrX = 500;//First card x position
        for(Card c : getController().getFirstTwoCards()) {
            firstTwoCards = new JLabel();
            firstTwoCards.setIcon(new ImageIcon(c.getImage()));
            firstTwoCards.setBounds(plrX,400,100,145); //player start position
            panel.add(firstTwoCards);
            plrX += 100; //Every new card, move the x over by 75
        }

        nextCards = new JLabel[10]; //Placeholders for the incoming cards for player
        for (int i = 0; i < nextCards.length; i++) {
            nextCards[i] = new JLabel();
            nextCards[i].setBounds(plrX,400,100,145);
            plrX+= 100;//For Every new card, move the x over by 75
            panel.add(nextCards[i]); //Add placeholders to panel but don't assign a card image
        }

        playerTotal = new JLabel(); //Displays total points
        playerTotal.setText(getController().getPlayerTotal() + " Points");
        playerTotal.setFont(new Font("SansSerif", Font.BOLD, 25));
        playerTotal.setBounds(300,450,200,50);
        panel.add(playerTotal);

        int dlrX = 500; //Dealer's first card x position
        dlrFirstCard = new JLabel(); //Dealer's first card
        getController().getDealerHand().add(getController().drawCard());
        dlrFirstCard.setIcon(new ImageIcon(getController().getDealerHand().get(0).getImage()));
        dlrFirstCard.setBounds(dlrX,50,100,145); //dealer start position
        dlrX += 100;//For Every new card, move the x over by 100
        panel.add(dlrFirstCard); //Add placeholders to panel but don't assign a card image

        dlrNext = new JLabel[10];//Placeholders for the incoming cards for dealer
        for (int i = 0; i < dlrNext.length; i++) {
            dlrNext[i] = new JLabel();
            dlrNext[i].setBounds(dlrX,32,125,181);
            dlrX += 100;//For Every new card, move the x over by 100
            panel.add(dlrNext[i]);
        }
        dlrNext[0].setIcon(new ImageIcon("playingcards/backofcard.png")); //Dealer's second card is face down

        dealerTotal = new JLabel(); //Displays dealers total
        dealerTotal.setFont(new Font("SansSerif", Font.BOLD, 25));
        dealerTotal.setBounds(300,75,200,50);
        panel.add(dealerTotal);

        btnHit = new JButton("Hit");
        btnHit.addActionListener(getController());
        btnHit.setBounds(390,600,200,50);
        panel.add(btnHit);

        btnStand = new JButton("Stand");
        btnStand.addActionListener(getController());
        btnStand.setBounds(690,600,200,50);
        panel.add(btnStand);

        //Background Image
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("images/gamebackground.jpg"));
        background.setBounds(0,0,1280,720);
        panel.add(background);

        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JLabel getPlayerTotal() {
        return playerTotal;
    }

    public JLabel getDealerTotal() {
        return dealerTotal;
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public JButton getBtnHit() {
        return btnHit;
    }

    public JButton getBtnStand() {
        return btnStand;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel[] getNextCards() {
        return nextCards;
    }

    public JLabel[] getDlrNext() {
        return dlrNext;
    }

}
