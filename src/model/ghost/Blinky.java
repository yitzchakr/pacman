package model.ghost;

import model.GameMap;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Blinky extends Ghost{

    public Blinky( GameMap gameMap){
        super(gameMap);
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/blinky.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void chase() {

    }

}
