package model.ghost;

import model.GameMap;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Clyde extends Ghost{

    public Clyde(GameMap gameMap) {
        super(gameMap);
        try {
            testImage = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/clyde.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void chase() {

    }
}
