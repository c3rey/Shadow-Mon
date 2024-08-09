package game.thing.entity;

import game.GamePanel;
import game.KeyHandler;
import game.world.World;
import game.thing.object.RetrievableGameObject;
import game.CollisionChecker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Player extends Entity {
    public GamePanel gp;
    public World world;
    public Inventory inventory;
    public CollisionChecker cChecker;
    KeyHandler keyH;

    public boolean isInteracting;
    public boolean isDropping;


    public Player(World world, KeyHandler keyH) { //constructor
        this.world = world;
        this.keyH = keyH;

        cChecker = new CollisionChecker(this);
        inventory = new Inventory(this);

        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle(worldX, (worldY + 28), GamePanel.tileSize, (GamePanel.tileSize - 28));
        interactArea = new Rectangle(worldX, worldY, GamePanel.tileSize, GamePanel.tileSize);
        width = GamePanel.tileSize;
        height = GamePanel.tileSize;

    }


    private void setDefaultValues(){
        worldX = 70;
        worldY = 250;
        speed = 4;
        direction = "down";
    }

    private void getPlayerImage() { //loads the player image files

        try{
            up1 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-4.png.png"));
            up2 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-11.png.png"));
            up3 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-12.png.png"));
            down1 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-1.png.png"));
            down2 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-2.png.png"));
            down3 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-3.png.png"));
            left1 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-8.png.png"));
            left2 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-9.png.png"));
            left3 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-10.png.png"));
            right1 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-5.png.png"));
            right2 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-6.png.png"));
            right3 = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\Player\\mainchar-7.png.png"));

        }
        catch(IOException e){
            System.err.println("Player files not loaded");
        }

    }

    public void pickUp(RetrievableGameObject object){
        inventory.add(object);
    }

    public void drop(RetrievableGameObject object, int dropDistance){
        inventory.drop(object);
    }

    public void update(){ //update method to be called in World.update()
        if (!inventory.inventoryDrawn){
            updateCount++; //is incremented every time update() is called, therefore 60 times a second

            //used to create Player walking animation
            if (updateCount == 12) {
                spriteCount++;
            }
            if (updateCount == 24){
                spriteCount = 0;
                updateCount = 0;
            }

            //direction conditions based on keyHandler
            if (keyH.upPressed){
                direction = "up";
            }
            else if (keyH.downPressed){
                direction = "down";
            }
            else if (keyH.leftPressed){
                direction = "left";
            }
            else if (keyH.rightPressed){
                direction = "right";
            }

            //COLLISION
            collisionOn = cChecker.checkTileCollision() || cChecker.checkForObjects();//collision is turned on if Player collides with either a Tile or a gameObject
            cChecker.checkForDoors();

            switch (direction){
                case "up":
                    image = up1;
                    break;

                case "down":
                    image = down1;
                    break;

                case "right":
                    image = right1;
                    break;

                case "left":
                    image = left1;
                    break;

            }

            if (!collisionOn){ //movement only occurs if collisionOn is false
                switch (direction){
                    case "up":
                        if (keyH.upPressed){
                            worldY -= speed;

                            if (spriteCount == 0){
                                image = up2;
                            } else if (spriteCount == 1) {
                                image = up3;
                            }
                        }
                        break;
                    case "down":
                        if (keyH.downPressed){
                            worldY += speed;

                            if (spriteCount == 0){
                                image = down2;
                            } else if (spriteCount == 1) {
                                image = down3;
                            }
                        }
                        break;
                    case "left":
                        if (keyH.leftPressed){
                            worldX -= speed;

                            if (spriteCount == 0){
                                image = left2;
                            }
                        }
                        break;
                    case "right":
                        if(keyH.rightPressed){
                            worldX += speed;
                        }

                        if (spriteCount == 0 && keyH.rightPressed){
                            image = right2;
                        }
                        break;
                }
            }

            //SOLID AREA
            solidArea = new Rectangle((worldX + 14), (worldY + 28), (GamePanel.tileSize - 28), (GamePanel.tileSize - 32)); //dimensions of SolidArea

            //INTERACTING
            interactArea = new Rectangle(worldX, worldY, GamePanel.tileSize, GamePanel.tileSize);
            isInteracting = keyH.ePressed;

            if (keyH.oPressed && !inventory.inventoryDrawn){
                inventory.inventoryDrawn = true;
            }

        }
    }



    public static class Inventory {

        private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        private final InventoryIterator iterator;
        public InventorySlot[][] inventoryArray;
        public BufferedImage inventoryPanel;
        KeyHandler keyH;
        Player player;
        GamePanel gp;
        public boolean inventoryDrawn = false;
        private boolean iteratorPaused = false;
        private final int MAXOBJCOUNT = 8;
        public int inventoryPanelWidth;
        public int inventoryPanelHeight;
        public int maxRows = 3, maxCols = 4;
        public int nextEmptyCol, nextEmptyRow;
        public int inventoryPanelX, inventoryPanelY;

        private Inventory(Player player){
            this.player = player;
            gp = player.gp;
            keyH = player.keyH;

            inventoryArray = new InventorySlot[maxRows][maxCols];
            nextEmptyCol = 0;
            nextEmptyRow = 0;

            inventoryPanelWidth = gp.tileSize * 8;
            inventoryPanelHeight = gp.tileSize * 10;
            inventoryPanelX = 50;
            inventoryPanelY = 50;
            setInventoryPanel();
            setInventorySlots();

            iterator = new InventoryIterator(this);
        }

        public void update(){
            navigateInventory();
            updateSlots();
        }

        public void draw(Graphics2D g2){
            if (inventoryDrawn){
                drawInventoryPanel(g2);
                drawInventorySlots(g2);
            }
        }

        private void add(RetrievableGameObject object){ //used when a Player picks up a RetrievableGameObject
            if (checkSpace()){
                inventoryArray[nextEmptyRow][nextEmptyCol].currentObject = object;
                object.retrieved = true;
            }else{
                System.out.println("inventory full!");
            }
        }

        private void drop(RetrievableGameObject object){ //used when player drops a RetrievableGameObject. Drops item where Player currently is
            switch (player.direction){
                case "up":
                    object.replace(player.worldX, player.worldY - GamePanel.tileSize /2);
                    break;
                case "down":
                    object.replace(player.worldX + GamePanel.tileSize /4, player.worldY + GamePanel.tileSize);
                    break;
                case "left":
                    object.replace(player.worldX - GamePanel.tileSize /2, player.worldY + GamePanel.tileSize /4);
                    break;
                case "right":
                    object.replace(player.worldX + GamePanel.tileSize /2, player.worldY + GamePanel.tileSize /4);
            }
            //inventoryArray.remove(object);
        }




        private void setInventoryPanel(){
            try {
                inventoryPanel = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\inventorybox.png"));
            }catch (IOException e){
                System.err.println("IOException in Player.Inventory");
            }
        }

        private void drawInventoryPanel(Graphics2D g2){
            g2.drawImage(inventoryPanel, inventoryPanelX, inventoryPanelY, inventoryPanelWidth, inventoryPanelHeight, null);
        }

        private void drawInventorySlots(Graphics2D g2){
            InventorySlot currentSlot;
            for (int i = 0; i < inventoryArray.length; i++){
                for (int j = 0; j< inventoryArray[i].length; j++){
                    currentSlot = inventoryArray[i][j];
                    currentSlot.draw(g2);
                }
            }

        }

        private void updateSlots(){
            for (int i = 0; i < inventoryArray.length; i++){
                for (int j = 0; j < inventoryArray[i].length; j++) {
                    InventorySlot currentSlot = inventoryArray[i][j];
                    currentSlot.updateSlot();
                }
            }
        }



        private boolean checkSpace(){ //checks if there is an available InventorySlot in InventoryArray
            if (nextEmptyCol < maxCols){
                return true;
            } else if (nextEmptyRow < maxRows) {
                nextEmptyRow++;
                nextEmptyCol = 0;
                return true;
            }else{
                return false;
            }
        }

        private void setInventorySlots(){ //fills the InventoryArray with InventorySlots that contain RetrievableGameObjects
            int i;
            int j;
            InventorySlot currentSlot;
            for (i = 0; i < inventoryArray.length; i++){
                for (j = 0; j < inventoryArray[i].length; j++){
                    InventorySlot inventorySlot = new InventorySlot(this, i, j);
                    inventoryArray[i][j] = inventorySlot;

                    currentSlot = inventoryArray[i][j];
                    currentSlot.slotX = inventoryPanelX + 105 + (currentSlot.colNum * 50);
                    currentSlot.slotY = inventoryPanelY + 100 + (currentSlot.rowNum * 50);
                }
            }
        }

        private void navigateInventory(){ //allows access to the InventoryIterator via the KeyHandler

                if (inventoryDrawn){
                    if (!iteratorPaused){
                        int iteratorDelay = 150; //ms delay for iterator when swapping between slots
                        if (keyH.upPressed && iterator.moveIsClear()){
                            iterator.goUp();
                            iteratorPaused = true;
                            executor.schedule(() -> {iteratorPaused = false;}, iteratorDelay, TimeUnit.MILLISECONDS); // delay after iterator moves so that it doesn't move multiple times for one button press
                        }
                        if (keyH.downPressed && iterator.moveIsClear()){
                            iterator.goDown();
                            iteratorPaused = true;
                            executor.schedule(() -> {iteratorPaused = false;}, iteratorDelay, TimeUnit.MILLISECONDS);
                        }
                        if (keyH.leftPressed && iterator.moveIsClear()){
                            iterator.goLeft();
                            iteratorPaused = true;
                            executor.schedule(() -> {iteratorPaused = false;}, iteratorDelay, TimeUnit.MILLISECONDS);
                        }
                        if (keyH.rightPressed && iterator.moveIsClear()){
                            iterator.goRight();
                            iteratorPaused = true;
                            executor.schedule(() -> {iteratorPaused = false;}, iteratorDelay, TimeUnit.MILLISECONDS);
                        }
                    }

                    if (keyH.ePressed){
                        inventoryDrawn = false;
                        iterator.resetCurrentSlot();
                    }
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
