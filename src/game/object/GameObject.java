package game.object;
import game.Thing;
import game.world.Level;
import game.world.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends Thing{

    public final boolean retrieved = false;
    public boolean collisionOn = true;
    public Level level;
    public Map map;
    public int interactAreaWidth, interactAreaHeight;
    public Rectangle interactArea;

    public GameObject(Map map, int worldX, int worldY, int width, int height) {
        this.map = map;
        this.level = map.level;
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;

        interactAreaWidth = width + 10;
        interactAreaHeight = height + 10;

        interactArea = new Rectangle(worldX, worldY, interactAreaWidth, interactAreaHeight);
        solidArea = new Rectangle(worldX, worldY, width, height);
    }

    void setImage(BufferedImage image) {
        this.image = image;
    }

}
