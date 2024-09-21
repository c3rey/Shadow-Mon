package UI.inventory;

import UI.Option;
import game.GamePanel;
import game.InteractManager;
import game.KeyHandler;
import game.thing.object.RetrievableGameObject;
import game.thing.object.Usable;
import game.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Inventory {

    final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    final InventoryIterator iterator;
    BufferedImage inventoryPanel;
    KeyHandler keyH;
    InventorySlot[][] inventoryArray;

    public boolean inventoryDrawn = false;
    private boolean iteratorPaused = false;
    private boolean slotSelected = false;
    public int inventoryPanelX, inventoryPanelY;
    public int inventoryPanelWidth, inventoryPanelHeight;
    public int maxRows = 3, maxCols = 4;
    public int nextEmptyCol, nextEmptyRow;

    public Inventory() {
        keyH = World.keyH;

        inventoryArray = new InventorySlot[maxRows][maxCols];
        nextEmptyCol = 0;
        nextEmptyRow = 0;

        inventoryPanelWidth = GamePanel.tileSize * 8;
        inventoryPanelHeight = GamePanel.tileSize * 10;
        inventoryPanelX = 50;
        inventoryPanelY = 50;
        setInventoryPanel();
        setInventorySlots();

        iterator = new InventoryIterator(this);
    }

    public void update() {
        navigateInventory();
        updateSlots();
    }

    public void draw(Graphics2D g2) {
        if (inventoryDrawn) {
            drawInventoryPanel(g2);
            drawInventorySlots(g2);
        }
    }




    void setInventoryPanel() {
        try {
            inventoryPanel = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\inventorybox.png"));
        } catch (IOException e) {
            System.err.println("IOException in Player.Inventory");
        }
    }

    void drawInventoryPanel(Graphics2D g2) {//draws the InventoryPanel
        g2.drawImage(inventoryPanel, inventoryPanelX, inventoryPanelY, inventoryPanelWidth, inventoryPanelHeight, null);
    }

    void updateSlots() { //updates each InventorySlot in InventoryArray
        for (InventorySlot[] inventorySlots : inventoryArray) {
            for (InventorySlot inventorySlot : inventorySlots) {
                inventorySlot.updateSlot();
            }
        }
    }

    void drawInventorySlots(Graphics2D g2) { //draws each InventorySlot in InventoryArray
        for (InventorySlot[] inventorySlots : inventoryArray) {
            for (InventorySlot inventorySlot : inventorySlots) {
                inventorySlot.draw(g2);
            }
        }
        if (iterator.currentSlot.selected && iterator.currentSlot.options != null) {
            for (Option option : iterator.currentSlot.options) {
                option.draw(g2);
            }
        }

    }

    void setInventorySlots() { //fills the InventoryArray with InventorySlots that contain RetrievableGameObjects
        int i;
        int j;
        InventorySlot currentSlot;
        for (i = 0; i < inventoryArray.length; i++) {
            for (j = 0; j < inventoryArray[i].length; j++) {
                InventorySlot inventorySlot = new InventorySlot(this, i, j);
                inventoryArray[i][j] = inventorySlot;

                currentSlot = inventoryArray[i][j];
                currentSlot.slotX = inventoryPanelX + 105 + (currentSlot.colNum * 50);
                currentSlot.slotY = inventoryPanelY + 100 + (currentSlot.rowNum * 50);
            }
        }
    }

    boolean checkForSpace() { //checks if there is an available InventorySlot in InventoryArray
        if (nextEmptyCol < maxCols) {
            return true;
        } else if (nextEmptyRow < maxRows) {
            nextEmptyRow++;
            nextEmptyCol = 0;
            return true;
        } else {
            return false;
        }
    }

    void navigateInventory() { //allows access to the InventoryIterator via the KeyHandler

        if (inventoryDrawn) {
            if (!iteratorPaused) {
                int iteratorDelay = 150; //ms delay for iterator when swapping between slots
                //cursor movement
                if (keyH.upPressed && iterator.moveIsClear()) {
                    iterator.traverse("UP");
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS); // delay after iterator moves so that it doesn't move multiple times for one button press
                }
                if (keyH.downPressed && iterator.moveIsClear()) {
                    iterator.traverse("DOWN");
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS);
                }
                if (keyH.leftPressed && iterator.moveIsClear()) {
                    iterator.traverse("LEFT");
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS);
                }
                if (keyH.rightPressed && iterator.moveIsClear()) {
                    iterator.traverse("RIGHT");
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS);
                }


                //SELECTING
                if (keyH.enterPressed && iterator.currentSlot.currentObject != null && !slotSelected){
                    iterator.selectSlot();
                    slotSelected = true;
                    keyH.enterPressed = false;
                }
                if (keyH.enterPressed && slotSelected) {
                    iterator.selectOption();

                }
            }

            if (keyH.backspacePressed && !slotSelected) {
                inventoryDrawn = false;
                keyH.mode = KeyHandler.PLAYER;
                iterator.resetCurrentSlot();
            }else if (keyH.backspacePressed){
                iterator.deselectSlot();
                slotSelected = false;
                keyH.backspacePressed = false;
            }
        }

    }







    public static class InventorySlot {

            BufferedImage image, slotImage, highlightedSlotImage;
            Inventory inventory;
            public ArrayList<Option> options;
            public RetrievableGameObject currentObject;
            boolean highlighted;
            boolean selected;

            public int slotX, slotY;
            public int slotWidth, slotHeight;

            int colNum, rowNum;

            InventorySlot(Inventory inventory, int rowNum, int colNum){ //constructor requires column and row number to identify where it is relative to other InventorySlots
                this.inventory = inventory;
                this.rowNum = rowNum;
                this.colNum = colNum;
                options = new ArrayList<>();


                currentObject = null;
                highlighted = false;
                slotWidth = 36;
                slotHeight = 36;

                setSlotImages();
            }

            void addObject(RetrievableGameObject gameObject){
                currentObject = gameObject;
                setOptions(gameObject);
            }

            void removeObject(){
                //currentObject = null;
                //options = null;
            }

            private void setOptions(RetrievableGameObject gameObject){
                if (gameObject instanceof Usable){
                    options.add(new Option(this, Option.USE)); //adds the option to USE the RetrievableGameObject
                }

                options.add(new Option(this, Option.INSPECT)); //adds the option to INSPECT the RetrievableGameObject
            }

            void updateSlot(){
                if (highlighted){
                    image = highlightedSlotImage;
                }else{
                    image = slotImage;
                }
            }

            void draw(Graphics2D g2) {
                g2.drawImage(image, slotX, slotY, slotWidth, slotHeight, null);
                if (currentObject != null) {
                    g2.drawImage(currentObject.displayImage, slotX + 5, slotY + 5, slotWidth - 10, slotHeight - 10, null);
                }
            }

            void setSlotImages() { //sets slotImage and highlightedSlotImage
                try{
                    slotImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\inventoryslot.png"));
                    highlightedSlotImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\highlightedinventoryslot.png"));
                }catch (IOException e){
                    System.err.println("IOException in Player.Inventory.InventorySlot");
                }
            }
        }







    static class InventoryIterator{
            Inventory inventory;
            InventorySlot[][] inventoryArray;
            InventorySlot currentSlot;
            ArrayList<Option> currentSlotOptions;
            KeyHandler keyH;
            int currentOptionIndex;

            int mode;

            public static final int TRAVERSEINVENTORY = 0;
            public static final int SLOTSELECTED = 1;

            InventoryIterator(Inventory inventory){
                this.inventory = inventory;
                inventoryArray = inventory.inventoryArray;
                keyH = World.keyH;
                currentSlot = inventoryArray[0][0];
                currentSlot.highlighted = true;

                mode = TRAVERSEINVENTORY;
            }



            void traverse(String direction){
                if (mode == TRAVERSEINVENTORY){
                    switch (direction){
                        case "UP":
                            currentSlot.highlighted = false;
                            currentSlot = inventoryArray[currentSlot.rowNum - 1][currentSlot.colNum];
                            currentSlot.highlighted = true;
                            break;
                        case "DOWN":
                            currentSlot.highlighted = false;
                            currentSlot = inventoryArray[currentSlot.rowNum + 1][currentSlot.colNum];
                            currentSlot.highlighted = true;
                            break;
                        case "LEFT":
                            currentSlot.highlighted = false;
                            currentSlot = inventoryArray[currentSlot.rowNum][currentSlot.colNum - 1];
                            currentSlot.highlighted = true;
                            break;
                        case "RIGHT":
                            currentSlot.highlighted = false;
                            currentSlot = inventoryArray[currentSlot.rowNum][currentSlot.colNum + 1];
                            currentSlot.highlighted = true;
                            break;
                    }
                } else if (mode == SLOTSELECTED) {
                    switch (direction){
                        case "UP", "LEFT":
                            currentSlotOptions.get(currentOptionIndex).highlighted = false;
                            currentOptionIndex--;
                            currentSlotOptions.get(currentOptionIndex).highlighted = true;
                            break;
                        case "DOWN", "RIGHT":
                            currentSlotOptions.get(currentOptionIndex).highlighted = false;
                            currentOptionIndex++;
                            currentSlotOptions.get(currentOptionIndex).highlighted = true;
                            break;
                    }
                }

            }

            private void selectSlot(){
                currentSlot.selected = true;
                currentSlotOptions = currentSlot.options;
                currentOptionIndex = 0;

                mode = SLOTSELECTED;

                currentSlot.highlighted = false;
                currentSlotOptions.get(currentOptionIndex).highlighted = true;
            }

            private void deselectSlot(){
                currentSlot.selected = false;

                mode = TRAVERSEINVENTORY;

                currentSlot.highlighted = true;
                currentSlot.options.get(currentOptionIndex).highlighted = false;
                currentSlotOptions = null;
                currentOptionIndex = 0;
            }

            private void selectOption() {
                currentSlotOptions.get(currentOptionIndex).function();
                keyH.enterPressed = false;
            }

            private void resetCurrentSlot(){
                currentSlot.highlighted = false;
                currentSlot = inventoryArray[0][0];
                currentSlot.highlighted = true;
            }

            private boolean moveIsClear(){
                boolean moveClear = true;
                if (mode == TRAVERSEINVENTORY){
                    if (keyH.upPressed && currentSlot.rowNum - 1 < 0){
                        moveClear = false;
                    }
                    if (keyH.downPressed && currentSlot.rowNum + 1 >= inventory.maxRows){
                        moveClear = false;
                    }
                    if (keyH.leftPressed && currentSlot.colNum - 1 < 0){
                        moveClear = false;
                    }
                    if (keyH.rightPressed && currentSlot.colNum + 1 >= inventory.maxCols){
                        moveClear = false;
                    }
                } else if (mode == SLOTSELECTED) {
                    if ((keyH.upPressed || keyH.leftPressed) && currentOptionIndex == 0){
                        moveClear = false;
                    }
                    if ((keyH.downPressed || keyH.rightPressed) && currentOptionIndex >= currentSlotOptions.size() - 1){
                        moveClear = false;
                    }
                }

                return moveClear;
            }
        }
}
