__InventorySlot__

A single slot in an [Inventory](inventory.md). Can contain a [RetrievableGameObject](retrievablegameobject.md). Iterated over via the [InventoryIterator](inventoryiterator.md)

|                   Variable                   |         Type          | Description                                                                                                                                                  |
|:--------------------------------------------:|:---------------------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| image<br/>slotImage<br/>highlightedSlotImage |     BufferedImage     | The image to be drawn.<br/>The default image of the InventorySlot.<br/>The image of the InventorySlot when highlighted.                                      |
|                  inventory                   |       Inventory       | The Inventory the slots belong to.                                                                                                                           |
|                currentObject                 | RetrievableGameObject | The RetrievableGameObject contained within the InventorySlot.                                                                                                |
|                 highlighted                  |        boolean        | Returns true if the current InventorySlot is the one being selected by the Player (in other words, if the "cursor" is on the slot). Returns false otherwise. |
|              colNum<br/>rowNum               |          int          | The column and row which the InventorySlot belongs in.                                                                                                       |
|               slotX<br/>slotY                |          int          | The x and Y values for the InventorySlot to be drawn on the screen.                                                                                          |
|           slotWidth<br/>slotHeight           |          int          | The width and height values for the InventorySlot to be drawn on the screen.                                                                                 |

\
\
\
__updateSlot()__

If the InventorySlot is the one being selected by the Player, the image is set to highlightedSlotImage. If not,
the image is set to slotImage.

\
__draw(GraphicsG2)__

Draws the slot. If the slot contains a RetrievableGameObject, draws its displayImage within the slot.

\
__setSlotImages()__

Sets slotImage and highlightedSlotImage.
