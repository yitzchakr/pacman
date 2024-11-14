package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {
    String direction = "down";
    String desiredDirection = "";
    GameMap gameMap;
    final int playerHeight = 32;
    final int playerWidth = 32;
    int pixelCounter = 0;
    int pictureCounter = 0;
    boolean switchPicture;
    int spriteCounter = 0;


    public Player(GameMap gameMap) {
        this.gameMap = gameMap;
        locX = gameMap.playerX;
        locY = gameMap.playerY;
        speed = 4;
        loadPictures();
    }


    public void update(String keyInput) {
        spriteCounter++;
        if (spriteCounter == 4)
            spriteCounter = 0;
        if (pictureCounter < 32) {
            pictureCounter += speed;

        }

        pixelCounter += speed;

        if (pixelCounter >= 32) {
            pixelCounter = 0;
            move(keyInput);
        }
    }

    public void move(String input) {

        if (locX < 1) {
            locX += 23;
            pictureCounter = 0;
            return;
        }
        if (locX > 23) {
            locX -= 23;
            pictureCounter = 0;
            return;
        }
        desiredDirection = input;
        int newX = locX;
        int newY = locY;


        if (!desiredDirection.isEmpty()) {
            switch (desiredDirection) {
                case "up":
                    newY -= 1;
                    break;
                case "down":
                    newY += 1;
                    break;
                case "left":
                    newX -= 1;
                    break;
                case "right":
                    newX += 1;
                    break;
            }


            if (!gameMap.isCollision(newX, newY)) {
                locX = newX;
                locY = newY;
                direction = desiredDirection;
                pictureCounter = 0;
                return;
            }
        }

        newX = locX;
        newY = locY;
        switch (direction) {
            case "up":
                newY -= 1;
                break;
            case "down":
                newY += 1;
                break;
            case "left":
                newX -= 1;
                break;
            case "right":
                newX += 1;
                break;
        }

        if (!gameMap.isCollision(newX, newY)) {
            locX = newX;
            locY = newY;
            pictureCounter = 0;
        }
    }

    public void draw(Graphics g2) {


        switch (direction) {
            case ("up"):
                if (switchPicture) {
                    g2.drawImage(images[0], locX * gameMap.tileSize, locY * gameMap.tileSize + 32 - pictureCounter, playerWidth, playerHeight, null);
                    if (spriteCounter == 0) {
                        switchPicture = false;
                    }
                } else {
                    if (spriteCounter == 0) {
                        switchPicture = true;
                    }
                    g2.drawImage(images[1], locX * gameMap.tileSize, locY * gameMap.tileSize + 32 - pictureCounter, playerWidth, playerHeight, null);

                }
                break;
            case ("down"):
                if (switchPicture) {
                    g2.drawImage(images[2], locX * gameMap.tileSize, locY * gameMap.tileSize - 32 + pictureCounter, playerWidth, playerHeight, null);
                    if (spriteCounter == 0) {
                        switchPicture = false;
                    }
                } else {
                    if (spriteCounter == 0) {
                        switchPicture = true;
                    }
                    g2.drawImage(images[3], locX * gameMap.tileSize, locY * gameMap.tileSize - 32 + pictureCounter, playerWidth, playerHeight, null);

                }
                break;
            case ("left"):
                if (switchPicture) {
                    g2.drawImage(images[4], locX * gameMap.tileSize + 32 - pictureCounter, locY * gameMap.tileSize, playerWidth, playerHeight, null);
                    if (spriteCounter == 0) {
                        switchPicture = false;
                    }
                } else {
                    if (spriteCounter == 0) {
                        switchPicture = true;
                    }
                    g2.drawImage(images[5], locX * gameMap.tileSize+32 -pictureCounter, locY * gameMap.tileSize , playerWidth, playerHeight, null);


                }
                break;
            case ("right"):
                if (switchPicture) {
                    g2.drawImage(images[6], locX * gameMap.tileSize - 32 + pictureCounter, locY * gameMap.tileSize, playerWidth, playerHeight, null);
                    if (spriteCounter == 0)
                        switchPicture = false;
                } else {
                    if (spriteCounter == 0)
                        switchPicture = true;
                    g2.drawImage(images[7], locX * gameMap.tileSize - 32 + pictureCounter, locY * gameMap.tileSize, playerWidth, playerHeight, null);

                }
                break;

        }

    }
    public void loadPictures(){
        images = new Image[8];
        try {
            images[0]= ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_up1_360.png"));
            images[1]= ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_up2_360.png"));
            images [2] = ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_down1_360.png"));
            images [3]= ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_down2_360.png"));
            images [4]= ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_left1_360.png"));
            images [5]= ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_left2_360.png"));
            images [6]= ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_right1.png"));
            images [7]= ImageIO.read(getClass().getResourceAsStream("/resources/pacman/pacman_right2.png"));



        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
