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
    GhostFactory ghostFactory = new GhostFactory(gameMap , player);
    Ghost[]ghosts = ghostFactory.createGhosts();
    GameManager gameManager = new GameManager(player,gameMap,ghosts);
    GamePanel gamePanel = new GamePanel(player,gameMap,gameManager,ghosts);
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
   private String receiveKeyInput(){
        String keyInput ;
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
                keyInput ="";
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
        while (true) {
            update();
            gamePanel.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}