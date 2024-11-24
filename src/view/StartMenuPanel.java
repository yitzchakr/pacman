package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartMenuPanel extends JPanel {
    public StartMenuPanel(ActionListener startGameListener) {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Pac-Man Game", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200,50));
        startButton.addActionListener(startGameListener);
        buttonPanel.add(startButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setPreferredSize(new Dimension(200,50));
        buttonPanel.add(exitButton);

        add(buttonPanel,BorderLayout.CENTER);


    }

}
