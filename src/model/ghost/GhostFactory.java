package model.ghost;

import model.GameMap;
import model.Player;

public class GhostFactory {
  private GameMap gameMap;
  private Player player;

    public GhostFactory(GameMap gameMap,Player player) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public Ghost[] createGhosts(){
       Ghost[]ghosts= new Ghost[4];
       ghosts[0]= new Pinky(gameMap, player);
        ghosts[1]= new Blinky(gameMap ,player);
        ghosts[2]= new Clyde(gameMap ,player);
        ghosts [3]= new Inky(gameMap,player,(Blinky) ghosts[1]);
       return ghosts;
    }

}
