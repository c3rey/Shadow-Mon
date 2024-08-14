package game.thing.object;

import game.world.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RetrievableGameObject extends GameObject{

    public static final int KEY = 0;

    int objectNum;
    Rectangle replacingArea;
    BufferedImage image1, image2;
    public BufferedImage displayImage;
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


    void update(int spriteCount){

        if (retrieved || level.currentMap != map){ //if the object is retrieved or the current map isn't the one that the object belongs on...
            //sets solidArea and interactArea to 0
            solidArea.setSize(0,0);
            interactArea.setSize(0, 0);
        }else{ //if the object is on the ground and the current map is the one that the object belongs on...

            if (spriteCount == 0){ //RetrievableGameObject animation
            image = image1;
            } else if (spriteCount == 1) {
            image = image2;
            }

            setObject(objectNum); //sets object's solidArea and interactArea to default values
        }
    }

    public void replace(int x, int y){
        this.worldX = x;
        this.worldY = y;
        solidArea.x = x;
        solidArea.y = y + 10;
        interactArea.x = x;
        interactArea.y = y;

        map = level.currentMap; // sets object's map to the one in which it's placed

        replacingArea = new Rectangle(worldX, worldY, width, height);
        isReplacing = true; // object will be placed where Player is currently standing, isReplacing turns off object collision so that Player can walk off of it
    }

    private void setObject(int objectNum) {
        try {
            switch (objectNum) {
                case (KEY):
                    image1 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\object\\RetrievableKey1.png")); //sets images
                    image2 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\object\\RetrievableKey2.png"));
                    displayImage = image1; //image to be displayed in inventory slot
                    collisionOn = true; // object has collision on

                    width = 30;
                    height = 30;

                    //solidArea and interactArea's sizes are set
                    solidArea.setSize(width, height);
                    interactArea.setSize(width + 10, height + 10);
            }
        }catch (IOException e){
            System.err.println("IOException in RetrievableGameObject");
        }
    }

}
