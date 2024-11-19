package model.ghost;

import model.GameMap;
import model.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Inky extends Ghost{
    Blinky blinky;
    public Inky(GameMap gameMap , Player player,Blinky blinky) {

        super(gameMap ,player);
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/inky.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scatterTarget = new int[] { 0,gameMap.mapLength-1,};
        this.blinky= blinky;
    }



    @Override
    public void chase() {
        int targetX = player.locX;
        int targetY = player.locY;
        if (player.locX > blinky.locX)
            targetX += player.locX-blinky.locX;
        else if (player.locX <blinky.locX)
            targetX -= blinky.locX- player.locX;
        if (player.locY> blinky.locY)
            targetY += player.locY-blinky.locY;
        else if (player.locY < blinky.locY)
            targetY -= blinky.locY- player.locY;
        int []move = getNextMove(targetX,targetY);
        move(move);
    }
}
