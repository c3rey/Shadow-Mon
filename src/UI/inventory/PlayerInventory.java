package UI.inventory;

import UI.Option;
import game.InteractManager;
import game.thing.door.Door;
import game.thing.door.LockedDoor;
import game.thing.entity.Player;
import game.thing.object.Key;
import game.thing.object.RetrievableGameObject;
import game.thing.object.Usable;

public class PlayerInventory extends Inventory{
    Player player;

    public PlayerInventory(Player player){
        super();
        this.player = player;
    }

    public void add(RetrievableGameObject object) { //used when a Player picks up a RetrievableGameObject
        if (checkForSpace()) {
            inventoryArray[nextEmptyRow][nextEmptyCol].addObject(object);
            //adds the object to the next available InventorySlot
            object.retrieved = true;
        } else {
            System.out.println("inventory full!");
        }
    }

    public boolean hasKeyFor(LockedDoor door){
        boolean hasKeyForDoor = false;
            for (InventorySlot[] inventorySlotRow : inventoryArray){
                for (InventorySlot inventorySlot : inventorySlotRow){

                    if (inventorySlot.currentObject instanceof Key key){
                        if (key.keyCode == door.keyCode){
                            hasKeyForDoor = true;
                        }
                    }
                }
            }
        return hasKeyForDoor;
    }
}
