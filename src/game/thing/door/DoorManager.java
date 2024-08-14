package game.thing.door;

import game.world.Level;
import game.world.World;
import game.thing.entity.Player;
import game.UI;

import static game.world.LevelStream.*;

public class DoorManager {
    World world;
    Level level;
    UI ui;
    final int currentDoorCount = 1;
    public final Door[] doors = new Door[currentDoorCount];

    public DoorManager(World world){
        this.world = world;
        ui = world.ui;
        level = world.level;

        setDoors();
    }

    private void setDoors(){
        doors[0] = new Door(map1, map3, Door.WOODENDOOR, 3, 4);
    }

    public void updateDoors(Player player){

        for (Door door : doors){
            door.update();

            if (player.interactsWith(door) && door.isClosed){ //when Player presses E, Door is opened
                door.isClosed = false;
            } else if (player.interactsWith(door) && !door.isClosed) {
                level.goTo(door.exitRoom);
            }
        }
    }
}
