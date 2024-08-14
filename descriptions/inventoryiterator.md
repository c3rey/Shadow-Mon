__InventoryIterator__

Iterates over the [InventorySlots](inventoryslot.md) within its [Inventory](inventory.md).

|    Variable    |       Type        | Description                                              |
|:--------------:|:-----------------:|----------------------------------------------------------|
|   inventory    |     Inventory     | The InventoryIterator's Inventory.                       |
| inventoryArray | InventorySlot[][] | A 2D array of InventorySlots representing the inventory. |
|  currentSlot   |   InventorySlot   | The currently highlighted InventorySlot                  |
|     player     |      Player       | The game's Player.                                       |
|      keyH      |    KeyHandler     | The InventoryIterator's KeyHandler.                      |

\
\
\
__goUp, Down, Left, Right()__

Un-highlights the current InventorySlot, sets currentSlot to the one above, below, to the left, or to the right of it (depending on the method), and highlights that InventorySlot.

\
__resetCurrentSlot()__

Un-highlights the current InventorySlot, sets currentSLot to the InventorySlot in the top right, and highlights that InventorySlot.

\
__moveIsClear()__

Ensures that the player isn't moving the "cursor" over the edge of the Inventory.
