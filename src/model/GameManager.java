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
    int score = 0;
    Queue<Ghost> chamber;
    int chamberTimer = 0;

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
        updateScore();
        if (!chamber.isEmpty()) {
            chamberTimer++;
            if (chamberTimer > 160) {
                releaseGhost();
                chamberTimer=0;
            }
        }

    }

    private void loadMap() {
        for (int i = 0; i < gameMap.mapLength; i++) {
            for (int j = 0; j < gameMap.mapWidth; j++) {
                if (gameMap.map[i][j] == '1')
                    foodMap[i][j] = new Coin();
                else if (gameMap.map[i][j] == '2')
                    foodMap[i][j] = new BigCoin();
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

    }

    private void releaseGhost() {
        Ghost ghost= chamber.remove();
        ghost.locX=ghost.startX;
        ghost.locY=ghost.startY-2;
    }

    private void updateScore() {
        Food food = foodMap[player.locY][player.locX];
        if (food != null && food.getClass() == Coin.class) {
            foodMap[player.locY][player.locX] = null;
            score += coin.value;
        } else if (food != null && food.getClass() == BigCoin.class) {
            foodMap[player.locY][player.locX] = null;
            score += bigCoin.value;
            for (Ghost ghost : ghosts) {
                ghost.chaseable = false;
            }
        }
    }


    @Override
    public void draw(Graphics g2) {

        for (int i = 0; i < foodMap.length; i++) {
            for (int j = 0; j < foodMap[0].length; j++) {
                Food food = foodMap[i][j];
                if (food != null && food.getClass() == Coin.class)
                    g2.drawImage(coin.image, j * gameMap.tileSize, i * gameMap.tileSize, null);
                if (food != null && food.getClass() == BigCoin.class)
                    g2.drawImage(bigCoin.image, j * gameMap.tileSize, i * gameMap.tileSize, null);

            }
        }
    }
}