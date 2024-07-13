package game.world;

import game.GamePanel;
import game.Thing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Door extends Thing {
    public Map entryMap, exitRoom;
    public Level level;
    public BufferedImage image, doorClosedImage, doorOpenImage;
    public Rectangle interactArea;
    public boolean isClosed;
    public int xTile, yTile;
    public int doorWidth, doorHeight;
    public static final int WOODENDOOR = 0;
    public static final int ANCIENTDOOR = 1;
    public static final int MODERNDOOR = 2;

    public Door(Map entryMap, Map exitRoom, int type, int xTileNum, int yTileNum){
        this.entryMap = entryMap;
        this.exitRoom = exitRoom;

        level = entryMap.level;
        isClosed = true;

        setDoorImage(type);
        setDoorCoOrds(xTileNum, yTileNum);

        width = GamePanel.tileSize;
        height = GamePanel.tileSize;

        solidArea = new Rectangle(worldX, worldY, doorWidth, doorHeight);
        interactArea = new Rectangle(worldX, worldY, doorWidth, doorHeight + 20);
    }

    private void setDoorImage(int type){
        try {
            doorOpenImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\doors\\opendoor.png"));
            switch (type){
                case WOODENDOOR:
                    doorClosedImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\doors\\woodendoor.png"));
                    break;
                case ANCIENTDOOR:
                    //blah blah blah
                    break;
                case MODERNDOOR:
                    //blah blah blah
                    break;
            }
        }catch (IOException e){
            System.err.println("IOException in Door");
        }
    }

    void setDoorCoOrds(int xTileNum, int yTileNum){
        this.xTile = xTileNum; this.yTile = yTileNum;

        worldX = xTileNum * GamePanel.tileSize;
        worldY = yTileNum * GamePanel.tileSize;
    }

    void update(){
        if (isClosed){
            image = doorClosedImage;
        }else{
            image = doorOpenImage;
        }
    }
}
