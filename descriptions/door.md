## Door

A [Thing](thing.md) that takes the [Player](player.md) from one map to another when interacted with.

|              Variable               |     Type      | Description                                                                                    |
|:-----------------------------------:|:-------------:|------------------------------------------------------------------------------------------------|
|        entryMap<br/>exitMap         |      Map      | The Map that the Player enters the Door from, and the Map that the Player exits the Door into. |
|                level                |     Level     | The Level that the Door exists in.                                                             |
| doorClosedImage<br/>doorOpenedImage | BufferedImage | The images of the door while closed and the door while open.                                   |
|              isClosed               |    boolean    | A boolean representing whether the Door is closed or not.                                      |
|           xTile<br/>yTile           |      int      | A pair of integers representing the coordinates of the Tile that the Door is to be drawn in.   |

\
\
\
__setDoorImage(int type)__

Sets the doorOpenImage and doorClosedImage for each door based on the int type passed as an argument.

\
__setDoorCoOrds()__

Sets the Door's worldX and worldY based on the Tile's xTile and yTile

\
__update()__

When called, sets image to either DoorClosedImage or DoorOpeningImage based on whether isClosed is true or false.