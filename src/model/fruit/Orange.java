package model.fruit;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Orange extends Fruit{
    public Orange() {
        value= 700;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/fruit/orange.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
