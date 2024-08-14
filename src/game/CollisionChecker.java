package game;

import game.thing.entity.Player;
import game.thing.object.GameObject;
import game.thing.object.ObjectManager;
import game.thing.object.RetrievableGameObject;
import game.world.Level;
import game.world.Map;
import game.world.World;
import game.world.tile.Tile;

import java.awt.*;

public class CollisionChecker {
    World world;
    Level currentLevel;
    Map currentMap;
    Player player;
    ObjectManager objectManager;

    public CollisionChecker(Player player){
        this.player = player;
        world = player.world;
    } //constructor

    public boolean checkForObjects(){ //checkObject(player, gameObject) for every gameObject in ObjectManager.objArray
        objectManager = world.objM;
        boolean collisionOn = false;

        for (GameObject object : objectManager.objArray){
            if (checkObject(object)) { //if any of the checkObject() calls returns true, collision is turned on
                collisionOn = true;
            }

            if (((RetrievableGameObject) object).isReplacing){
                collisionOn = false;
            }
        }
        return collisionOn;
    }

    private boolean checkObject(GameObject gameObject){ //checks collision for a single GameObject
        currentLevel = world.stream.level1;
        currentMap = currentLevel.currentMap;
        boolean collisionOn = false;

        if (currentMap == gameObject.map && gameObject.collisionOn) {
            collisionOn = getNextPlayerPosition().intersects(gameObject.solidArea);
        }

        return collisionOn;
    }

    public boolean checkDropLocation(int distance){

        boolean dropInvalid = false;

        currentLevel = world.stream.level1;
        currentMap = currentLevel.currentMap;

        int objectLeftWorldX, objectRightWorldX, objectTopWorldY, objectBottomWorldY;
        double objectLeftCol, objectRightCol, objectTopRow, objectBottomRow;

        int leftTileNum, rightTileNum, topTileNum, bottomTileNum;

        Tile leftTile, rightTile, topTile, bottomTile;

        switch (player.direction) {

            case "up":
                objectLeftWorldX = player.solidArea.x;
                objectRightWorldX = player.solidArea.x + player.solidArea.width;
                objectTopWorldY = player.solidArea.y - distance;

                objectLeftCol = (double) objectLeftWorldX / GamePanel.tileSize;
                objectRightCol = (double) objectRightWorldX / GamePanel.tileSize;
                objectTopRow = (double) objectTopWorldY / GamePanel.tileSize;

                leftTileNum = (int) Math.floor(objectLeftCol);
                rightTileNum = (int) Math.floor(objectRightCol);

                leftTile = currentMap.tileArray[(int) objectTopRow][leftTileNum];
                rightTile = currentMap.tileArray[(int) objectTopRow][rightTileNum];

                dropInvalid = leftTile.collision || rightTile.collision;
                break;
            case "down":
                objectLeftWorldX = player.solidArea.x;
                objectRightWorldX = player.solidArea.x + player.solidArea.width;
                objectBottomWorldY = (player.solidArea.y + distance) + player.solidArea.height;

                objectLeftCol = (double) objectLeftWorldX / GamePanel.tileSize;
                objectRightCol = (double) objectRightWorldX / GamePanel.tileSize;
                objectBottomRow = (double) objectBottomWorldY / GamePanel.tileSize;

                leftTileNum = (int) Math.floor(objectLeftCol);
                rightTileNum = (int) Math.floor(objectRightCol);

                leftTile = currentMap.tileArray[(int) objectBottomRow][leftTileNum];
                rightTile = currentMap.tileArray[(int) objectBottomRow][rightTileNum];

                dropInvalid = leftTile.collision || rightTile.collision;
                break;
            case "left":
                objectLeftWorldX = player.solidArea.x - distance;
                objectTopWorldY = player.solidArea.y;
                objectBottomWorldY = player.solidArea.y + player.solidArea.height;

                objectLeftCol = (double) objectLeftWorldX / GamePanel.tileSize;
                objectTopRow = (double) objectTopWorldY / GamePanel.tileSize;
                objectBottomRow = (double) objectBottomWorldY / GamePanel.tileSize;


                topTileNum = (int) Math.floor(objectTopRow);
                bottomTileNum = (int) Math.floor(objectBottomRow);

                topTile = currentMap.tileArray[topTileNum][(int) objectLeftCol];
                bottomTile = currentMap.tileArray[bottomTileNum][(int) objectLeftCol];

                dropInvalid = topTile.collision || bottomTile.collision;
                break;
            case "right":
                objectRightWorldX = (player.solidArea.x + distance) + player.solidArea.width;
                objectTopWorldY = player.solidArea.y;
                objectBottomWorldY = player.solidArea.y + player.solidArea.height;

                objectRightCol = (double) objectRightWorldX / GamePanel.tileSize;
                objectTopRow = (double) objectTopWorldY / GamePanel.tileSize;
                objectBottomRow = (double) objectBottomWorldY / GamePanel.tileSize;


                topTileNum = (int) Math.floor(objectTopRow);
                bottomTileNum = (int) Math.floor(objectBottomRow);

                topTile = currentMap.tileArray[topTileNum][(int) objectRightCol];
                bottomTile = currentMap.tileArray[bottomTileNum][(int) objectRightCol];

                dropInvalid = topTile.collision || bottomTile.collision;
                break;

        }

        return dropInvalid;

    }

    public boolean checkTileCollision(){ //checks the tiles at specified distance away from Player in the direction Player is facing

        currentLevel = world.stream.level1;
        currentMap = currentLevel.currentMap;

        //the four lines that will be used to create the solidArea perimeter
        int entityLeftWorldX = player.solidArea.x;
        int entityRightWorldX = player.solidArea.x + player.solidArea.width;
        int entityTopWorldY = player.solidArea.y;
        int entityBottomWorldY = player.solidArea.y + player.solidArea.height;

        //reduces scale of numbers to a maximum of 16
        double entityLeftCol = (double) entityLeftWorldX / GamePanel.tileSize;
        double entityRightCol = (double) entityRightWorldX / GamePanel.tileSize;
        double entityTopRow =  (double) entityTopWorldY / GamePanel.tileSize;
        double entityBottomRow = (double) entityBottomWorldY / GamePanel.tileSize;

        int tileNum1, tileNum2; //left and right game.world.tile's locations on the game.map respectively
        Tile tileSeek1, tileSeek2; //where to find this game.world.tile in tileArray

        int checkedEntityY; //the tiles for which we are checking collision
        int checkedEntityX;

        boolean collisionOn = false;
        boolean metBoundary = false;

        //collision conditions for each direction
        switch (player.direction){
            case "up":

                //the row the tiles being checked will be in
                checkedEntityY = ((int) Math.floor(entityTopRow - (double) player.speed / GamePanel.tileSize));
                if (checkedEntityY < 0){
                    checkedEntityY = 0;
                    if (currentMap.up != null){
                        currentLevel.goUp();
                        setDownPositions();
                    }else{
                        metBoundary = true;
                    }
                }

                //left game.world.tile
                tileNum1 = ((int) Math.floor(entityLeftCol));
                //right game.world.tile
                tileNum2 = ((int) Math.floor(entityRightCol));

                tileSeek1 = currentMap.tileArray[checkedEntityY][tileNum1];
                tileSeek2 = currentMap.tileArray[checkedEntityY][tileNum2];

                //if either game.world.tile has collision on, turns on game.entity collision
                //simplified if else statement
                collisionOn = tileSeek1.collision || tileSeek2.collision || metBoundary;
                break;
            case "down":
                checkedEntityY = ((int) Math.floor(entityBottomRow + (double) player.speed / GamePanel.tileSize));

                if (checkedEntityY > GamePanel.maxScreenRow - 1) {
                    checkedEntityY = GamePanel.maxScreenRow - 1;
                    if (currentMap.down != null){
                        currentLevel.goDown();
                        setUpPositions();
                    }else{
                        metBoundary = true;
                    }
                }

                //left game.world.tile
                tileNum1 = ((int) Math.floor(entityLeftCol));
                //right game.world.tile
                tileNum2 = ((int) Math.floor(entityRightCol));

                tileSeek1 = currentMap.tileArray[checkedEntityY][tileNum1];
                tileSeek2 = currentMap.tileArray[checkedEntityY][tileNum2];

                //if either game.world.tile has collision on, turns on game.entity collision
                //simplified if else statement
                collisionOn = tileSeek1.collision || tileSeek2.collision || metBoundary;
                break;
            case "left":
                //the column the tiles being checked are in
                checkedEntityX = ((int) Math.floor(entityLeftCol - (double) player.speed / GamePanel.tileSize));
                if (checkedEntityX < 0){
                    checkedEntityX = 0;
                    if (currentMap.left != null){
                        currentLevel.goLeft();
                        setRightPositions();
                    }else{
                        metBoundary = true;
                    }
                }

                //top game.world.tile
                tileNum1 = ((int) Math.floor(entityTopRow));
                //bottom game.world.tile
                tileNum2 = ((int) Math.floor(entityBottomRow));

                tileSeek1 = currentMap.tileArray[tileNum1][checkedEntityX];
                tileSeek2 = currentMap.tileArray[tileNum2][checkedEntityX];

                collisionOn = tileSeek1.collision || tileSeek2.collision || metBoundary;
                break;
            case "right":
                checkedEntityX = ((int) Math.floor(entityRightCol + (double) player.speed / GamePanel.tileSize));
                if (checkedEntityX > GamePanel.maxScreenCol - 1) {
                    checkedEntityX = GamePanel.maxScreenCol - 1;
                    if (currentMap.right != null){
                        currentLevel.goRight();
                        setLeftPositions();
                    }else{
                        metBoundary = true;
                    }
                }

                //top game.world.tile
                tileNum1 = ((int) Math.floor(entityTopRow));
                //bottom game.world.tile
                tileNum2 = ((int) Math.floor(entityBottomRow));

                tileSeek1 = currentMap.tileArray[tileNum1][checkedEntityX];
                tileSeek2 = currentMap.tileArray[tileNum2][checkedEntityX];

                collisionOn = (tileSeek1.collision) || (tileSeek2.collision) || metBoundary;
                break;
        }
        return collisionOn;
    }


//    public void checkForDoors(){
//        doorManager = world.doorM;
//        Rectangle nextPlayerPosition = getNextPlayerPosition();
//
//        for (Door door : doorManager.doors){
//            if (!door.isClosed && nextPlayerPosition.intersects(door.solidArea)){ // if Door is open and Player walks into Door, player is taken to the Door's Room
//                currentLevel.goTo(door.exitRoom);
//            }
//        }
//    }

    private Rectangle getNextPlayerPosition() {
        Rectangle nextPlayerPosition = new Rectangle(player.solidArea.x, player.solidArea.y, player.solidArea.width, player.solidArea.height);
        nextPlayerPosition = switch (player.direction) {
            case "up" ->
                    new Rectangle(player.solidArea.x, player.solidArea.y - player.speed, player.solidArea.width, player.solidArea.height);
            case "down" ->
                    new Rectangle(player.solidArea.x, player.solidArea.y + player.speed, player.solidArea.width, player.solidArea.height);
            case "left" ->
                    new Rectangle(player.solidArea.x - player.speed, player.solidArea.y, player.solidArea.width, player.solidArea.height);
            case "right" ->
                    new Rectangle(player.solidArea.x + player.speed, player.solidArea.y, player.solidArea.width, player.solidArea.height);
            default -> nextPlayerPosition;
        };
        return nextPlayerPosition;
    }

    //used to set player position when entering a map

    private void setLeftPositions(){
        player.worldX = currentLevel.currentMap.leftPlayerX;
        player.worldY = currentLevel.currentMap.leftPlayerY;
    }
    private void setRightPositions(){
        player.worldX = currentLevel.currentMap.rightPlayerX;
        player.worldY = currentLevel.currentMap.rightPlayerY;
    }
    private void setUpPositions(){
        player.worldX = currentLevel.currentMap.upPlayerX;
        player.worldY = currentLevel.currentMap.upPlayerY;
    }
    private void setDownPositions(){
        player.worldX = currentLevel.currentMap.downPlayerX;
        player.worldY = currentLevel.currentMap.downPlayerY;
    }

}
