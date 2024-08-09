package game.world;

import game.world.tile.Tile;

import java.util.ArrayList;

public class Map {
    //2D array that represents the game.map
    public Level level;
    public Tile[][] tileArray;
    public Map up = null;
    public Map down = null;
    public Map left = null;
    public Map right = null;

    public int leftPlayerX, leftPlayerY, rightPlayerX, rightPlayerY, upPlayerX, upPlayerY, downPlayerX, downPlayerY;

    public Map(Tile[][] tileArray){ //constructor
        this.tileArray = tileArray;
    }

    //Sets position Player will be in when entering a Map
    public void setNorthSpawn(int x, int y){
        upPlayerX = x;
        upPlayerY = y;
    }
    public void setSouthSpawn(int x, int y){
        downPlayerX = x;
        downPlayerY = y;
    }
    public void setWestSpawn(int x, int y){
        leftPlayerX = x;
        leftPlayerY = y;
    }
    public void setEastSpawn(int x, int y){
        rightPlayerX = x;
        rightPlayerY = y;
    }


}
