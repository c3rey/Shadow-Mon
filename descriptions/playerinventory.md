## PlayerInventory

A special [Inventory](inventory.md) belonging to the [Player](player.md).

| Variable |  Type  | Description                                      |
|:--------:|:------:|--------------------------------------------------|
|  player  | Player | The Player that this PlayerInventory belongs to. |

\
\
\
__add(RetrievableGameObject object)__

Checks for available space in the Inventory and adds object to the next free position.

\
__hasKeyFor(LockedDoor door)__

Loops through InventoryArray and searches for a Key that has a matching keyCode to the 
LockedDoor passed as an argument.