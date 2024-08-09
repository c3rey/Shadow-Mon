__World__

Updates and draws all [Things](thing.md) currently in the game as well as the [Tiles](tile.md).

| Variable   | Type             | Description                                  |
|------------|------------------|----------------------------------------------|
| gp         | GamePanel        | The game's Gamepanel.                        |
| keyH       | KeyHandler       | The game's KeyHandler.                       |
| player     | Player           | The game's Player.                           |
| ui         | UI               | The game's UI.                               |
| tileM      | TileManager      | The game's TileManager.                      |
| stream     | LevelStream      | The game's LevelStream.                      |
| objM       | ObjectManager    | The game's ObjectManager.                    |
| doorM      | DoorManager      | The game's DoorManager.                      |
| level      | Level            | Currently the game's only Level.             |
| thingArray | ArrayList<Thing> | The array containing all Things in the game. |

\
\
\
__update()__

Calls the update methods in objM, doorM, and player.

\
__draw(Graphics2D)__

First calls tileM's draw method to draw the current map. Then sorts thingArray in order of worldY value
(highest on the map to lowest) and draws all Things in that order. That way, when the player
approaches a Thing from above it, they are drawn before that Thing, causing the appearance of the Player being
behind that thing. When the player approaches a Thing from below, the opposite effect is induced, causing the 
effect of the player being in front of that Thing.