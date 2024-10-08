package game.world;

import game.FileToMapConverter;
import game.thing.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;

public class LevelStream {

    Player player;

    //All levels will eventually be instantiated here
    public Level level1;

    FileToMapConverter converter = new FileToMapConverter();

    public LevelStream() {;
        player = World.player;



        setMaps();
        setLevel1();
    }

    private void setMaps(){
        try {
            map1 = new Map(converter.convertToTileArray
                    (new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\maps\\ShadowMon-map1.txt")));

            map2 = new Map(converter.convertToTileArray
                    (new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\maps\\ShadowMon-map2.txt")));

            map3 = new Map(converter.convertToTileArray
                    (new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\maps\\ShadowMon-map3.txt")));

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in LevelStream");
        }
    }

    private void setLevel1(){
        level1 = new Level(player);
        World.level = level1;

        level1.setStartingMap(map1);
        level1.addRight(map1, map2);
        level1.addUp(map2, map3);

        map1.setWestSpawn(10, 250);
        map1.setEastSpawn(700, 250);

        map2.setWestSpawn(10, 250);
        map2.setEastSpawn(700, 250);
        map2.setNorthSpawn(350, 10);

        map3.setSouthSpawn(350, 525);
    }

    public static Map map1;

    public static Map map2;

    public static Map map3;


}