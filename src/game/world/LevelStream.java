package game;

import game.thing.entity.Player;
import game.world.Level;
import game.world.Map;
import game.world.World;

import java.io.File;
import java.io.FileNotFoundException;

public class WorldBuilder {

    //All levels will eventually be instantiated here
    public Level level1 = new Level();

    World world;
    Player player;

    FileToMapConverter converter = new FileToMapConverter();

    public WorldBuilder(World world) {
        this.world = world;
        player = world.player;

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
            System.out.println("FileNotFoundException in WorldBuilder");
        }
    }

    private void setLevel1(){
        world.level = level1; // is here in a method because WorldBuilder is instantiated in Gamepanel before gp.Level

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