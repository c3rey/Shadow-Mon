__Map__

The background that the [Player](player.md) exists in.

|            Variable            |   Type   | Description                                                |
|:------------------------------:|:--------:|------------------------------------------------------------|
|             level              |  Level   | The Map's level.                                           |
|           tileArray            | Tile[][] | A 2D array of Tiles that represent the tiles on the Map.   |
| up<br/>down<br/>left<br/>right |   Map    | The Map to the up, down, left, or right of the current Map |
\
\
\
__set[North, West, East, South]Spawn()__

Sets the location the player will spawn when entering a Map depending on the direction
from which they're entering the map.