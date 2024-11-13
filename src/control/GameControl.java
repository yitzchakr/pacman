package control;

import model.GameManager;
import model.GameMap;
import model.Player;
import model.ghost.Ghost;
import model.ghost.GhostFactory;
import view.GamePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class GameControl implements Runnable {
    KeyHandler keyHandler = new KeyHandler();
    GameMap gameMap = new GameMap();
    Player player = new Player(gameMap);
    Thread gameThread;
    GhostFactory ghostFactory = new GhostFactory();
    Ghost[]ghosts = ghostFactory.createGhosts();
    GameManager gameManager = new GameManager(player,gameMap,ghosts);
    GamePanel gamePanel = new GamePanel(player,gameMap,gameManager);
    JFrame window = new JFrame();


    public GameControl() {
    }

    public void setWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Pacman");
        gamePanel.addKeyListener(keyHandler);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void update() {

        String keyInput ;
        switch (keyHandler.code) {
            case KeyEvent.VK_W:
                keyInput = "up";
                break;
            case KeyEvent.VK_S:
                keyInput = "down";
                break;
            case KeyEvent.VK_A:
                keyInput = "left";
                break;
            case KeyEvent.VK_D:
                keyInput = "right";
                break;
            default:
                keyInput ="";
        }
        player.update(keyInput);
        gameManager.update();

    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            update();
            gamePanel.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}