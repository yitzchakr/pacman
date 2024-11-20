package model;

import model.fruit.Fruit;
import model.ghost.Ghost;

import java.awt.*;
import java.util.*;

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
    public int level=1;
    FruitManager fruitManager;


    public GameManager(Player player, GameMap gameMap, Ghost[] ghosts) {
        this.player = player;
        this.gameMap = gameMap;
        foodMap = new Food[gameMap.mapLength][gameMap.mapWidth];
        this.ghosts = ghosts;
        chamber = new LinkedList<>();
        chamber.addAll(Arrays.asList(ghosts));
        loadMap();

        fruitManager = new FruitManager(gameMap, foodMap);
        fruitManager.initializeFruits();

    }

    public void update() {
        if (coins == 0) {
            loadMap();
            levelUp();
            fruitManager.initializeFruits();
            restartLevel();
            return;
        }
        handleEncounter();
        updateScore();
        fruitManager.updateFruit( level);
        releaseGhost();
        for (Ghost ghost : ghosts) {
            ghost.update(level);
        }

    }
    private void handleEncounter() {
        Timer timer = new Timer();
        for (Ghost ghost : ghosts) {
            if (ghost.locX == player.locX && ghost.locY == player.locY) {
                if (ghost.chasable) {

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            player.lives--;
                            fruitManager.clearFruit();
                            restartLevel();
                        }
                    }, 150);
                    return;
                }else {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            score += 200;
                            ghost.setDefaultValues();
                            chamber.add(ghost);

                        }
                    },150);
                }
            }
        }
    }


    private void levelUp() {
        player.lives++;
        if (level< 3)
            level++;
    }

    private void restartLevel() {

        for (Ghost ghost : ghosts) {
            ghost.setDefaultValues();
        }
        player.setDefaultValues();
        chamber.clear();
        chamber.addAll(Arrays.asList(ghosts));

    }



    private void loadMap() {
        for (int i = 0; i < gameMap.mapLength; i++) {
            for (int j = 0; j < gameMap.mapWidth; j++) {
                if (gameMap.map[i][j] == '1') {
                    foodMap[i][j] = new Coin();
                    coins++;
                } else if (gameMap.map[i][j] == '2') {
                    foodMap[i][j] = new BigCoin();
                    coins++;
                } else if (gameMap.map[i][j] == '4') {
                    for (Ghost ghost : ghosts) {
                        ghost.startX = j;
                        ghost.startY = i;
                        ghost.locX = j;
                        ghost.locY = i;
                    }
                }
            }
        }
        initialCoins = coins;
    }

    private void releaseGhost() {
        if (!chamber.isEmpty()) {
            chamberTimer++;
            if (chamberTimer > 100) {
                Ghost ghost = chamber.remove();
                ghost.scatterTimer = 0;
                ghost.locX = ghost.startX;
                ghost.locY = ghost.startY - 2;
                chamberTimer = 0;
            }
        }

    }


    private void updateScore() {
        Food food = foodMap[player.locY][player.locX];
        if (food == null)
            return;
        if (food.getClass() == Coin.class) {
            foodMap[player.locY][player.locX] = null;
            score += coin.value;
            coins--;
        } else if (food.getClass() == BigCoin.class) {
            foodMap[player.locY][player.locX] = null;
            score += bigCoin.value;
            coins--;
            for (Ghost ghost : ghosts) {
                ghost.chasable = false;
                ghost.escapeTimer = 0;
                reverseDirection(ghost);

            }
        } else if (food instanceof Fruit) {
            foodMap[player.locY][player.locX] = null;
            score += food.value;
            fruitManager.cycleFruits(food);
        }
    }

    private void reverseDirection(Ghost ghost) {
        if (ghost.locY > ghost.lastY)
            ghost.lastY += 2;
        else if (ghost.locY < ghost.lastY)
            ghost.lastY -= 2;
        else if (ghost.locX > ghost.lastX)
            ghost.lastX += 2;
        else ghost.lastX -= 2;
    }


    @Override
    public void draw(Graphics g2) {

        for (int i = 0; i < foodMap.length; i++) {
            for (int j = 0; j < foodMap[0].length; j++) {
                Food food = foodMap[i][j];
                if (food != null)
                    g2.drawImage(food.image, j * gameMap.tileSize, i * gameMap.tileSize,gameMap.tileSize,gameMap.tileSize, null);

            }
        }
    }
}