## UI

Updates and draws anything that isn't physically in the [World](world.md), but appears on the screen.

| Variable  |       Type       | Description                                           |
|:---------:|:----------------:|-------------------------------------------------------|
|   world   |      World       | The game's World.                                     |
|  player   |      Player      | The game's Player.                                    |
| inventory | Player.Inventory | The Player's Inventory.                               |
|  prompts  |     Prompt[]     | The array containing all current Prompts in the game. |

\
\
\
__update()__

Updates the Player's Inventory.

\
__draw(Graphics2D)__

calls displayIntroPrompt, displayPrompts, and draws the Player's Inventory

\
__setPrompts()__

Instantiates Prompts in PromptArray.

\
__displayIntroPrompt()__

Currently displays the traversal Prompt permanently, but in future updates it will only display it for 
a set period of time.

\
__displayInteractPrompt(boolean)__

Displays the interact prompt according to the boolean argument.

\
__displayTraversalPrompt(boolean)__

Displays the traversal prompt according to the boolean argument.

\
__displayPrompts(Graphics2D)__

draws the prompts in prompts[] according to their type and whether they're active or not.