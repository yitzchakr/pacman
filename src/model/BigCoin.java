package model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BigCoin extends Food{
    public BigCoin (){
        value =50;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/bigCoin.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
