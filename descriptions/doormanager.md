__DoorManager__

Sets and updates all [Doors](door.md).

|     Variable     |  Type  | Description                                                                                 |
|:----------------:|:------:|---------------------------------------------------------------------------------------------|
|      world       | World  | The game's World.                                                                           |
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
__updateDoors(Player)__

1. Updates doors
2. Calls upon the UI to display the interact prompt if the Player's interactArea overlaps that of any doors
3. Opens a Door if the Player is close enough to it and interacts