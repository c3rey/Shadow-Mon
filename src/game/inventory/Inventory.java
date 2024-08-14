package game.inventory;

import game.GamePanel;
import game.KeyHandler;
import game.thing.entity.Player;
import game.thing.object.RetrievableGameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Inventory {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final InventoryIterator iterator;
    public InventorySlot[][] inventoryArray;
    public BufferedImage inventoryPanel;
    KeyHandler keyH;
    Player player;
    GamePanel gp;
    public boolean inventoryDrawn = false;
    private boolean iteratorPaused = false;
    public int inventoryPanelX, inventoryPanelY;
    public int inventoryPanelWidth, inventoryPanelHeight;
    public int maxRows = 3, maxCols = 4;
    public int nextEmptyCol, nextEmptyRow;

    public Inventory(Player player) {
        this.player = player;
        gp = player.gp;
        keyH = player.keyH;

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

    public void add(RetrievableGameObject object) { //used when a Player picks up a RetrievableGameObject
        if (checkSpace()) {
            inventoryArray[nextEmptyRow][nextEmptyCol].currentObject = object;
            object.retrieved = true;
        } else {
            System.out.println("inventory full!");
        }
    }

    public void drop(RetrievableGameObject object) { //used when player drops a RetrievableGameObject. Drops item where Player currently is
        switch (player.direction) {
            case "up":
                object.replace(player.worldX, player.worldY - GamePanel.tileSize / 2);
                break;
            case "down":
                object.replace(player.worldX + GamePanel.tileSize / 4, player.worldY + GamePanel.tileSize);
                break;
            case "left":
                object.replace(player.worldX - GamePanel.tileSize / 2, player.worldY + GamePanel.tileSize / 4);
                break;
            case "right":
                object.replace(player.worldX + GamePanel.tileSize / 2, player.worldY + GamePanel.tileSize / 4);
        }
        //[]inventoryArray.remove(object);
    }


    private void setInventoryPanel() {
        try {
            inventoryPanel = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\inventorybox.png"));
        } catch (IOException e) {
            System.err.println("IOException in Player.Inventory");
        }
    }

    private void drawInventoryPanel(Graphics2D g2) {
        g2.drawImage(inventoryPanel, inventoryPanelX, inventoryPanelY, inventoryPanelWidth, inventoryPanelHeight, null);
    }

    private void drawInventorySlots(Graphics2D g2) {
        for (InventorySlot[] inventorySlots : inventoryArray) {
            for (InventorySlot inventorySlot : inventorySlots) {
                inventorySlot.draw(g2);
            }
        }

    }

    private void updateSlots() {
        for (InventorySlot[] inventorySlots : inventoryArray) {
            for (InventorySlot inventorySlot : inventorySlots) {
                inventorySlot.updateSlot();
            }
        }
    }


    private boolean checkSpace() { //checks if there is an available InventorySlot in InventoryArray
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

    private void setInventorySlots() { //fills the InventoryArray with InventorySlots that contain RetrievableGameObjects
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

    private void navigateInventory() { //allows access to the InventoryIterator via the KeyHandler

        if (inventoryDrawn) {
            if (!iteratorPaused) {
                int iteratorDelay = 150; //ms delay for iterator when swapping between slots
                if (keyH.upPressed && iterator.moveIsClear()) {
                    iterator.goUp();
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS); // delay after iterator moves so that it doesn't move multiple times for one button press
                }
                if (keyH.downPressed && iterator.moveIsClear()) {
                    iterator.goDown();
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS);
                }
                if (keyH.leftPressed && iterator.moveIsClear()) {
                    iterator.goLeft();
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS);
                }
                if (keyH.rightPressed && iterator.moveIsClear()) {
                    iterator.goRight();
                    iteratorPaused = true;
                    executor.schedule(() -> {
                        iteratorPaused = false;
                    }, iteratorDelay, TimeUnit.MILLISECONDS);
                }
            }

            if (keyH.ePressed) {
                inventoryDrawn = false;
                iterator.resetCurrentSlot();
            }
        }

    }

    public static class InventorySlot {

            public BufferedImage image, slotImage, highlightedSlotImage;
            Inventory inventory;
            RetrievableGameObject currentObject;
            boolean highlighted;
            int colNum, rowNum;
            int slotX, slotY;
            int slotWidth, slotHeight;

            public InventorySlot(Inventory inventory, int rowNum, int colNum){ //constructor requires column and row number to identify where it is relative to other InventorySlots
                this.inventory = inventory;
                this.rowNum = rowNum;
                this.colNum = colNum;
                currentObject = null;
                highlighted = false;
                slotWidth = 36;
                slotHeight = 36;

                setSlotImages();
            }

            void updateSlot(){
                if (highlighted){
                    image = highlightedSlotImage;
                }else{
                    image = slotImage;
                }
            }

            private void draw(Graphics2D g2){
                g2.drawImage(image, slotX, slotY, slotWidth, slotHeight, null);
                if (currentObject != null){
                    g2.drawImage(currentObject.displayImage, slotX + 5, slotY + 5, slotWidth - 10, slotHeight - 10, null);
                }
            }

            private void setSlotImages() {
                try{
                    slotImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\inventoryslot.png"));
                    highlightedSlotImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\highlightedinventoryslot.png.png"));
                }catch (IOException e){
                    System.err.println("IOException in Player.Inventory.InventorySlot");
                }
            }
        }

    public static class InventoryIterator{
            Inventory inventory;
            InventorySlot[][] inventoryArray;
            InventorySlot currentSlot;
            Player player;
            KeyHandler keyH;

            InventoryIterator(Inventory inventory){
                this.inventory = inventory;
                inventoryArray = inventory.inventoryArray;
                player = inventory.player;
                keyH = player.keyH;
                currentSlot = inventoryArray[0][0];
                currentSlot.highlighted = true;
            }

            private void goUp(){
                currentSlot.highlighted = false;
                currentSlot = inventoryArray[currentSlot.rowNum - 1][currentSlot.colNum];
                currentSlot.highlighted = true;
            }
            private void goDown(){
                currentSlot.highlighted = false;
                currentSlot = inventoryArray[currentSlot.rowNum + 1][currentSlot.colNum];
                currentSlot.highlighted = true;
            }
            private void goLeft(){
                currentSlot.highlighted = false;
                currentSlot = inventoryArray[currentSlot.rowNum][currentSlot.colNum - 1];
                currentSlot.highlighted = true;
            }
            private void goRight(){
                currentSlot.highlighted = false;
                currentSlot = inventoryArray[currentSlot.rowNum][currentSlot.colNum + 1];
                currentSlot.highlighted = true;
            }

            private void resetCurrentSlot(){
                currentSlot.highlighted = false;
                currentSlot = inventoryArray[0][0];
                currentSlot.highlighted = true;
            }

            private boolean moveIsClear(){
                boolean moveClear = false;
                if (keyH.upPressed && currentSlot.rowNum - 1 >= 0){
                    moveClear = true;
                }
                if (keyH.downPressed && currentSlot.rowNum + 1 < inventory.maxRows){
                    moveClear = true;
                }
                if (keyH.leftPressed && currentSlot.colNum - 1 >= 0){
                    moveClear = true;
                }
                if (keyH.rightPressed && currentSlot.colNum + 1 < inventory.maxCols){
                    moveClear = true;
                }

                return moveClear;
            }
        }
}
