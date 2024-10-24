package game.thing.entity;

import game.*;
import UI.inventory.PlayerInventory;
import game.thing.Thing;
import game.thing.door.LockedDoor;
import game.world.World;
import game.thing.object.RetrievableGameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Player extends Entity {
    public World world;
    public PlayerInventory inventory;
    public CollisionChecker cChecker;
    public KeyHandler keyH;

    public Player() { //constructor
        keyH = World.keyH;

        cChecker = new CollisionChecker(this);
        inventory = new PlayerInventory(this);

        setDefaultValues();
        setPlayerImage();
    }


    private void setDefaultValues() {
        worldX = 70;
        worldY = 250;
        speed = 4;
        direction = DOWN;

        solidArea = new Rectangle(worldX, (worldY + 28), GamePanel.tileSize, (GamePanel.tileSize - 28));
        interactArea = new Rectangle(worldX, worldY, GamePanel.tileSize, GamePanel.tileSize);
        width = GamePanel.tileSize;
        height = GamePanel.tileSize;

        interactOn = true;
    }

    private void setPlayerImage() { //loads the player image files

        try {
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

        } catch (IOException e) {
            System.err.println("Player files not loaded");
        }

    }

    public void pickUp(RetrievableGameObject object) {
        inventory.add(object);
    }

    public boolean interactsWith(Thing thing) {
        return interactArea.intersects(thing.interactArea) && keyH.playerEPressed;
    }

    public boolean hasKeyFor(LockedDoor door) {
        return inventory.hasKeyFor(door);
    }


    @Override
    public void update() { //update method to be called in World.update()
        if (!inventory.inventoryDrawn) {
            isWalking = false;

            //used to create Player walking animation
            updateCount++; //is incremented every time update() is called, therefore 60 times a second
            if (updateCount == 12) {
                spriteCount++;
            }
            if (updateCount == 24) {
                spriteCount = 0;
                updateCount = 0;
            }


            //sets player image for when Player is standing still
            switch (direction) {
                case UP:
                    image = up1;
                    break;

                case DOWN:
                    image = down1;
                    break;

                case RIGHT:
                    image = right1;
                    break;

                case LEFT:
                    image = left1;
                    break;

            }

            //direction conditions based on keyHandler
            if (!movementLocked) {
                if (keyH.upPressed) {
                    direction = UP;
                } else if (keyH.downPressed) {
                    direction = DOWN;
                } else if (keyH.leftPressed) {
                    direction = LEFT;
                } else if (keyH.rightPressed) {
                    direction = RIGHT;
                }
            }
            //updates walking animation while Player is movementLocked
            else {
                updateSprites();
            }

            // controlled movement only occurs if collisionOn is false and Player is allowed to move freely
            if (!collisionOn && !movementLocked) {
                switch (direction) {
                    case UP:
                        if (keyH.upPressed) {
                            updateSprites();

                            //diagonal movement
                            if (keyH.leftPressed) {
                                Rectangle nextPlayerPosition = new Rectangle(solidArea.x - speed, solidArea.y - speed, solidArea.width, solidArea.height);
                                cChecker.checkForDoors(nextPlayerPosition);
                                if (cChecker.checkTileCol(nextPlayerPosition, UP_LEFT) && cChecker.checkForObjects(nextPlayerPosition)) {
                                    walk(LEFT);
                                }
                            }
                            if (keyH.rightPressed) {
                                Rectangle nextPlayerPosition = new Rectangle(solidArea.x + speed, solidArea.y - speed, solidArea.width, solidArea.height);
                                cChecker.checkForDoors(nextPlayerPosition);
                                if (cChecker.checkTileCol(nextPlayerPosition, UP_RIGHT) && cChecker.checkForObjects(nextPlayerPosition)) {
                                    walk(RIGHT);
                                }
                            }


                            Rectangle nextPlayerPosition = new Rectangle(solidArea.x, solidArea.y - speed, solidArea.width, solidArea.height);
                            cChecker.checkForDoors(nextPlayerPosition);
                            if (cChecker.checkTileCol(nextPlayerPosition, UP) && cChecker.checkForObjects(nextPlayerPosition)) {
                                walk(UP);
                            }

                        }
                        break;
                    case DOWN:
                        if (keyH.downPressed) {
                            updateSprites();

                            //diagonal movement
                            if (keyH.leftPressed) {
                                Rectangle nextPlayerPosition = new Rectangle(solidArea.x - speed, solidArea.y + speed, solidArea.width, solidArea.height);
                                cChecker.checkForDoors(nextPlayerPosition);
                                if (cChecker.checkTileCol(nextPlayerPosition, DOWN_LEFT) && cChecker.checkForObjects(nextPlayerPosition)) {
                                    walk(LEFT);
                                }
                            }
                            if (keyH.rightPressed) {
                                Rectangle nextPlayerPosition = new Rectangle(solidArea.x + speed, solidArea.y + speed, solidArea.width, solidArea.height);
                                cChecker.checkForDoors(nextPlayerPosition);
                                if (cChecker.checkTileCol(nextPlayerPosition, DOWN_RIGHT) && cChecker.checkForObjects(nextPlayerPosition)) {
                                    walk(RIGHT);
                                }
                            }


                            Rectangle nextPlayerPosition = new Rectangle(solidArea.x, solidArea.y + speed, solidArea.width, solidArea.height);
                            cChecker.checkForDoors(nextPlayerPosition);
                            if (cChecker.checkTileCol(nextPlayerPosition, DOWN) && cChecker.checkForObjects(nextPlayerPosition)) {
                                walk(DOWN);
                            }


                        }
                        break;
                    case LEFT:
                        if (keyH.leftPressed) {
                            updateSprites();

                            Rectangle nextPlayerPosition = new Rectangle(solidArea.x - speed, solidArea.y, solidArea.width, solidArea.height);
                            cChecker.checkForDoors(nextPlayerPosition);
                            if (cChecker.checkTileCol(nextPlayerPosition, LEFT) && cChecker.checkForObjects(nextPlayerPosition)) {
                                walk(LEFT);
                            }

                        }
                        break;
                    case RIGHT:
                        if (keyH.rightPressed) {
                            updateSprites();

                            Rectangle nextPlayerPosition = new Rectangle(solidArea.x + speed, solidArea.y, solidArea.width, solidArea.height);
                            cChecker.checkForDoors(nextPlayerPosition);
                            if (cChecker.checkTileCol(nextPlayerPosition, RIGHT) && cChecker.checkForObjects(nextPlayerPosition)) {
                                walk(RIGHT);
                            }

                        }
                        break;


                }
            }

            //SOLIDAREA AND INTERACTAREA
            solidArea = new Rectangle((worldX + 14), (worldY + 28), (GamePanel.tileSize - 28), (GamePanel.tileSize - 32)); //dimensions of SolidArea
            interactArea = new Rectangle(worldX, worldY, GamePanel.tileSize, GamePanel.tileSize);

            if (keyH.iPressed && !inventory.inventoryDrawn) {
                inventory.inventoryDrawn = true;
                keyH.mode = KeyHandler.INTERFACE;

            }

            chaseIntendedPosition();
            keyH.playerEPressed = false; //so that the player only interacts once per button press
        }
    }
}