package model;

import model.fruit.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FruitManager {
    GameMap gameMap;
    Fruit currentFruit;
    Food [][]foodMap;
    int fruitTimer;

    Queue<Fruit>fruits ;

    public FruitManager(GameMap gameMap,  Food[][] foodMap) {
        this.gameMap = gameMap;
        this.foodMap = foodMap;
        fruitTimer=0;
        fruits= new LinkedList<>();
    }

    void clearFruit() {
        fruitTimer=0;
        if (currentFruit.point!= null){
            foodMap[currentFruit.point.x][currentFruit.point.y]=null;
        }
    }

    void initializeFruits(){
        fruitTimer=0;
        fruits.add(new Cherry());
        fruits.add(new Strawberry());
        fruits.add(new Apple());
        fruits.add(new Orange());
        currentFruit= fruits.remove();

    }
    void updateFruit( int level)  {
        if (fruitTimer<600){
            fruitTimer++;
        }else {
            if (currentFruit.point==null)
                currentFruit.point =  createFruitLocation();
            if (foodMap[currentFruit.point.x][currentFruit.point.y]!=null){
                currentFruit.fruitCounter -=(3+level);
                if (currentFruit.fruitCounter <=0) {
                    foodMap[currentFruit.point.x][currentFruit.point.y] = null;
                    currentFruit.resetCounter();
                    fruitTimer=0;
                    currentFruit.point= createFruitLocation();
                }
            }else {
                foodMap[currentFruit.point.x][currentFruit.point.y]=currentFruit;

            }

        }
    }
    Point createFruitLocation(){
        Random r = new Random();
        int x;
        int y;
        do {
            x  = r.nextInt(0,25);
            y = r.nextInt(0,25);
        }while (foodMap[x][y] != null  || gameMap.map[x][y]=='0'|| x== 10);
        return new Point(x,y);
    }
    public void cycleFruits( Food food){
        ((Fruit) food).resetCounter();
        fruits.add((Fruit) food);
        currentFruit= fruits.remove();
        fruitTimer=0;
    }



}
