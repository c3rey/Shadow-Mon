## Level

A 2-dimensional LinkedList-based structure that contains and connects [Maps](map.md), 
allowing the [Player](player.md) to traverse through the game.

|  Variable  |  Type  | Description              |
|:----------:|:------:|--------------------------|
| currentMap |  Map   | The Level's current Map. |
|   player   | Player | The game's Player.       |

\
\
\
__add[Up, Down, Left, Right]()()__

Adds a Map in the specified direction, depending on the method.

\
__go[Up, Down, Left, Right]()()__

sets the Level's currentMap to the one above, below, to the left,
or to the right of it, depending on the method name. Also sets the Player's
coordinates to the spawn coordinates of the map.

\
__goTo(Map)__

sets the Level's currentMap to the Map passed as an argument.

