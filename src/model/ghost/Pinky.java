package model.ghost;

import model.GameMap;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Pinky extends Ghost{
    public Pinky(GameMap gameMap) {
        super(gameMap);
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/pinky.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void chase() {

    }
}
