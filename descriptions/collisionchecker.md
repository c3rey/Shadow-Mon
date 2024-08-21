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
__checkTileCollision(Rectangle, int)__

checkTileCollision() accepts a Rectangle argument as well as an int. The Rectangle represents the Player's next position upon moving in any of the directions, 
and the int represents the direction the Player is moving in, out of 8 directions that correspond with the cardinal directions of North, South, East, and West, 
and the ordinal directions of Northeast, Northwest, Southeast, and Southwest. Depending on the direction, the method will check 2 or 3 [Tiles](tile.md) representing the tiles that the player can end up colliding 
with while moving in that direction. If any of those tiles has collision set to on, the Player's attempt at moving will be rejected and the Player's collision will turn on, holding them in place. Collision is also
turned on if the Player collides with an edge of the map with no map beyond it.

\
__checkObject(Rectangle, GameObject) & checkForObjects(Rectangle)__

checkObject is a helper method for checkForObjects and uses getNextPlayerPosition to check whether 
the player's solidArea will intersect with that of the object passed as its argument. Returns true if
that is the case, and false if not. CheckForObjects() loops through objectManager's objArray and calls
checkObject() on each object in the array, to ensure the player isn't colliding with any objects. 
returns true if any one of the checkObject() calls returns true. Both accept nextPlayerPosition as a parameter.


