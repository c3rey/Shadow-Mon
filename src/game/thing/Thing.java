package game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Thing implements Comparable<Thing>{
    public int worldX, worldY, width, height;
    public BufferedImage image;
    public Rectangle solidArea;


    public void draw(Graphics2D g2){
        g2.drawImage(image, worldX, worldY, width, height, null);
    }

    @Override
    public int compareTo(Thing otherThing) {
        return this.worldY - otherThing.worldY;
    }
}
