package model.ghost;

import model.GameMap;
import model.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Clyde extends Ghost{

    public Clyde(GameMap gameMap , Player player) {
        super(gameMap, player);
        scatterTarget = new int[]{0,0};
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/clyde.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void chase() {
        int targetX =player.locX;
        int targetY = player.locY;
        if ((Math.abs(targetY-locY)+Math.abs(targetX-locX)>8)){
            move (getNextMove(targetX,targetY));
        }else
            scatter();

    }
}
