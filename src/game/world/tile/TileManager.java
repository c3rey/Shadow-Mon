package game.world.tile;

import game.GamePanel;
import game.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TileManager {
    int currentTileCount = 4;
    public Tile[] tileArray = new Tile[currentTileCount];
    World world;
    GamePanel gp;

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

    public void draw(Graphics2D g2, Tile[][] map) {

        BufferedImage tileImg;
        Tile currentTile;
        int x = 0;
        int y = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                    currentTile = map[i][j];

                    tileImg = currentTile.image;

                    g2.drawImage(tileImg, x, y, GamePanel.tileSize, GamePanel.tileSize, null);

                    x += GamePanel.tileSize; //shift to the right to print the next game.world.tile


            }
            x = 0;
            y+= GamePanel.tileSize;

        }
    }
}