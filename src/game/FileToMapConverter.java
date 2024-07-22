package game;

import game.world.tile.Tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileToMapConverter {
    Scanner scanner;

    public FileToMapConverter(){
    }

    public Tile[][] convertToTileArray(File mapFile) throws FileNotFoundException{

        Tile[][] tileArray = new Tile[12][16];
        scanner = new Scanner(mapFile);
        int tileNumber;
        int i = 0;
        int j = 0;

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            String[] row = line.split(" ");
            for (String number : row){
                tileNumber = Integer.parseInt(number);
                tileArray[j][i] = new Tile(tileNumber);
                i++;
            }
            i = 0;
            j++;
        }


        return tileArray;

    }
}
