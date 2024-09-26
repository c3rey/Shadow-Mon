package game.world;

import SFX.Sound;
import game.GamePanel;
import game.InteractManager;
import game.KeyHandler;
import game.thing.entity.Player;
import game.thing.object.GameObject;
import game.thing.object.Key;
import game.thing.object.ObjectManager;
import game.thing.object.RetrievableGameObject;
import UI.UI;
import game.thing.door.Door;
import game.thing.door.DoorManager;
import game.thing.Thing;
import game.world.tile.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class World {

    GamePanel gp;

    public static KeyHandler keyH = new KeyHandler();
    public static Player player= new Player();

    public static UI ui = new UI();

    public static TileManager tileM = new TileManager();
    public static LevelStream stream = new LevelStream();
    public static ObjectManager objM = new ObjectManager();
    public static DoorManager doorM = new DoorManager();

    public static Level level;
    public static ArrayList<Thing> thingArray;
    public static InteractManager intrM = new InteractManager();
    public static Sound sound = new Sound();

    public World(GamePanel gp){
        this.gp = gp;

        thingArray = new ArrayList<>();

        thingArray.add(player);
        thingArray.addAll(Arrays.asList(ObjectManager.objArray));
        thingArray.addAll(Arrays.asList(DoorManager.doors));
    }

    public void update(){

        objM.updateObjects(player);
        doorM.updateDoors(player);
        player.update();
        ui.update();
        sound.update();

    }


    public void draw(Graphics2D g2){
        tileM.draw(g2, level.currentMap.tileArray); //draws current map first

        Collections.sort(thingArray); //sorts thingArray in order of screenY value (least to greatest)

        for (Thing thing : thingArray){

           if (thing.getClass() == GameObject.class){

               if (((GameObject) thing).map == level.currentMap){
                   thing.draw(g2);
               }

           }else if (thing instanceof RetrievableGameObject){

               if (!((RetrievableGameObject) thing).retrieved && ((RetrievableGameObject) thing).map == level.currentMap){
                   thing.draw(g2);
               }

           }else if (thing instanceof Door) {

               if (((Door) thing).entryMap == level.currentMap){
                   thing.draw(g2);
               }

           }else{

               thing.draw(g2);

           }
        }

        ui.draw(g2);
    }
}
