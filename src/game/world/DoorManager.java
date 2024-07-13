package game.world;

import game.World;
import game.entity.Player;
import game.prompt.PromptManager;

import static game.world.LevelManager.map1;
import static game.world.LevelManager.map2;

public class DoorManager {
    World world;
    PromptManager promptM;
    final int currentDoorCount = 1;
    public final Door[] doors = new Door[currentDoorCount];

    public DoorManager(World world, PromptManager promptM){
        this.world = world;
        this.promptM = promptM;

        setDoors();
    }

    private void setDoors(){
        doors[0] = new Door(map1, map2, Door.WOODENDOOR, 8, 8);
    }

    public void update(Player player){

        for (Door door : doors){
            door.update();

            boolean interactAreaOverlap = player.interactArea.intersects(door.interactArea);
            promptM.displayInteractPrompt(interactAreaOverlap);

            if (player.isInteracting && interactAreaOverlap){ //when Player presses E, Door is opened
                door.isClosed = false;
            }
        }
    }
}
