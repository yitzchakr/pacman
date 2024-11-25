package model.ghost;

import model.Drawable;
import model.Entity;
import model.GameMap;
import model.Player;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public abstract class Ghost extends Entity implements Drawable {
    private GameMap gameMap;
    Player player;
    public int startX;
    public int startY;
    public boolean chasable = true;
    public int escapeTimer = 0;
    public int scatterTimer;
    int[] scatterTarget;
    public int lastX=12;
    public int lastY=10;
    int moveCounter = 0;

   public Image escape;

    {
        try {
            escape = ImageIO.read(getClass().getResourceAsStream("/resources/ghosts/escape.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Ghost(GameMap gameMap, Player player) {
        this.gameMap = gameMap;
        this.player = player;
        speed = 4;
        scatterTimer=0;
    }

    public void setDefaultValues() {
        chasable = true;
        escapeTimer = 0;
        speed = 4;
        locX = startX;
        locY = startY;
        lastX = locX;
        lastY = locY;
    }

    public void update( int level) {
        moveCounter += speed;
        if (moveCounter < 32) {
            return;
        }
        moveCounter = 0;
        scatterTimer++;


        if (!chasable) {
            escapeTimer++;
            speed = 2;
            escape();
            if (escapeTimer >= 40 -(level*10)) {
                chasable = true;
                escapeTimer = 0;
                speed = 4;
            }
        } else if (scatterTimer % 180 + level*3 < 40) {

            scatter();
        } else {

            chase();

        }

    }

    public int[] getNextMove(int targetX, int targetY) {
        int distance;
        int[] next = null;
        int min = Integer.MAX_VALUE;
        ArrayList<int[]> moves = getAvailableMoves();
        for (int[] move : moves) {
            distance = (move[0] - targetX) * (move[0] - targetX) + (move[1] - targetY) * (move[1] - targetY);
            if (distance < min) {
                min = distance;
                next = move;
            }
        }
        return next;
    }

    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        if (!(locX - 1 == lastX && locY == lastY) && !gameMap.isCollision(locX - 1, locY))
            moves.add(new int[]{locX - 1, locY});
        if (!(locX + 1 == lastX && locY == lastY) && !gameMap.isCollision(locX + 1, locY))
            moves.add(new int[]{locX + 1, locY});
        if ((locX != lastX || locY - 1 != lastY) && !gameMap.isCollision(locX, locY - 1))
            moves.add(new int[]{locX, locY - 1});
        if (!(locX == lastX && locY + 1 == lastY) && !gameMap.isCollision(locX, locY + 1))
            moves.add(new int[]{locX, locY + 1});
        if (moves.isEmpty())
            moves.add(new int[]{lastX, lastY});
        return moves;

    }

    public void move(int[] move) {
        lastY = locY;
        lastX = locX;
        locX = move[0];
        locY = move[1];
    }

    public void scatter() {

        int[] move = getNextMove(scatterTarget[0], scatterTarget[1]);
        move(move);
    }


    public abstract void chase();


    public void escape() {
        Random r = new Random();
        ArrayList<int[]> moves = getAvailableMoves();
        int direction = r.nextInt(moves.size());
        int[] nextMove = moves.get(direction);
        move(nextMove);
    }
    Point getOffset(){
     return new Point(lastX-locX,lastY-locY);
    }

    public void draw(Graphics g2) {
        if (chasable)
            g2.drawImage(testImage,((locX * gameMap.tileSize))+ (32-moveCounter)*(getOffset().x), ((locY* gameMap.tileSize)+(32-moveCounter)*getOffset().y), gameMap.tileSize, gameMap.tileSize, null);
        else
            g2.drawImage(escape, ( (32-moveCounter)*(getOffset().x)+(locX * gameMap.tileSize)), ((32-moveCounter)*getOffset().y)+(locY* gameMap.tileSize), gameMap.tileSize, gameMap.tileSize, null);;
    }

}

