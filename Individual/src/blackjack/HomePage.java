package blackjack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton play;
    private JButton exit;

    public HomePage() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Lets Play Blackjack!");
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(panel);
        panel.setLayout(null);

        play = new JButton("PLAY");
        play.addActionListener(this);
        play.setBounds(310,650,200,50);
        panel.add(play);

        exit = new JButton("EXIT");
        exit.addActionListener(this);
        exit.setBounds(720,650,200,50);
        panel.add(exit);

        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon("images/homescreen.jpg"));
        bg.setBounds(0,0,1230,762);
        panel.add(bg);

        setPreferredSize(new Dimension(1230,762));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(exit)) {
            System.exit(0); //If exit button is clicked, the program ends
        }
        if (e.getSource().equals(play)) {
            new GameController(); //If play is clicked, we start up the game
            dispose(); //Close the start up window
        }
    }
}
