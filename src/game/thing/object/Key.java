package game.thing.object;

import game.world.Map;

public class Key extends RetrievableGameObject implements Usable{

    public int keyCode;

    Key(Map map, int objectNum, int worldX, int worldY){
        super(map, objectNum, worldX, worldY);
    }

}
