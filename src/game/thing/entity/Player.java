package game.thing.entity;

import game.*;
import game.inventory.Inventory;
import game.thing.Thing;
import game.world.World;
import game.thing.object.RetrievableGameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Player extends Entity {
    public GamePanel gp;
    public World world;
    public Inventory inventory;
    public CollisionChecker cChecker;
    public KeyHandler keyH;

    public boolean isDropping;


    public Player(World world, KeyHandler keyH) { //constructor
        this.world = world;
        this.keyH = keyH;

        cChecker = new CollisionChecker(this);
        inventory = new Inventory(this);

        setDefaultValues();
        setPlayerImage();

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

    private void setPlayerImage() { //loads the player image files

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

    public boolean interactsWith(Thing thing){
        return interactArea.intersects(thing.interactArea) && keyH.ePressed;
    }

    public void update(){ //update method to be called in World.update()
        if (!inventory.inventoryDrawn){


            //used to create Player walking animation
            updateCount++; //is incremented every time update() is called, therefore 60 times a second
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
            //cChecker.checkForDoors();

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

            //SOLIDAREA AND INTERACTAREA
            solidArea = new Rectangle((worldX + 14), (worldY + 28), (GamePanel.tileSize - 28), (GamePanel.tileSize - 32)); //dimensions of SolidArea
            interactArea = new Rectangle(worldX, worldY, GamePanel.tileSize, GamePanel.tileSize);

            if (keyH.oPressed && !inventory.inventoryDrawn){
                inventory.inventoryDrawn = true;
            }

        }
    }


}
