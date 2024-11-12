package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity{
    String direction ="up";
    String desiredDirection = "";
    GameMap gameMap;
    final int playerHeight =32;
    final int playerWidth = 32;
    int pixelCounter= 0;

    public Player (GameMap gameMap){
        this.gameMap= gameMap;
        locX = gameMap.playerX;
        locY = gameMap.playerY;
        speed= 4;
    }




    public void update (String keyInput){
        pixelCounter += speed;
        if (pixelCounter >=32){
            pixelCounter=0;
            move(keyInput);
        }


    }
    public void move(String input) {
        if (locX<1 ){
            locX+=23;
            return;
        }
        if (locX>23){
            locX-=23;
            return;
        }
        desiredDirection = input;
        int newX = locX;
        int newY = locY;



        if (!desiredDirection.isEmpty()) {
            switch (desiredDirection) {
                case "up":    newY -= 1; break;
                case "down":  newY += 1; break;
                case "left":  newX -= 1; break;
                case "right": newX += 1; break;
            }


            if (!gameMap.isCollision(newX, newY) ) {
                locX = newX;
                locY = newY;
                direction = desiredDirection;
                return;
            }
        }


        newX = locX;
        newY = locY;
        switch (direction) {
            case "up":    newY -= 1; break;
            case "down":  newY += 1; break;
            case "left":  newX -= 1; break;
            case "right": newX += 1; break;
        }


        if (!gameMap.isCollision(newX, newY)) {
            locX = newX;
            locY = newY;
        }
    }

    public void draw(Graphics g2){
        Image image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/pacman.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (direction) {
            case ("up"):
                if (!gameMap.isCollision(locX,locY-1)) {
                    g2.drawImage(image, locX * gameMap.tileSize, locY * gameMap.tileSize - pixelCounter, playerWidth, playerHeight, null);
                }else g2.drawImage(image, locX * gameMap.tileSize, locY * gameMap.tileSize , playerWidth, playerHeight, null);
                break;
            case ("down"):
                if (!gameMap.isCollision(locX,locY+1)) {
                    g2.drawImage(image, locX * gameMap.tileSize, locY * gameMap.tileSize + pixelCounter, playerWidth, playerHeight, null);
                }else g2.drawImage(image, locX * gameMap.tileSize, locY * gameMap.tileSize , playerWidth, playerHeight, null);
                break;
            case ("left"):
                if (!gameMap.isCollision(locX-1,locY)) {
                    g2.drawImage(image, locX * gameMap.tileSize - pixelCounter, locY * gameMap.tileSize, playerWidth, playerHeight, null);
                }else g2.drawImage(image, locX * gameMap.tileSize, locY * gameMap.tileSize , playerWidth, playerHeight, null);
                break;
            case ("right"):
                if (!gameMap.isCollision(locX+1,locY)) {
                    g2.drawImage(image, locX * gameMap.tileSize + pixelCounter, locY * gameMap.tileSize, playerWidth, playerHeight, null);
                }else g2.drawImage(image, locX * gameMap.tileSize, locY * gameMap.tileSize , playerWidth, playerHeight, null);
                break;
        }

    }
}
