package game.object;
import game.Thing;
import game.world.Level;
import game.world.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends Thing{

    //public static final int [name of GameObject here]

    public final boolean retrieved = false;
    public boolean collisionOn = true;
    public Level level;
    public Map map;
    public int interactAreaWidth, interactAreaHeight;
    public Rectangle interactArea;
    public int type;

    public GameObject(Map map, int type, int worldX, int worldY) {
        this.map = map;
        this.type = type;

        interactAreaWidth = width + 10;
        interactAreaHeight = height + 10;

        interactArea = new Rectangle(worldX, worldY, interactAreaWidth, interactAreaHeight);
        solidArea = new Rectangle(worldX, worldY, width, height);
    }

    private void setGameObject(int type){
        switch (type){
            //case [name of GameObject here]:
            //  image = [image file]
            //  width, height = [witdth and height]
        }
    }

}
