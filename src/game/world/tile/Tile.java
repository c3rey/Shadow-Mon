package game.world.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {

    public boolean collision;
    BufferedImage image;
    int tileNum;

    public static final int BLACKTILE = 0;
    public static final int WOODTILE = 1;
    public static final int CREAMWALL = 2;
    public static final int STONETILE = 3;


    public Tile(int tileNum){
        this.tileNum = tileNum;
        setTile();
    }

    private void setTile(){
        try{
            switch (tileNum){
                case BLACKTILE:
                    image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\tile\\blacktile.png"));
                    collision = true;
                    break;
                case WOODTILE:
                    image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\tile\\woodtile.png"));
                    collision = false;
                    break;
                case CREAMWALL:
                    image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\tile\\creamwall.png"));
                    collision = true;
                    break;
                case STONETILE:
                    image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\tile\\stonetile.png"));
                    collision = false;
                    break;
                default: tileNum = 0;
            }
        }catch(IOException e){
            System.err.println("IOException in Tile");
        }

    }



}
