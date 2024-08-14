__Player__

The [Entity](entity.md) representing the player of the game. Responds to key input via [KeyHandler](keyhandler.md).

|  Variable  |       Type       | Description                                                                                                |
|:----------:|:----------------:|------------------------------------------------------------------------------------------------------------|
|     gp     |    GamePanel     | The game's GamePanel.                                                                                      |
|   world    |      World       | The game's World.                                                                                          |
| inventory  |    Inventory     | The player's Inventory.                                                                                    |
|  cChecker  | CollisionChecker | The game's CollisionChecker.                                                                               |
|    keyH    |    KeyHandler    | The Player's KeyHandler                                                                                    |
| isDropping |     boolean      | Returns true if the Player is in the process of dropping a RetrievableGameObject, returns false otherwise. |

\
\
\
__setDefaultValues()__

Sets the worldX, worldY, speed, and direction of the Player at the beginning of the game.

\
__setPlayerImage()__

Sets the images for up 1-3, down 1-3, left 1-3, and right 1-3.

\
__pickUp(RetrievableGameObject)__

Adds the specified RetrievableGameObject to the Player's Inventory.

\
__drop(RetrievableGameObject)__

Removes the specified RetrievableGameObject from the Player's Inventory.

\
__interactsWith(Thing)__

A method intended to be called by Manager classes like DoorManager and ObjectManager. Returns true if the Player's interactArea intersects that of the specified Thing
and the player presses E, signifying that they want to interact with the Thing specified.

\
__update()__

1. Creates the effect of a Player walking animation by incrementing updateCount every time Player.update() is called, and spriteCount every 12 updates.
2. Sets Player direction depending on key input from [KeyHandler](keyhandler.md)
3. Checks [Tiles](tile.md) and [GameObjects](gameobject.md) for collision
4. Sets image depending on which direction Player is facing
5. If Player isn't walking directly into something, allows movement in that direction. Also uses updateCount and spriteCount to create a Player walking animation.
6. sets solidArea and interactArea 
7. Draws [Inventory](inventory.md) when O is pressed