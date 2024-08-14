__Thing__

A Thing is anything that's on the current [Map](map.md) (excluding the map itself). All things must have
a worldX and worldY value, a length and width value, a solidArea, and an image.


\
\
\

|     Variable      |     Type      | Description                                                                                                                                                                                                                                      |
|:-----------------:|:-------------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| worldX<br/>worldY |      int      | The X and Y location of the Thing on the map.                                                                                                                                                                                                    |
|       image       | BufferedImage | The image representing the Thing.                                                                                                                                                                                                                |
|     solidArea     |   Rectangle   | A Rectangle representing the Thing's solid area, <br/> essentially meaning the space it takes up in the world.                                                                                                                                   |
|   interactArea    |   Rectangle   | In the case of the [Player](player.md), a Rectangle representing the range in which it can interact with other Things. <br/> In the case of other Things, it is the radius the player has to be in in order for said player to interact with it. |
|    collisionOn    |    boolean    | A boolean representing the Thing's collision; for Entities, collision being on (or true) represents that Entity running into something; for other Things, it represents whether that Thing is capable of being passed through by an Entity.      |
\
__draw(Graphics2D)__

Draws the Thing on the screen.

\
__compareTo(Thing OtherThing)

Compares this Thing to another Thing based on its worldY value.
Returns the Thing with the lower Y value, or the Thing that's highest up on the map.