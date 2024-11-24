package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartMenuPanel extends JPanel {
    public StartMenuPanel(ActionListener startGameListener) {
       this.setPreferredSize(new Dimension(800,600));
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
        exitButton.setPreferredSize(new Dimension(200,50));
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(exitButton);

        add(buttonPanel,BorderLayout.CENTER);


    }

}
