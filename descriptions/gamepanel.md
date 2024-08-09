__Gamepanel__

This is where the game is run, updated, and drawn; World calls its update() and draw() methods here. 
KeyHandler is also added to this panel for player control.

| Variable         | Type       | Description                                                                                       |
|------------------|------------|---------------------------------------------------------------------------------------------------|
| originalTileSize | int        | The game is designed to resemble a 16-bit pixel game. Therefore, the size of a tile would be 16.  |
| scale            | int        | to make sure the game isn't unbearably tiny, the actual tile size is scaled up by 3 to become 48. |
| tileSize         | int        | The resulting tile size when originalTileSize is multiplied by the scale.                         |
| maxScreenCol     | int        | The max number of columns (of tiles) on the screen.                                               |
| maxScreenRow     | int        | The max number of rows (of tiles) on the screen.                                                  |
| screedWidth      | int        | The width of [maxScreenCol] number of tiles.                                                      |
| screenHeight     | int        | The height of [maxScreenRow] number of tiles.                                                     |
| FPS              | int        | The intended frame rate of the game.                                                              |
| keyH             | KeyHandler | The game's keyHandler.                                                                            |
| gameThread       | gameThread | The game's gameThread.                                                                            |
| world            | World      | The game's World.                                                                                 |

\
\
\
__startGameThread()__

Starts the game's Thread.

\
__run()__

1. The drawInterval is declared to be every 1/60 second, or every 16 million nanoseconds
2. The nextDrawTime is declared as the current System.nanoTime PLUS the draw interval, aka 1/60 second from current time
3. While the gameThread is active:
   4. update() and repaint() are called
   5. The remainingTime is declared to be the difference between the nextDrawTime and the current time (System.nanoTime)
   6. remainingTime is then divided by 1000000 to return a value in milliseconds
   7. Thread.sleep(remainingTime) is called, causing the gameThread to sleep for whatever amount of time is left until the nextDrawInterval is complete
   8. the nextDrawTime is incremented by drawInterval and the loop continues

\
__update()__

World's update method is called here.

\
__paintComponent(Graphics)__

World's draw method is called here.
