package game;

import game.entity.Player;
import game.object.GameObject;
import game.object.ObjectManager;
import game.object.RetrievableGameObject;
import game.prompt.PromptManager;
import game.world.Door;
import game.world.DoorManager;
import game.world.Level;
import game.world.LevelManager;
import game.world.tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class World {

    GamePanel gp;
    KeyHandler keyH = new KeyHandler();
    public Player player= new Player(this,keyH);;
    public TileManager tileM = new TileManager(this);
    public LevelManager lvlM = new LevelManager(this);
    public PromptManager promptM = new PromptManager(this);
    public ObjectManager objM = new ObjectManager(this, promptM);
    public DoorManager doorM = new DoorManager(this, promptM);
    public Level level;
    ArrayList<Thing> thingArray;

    World(GamePanel gp){
        this.gp = gp;

        thingArray = new ArrayList<Thing>();

        thingArray.add(player);
        thingArray.addAll(Arrays.asList(objM.objArray));
        thingArray.addAll(Arrays.asList(doorM.doors));
    }

    void update(){
        objM.updateObjects(player);
        doorM.update(player);
        player.update();
    }


    void draw(Graphics2D g2){
        tileM.draw(g2, level.currentMap.tileArray); //draws current map

        Collections.sort(thingArray); //sorts thingArray in order of worldY value (least to greatest)

        for (Thing thing : thingArray){

           if (thing.getClass() == GameObject.class){

               if (((GameObject) thing).map == level.currentMap){
                   thing.draw(g2);
                   System.out.println("gameObject drawn");
               }

           }else if (thing.getClass() == RetrievableGameObject.class){

               if (!((RetrievableGameObject) thing).retrieved && ((RetrievableGameObject) thing).map == level.currentMap){
                   thing.draw(g2);
                   System.out.println("retrievableGameObject drawn");
               }

           }else if (thing.getClass() == Door.class) {

               if (((Door) thing).entryMap == level.currentMap){
                   thing.draw(g2);
                   System.out.println("door drawn");
               }

           }else{

               thing.draw(g2);
               System.out.println("player drawn");

           }
        }
    }
}
