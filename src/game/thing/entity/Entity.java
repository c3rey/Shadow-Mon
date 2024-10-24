package game.thing.entity;

import game.pathing.Path;
import game.thing.Thing;

import java.awt.image.BufferedImage;

public class Entity extends Thing {
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public int direction;

    public int updateCount = 0;
    public int spriteCount = 0;

    public boolean isWalking = false;

    public boolean movementLocked;

    private boolean xPositionReached;
    private boolean yPositionReached;
    public Path currentPath;
    public Path.GamePoint intendedPosition;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int UP_LEFT = 5;
    public static final int UP_RIGHT = 6;
    public static final int DOWN_LEFT = 7;
    public static final int DOWN_RIGHT = 8;


    public void update(){

        //used to create Player walking animation
        updateCount++; //is incremented every time update() is called, therefore 60 times a second
        if (updateCount == 12) {
            spriteCount++;
        }
        if (updateCount == 24){
            spriteCount = 0;
            updateCount = 0;
        }

        switch (direction){
            case UP:
                image = up1;
                if (isWalking) {
                    if (spriteCount == 0) {
                        image = up2;
                    } else if (spriteCount == 1) {
                        image = up3;
                    }
                }
                break;

            case DOWN:
                image = down1;
                if (isWalking) {
                    if (spriteCount == 0) {
                        image = down2;
                    } else if (spriteCount == 1) {
                        image = down3;
                    }
                }
                break;

            case LEFT:
                image = left1;
                if (isWalking){
                    if(spriteCount == 0){
                        image = left2;
                    }
                }
                break;

            case RIGHT:
                image = right1;
                if (isWalking){
                    if (spriteCount == 0){
                        image = right2;
                    }
                }
                break;

        }

        chaseIntendedPosition();;
    }

    void walk(int direction){
        switch (direction){
            case UP : worldY -= speed;
                break;
            case DOWN: worldY += speed;
                break;
            case LEFT: worldX -= speed;
                break;
            case RIGHT: worldX += speed;
        }
        isWalking = true;
    }

    void chaseIntendedPosition(){
        if (intendedPosition != null){
            movementLocked = true;
            if (intendedPosition.x < worldX) {
                direction = LEFT;
                walk(direction);

                if (worldX - intendedPosition.x < speed) {
                    worldX = intendedPosition.x;
                    xPositionReached = true;
                }
            }
            else if (intendedPosition.x > worldX) {
                direction = RIGHT;
                walk(direction);

                if (intendedPosition.x - worldX < speed) {
                    worldX = intendedPosition.x;
                    xPositionReached = true;
                }
            }

            if (xPositionReached){
                if (intendedPosition.y < worldY) {
                    direction = UP;
                    walk(direction);
                    if (worldY - intendedPosition.y < speed) {
                        worldY = intendedPosition.y;
                        yPositionReached = true;
                    }
                } else {
                    direction = DOWN;
                    walk(direction);

                    if (intendedPosition.y - worldY < speed) {
                        worldY = intendedPosition.y;
                        yPositionReached = true;
                    }
                }
            }
        }

        if (xPositionReached && yPositionReached){
            isWalking = false;

            xPositionReached = false;
            yPositionReached = false;
        }

        if (intendedPosition == null) {
            movementLocked = false;
            currentPath = null;
        }
    }

    void updateSprites(){
        switch (direction){
            case UP:
                if (spriteCount == 0) {
                    image = up2;
                } else if (spriteCount == 1) {
                    image = up3;
                }
                break;
            case DOWN:
                if (spriteCount == 0) {
                    image = down2;
                } else if (spriteCount == 1) {
                    image = down3;
                }
                break;
            case LEFT:
                if (spriteCount == 0) {
                    image = left2;
                }
                break;
            case RIGHT:
                if (spriteCount == 0) {
                    image = right2;
                }
        }
    }
}
