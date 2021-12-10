package blackjack;

import javax.swing.*;
import java.awt.*;

public class Card {

    enum Suits {
        CLUBS,
        SPADES,
        HEARTS,
        DIAMONDS
    }

    enum Ranks {
        ACE(11),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private int val;
        private Ranks(int v) {
            this.val = v;
        }
        public int getVal() { return val;}
        public void setVal(int num) {
            this.val = num;
        }
    }

    private Suits suit;
    private Ranks rank;
    private String image;

    public Card(Suits s, Ranks r) {
        this.rank = r;
        this.suit = s;
        this.image = "playingcards/" + r.toString().toLowerCase() + "_of_" + s.toString().toLowerCase() + ".png";
    }

//    public static void main(String[] args) {
//        Ranks r = Ranks.ACE;
//        Suits s = Suits.SPADES;
//        Card c = new Card(s,r);
//        System.out.println(c.getImage().toString());
//    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public String getImage() {
        return image;
    }
}
