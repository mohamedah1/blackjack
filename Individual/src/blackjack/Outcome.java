package blackjack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Outcome extends JFrame  implements ActionListener {
    private JPanel panel;
    private JLabel message;
    private JButton playAgain;
    private JButton exit;
    private GameController controller;

    public Outcome(GameController gc, String result) {
        setController(gc);
        setVisible(true);
        setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(10,10,300,200);
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        message = new JLabel(result);
        message.setVerticalAlignment(JLabel.CENTER);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setFont(new Font("SansSerif", Font.BOLD, 25));
        panel.add(message, gbc);

        gbc.gridy = 1;
        playAgain = new JButton("Play Again");
        playAgain.addActionListener(this);
        panel.add(playAgain, gbc);

        gbc.gridy = 2;
        exit = new JButton("Exit");
        exit.addActionListener(this);
        panel.add(exit, gbc);

        setPreferredSize(new Dimension(600,250));
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playAgain)) {
            getController().getView().dispose(); //Close the old game window
            new GameController(); //Start a new game
            dispose(); //Close the outcome window
        }
        if (e.getSource().equals(exit)) {
            System.exit(0);
        }
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}
