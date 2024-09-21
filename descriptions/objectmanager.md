## ObjectManager

Sets and updates all [GameObjects](gameobject.md) and [RetrievableGameObjects](retrievablegameobject.md).

|          Variable           |       Type       | Description                                                                                      |
|:---------------------------:|:----------------:|--------------------------------------------------------------------------------------------------|
|            world            |      World       | The game's World.                                                                                |
|             ui              |        UI        | The game's UI.                                                                                   |
|           player            |      Player      | The game's Player.                                                                               |
|          cChecker           | CollisionChecker | The game's CollisionChecker.                                                                     |
|       CURRENTOBJCOUNT       |       int        | The current number of GameObjects in the game, meant to manually be set as new objects are added |
|          objArray           |   GameObject[]   | The array that contains all GameObjects and RetrievableGameObjects                               |
| spriteCount<br/>updateCount |       int        | Used to update RetrievableGameObjects in update()                                                |

\
\
\
__setObjectArray()__

Instantiates GameObjects within objArray

\
__setObjects()__

Sets the images, width and height, solidArea and interactArea, and other class specifics of each object
in objArray.

\
__updateObjects(Player)__

1. Increments spriteCount per every time this method is called
2. Increments updateCount every time spriteCount hits 30, and sets both updateCount and spriteCount to 0 once spriteCount hits 60
3. Calls upon the UI to display the interact prompt if the Player's interactArea overlaps that of any objects
4. Updates RetrievableGameObjects(regular GameObjects don't need to be updated at this point in development)
5. Handles retrieval of RetrievableGameObjects
6. Handles dropping or RetrievableGameObjects
