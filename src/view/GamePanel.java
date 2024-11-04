package view;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel {

    public final int tileSize = 16;
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 40;
    public final int screeWidth = maxScreenCol * tileSize;
    public final int screenLength = maxScreenRow * tileSize;
public GamePanel(){
    this.setPreferredSize(new Dimension(screeWidth,screenLength));
    this.setDoubleBuffered(true);
    this.setBackground(Color.black);
    this.setVisible(true);

}

}
