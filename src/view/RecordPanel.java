package view;

import control.GameRecorder;
import model.recorder.RecordFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RecordPanel extends JPanel {
    public final int tileSize = 32;
    public final int maxScreenCol = 35;
    public final int maxScreenRow = 25;

    public final int screeWidth = maxScreenCol * tileSize;
    public final int screenLength = maxScreenRow * tileSize;
    GameRecorder gameRecorder;
   public boolean ended = false;
   public  boolean stopPlaying= false;

    public RecordPanel (GameRecorder gameRecorder){
        this.gameRecorder= gameRecorder;
        this.setPreferredSize(new Dimension(screeWidth, screenLength));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               if (e.getKeyCode()== KeyEvent.VK_ESCAPE){
                   stopPlaying= true;
               }
            }
        });
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameRecorder.recorder.isEmpty()) {
            ended = true;
            return;
        }
        RecordFrame record = gameRecorder.recorder.remove();
        char[][] frame = record.getFrame();
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[0].length; j++) {
                char let = frame[i][j];
                Image image = gameRecorder.imageMap.get(let);
                g.drawImage(image, j * tileSize, i * tileSize, tileSize, tileSize, null);
            }
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("SCORE: " + record.getScore(), tileSize * 25, tileSize * 5);
        g.drawString("LIVES: " + record.getLives(), tileSize * 25, tileSize * 10);
        g.drawString("COINS LEFT: " + record.getCoins(), tileSize * 25, tileSize * 15);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("PRESS ESCAPE TO EXIT",tileSize*25,tileSize);
    }
}
