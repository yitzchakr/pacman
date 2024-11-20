package model.fruit;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Cherry extends Fruit {
    public Cherry(){
      value= 100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/fruit/cherry.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
