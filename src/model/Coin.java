package model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Coin extends Food{
    public Coin(){
        value = 10;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/coin.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
