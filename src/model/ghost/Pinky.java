package model.ghost;

import model.GameMap;
import model.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Pinky extends Ghost{
    public Pinky(GameMap gameMap , Player player) {
        super(gameMap ,player);
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/pinky.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scatterTarget = new int[]{gameMap.mapWidth-1, gameMap.mapLength-1};
    }


    @Override
    public void chase() {
        int targetX = player.locX;
        int targetY = player.locY;
        switch (player.direction){
            case "up":
                targetY -= 4;
            case "down":
                targetY += 4;
            case "right":
                targetX += 4;
            default:
                targetX -=4;
        }
        int []move = getNextMove(targetX,targetY);
        move (move);
    }
}
