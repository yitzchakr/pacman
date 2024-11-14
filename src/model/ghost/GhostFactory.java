package model.ghost;

import model.GameMap;

public class GhostFactory {
    public GameMap gameMap;

    public GhostFactory(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Ghost[] createGhosts(){
       Ghost[]ghosts= new Ghost[4];
       ghosts[0]= new Blinky(gameMap);
        ghosts[1]= new Inky(gameMap);
        ghosts[2]= new Pinky(gameMap);
        ghosts [3]= new Clyde(gameMap);
       return ghosts;
    }

}
