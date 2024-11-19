package model.ghost;

import model.GameMap;
import model.Player;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Blinky extends Ghost{
    ;

    public Blinky(GameMap gameMap , Player player){
        super(gameMap , player);
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/blinky.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scatterTarget= new int[]{gameMap.mapWidth-1,0};
    }


    @Override
    public void chase() {
        int [] move = getNextMove(player.locX,player.locY);
     move(move);

    }


}
