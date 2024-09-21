package game.thing.door;

import game.world.Level;
import game.world.World;
import game.thing.entity.Player;
import UI.UI;

import static game.world.LevelStream.*;

public class DoorManager {
    Level level;
    UI ui;
    final static int currentDoorCount = 1;
    public static final Door[] doors = new Door[currentDoorCount];

    public DoorManager(){
        ui = World.ui;
        level = World.level;

        setDoors();
    }

    private void setDoors(){
        doors[0] = new LockedDoor(map1, map2, Door.WOODENDOOR, 3, 4, 1);
    }

    public void updateDoors(Player player){

        for (Door door : doors){
            door.update();

            if (player.interactsWith(door)){
                if (door.isClosed){ //If the door is closed...

                    if (door instanceof LockedDoor && !((LockedDoor) door).isLocked) { //If the door is a LockedDoor and is unlocked...
                        door.isClosed = false;

                    }
                    else if (door instanceof LockedDoor && player.hasKeyFor((LockedDoor) door)){ //If the door is a LockedDoor and the player has the key...
                        ((LockedDoor) door).isLocked = false;
                        System.out.println("I unlocked it!");

                    }
                    else if (door instanceof LockedDoor){ //If the door is locked and the player doesn't have the key...
                        System.out.println("it's locked!");

                    }
                    else if (door.getClass() == Door.class){ //If the door is a normal Door...
                        door.isClosed = false; //Door opens

                    }
                }else{ //If the door is open...
                    level.goTo(door.exitRoom); //Player is taken to the door's exitRoom
                }
            }
        }
    }
}
