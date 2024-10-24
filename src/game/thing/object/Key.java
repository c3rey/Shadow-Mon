package game.thing.object;

import game.thing.door.Door;
import game.thing.door.DoorManager;
import game.thing.door.LockedDoor;
import game.world.Map;
import game.world.World;

public class Key extends RetrievableGameObject implements Usable{

    public int keyCode;

    Key(Map map, int objectNum, int worldX, int worldY){
        super(map, objectNum, worldX, worldY);
    }

    @Override
    public void use() {
        LockedDoor attemptedDoor;

        for (Door door : DoorManager.doors){
            boolean nearDoor = World.player.interactArea.intersects(door.interactArea);
            if (nearDoor && door instanceof LockedDoor && ((LockedDoor) door).isLocked){
                attemptedDoor = (LockedDoor) door;

                if (attemptedDoor.tryKey(this)){
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
