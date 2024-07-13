package game.object;

import game.world.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RetrievableGameObject extends GameObject{
    Rectangle replacingArea;
    BufferedImage image1, image2;
    public BufferedImage displayImage;
    public boolean retrieved;
    public boolean isReplacing = false;


    RetrievableGameObject(Map map, int objectX, int objectY, int objectLength, int objectHeight) {
        super(map, objectX, objectY, objectLength, objectHeight);

        this.map = map;
        worldX = objectX;
        worldY = objectY;
        width = objectLength;
        height = objectHeight;

        retrieved = false;
    }

    public void replace(int x, int y){
        this.worldX = x;
        this.worldY = y;
        solidArea.x = x;
        solidArea.y = y;
        interactArea.x = x;
        interactArea.y = y;

        replacingArea = new Rectangle(worldX, worldY, width, height);
        isReplacing = true; // object will be placed where Player is currently standing, isReplacing turns off object collision so that Player can walk off of it
    }

    void setImage(BufferedImage image1, BufferedImage image2) {
        this.image1 = image1;
        this.image2 = image2;
        displayImage = image1;
    }

    void checkRetrieved(){ //sets interactArea and SolidArea depending on whether the item is retrieved
        if (retrieved){
            solidArea.setSize(0,0);
            interactArea.setSize(0, 0);
        }else{
            solidArea.setSize(width, height);
            interactArea.setSize(interactAreaWidth, interactAreaHeight);
        }
    }
}
