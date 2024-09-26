package game.thing.door;

import game.GamePanel;
import game.thing.Thing;
import game.world.Level;
import game.world.Map;
import game.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Door extends Thing {
    public Map entryMap, exitMap;
    public Level level;
    public BufferedImage doorClosedImage, doorOpenImage;
    public boolean isClosed;
    public int xTile, yTile;

    public static final int WOODENDOOR = 0;
    public static final int ANCIENTDOOR = 1;
    public static final int MODERNDOOR = 2;

    public Door(Map entryMap, Map exitMap, int type, int xTile, int yTile){
        this.entryMap = entryMap;
        this.exitMap = exitMap;
        this.xTile = xTile;
        this.yTile = yTile;

        level = entryMap.level;
        isClosed = true;

        width = GamePanel.tileSize;
        height = GamePanel.tileSize;

        setDoorImage(type);
        setDoorCoOrds();

        solidArea = new Rectangle(worldX, worldY, width, height);
        interactArea = new Rectangle(worldX, worldY, width, height + GamePanel.tileSize);
    }

    private void setDoorImage(int type){
        try {
            doorOpenImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\doors\\opendoor.png"));
            switch (type){
                case WOODENDOOR:
                    doorClosedImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\doors\\woodendoor.png"));
                    break;
            }
        }catch (IOException e){
            System.err.println("IOException in Door");
        }

        image = doorClosedImage;
    }

    private void setDoorCoOrds(){
        worldX = xTile * GamePanel.tileSize;
        worldY = yTile * GamePanel.tileSize;
    }

    public void update(){
        if (isClosed){
            image = doorClosedImage;
        }else{
            image = doorOpenImage;
        }

        if (level.currentMap != entryMap){
            solidArea.setSize(0,0);
            interactArea.setSize(0,0);
        }else{
            solidArea = new Rectangle(worldX, worldY, width, height);
            interactArea = new Rectangle(worldX, worldY, width, height + GamePanel.tileSize);
        }
    }

    public void open(){
        isClosed = false;
        World.sound.playDoorOpening();
    }
}
