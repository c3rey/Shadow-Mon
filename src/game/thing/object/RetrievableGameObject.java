package game.thing.object;

import game.world.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RetrievableGameObject extends GameObject{

    int objectNum;
    Rectangle replacingArea;
    BufferedImage image1, image2;
    public BufferedImage displayImage;
    public String description;
    public boolean retrieved;
    public boolean isReplacing = false;


    RetrievableGameObject(Map map, int objectNum, int worldX, int worldY) {
        super(map, objectNum, worldX, worldY);

        this.map = map;
        this.worldX = worldX;
        this.worldY = worldY;
        this.objectNum = objectNum;
        setObject(objectNum);

        retrieved = false;
    }


    void update(int updateCount){

        if (retrieved || level.currentMap != map){ //if the object is retrieved or the current map isn't the one that the object belongs on...
            //sets solidArea and interactArea to 0
            solidArea.setSize(0,0);
            interactArea.setSize(0, 0);
        }else{ //if the object is on the ground and the current map is the one that the object belongs on...

            if (updateCount == 0){ //RetrievableGameObject animation
            image = image1;
            } else if (updateCount == 1) {
            image = image2;
            }

            //sets object's solidArea and interactArea to default values
            solidArea.setSize(width, height);
            interactArea.setSize(width + 10, height + 10);
        }
    }

    private void setObject(int objectNum) {

    }

}
