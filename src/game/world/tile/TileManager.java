package game.world.tile;

import game.GamePanel;
import game.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TileManager {
    int currentTileCount = 4; //UPDATE MANUALLY AS NEW TILES ARE ADDED
    public Tile[] tileArray = new Tile[currentTileCount];
    World world;

    private void setTiles() {
        tileArray[0] = new Tile(0);

        tileArray[1] = new Tile(1);

        tileArray[2] = new Tile( 2);

        tileArray[3] = new Tile(3);
    }

    public TileManager(World world) {
        this.world = world;
        setTiles();
    }

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