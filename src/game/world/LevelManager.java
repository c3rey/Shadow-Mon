package game.world;

import game.FileToMapConverter;
import game.World;
import game.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;

public class LevelManager {

    //All levels will eventually be instantiated here
    public Level level = new Level();

    World world;
    Player player;

    FileToMapConverter converter = new FileToMapConverter();

    public LevelManager(World world) {
        this.world = world;
        player = world.player;

        setMaps();
        setLevel1();
    }

    private void setMaps(){
        try {
            map1 = new Map(converter.convertToMap
                    (new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\maps\\ShadowMon-map1.txt")));
            map1.setLevel(level);

            map2 = new Map(converter.convertToMap
                    (new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\maps\\ShadowMon-map2.txt")));
            map2.setLevel(level);

            map3 = new Map(converter.convertToMap
                    (new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\world\\maps\\ShadowMon-map3.txt")));
            map3.setLevel(level);

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in LevelManager");
        }
    }

    private void setLevel1(){
        world.level = level; // is here in a method because LevelManager is instantiated in Gamepanel before gp.Level

        level.setStartingMap(map1);

        level.addRight(map1, map2);
        level.addUp(map2, map3);

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