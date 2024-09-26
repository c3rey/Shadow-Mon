package game;

import SFX.Sound;
import game.thing.door.Door;
import game.thing.door.DoorManager;
import game.thing.door.LockedDoor;
import game.thing.object.Key;
import game.thing.object.RetrievableGameObject;
import game.world.World;

import javax.sound.sampled.Clip;


public class InteractManager {


    public void update(){

    }



    public void use(RetrievableGameObject object){
        if (object instanceof Key){
            tryKey((Key) object);
        }
    }

    private void tryKey(Key key){
        LockedDoor attemptedDoor;
        
        for (Door door : DoorManager.doors){
            boolean nearDoor = World.player.interactArea.intersects(door.interactArea);
            if (nearDoor && door instanceof LockedDoor && ((LockedDoor) door).isLocked){
                attemptedDoor = (LockedDoor) door;

                if (attemptedDoor.tryKey(key)){
                    System.out.println("I unlocked it!");
                }


            } else if (nearDoor && !door.isClosed) {
                System.out.println("It's already open...");
            }else if (nearDoor){
                System.out.println("It's not even locked...");
            }else{
                System.out.println("I'm not close enough to a door...");
            }
        }
        
    }
}
