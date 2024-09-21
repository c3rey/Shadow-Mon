package game.world.tile;

import game.GamePanel;
import game.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TileManager {

    public void draw(Graphics2D g2, Tile[][] tileArray) {

        int x = 0;
        int y = 0;

        for (Tile[] tiles : tileArray) {
            for (Tile tile : tiles) {

                g2.drawImage(tile.image, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
                x += GamePanel.tileSize; //shift to the right to print the next game.world.tile

            }
            x = 0;
            y += GamePanel.tileSize;

        }
    }
}