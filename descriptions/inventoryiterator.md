## InventoryIterator

Iterates over the [InventorySlots](inventoryslot.md) within its [Inventory](inventory.md).

|      Variable      |       Type        | Description                                                                          |
|:------------------:|:-----------------:|--------------------------------------------------------------------------------------|
|     inventory      |     Inventory     | The InventoryIterator's Inventory.                                                   |
|   inventoryArray   | InventorySlot[][] | A 2D array of InventorySlots representing the inventory.                             |
|    currentSlot     |   InventorySlot   | The currently highlighted InventorySlot                                              |
| currentSlotOptions |     Option[]      | An array of the current InventorySlot's available Options.                           |
|        keyH        |    KeyHandler     | The InventoryIterator's KeyHandler.                                                  |
| currentOptionIndex |        int        | The current Option's index in currentSlotOptions.                                    |
|        mode        |        int        | The mode of the Iterator; whether it's traversing through InventorySlots or Options. |

\
\
\
__traverse(String)__

If no InventorySlot is currently selected, Un-highlights the current InventorySlot, sets currentSlot to the one above, below, to the left, or to the right of it 
(depending on the direction argument), and highlights that InventorySlot. If an inventorySlot is selected, traverses the slot's [Options](option.md) in a similar fashion.


\
__selectSlot()__
sets

\
__resetCurrentSlot()__

Un-highlights the current InventorySlot, sets currentSLot to the InventorySlot in the top right, and highlights that InventorySlot.

\
__moveIsClear()__

Ensures that the player isn't moving the "cursor" out of bounds of the Inventory or current InventorySlot's Option list.
