__RetrievableGameObject__

Hence the name, a [GameObject](gameobject.md) that is retrievable by the [Player](player.md). Retrieved RetrievableGame
objects are stored in the Player's [Inventory](inventory.md).

|     Variable      |     Type      | Description                                                                                                                                                                                                                                                              |
|:-----------------:|:-------------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|   replacingArea   |   Rectangle   | A rectangle equal to that of the solidArea of the RetrievableGameObject. When the object is placed, the player will have to leave its replacingArea before the solidArea can be set back to normal (to prevent the player from getting stuck in the object)              |
| image1<br/>image2 | BufferedImage | A pair of images used to simulate the RetrievableGameObject animation (the object floating up and down) to signify to the player that it's retrievable                                                                                                                   |
|   displayImage    | BufferedImage | The image to be used to display the RetrievableGameObject in the player's inventory.                                                                                                                                                                                     |
|     retrieved     |    boolean    | A boolean representing whether the RetrievableGameObject has been retrieved by the player or not.                                                                                                                                                                        |
|    isReplacing    |    boolean    | A boolean signifying whether the RetrievableGameObject is in the process of being replaced into the map. If true, the solidArea's dimensions are kept to 0. Upon leaving the replacingArea, isReplacing is set to false and solidArea is set to its original dimensions. |

\
\
\
__update()__

Based on a spriteCount which is set in [ObjectManager](objectmanager.md), the RetrievableObject's image is swapped between image1 and image2. Also sets
the solidArea and interactArea to 0 if the object is retrieved (so that the Player doesn't bump into where the RetrievableGameObject would be on it's correct Map)
and sets the solidArea and interactArea to its default values if the currentMap is where it should be, and it's on the floor.

\
__replace(x, y)__

sets object coordinates to the argument coordinates passed, sets the replacingArea, and sets isReplacing to true.
