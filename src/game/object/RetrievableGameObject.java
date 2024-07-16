package game.object;

import game.world.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RetrievableGameObject extends GameObject{

    public static final int KEY = 0;

    Rectangle replacingArea;
    BufferedImage image1, image2;
    public BufferedImage displayImage;
    public boolean retrieved;
    public boolean isReplacing = false;


    RetrievableGameObject(Map map, int type, int worldX, int worldY) {
        super(map, type, worldX, worldY);

        this.map = map;
        this.worldX = worldX;
        this.worldY = worldY;
        setObject(type);

        retrieved = false;
    }

    //RETRIEVABLEGAMEOBJECT ANIMATION
    //creates an animation to signify to player that game.object is retrievable
    void update(int spriteCount){
        if (spriteCount == 0){
            image = image1;
        } else if (spriteCount == 1) {
            image = image2;
        }

        if (retrieved){
            solidArea.setSize(0,0);
            interactArea.setSize(0, 0);
        }else{
            solidArea.setSize(width, height);
            interactArea.setSize(interactAreaWidth, interactAreaHeight);
        }
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

    private void setObject(int type) {
        try {
            switch (type) {
                case (KEY):
                    image1 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\object\\RetrievableKey1.png"));
                    image2 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\object\\RetrievableKey2.png"));
                    displayImage = image1;

                    width = 30;
                    height = 30;
            }
        }catch (IOException e){
            System.err.println("IOException in RetrievableGameObject");
        }
    }

}
