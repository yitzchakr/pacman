package model.ghost;

import model.GameMap;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Inky extends Ghost{
    public Inky(GameMap gameMap) {
        super(gameMap);
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/inky.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void chase() {

    }
}
