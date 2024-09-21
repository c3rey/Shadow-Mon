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

    public boolean checkTileCol(Rectangle nextPlayerPosition, int direction){
        currentLevel = World.level;
        currentMap = currentLevel.currentMap;

        boolean collisionOn = false;
        boolean metBoundary = false;

        int playerTopRow = (int) Math.floor( (double) nextPlayerPosition.y /GamePanel.tileSize); // the row that the top of the Player's solidarea will be in
        int playerBottomRow = (int) Math.floor( (double) (nextPlayerPosition.y + nextPlayerPosition.height) /GamePanel.tileSize); // the row that the bottom of the Player's solidArea will be in
        int playerLeftCol = (int) Math.floor( (double) nextPlayerPosition.x /GamePanel.tileSize); // the column that the left side of the Player's solidArea will be in
        int playerRightCol = (int) Math.floor( (double) (nextPlayerPosition.x + nextPlayerPosition.width) /GamePanel.tileSize); // the column that the right side of the Player's solidArea will be in

        Tile checkedTile1, checkedTile2, checkedTile3;

        switch (direction) {
            case Player.UP:
                if (playerTopRow < 0) {
                    playerTopRow = 0;
                    if (currentMap.up != null) {
                        currentLevel.goUp();
                    } else {
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerTopRow][playerLeftCol];
                checkedTile2 = currentMap.tileArray[playerTopRow][playerRightCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || metBoundary;
                break;

            case Player.UP_RIGHT:
                if (playerTopRow < 0) {
                    playerTopRow = 0;
                    if (currentMap.up != null) {
                        currentLevel.goUp();
                    } else {
                        metBoundary = true;
                    }
                } else if (playerRightCol > GamePanel.maxScreenCol - 1) {
                    playerRightCol = GamePanel.maxScreenCol - 1;
                    if (currentMap.right != null) {
                        currentLevel.goRight();
                    } else {
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerTopRow][playerLeftCol];
                checkedTile2 = currentMap.tileArray[playerTopRow][playerRightCol];
                checkedTile3 = currentMap.tileArray[playerBottomRow][playerRightCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || checkedTile3.collision || metBoundary;
                break;
            case Player.DOWN:
                if (playerBottomRow > GamePanel.maxScreenRow - 1){
                    playerBottomRow = GamePanel.maxScreenRow - 1;
                    if (currentMap.down != null){
                        currentLevel.goDown();
                    }else{
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerBottomRow][playerLeftCol];
                checkedTile2 = currentMap.tileArray[playerBottomRow][playerRightCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || metBoundary;
                break;

            case Player.DOWN_RIGHT:
                if (playerBottomRow > GamePanel.maxScreenRow - 1){
                    playerBottomRow = GamePanel.maxScreenRow - 1;
                    if (currentMap.down != null){
                        currentLevel.goDown();
                    }else{
                        metBoundary = true;
                    }
                }else if (playerRightCol > GamePanel.maxScreenCol - 1) {
                    playerRightCol = GamePanel.maxScreenCol -1;
                    if (currentMap.right != null) {
                        currentLevel.goRight();
                    }else{
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerBottomRow][playerLeftCol];
                checkedTile2 = currentMap.tileArray[playerBottomRow][playerRightCol];
                checkedTile3 = currentMap.tileArray[playerTopRow][playerRightCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || checkedTile3.collision || metBoundary;
                break;

            case Player.LEFT:
                if (playerLeftCol < 0) {
                    playerLeftCol = 0;
                    if (currentMap.left != null) {
                        currentLevel.goLeft();
                    } else {
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerTopRow][playerLeftCol];
                checkedTile2 = currentMap.tileArray[playerBottomRow][playerLeftCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || metBoundary;
                break;

            case Player.DOWN_LEFT:
                if (playerBottomRow > GamePanel.maxScreenRow - 1){
                    playerBottomRow = GamePanel.maxScreenRow - 1;
                    if (currentMap.down != null){
                        currentLevel.goDown();
                    }else{
                        metBoundary = true;
                    }
                }else if (playerLeftCol < 0) {
                    playerLeftCol = 0;
                    if (currentMap.left != null) {
                        currentLevel.goLeft();
                    }else{
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerBottomRow][playerRightCol];
                checkedTile2 = currentMap.tileArray[playerBottomRow][playerLeftCol];
                checkedTile3 = currentMap.tileArray[playerTopRow][playerLeftCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || checkedTile3.collision || metBoundary;
                break;

            case Player.RIGHT:
                if (playerRightCol > GamePanel.maxScreenCol - 1) {
                    playerRightCol = GamePanel.maxScreenCol - 1;
                    if (currentMap.right != null) {
                        currentLevel.goRight();
                    } else {
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerTopRow][playerRightCol];
                checkedTile2 = currentMap.tileArray[playerBottomRow][playerRightCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || metBoundary;
                break;

            case Player.UP_LEFT:
                if (playerTopRow < 0) {
                    playerTopRow = 0;
                    if (currentMap.up != null){
                        currentLevel.goUp();
                    }else{
                        metBoundary = true;
                    }
                }else if (playerLeftCol < 0) {
                    playerLeftCol = 0;
                    if (currentMap.left != null) {
                        currentLevel.goLeft();
                    }else{
                        metBoundary = true;
                    }
                }

                checkedTile1 = currentMap.tileArray[playerTopRow][playerRightCol];
                checkedTile2 = currentMap.tileArray[playerTopRow][playerLeftCol];
                checkedTile3 = currentMap.tileArray[playerBottomRow][playerLeftCol];

                collisionOn = checkedTile1.collision || checkedTile2.collision || checkedTile3.collision || metBoundary;
                break;
        }

        return !collisionOn;
    }



    public boolean checkForObjects(Rectangle nextPlayerPosition){ //checkObject(player, gameObject) for every gameObject in ObjectManager.objArray
        objectManager = World.objM;
        boolean collisionOn = false;

        for (GameObject object : ObjectManager.objArray){
            if (checkObject(nextPlayerPosition, object)) { //if any of the checkObject() calls returns true, collision is turned on
                collisionOn = true;
            }

            if (((RetrievableGameObject) object).isReplacing){
                collisionOn = false;
            }
        }
        return !collisionOn;
    }

    private boolean checkObject(Rectangle nextPlayerPosition, GameObject gameObject){ //checks collision for a single GameObject
        currentLevel = world.stream.level1;
        currentMap = currentLevel.currentMap;
        boolean collisionOn = false;

        if (currentMap == gameObject.map && gameObject.collisionOn) {
            collisionOn = nextPlayerPosition.intersects(gameObject.solidArea);
        }

        return collisionOn;
    }




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



}
