package model.fruit;

import model.Food;

import java.awt.*;

public abstract class Fruit extends Food {
     public Point point;
   public int fruitCounter;

    public  void resetCounter(){
        fruitCounter= 1200-value;

    }
    public Fruit(){
        resetCounter();
    }
}
