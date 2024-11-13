package model.ghost;

import model.Drawable;
import model.Entity;
import model.GameMap;

import java.awt.*;

public abstract class Ghost extends Entity implements Drawable {
    GameMap gameMap;
    public boolean chaseable = true;
    public int escapeTimer = 0;
    public int scatterTimer;

    public Ghost(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void update() {

        if (!chaseable) {
            escapeTimer++;
            escape();
            if (escapeTimer == 360) {
                chaseable = true;
                escapeTimer = 0;
            }
        } else if (scatterTimer < 600) {
            scatterTimer++;
            scatter();
        } else
            chase();
    }

    public void scatter() {
    }


    public abstract void chase() ;


        public void escape () {

        }

        public void draw (Graphics g2){

            g2.drawImage(testImage, locX * gameMap.tileSize, locY * gameMap.tileSize, gameMap.tileSize, gameMap.tileSize, null);
        }

    }

