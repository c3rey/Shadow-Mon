## Option

A user interface tool that allows the [Player](player.md) to interact with a 
[RetrievableGameObject](retrievablegameobject.md) through the [PlayerInventory](playerinventory.md)

|               Variable               |       Type        | Description                                                       |
|:------------------------------------:|:-----------------:|-------------------------------------------------------------------|
|      image<br/>highlightedImage      |   BufferedImage   | The normal and highlighted variations of an option slot image.    |
|             highlighted              |      boolean      | Whether the Option is highlighted or not.                         |
|            inventorySlot             |   InventorySlot   | The [InventorySlot](inventoryslot.md) that the Option belongs to. |
|               options                | ArrayList<Option> | The Option ArrayList that this Option is a part of.               |
|         screenX<br/>screenY          |        int        | The x and y the Option will be drawn at on the screen.            |
| optionSlotWidth<br/>optionSlotHeight |        int        | The width and height of the option slot.                          |
|                 type                 |        int        | The type of option this is.                                       |

\
\
\
__draw(Graphics2D g2)__

Draws the option slot depending on its highlighted status and place in the options Arraylist. Also draws
text for the option slot depending on the type.

\
__setOptionImage()__

Sets the image and highlightedImage for the Option.

\
__setOption()__

Sets the screenX, screenY, width, and height of the Option.

\
__function()__

Causes the Option to perform its function based on its type.