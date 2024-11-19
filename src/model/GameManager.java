package model;

import model.ghost.Ghost;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GameManager implements Drawable {
    Food[][] foodMap;
    Ghost[] ghosts;
    Player player;
    GameMap gameMap;
    Coin coin = new Coin();
    BigCoin bigCoin = new BigCoin();

    Queue<Ghost> chamber;
    private int chamberTimer = 0;
    public int score = 0;
    public int coins = 0;
    int initialCoins;



    public GameManager(Player player, GameMap gameMap, Ghost[] ghosts) {
        this.player = player;
        this.gameMap = gameMap;
        foodMap = new Food[gameMap.mapLength][gameMap.mapWidth];
        this.ghosts = ghosts;
        chamber = new LinkedList<>();
        chamber.addAll(Arrays.asList(ghosts));
        loadMap();

    }

    public void update() {
        if (coins==0){
            loadMap();
            levelUp();
            restartLevel();
        }
        if (pacmanIsEaten()) {
            player.lives--;
            restartLevel();
        }

        updateScore();
        releaseGhost();
        for (Ghost ghost: ghosts){
            ghost.update();
        }

    }

    private void levelUp() {
        player.lives++;
        for (Ghost ghost: ghosts){
            if (ghost.level <=3 )
                ghost.level++;
        }
    }

    private void restartLevel() {

        for (Ghost ghost : ghosts) {
            ghost.setDefaultValues();
        }
        player.setDefaultValues();
        chamber.clear();
        chamber.addAll(Arrays.asList(ghosts));

    }

    private boolean pacmanIsEaten() {
        for (Ghost ghost : ghosts) {
            if (player.locX == ghost.locX && player.locY == ghost.locY && ghost.chasable) {
                return true;
            }else if (player.locX == ghost.locX && player.locY == ghost.locY ){
                score+=200;
                ghost.setDefaultValues();
                chamber.add(ghost);
            }
        }
        return false;
    }


    private void loadMap() {
        for (int i = 0; i < gameMap.mapLength; i++) {
            for (int j = 0; j < gameMap.mapWidth; j++) {
                if (gameMap.map[i][j] == '1') {
                    foodMap[i][j] = new Coin();
                    coins++;
                }
                else if (gameMap.map[i][j] == '2') {
                    foodMap[i][j] = new BigCoin();
                    coins++;
                }
                else if (gameMap.map[i][j] == '4') {
                    for (Ghost ghost : ghosts) {
                        ghost.startX = j;
                        ghost.startY = i;
                        ghost.locX = j;
                        ghost.locY = i;
                    }
                }
            }
        }
        initialCoins=coins;
    }

    private void releaseGhost() {
        if (!chamber.isEmpty()) {
            chamberTimer++;
            if (chamberTimer > 100) {
                Ghost ghost = chamber.remove();
                ghost.scatterTimer=0;
                ghost.locX = ghost.startX;
                ghost.locY = ghost.startY - 2;
                chamberTimer = 0;
            }
        }

    }


    private void updateScore() {
        Food food = foodMap[player.locY][player.locX];
        if (food != null && food.getClass() == Coin.class) {
            foodMap[player.locY][player.locX] = null;
            score += coin.value;
            coins--;
        } else if (food != null && food.getClass() == BigCoin.class) {
            foodMap[player.locY][player.locX] = null;
            score += bigCoin.value;
            coins--;
            for (Ghost ghost : ghosts) {
                if (!chamber.contains(ghost)) {
                    ghost.chasable = false;
                    reverseDirection(ghost);
                }
            }

        }
    }

    private void reverseDirection(Ghost ghost) {
        if (ghost.locY> ghost.lastY)
            ghost.lastY+=2;
        else if (ghost.locY< ghost.lastY)
            ghost.lastY-=2;
        else if (ghost.locX > ghost.lastX)
            ghost.lastX +=2;
        else ghost.lastX -=2;
    }


    @Override
    public void draw(Graphics g2) {

        for (int i = 0; i < foodMap.length; i++) {
            for (int j = 0; j < foodMap[0].length; j++) {
                Food food = foodMap[i][j];
                    if (food!= null)
                    g2.drawImage(food.image, j * gameMap.tileSize, i * gameMap.tileSize, null);

            }
        }
    }
}