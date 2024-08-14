__TileManager__

Draws the [Tiles](tile.md) of a [Map](map.md).

|     Variable     |  Type  | Description                                                                              |
|:----------------:|:------:|------------------------------------------------------------------------------------------|
| currentTileCount |  int   | The current number of Tiles in the game, meant to manually be set as new Tiles are added |
|    tileArray     | Tile[] | The array containing the different Tile types currently in the game.                     |
|      world       | World  | The TileManager's World.                                                                 |

\
\
\
__draw(Graphics2D, Tile[][])__

draws the entire Map by individually drawing each Tile's tileImg. 