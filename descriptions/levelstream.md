## LevelStream

Sets all [Levels](level.md) and [Maps](map.md) and progresses through the Levels as the [Player](player.md) completes them

| Variable  |        Type        | Description                                       |
|:---------:|:------------------:|---------------------------------------------------|
|  level1   |       Level        | The first Level of the game.                      |
|   world   |       World        | The game's World.                                 |
|  player   |       Player       | The game's Player.                                |
| converter | FileToMapConverter | The program that converts a text file into a Map. |

\
\
\
__setMaps()__

Uses the fileToMapConverter to instantiate the maps.

\
__setLevel1()__

Adds the Maps to the Level and sets where the player spawns on those Maps.
