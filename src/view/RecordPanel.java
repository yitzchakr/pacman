package view;

import control.GameRecorder;

import javax.swing.*;
import java.awt.*;

public class RecordPanel extends JPanel {
    public final int tileSize = 32;
    public final int maxScreenCol = 35;
    public final int maxScreenRow = 25;

    public final int screeWidth = maxScreenCol * tileSize;
    public final int screenLength = maxScreenRow * tileSize;
    GameRecorder gameRecorder;
   public boolean ended = false;

    public RecordPanel (GameRecorder gameRecorder){
        this.gameRecorder= gameRecorder;
        this.setPreferredSize(new Dimension(screeWidth, screenLength));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setFocusable(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (gameRecorder.recorder.isEmpty()){
            ended=true;
            return;
        }
        char [][] frame = gameRecorder.recorder.remove();
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[0].length; j++) {
                char let =  frame[i][j];
                Image image = gameRecorder.imageMap.get(let) ;
                g.drawImage(image,j*tileSize,i*tileSize,tileSize,tileSize,null);
            }
        }
    }

}
