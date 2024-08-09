package game.thing.door;

import game.world.World;
import game.thing.entity.Player;
import game.UI;

import static game.world.LevelStream.map1;
import static game.world.LevelStream.map2;

public class DoorManager {
    World world;
    UI ui;
    final int currentDoorCount = 1;
    public final Door[] doors = new Door[currentDoorCount];

    public DoorManager(World world, UI ui){
        this.world = world;
        this.ui = ui;

        setDoors();
    }

    private void setDoors(){
        doors[0] = new Door(map1, map2, Door.WOODENDOOR, 8, 4);
    }

    public void updateDoors(Player player){

        for (Door door : doors){
            door.update();

            boolean interactAreaOverlap = player.interactArea.intersects(door.interactArea);
            ui.displayInteractPrompt(interactAreaOverlap);

            if (player.isInteracting && interactAreaOverlap){ //when Player presses E, Door is opened
                door.isClosed = false;
            }
        }
    }
}
