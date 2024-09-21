package game.thing.object;
import game.world.World;
import game.thing.entity.Player;
import UI.UI;
import game.CollisionChecker;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static game.world.LevelStream.map2;

public class ObjectManager {

    World world;
    UI ui;
    Player player;
    CollisionChecker cChecker;
    public static final int CURRENTOBJCOUNT = 1; //MANUALLY UPDATE PER EVERY NEW ITEM ADDED
    public static final GameObject[] objArray = new GameObject[CURRENTOBJCOUNT];
    private int spriteCount = 0;
    private int updateCount = 0;

    public static final int KEY1 = 1;

    public ObjectManager(){

        ui = World.ui;
        player = World.player;
        cChecker = player.cChecker;

        setObjectArray();
        setObjects();
    }

    private void setObjectArray(){
        objArray[0] = new Key(map2, KEY1, 350, 280);
    }


    private void setObjects(){
        for (GameObject gameObject : objArray){
            try {
                switch (gameObject.objectNum) {
                    case (KEY1):
                        ((RetrievableGameObject) gameObject).image1 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\object\\RetrievableKey1.png")); //sets images
                        ((RetrievableGameObject) gameObject).image2 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\object\\RetrievableKey2.png"));
                        ((RetrievableGameObject) gameObject).displayImage = ((Key) gameObject).image1; //image to be displayed in inventory slot
                        gameObject.collisionOn = true; // object has collision on

                        gameObject.width = 30;
                        gameObject.height = 30;

                        //solidArea and interactArea's sizes are set
                        gameObject.solidArea.setSize(gameObject.width, gameObject.height);
                        gameObject.interactArea.setSize(gameObject.width + 10, gameObject.height + 10);

                        ((RetrievableGameObject) gameObject).description = "A key. It's got a weird energy coming from it...";

                        ((Key) gameObject).keyCode = 1;
                }
            }catch (IOException e){
                System.err.println("IOException in RetrievableGameObject");
            }
        }
    }


    //updates objects in World.updateDoors()
    public void updateObjects(Player player){

        spriteCount++; //is incremented every time updateDoors() is called, 60 times per second

        if (spriteCount > 30){
            updateCount++;
        }
        if (spriteCount == 60){
            spriteCount = 0;
            updateCount = 0;
        }

        for (GameObject gameObject : objArray){ //for gameObject in objArray:
            
            if (((RetrievableGameObject) gameObject).retrieved){
                gameObject.map = World.level.currentMap;
            }

            if (gameObject instanceof RetrievableGameObject){ // if the gameObject is a retrievableGameObject...

                ((RetrievableGameObject) gameObject).update(updateCount);





                //and checks whether the player has gotten close enough to the game.object to interact with it
                if (player.interactsWith(gameObject)) {
                    player.pickUp((RetrievableGameObject) gameObject);
                }


                if (((RetrievableGameObject) gameObject).isReplacing && !player.solidArea.intersects(((RetrievableGameObject) gameObject).replacingArea)){
                    //once the Player leaves the GameObject's solidArea, the replacement process is completed and the solidArea is restored
                    ((RetrievableGameObject) gameObject).isReplacing = false;
                    ((RetrievableGameObject) gameObject).retrieved = false;
                }

            }

        }
    }
}
