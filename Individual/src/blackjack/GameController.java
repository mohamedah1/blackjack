package blackjack;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameController implements ActionListener, WindowListener {
    private GameView view;
    private ArrayList<Card> deck = createDeck();
    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private int dealerTotal;
    private int playerTotal;

    public GameController() {
        view = new GameView(this);
        view.setVisible(true);
    }

    private ArrayList<Card> createDeck() { //Creates the deck for every game/round
        ArrayList<Card> deck = new ArrayList<>();

        for (Card.Suits suit: Card.Suits.values()) {
            for (Card.Ranks rank: Card.Ranks.values()) {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
        return deck;
    }

    public Card drawCard() { //Draws a random card from the deck, removes it from the deck, then returns it
        Random rand = new Random();
        int randIndex = rand.nextInt(deck.size());
        Card randCard = deck.get(randIndex);
        deck.remove(randCard);
        return randCard;
    }

    public ArrayList<Card> getFirstTwoCards() { //Get the first two cards dealt to the player
        if (playerHand.isEmpty()) {
            playerTotal = 0;
            for (int i = 0; i < 2; i++) {
                Card c = drawCard();
                playerHand.add(c);
                playerTotal+=c.getRank().getVal();
            }
        }
        if (playerTotal == 21) { //if those two cards add up to 21, you win right away
            getView().getPanel().remove(getView().getBtnHit());
            getView().getPanel().remove(getView().getBtnStand());
            new Outcome(this, "BLACKJACK! YOU WIN");
        }
        return playerHand;
    }

    private void pointCalc() { //Calculates the points of the player and dealer hands
        playerTotal = 0;
        dealerTotal = 0;
        boolean foundAce = false;

        for(Card c : playerHand) { //Calculate player hand
            playerTotal = playerTotal + c.getRank().getVal();
            if (c.getRank().equals(Card.Ranks.ACE)) { //checks if the player is holding an Ace
                foundAce = true;
            }
        }
        //if the total is over 21 and player holds an ace, set the value of ace from 11 to 1 and recalculates
        if(playerTotal > 21 && foundAce) {
            playerTotal = 0;
            Card.Ranks.ACE.setVal(1);
            for (Card c: playerHand) {
                playerTotal+=c.getRank().getVal();
            }
        }

        foundAce = false;
        for(Card c : dealerHand) { //Calculate dealer hand
            dealerTotal = dealerTotal + c.getRank().getVal();
            if (c.getRank().equals(Card.Ranks.ACE)) {
                foundAce = true;
            }
        }
        //if the total is over 21 and dealer holds an ace, set the value of ace from 11 to 1 and recalculates
        if(dealerTotal > 21 && foundAce) {
            dealerTotal = 0;
            Card.Ranks.ACE.setVal(1);
            for (Card c: dealerHand) {
                dealerTotal+=c.getRank().getVal();
            }
        }

        Card.Ranks.ACE.setVal(11);//Set it back to 11
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(getView().getBtnHit())) {
            JLabel[] lbls = getView().getNextCards(); //Array of JLabels for the next card
            int index = 0; //index of the JLabel
            playerHand.add(drawCard()); //Player is dealt a card

            //For every card after the initial first two, display the new cards that have been dealt
            for (int i = 2; i < playerHand.size(); i++) {
                lbls[index].setIcon(new ImageIcon(playerHand.get(i).getImage()));
                index++;
            }

            pointCalc(); //Recalculate the players points
            getView().getPlayerTotal().setText(playerTotal + " Points"); //Displays the player's total points


            if(playerTotal > 21) { //If player's total exceeds 21, they lose
                getView().getPanel().remove(getView().getBtnHit()); //Disables the hit button
                getView().getPanel().remove(getView().getBtnStand()); //Disables the stand button
                new Outcome(this,"OVER 21! YOU LOST"); //Displays the outcome message
            } else if (playerTotal == 21) {
                getView().getPanel().remove(getView().getBtnHit());//Disables the hit button
                getView().getPanel().remove(getView().getBtnStand());//Disables the stand button
                new Outcome(this, "BLACKJACK! YOU WIN");//Displays the outcome message
            }

        }

        if (e.getSource().equals(getView().getBtnStand())) {
            getView().getPanel().remove(getView().getBtnHit()); //Disables the hit button so player cannot hit anymore
            JLabel[] dlrLbl = getView().getDlrNext(); //Array of JLabels for dealers next cards
            dealerHand.add(drawCard());

            //Flips the dealer's face down card and presents a real card
            dlrLbl[0].setIcon(new ImageIcon(dealerHand.get(1).getImage()));

            pointCalc(); //Calculate the dealers first two cards
            getView().getDealerTotal().setText(dealerTotal + " Points");

            int dlrInd = 1; //Index of the JLabels for the added cards
            int cardInd = 2; //Index of the card we want from the dealer's hand
            //If the dealer's first two cards aren't 17 or more, we want to keep dealing until it is
            if (dealerTotal < 17) {
                do {
                    dealerHand.add(drawCard()); //Draws another card for dealer
                    pointCalc(); //Recalculate hand with the added cards
                    getView().getDealerTotal().setText(dealerTotal + " Points");
                    dlrLbl[dlrInd].setIcon(new ImageIcon(dealerHand.get(cardInd).getImage())); //Display the new cards
                    dlrInd++;
                    cardInd++;
                }while(dealerTotal < 17);
            }
            getView().getPanel().remove(getView().getBtnStand());//Disables the stand button
            //if dealer's points exceed 21, they bust and you win
            if (dealerTotal > 21) {
                new Outcome(this,"DEALER BUSTED! YOU WIN");
            } else if (dealerTotal > playerTotal || dealerTotal == 21) {
                //If both hands are under 21 and dealer's hand is higher or dealer gets 21, dealer wins
                new Outcome(this,"DEALER WINS!");
            }
            if (playerTotal > dealerTotal) { //If both hands are under 21 and player hand is higher, you win
                new Outcome(this,"YOU HAD THE BETTER HAND! YOU WIN");
            }
            if (dealerTotal == playerTotal) { //If both hands are under 21 and are equal, its a draw
                new Outcome(this,"IT'S A DRAW");
            }
        }
    }

    public GameView getView() {
        return view;
    }

    public int getPlayerTotal() {
        return playerTotal;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
