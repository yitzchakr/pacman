package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class GameMap implements Drawable {
    public final int mapLength =25;
    public final int mapWidth =25;
    public int tileSize =32;
    int playerX;
    int playerY;

    public char [][] map ;
    public GameMap() {

        map = new char[mapLength][mapWidth];
        loadMap("src/resources/map01.txt");
    }
    public void loadMap( String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String content = reader.lines().collect(Collectors.joining());
            content = content.replace(" ", "");
            int Idx = 0;
            for (int i = 0; i < mapLength; i++) {
                for (int j = 0; j < mapWidth; j++) {
                    map[i][j] = content.charAt(Idx++);
                    if (map[i][j]=='3'){
                        playerX= j;
                        playerY =i;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isCollision( int x, int y){
        if (x < 0 || y < 0 || x >= mapWidth || y >= mapLength)
            return true;
        return map[y][x]=='0';
    }

    @Override
    public void draw(Graphics g2) {
        Image image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/wall.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int posX=0;
        int posY=0;
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (map[i][j]=='0')
                    g2.drawImage(image,posX,posY,tileSize,tileSize,null);
                posX += tileSize;

            }
            posX=0;
            posY += tileSize;
        }
    }

}
