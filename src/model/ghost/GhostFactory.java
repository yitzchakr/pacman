package model.ghost;

import model.GameMap;

public class GhostFactory {
    public GameMap gameMap;

    public GhostFactory(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Ghost[] createGhosts(){
       Ghost[]ghosts= new Ghost[1];
       ghosts[0]= new Blinky(gameMap);
       return ghosts;
    }

}
