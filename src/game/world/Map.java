package game.world;

import game.world.tile.Tile;

public class Map {
    //2D array that represents the game.map
    public Level level;
    public Tile[][] tileArray;
    public Map up = null;
    public Map down = null;
    public Map left = null;
    public Map right = null;

    public int northPlayerX, northPlayerY, southPlayerX, southPlayerY, eastPlayerX, eastPlayerY, westPlayerX, westPlayerY;

    public Map(Tile[][] tileArray){ //constructor
        this.tileArray = tileArray;
    }

    //Sets position Player will be in when entering a Map
    public void setNorthSpawn(int x, int y){
        northPlayerX = x;
        northPlayerY = y;
    }
    public void setSouthSpawn(int x, int y){
        southPlayerX = x;
        southPlayerY = y;
    }
    public void setWestSpawn(int x, int y){
        westPlayerX = x;
        westPlayerY = y;
    }
    public void setEastSpawn(int x, int y){
        eastPlayerX = x;
        eastPlayerY = y;
    }


}
