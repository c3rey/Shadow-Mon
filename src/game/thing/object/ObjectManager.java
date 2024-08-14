package game.thing.object;
import game.world.World;
import game.thing.entity.Player;
import game.GamePanel;
import game.UI;
import game.CollisionChecker;

import static game.world.LevelStream.map2;

public class ObjectManager {

    World world;
    UI ui;
    Player player;
    CollisionChecker cChecker;
    public static final int CURRENTOBJCOUNT = 1; //MANUALLY UPDATE PER EVERY NEW ITEM ADDED
    public final GameObject[] objArray = new GameObject[CURRENTOBJCOUNT];
    private int spriteCount = 0;
    private int updateCount = 0;

    public ObjectManager(World world){
        this.world = world;
        ui = world.ui;
        player = world.player;
        cChecker = player.cChecker;

        setObjects();
    }

    private void setObjects(){
        objArray[0] = new RetrievableGameObject(map2, RetrievableGameObject.KEY, 350, 280);
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
                gameObject.map = world.level.currentMap;
            }

            if (gameObject.getClass() == RetrievableGameObject.class){ // if the gameObject is a retrievableGameObject...

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

                if (player.isDropping && ((RetrievableGameObject)gameObject).retrieved){
                    player.drop((RetrievableGameObject) gameObject, GamePanel.tileSize);
                }

            }

        }
    }
}
