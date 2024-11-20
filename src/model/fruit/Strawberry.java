package model.fruit;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Strawberry extends Fruit{
    public Strawberry(){
        value= 300;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/fruit/strawberry.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
