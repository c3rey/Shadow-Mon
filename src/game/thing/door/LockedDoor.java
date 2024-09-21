package game.thing.door;

import game.thing.object.Key;
import game.world.Map;

public class LockedDoor extends Door{

    public boolean isLocked = true;
    public int keyCode;

    public LockedDoor(Map entryMap, Map exitRoom, int type, int xTile, int yTile, int keyCode) {
        super(entryMap, exitRoom, type, xTile, yTile);
        this.keyCode = keyCode;
    }

    public boolean tryKey(Key key){
        if (key.keyCode == keyCode){
            isLocked = false;
            return true;
        }else{
            return false;
        }
    }
}
