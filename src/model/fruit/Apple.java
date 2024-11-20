package model.fruit;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Apple extends Fruit{
    public Apple(){
        value = 500;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/fruit/apple.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
