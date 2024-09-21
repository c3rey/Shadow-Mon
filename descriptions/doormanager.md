## DoorManager

Sets and updates all [Doors](door.md).

|     Variable     |  Type  | Description                                                                                 |
|:----------------:|:------:|---------------------------------------------------------------------------------------------|
|      level       | Level  | The game's Level.                                                                           |
|        ui        |   UI   | The game's UI.                                                                              |
| currentDoorCount |  int   | The current number of Doors in the game, to be manually incremented as new Doors are added. |
|      doors       | Door[] | The array of all doors in the game.                                                         |

\
\
\
__setDoors()__

Sets the Doors in doors[].

\
__updateDoors(Player player)__

1. Updates doors
2. Calls player.interactsWith() on each door
3. Opens the door or sends Player through the door depending on the door and whether the [Player](player.md) has the right [Key](key.md) or not