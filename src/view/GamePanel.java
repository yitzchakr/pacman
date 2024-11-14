package view;

import model.GameManager;
import model.GameMap;
import model.Player;
import model.ghost.Ghost;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public final int tileSize = 32;
    public final int maxScreenCol = 35;
    public final int maxScreenRow = 25;

    public final int screeWidth = maxScreenCol * tileSize;
    public final int screenLength = maxScreenRow * tileSize;
    GameMap gameMap;
    Player player;
    GameManager gameManager;
    Ghost[]ghosts;

    public GamePanel(Player player, GameMap gameMap, GameManager gameManager, Ghost[]ghosts) {
        this.setPreferredSize(new Dimension(screeWidth, screenLength));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setFocusable(true);
        this.gameMap= gameMap;
        this.player = player;
        this.gameManager= gameManager;
        this.ghosts = ghosts;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        gameMap.draw(g2);
        player.draw(g2);
        gameManager.draw(g2);

        for (Ghost ghost : ghosts)
            ghost.draw(g2);
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial",Font.BOLD,40));
        g2.drawString("SCORE: "+gameManager.score,tileSize*26,tileSize*5);
        g2.drawString("LIVES: "+ player.lives,tileSize*26,tileSize*10);
        g2.dispose();
    }



}
