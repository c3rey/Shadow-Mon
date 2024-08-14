__CollisionChecker__

Handles all collision events in the game.

|   Variable    |     Type      | Description                      |
|:-------------:|:-------------:|----------------------------------|
|     world     |     World     | The game's current World.        |
| currentLevel  |     Level     | The game's current Level.        |
|  currentMap   |      Map      | The current Level's current Map. |
|    player     |    Player     | The game's Player.               |
| objectManager | ObjectManager | The game's ObjectManager.        |
|     world     |     World     |                                  |
\
\
\
\
__checkTileCollision()__

checkTileCollision() checks the [tile(s)](tile.md) that are [player's speed here] pixels away from the player in the direction the player is facing.
It does this by drawing a line where the player will be next, with all units divided by Gamepanel.tilesize and rounded down
(16 at the time this was written) to represent the line's position in a 16 column, 12 row table that corresponds with 
the current [Map's](map.md) maparray. If any tile (out of a maximum of 2) has collision set to on, checkTileCollision() will 
return true. If both tiles' collision is set to false, checkTileCollision() will return false.

\
__getNextPlayerPosition()__

This is a helper method that is used in multiple other check methods that creates a rectangle representing where the solidArea of the player will be
the next time it moves, based on the direction the player is currently facing.

\
__checkObject(GameObject) & checkForObjects()__

checkObject is a helper method for checkForObjects and uses getNextPlayerPosition to check whether 
the player's solidArea will intersect with that of the object passed as its argument. Returns true if
that is the case, and false if not. CheckForObjects() loops through objectManager's objArray and calls
checkObject() on each object in the array, to ensure the player isn't colliding with any objects. 
returns true if any one of the checkObject() calls returns true.

\
__set[Left, Right, Up, and Down]Positions()__

CollisionChecker also handles where the Player spawns in upon entering a new map, as it has direct access to the player
and also handles map switching once the player collides with the edge of a map.

