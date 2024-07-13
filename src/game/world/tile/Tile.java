package game.world.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {

    public boolean collision;
    BufferedImage image;
    int tileNum;

    public Tile(int tileNum){
        this.tileNum = tileNum;
        setTiles();
    }

    private void setTiles(){
        try{
            switch (tileNum){
                case 0:
                    image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\tile\\blacktile.png"));
                    collision = true;
                    break;
                case 1:
                    image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\tile\\woodtile.png"));
                    collision = false;
                    break;
                case 2:
                    image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\tile\\creamwall.png"));
                    collision = true;
                    break;
                case 3:
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
