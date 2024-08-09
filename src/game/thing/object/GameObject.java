package game.thing.object;
import game.thing.Thing;
import game.world.Map;

import java.awt.*;

public class GameObject extends Thing{

    //public static final int [name of GameObject here]

    public Map map;
    public int objectNum;

    public GameObject(Map map, int objectNum, int worldX, int worldY) {
        this.map = map;
        this.objectNum = objectNum;

        interactArea = new Rectangle(worldX, worldY, width + 10, height + 10);
        solidArea = new Rectangle(worldX, worldY + 10, width, height);

        interactArea.width = width + 10;
        interactArea.height = height + 10;
    }

    private void setObject(int objectNum){
        //see setObject() in RetrievableGameObject for reference
    }

}
