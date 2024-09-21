## Entity


A [Thing](thing.md) that can move and interact with the [World](world.md). 


|              Variable              |     Type      | Description                                                                                                                                                                                   |
|:----------------------------------:|:-------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|               worldX               |      int      | The Entity's x position on the map                                                                                                                                                            |
|               worldY               |      int      | The Entity's y position on the map                                                                                                                                                            |
|               speed                |      int      | The number of pixels the entity will move every time <br/> update() is called and a movement button is pressed                                                                                |
| up1,2,3, <br/>down1,2,3,<br/> etc. | BufferedImage | Allows an Entity to simulate a walking animation <br/> in any of the 4 directions                                                                                                             |
|             direction              |    String     | The direction the Entity is facing                                                                                                                                                            |
|            updateCount             |      int      | Increments every time update() is called and is set back to 0 <br/> at a certain limit to produce a smooth walking animation                                                                  |
|            spriteCount             |      int      | Increments every time updateCount() hits a certain limit and <br/> is reset to 0 if both updateCount hits its limit and spriteCount is<br/> currently 1 to produce a smooth walking animation |
|            collisionOn             |    Boolean    | Represents Entity's collision; if on, the Entity can't move.                                                                                                                                  |