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
    int level = 1;
    private int escapeTimer = 0;
    private boolean escapeMode = false;

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
        if (gameIsOver()) {
            restart();
        }
        updateEscape();
        updateScore();
        releaseGhost();

    }

    private void restart() {
        player.lives--;
        loadMap();
        for (Ghost ghost : ghosts) {
            ghost.setDefaultValues();
        }
        player.setDefaultValues();
        chamber.clear();
        chamber.addAll(Arrays.asList(ghosts));

    }
    public void updateEscape (){
        if (escapeMode) {
            if (escapeTimer < 500 / level) {
                escapeTimer++;
            } else {
                escapeMode = false;
                for (Ghost ghost : ghosts) {
                    ghost.chaseable = true;
                }
                escapeTimer = 0;
            }
        }
    }

    private boolean gameIsOver() {
        for (Ghost ghost : ghosts) {
            if (player.locX == ghost.locX && player.locY == ghost.locY && ghost.chaseable) {
                return true;
            }
        }
        return false;
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
        if (!chamber.isEmpty()) {
            chamberTimer++;
            if (chamberTimer > 160) {
                Ghost ghost = chamber.remove();
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
        } else if (food != null && food.getClass() == BigCoin.class) {
            foodMap[player.locY][player.locX] = null;
            score += bigCoin.value;
            for (Ghost ghost : ghosts) {
                ghost.chaseable = false;
            }
            escapeMode = true;
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