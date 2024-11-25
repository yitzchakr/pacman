package control;

import model.*;
import model.fruit.Apple;
import model.fruit.Cherry;
import model.fruit.Orange;
import model.fruit.Strawberry;
import model.ghost.*;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GameRecorder {
    Queue<char[][]> recorder;

    GameMap gameMap;
    Player player;
    Ghost [] ghosts;
    GameManager gameManager;
    HashMap <Character, Image> imageMap ;

    public GameRecorder(GameMap gameMap, Player player, Ghost[] ghosts, GameManager gameManager) {
        this.gameMap = gameMap;
        this.player = player;
        this.ghosts = ghosts;
        this.gameManager = gameManager;
        recorder= new LinkedList<>();
    }
    public void loadHashMap(){
        Coin a = new Coin(); BigCoin b = new BigCoin(); Cherry c = new Cherry();Strawberry d = new Strawberry();
        Apple e = new Apple(); Orange f = new Orange();
        imageMap = new HashMap<>();
        imageMap.put('0', gameMap.image);
        imageMap.put('1', a.image);
        imageMap.put('2', b.image);
        imageMap.put('4', d.image);
        imageMap.put('5', e.image);
        imageMap.put('6', f.image);
        imageMap.put('a',player.images[0]);
        imageMap.put('b', player.images[2]);
        imageMap.put('c', player.images[4]);
        imageMap.put('d', player.images[6]);
        imageMap.put('A',ghosts[0].escape);
        imageMap.put('B' , ghosts[0].testImage);
        imageMap.put('C' , ghosts[1].testImage);
        imageMap.put('D' , ghosts[2].testImage);
        imageMap.put('E' , ghosts[3].testImage);

    }
    public void record() {
        char[][] recordMap = new char[gameMap.mapLength][gameMap.mapWidth];
        for (int i = 0; i < recordMap.length; i++) {
            for (int j = 0; j < recordMap[0].length; j++) {
                if (gameMap.map[i][j] == '0') {
                    recordMap[i][j] = '0';
                }
                Food food = gameManager.getFoodMap()[i][j];
                if (food != null) {
                    switch (food) {
                        case Coin coin -> recordMap[i][j] = '1';
                        case BigCoin bigCoin -> recordMap[i][j] = '2';
                        case Cherry cherry -> recordMap[i][j] = '3';
                        case Strawberry strawberry -> recordMap[i][j] = '4';
                        case Apple apple -> recordMap[i][j] = '5';
                        case Orange orange -> recordMap[i][j] = '6';
                        default -> {
                        }
                    }
                }else if (player.locY== i && player.locX==j){
                    switch (player.direction){
                        case "up":
                            recordMap [i][j]= 'a';
                            break;
                        case "down":
                            recordMap [i][j]= 'b';
                            break;
                        case "left":
                            recordMap [i][j]= 'c';
                            break;
                        case "right":
                            recordMap [i][j]= 'd';

                    }
                }
                for (Ghost ghost : ghosts){
                    if (ghost.locY == i && ghost.locX == j ){
                        if (!ghost.chasable){
                            recordMap [i][j] = 'A';
                        }else if (ghost instanceof Pinky){
                            recordMap [i][j] = 'B';
                        }else if (ghost instanceof Blinky){
                            recordMap [i][j] = 'C';
                        }else if (ghost instanceof Clyde){
                            recordMap [i][j] = 'D';
                        }else if (ghost instanceof Inky){
                            recordMap [i][j] = 'E';
                        }
                    }
                }

            }
        }
        recorder.add(recordMap);
    }



}
