package game.world;

import game.GamePanel;
import game.KeyHandler;
import game.thing.entity.Player;
import game.thing.object.GameObject;
import game.thing.object.ObjectManager;
import game.thing.object.RetrievableGameObject;
import game.UI;
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
    public KeyHandler keyH = new KeyHandler();
    public Player player= new Player(this,keyH);
    public UI ui = new UI(this);
    public TileManager tileM = new TileManager(this);
    public LevelStream stream = new LevelStream(this);
    public ObjectManager objM = new ObjectManager(this);
    public DoorManager doorM = new DoorManager(this);
    public Level level;
    public ArrayList<Thing> thingArray;

    public World(GamePanel gp){
        this.gp = gp;

        thingArray = new ArrayList<>();

        thingArray.add(player);
        thingArray.addAll(Arrays.asList(objM.objArray));
        thingArray.addAll(Arrays.asList(doorM.doors));
    }

    public void update(){

        objM.updateObjects(player);
        doorM.updateDoors(player);
        player.update();
        ui.update();

    }


    public void draw(Graphics2D g2){
        tileM.draw(g2, level.currentMap.tileArray); //draws current map first

        Collections.sort(thingArray); //sorts thingArray in order of screenY value (least to greatest)

        for (Thing thing : thingArray){

           if (thing.getClass() == GameObject.class){

               if (((GameObject) thing).map == level.currentMap){
                   thing.draw(g2);
               }

           }else if (thing.getClass() == RetrievableGameObject.class){

               if (!((RetrievableGameObject) thing).retrieved && ((RetrievableGameObject) thing).map == level.currentMap){
                   thing.draw(g2);
               }

           }else if (thing.getClass() == Door.class) {

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
