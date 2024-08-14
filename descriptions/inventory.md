__Inventory__

A Linked Node-based storage system for GameObjects. Made up of [InventorySlots](inventoryslot.md) and is traversed using [InventoryIterator]()

|                   Variable                   |           Type           | Description                                                                           |
|:--------------------------------------------:|:------------------------:|---------------------------------------------------------------------------------------|
|                   executor                   | ScheduledExecutorService | An executor that allows a pause between the Iterator switching between InventorySlots |
|                   iterator                   |    InventoryIterator     | The Inventory's Iterator.                                                             |
|                inventoryArray                |    InventorySlot[][]     | A 2D array of InventorySlots representing the inventory.                              |
|                inventoryPanel                |      BufferedImage       | The image representing the inventory panel.                                           |
|                     keyH                     |        KeyHandler        | The Inventory's keyHandler.                                                           |
|                    player                    |          Player          | The Inventory's Player.                                                               |
|                      gp                      |        GamePanel         | The game's GamePanel.                                                                 |
|                inventoryDrawn                |         boolean          | True whenever the inventory panel is drawn, false when not.                           |
|                iteratorPaused                |         boolean          | True whenever InventoryIterator's current inventorySlot is changed;                   |
|     inventoryPanelX<br/>inventoryPanelY      |           int            | The X and Y values of the inventory panel.                                            |
| inventoryPanelWidth<br/>inventoryPanelHeight |           int            | The width and height of the inventory panel.                                          |
|             maxRows<br/>maxCols              |           int            | The number of rows and columns in the inventory.                                      |
|        nextEmptyCol<br/>nextEmptyRow         |           int            | The next row or column with an empty InventorySlot.                                   |

\
\
\
__update()__

Allows the navigation of the Inventory via the navigateInventory() method, and updates InventorySLots using updateSlots().

\
__draw(Graphics2D)__

If the Inventory is supposed to be on the screen, draws the inventoryPanel and InventorySlots.

\
__add(RetrievableGameObject)__

Checks to see if there's any space for the specified RetrievableGameObject in the Inventory. 
If there is, the RetrievableGameObject is added to the next free slot.

\
__drop(RetrievableGameObject)__

[WIP]

\
__setInventoryPanel()__

Sets the inventoryPanel image.

\
__drawInventoryPanel(Graphics2D)__

Draws the Inventory Panel.

\
__drawInventorySlots()__

Loops through the InventoryArray and draws each InventorySlot.

\
__updateSlots()__

Loops through the InventoryArray and updates each InventorySlot.

\
__checkSpace()__

Checks to see if there is an empty InventorySlot in the Inventory. If there is space in the next column in the current row of the current InventorySlot, or if there's space in the row below, returns true. Returns false otherwise (if all available slots are filled).

\
__setInventorySlots()__

Instantiates and sets all InventorySlots in inventoryArray, as well as their slotX and slotY.

\
__navigateInventory()__

Uses KeyHandler to listen for key input. Depending on the key pressed (any of the traversal keys), cursor moves up, down, left, or right in the Inventory.