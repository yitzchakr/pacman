package control;

import model.GameManager;
import model.GameMap;
import model.LeaderBoard;
import model.Player;
import model.ghost.Ghost;
import model.ghost.GhostFactory;
import view.GamePanel;
import view.StartMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;




public class GameControl implements Runnable {
    JTextField textField;
    KeyHandler keyHandler = new KeyHandler();
    GameMap gameMap;
    Player player;
    Thread gameThread;
    GhostFactory ghostFactory;
    Ghost[] ghosts;
    GameManager gameManager;
    GamePanel gamePanel;
    JFrame window = new JFrame();
    LeaderBoard leaderBoard;
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);
    private boolean isRunning;


    public GameControl() {
        init();
    }

    private void init() {
        keyHandler = new KeyHandler();
        gameMap = new GameMap();
        player = new Player(gameMap);
        ghostFactory = new GhostFactory(gameMap, player);
        ghosts = ghostFactory.createGhosts();
        gameManager = new GameManager(player, gameMap, ghosts);
        leaderBoard = new LeaderBoard(gameManager);
        gamePanel = new GamePanel(player, gameMap, gameManager, ghosts, leaderBoard);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gamePanel.addKeyListener(keyHandler);
        textField = new JTextField();
    }

    public void setWindow() {
        StartMenuPanel startMenu = new StartMenuPanel(
                e -> startGame()

        );
        mainPanel.add(startMenu, "StartMenu");
        mainPanel.add(gamePanel, "game");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pacman");

        window.add(mainPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gamePanel.addKeyListener(keyHandler);
        cardLayout.show(mainPanel, "StartMenu");
    }

    private void startGame() {
        resetGame();
        cardLayout.show(mainPanel, "game");
        gamePanel.requestFocusInWindow();
        startThread();
    }

    private String receiveKeyInput() {
        String keyInput;
        switch (keyHandler.code) {
            case KeyEvent.VK_UP:
                keyInput = "up";
                break;
            case KeyEvent.VK_DOWN:
                keyInput = "down";
                break;
            case KeyEvent.VK_LEFT:
                keyInput = "left";
                break;
            case KeyEvent.VK_RIGHT:
                keyInput = "right";
                break;
            default:
                keyInput = "";
        }
        return keyInput;
    }

    public void update() {


        player.update(receiveKeyInput());
        gameManager.update();

    }

    public void startThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }




    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            update();
            if (gameManager.isOver()) {
              updateHighScores();
                cardLayout.show(mainPanel, "StartMenu");
                isRunning = false;
            }
            gamePanel.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void updateHighScores (){
        if (leaderBoard.isHighScore()) {
            String playerName = JOptionPane.showInputDialog(window,
                    "Game Over! \n Congratulations you have a high score \n Enter your name:",
                    "Game Over", JOptionPane.PLAIN_MESSAGE);
            leaderBoard.updateScores(playerName);
            JOptionPane.showMessageDialog(window,
                    "Current High Scores:\n" + leaderBoard.getHighScores(),
                    "High Scores", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(window,
                    "Game Is Over", "GameOver", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(window,
                    "Current High Scores:\n" + leaderBoard.getHighScores(),
                    "High Scores", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private void resetGame() {
        init();

        mainPanel.add(gamePanel, "game");
    }
}