package game.world;

import game.world.tile.Tile;

import java.util.ArrayList;

public class Map {
    //2D array that represents the game.map
    public Level level;
    public Tile[][] tileArray;
    public int currentNumBoundaries;
    public Map up = null;
    public Map down = null;
    public Map left = null;
    public Map right = null;

    public int leftPlayerX, leftPlayerY, rightPlayerX, rightPlayerY, upPlayerX, upPlayerY, downPlayerX, downPlayerY;

    public Map(Tile[][] tileArray){ //constructor
        this.tileArray = tileArray;
        currentNumBoundaries = 0;
    }

    void setLevel(Level level){
        this.level = level;
    }

    //Sets position Player will be in when entering a Map
    void setNorthSpawn(int x, int y){
        upPlayerX = x;
        upPlayerY = y;
    }
    void setSouthSpawn(int x, int y){
        downPlayerX = x;
        downPlayerY = y;
    }
    void setWestSpawn(int x, int y){
        leftPlayerX = x;
        leftPlayerY = y;
    }
    void setEastSpawn(int x, int y){
        rightPlayerX = x;
        rightPlayerY = y;
    }


}
