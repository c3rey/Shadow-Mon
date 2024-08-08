package game.object;
import game.World;
import game.entity.Player;
import game.GamePanel;
import game.prompt.PromptManager;
import game.CollisionChecker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static game.world.LevelManager.map2;

public class ObjectManager {

    World world;
    PromptManager promptM;
    Player player;
    CollisionChecker cChecker;
    public int currentObjCount = 1;
    public final GameObject[] objArray = new GameObject[currentObjCount];
    private int spriteCount = 0;
    private int spriteNum = 0;

    public ObjectManager(World world, PromptManager promptM){
        this.world = world;
        this.promptM = promptM;
        player = world.player;
        cChecker = player.cChecker;

        setObjects();
    }

    private void setObjects(){
        objArray[0] = new RetrievableGameObject(map2, RetrievableGameObject.KEY, 350, 280);
    }


    //updates objects in World.updateDoors()
    public void updateObjects(Player player){

        spriteNum++; //is incremented every time updateDoors() is called, 60 times per second

        if (spriteNum > 30){
            spriteCount++;
        }
        if (spriteNum == 60){
            spriteCount = 0;
            spriteNum = 0;
        }

        for (GameObject gameObject : objArray){ //for gameObject in objArray:
            boolean interactAreaOverlap = (player.interactArea.intersects(gameObject.interactArea) && (world.level.currentMap == gameObject.map));


            if (gameObject.getClass() == RetrievableGameObject.class){ // if the gameObject is a retrievableGameObject...

                promptM.displayInteractPrompt(interactAreaOverlap); //displays interact prompt

                ((RetrievableGameObject) gameObject).update(spriteCount);


                //and checks whether the player has gotten close enough to the game.object to interact with it
                if (interactAreaOverlap && player.isInteracting) {
                    player.pickUp((RetrievableGameObject) gameObject);
                }


                //RETRIEVING OBJECTS

                if (((RetrievableGameObject) gameObject).isReplacing && !player.solidArea.intersects(((RetrievableGameObject) gameObject).replacingArea)){
                    //once the Player leaves the GameObject's solidArea, the replacement process is completed and the solidArea is restored
                    ((RetrievableGameObject) gameObject).isReplacing = false;
                    ((RetrievableGameObject) gameObject).retrieved = false;
                }

                if (player.isDropping && ((RetrievableGameObject)gameObject).retrieved){
                    player.drop((RetrievableGameObject) gameObject, GamePanel.tileSize);
                }

            }

        }
    }
}
