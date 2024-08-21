package game.world;


import game.thing.entity.Player;

public class Level {
    public Map currentMap;
    public Player player;

    public Level(Player player){
        this.player = player;
    }

    public void setStartingMap(Map startingMap){
        currentMap = startingMap;
        currentMap.level = this;
    }

    //ADD METHODS

    public void addUp(Map originMap, Map addedMap){
        if (originMap.up != null){
            Map oldMap;
            oldMap = originMap.up;

            addedMap.up = oldMap.up;
            addedMap.left = oldMap.left;
            addedMap.right = oldMap.right;
        }

        originMap.up = addedMap;
        addedMap.down = originMap;
        addedMap.level = this;

    }

    public void addDown(Map originMap, Map addedMap){
        if (originMap.down != null){
            Map oldMap;
            oldMap = originMap.down;

            addedMap.down = oldMap.down;
            addedMap.left = oldMap.left;
            addedMap.right = oldMap.right;
        }

        originMap.up = addedMap;
        addedMap.down = originMap;
        addedMap.level = this;

    }

    public void addLeft(Map originMap, Map addedMap){
        if (originMap.left != null){
            Map oldMap;
            oldMap = originMap.left;

            addedMap.up = oldMap.up;
            addedMap.down = oldMap.down;
            addedMap.left = oldMap.left;
        }

        originMap.left = addedMap;
        addedMap.right = originMap;
        addedMap.level = this;

    }

    public void addRight(Map originMap, Map addedMap){
        if (originMap.right != null){
            Map oldMap;
            oldMap = originMap.right;

            addedMap.up = oldMap.up;
            addedMap.down = oldMap.down;
            addedMap.right = oldMap.right;
        }

        originMap.right = addedMap;
        addedMap.left = originMap;
        addedMap.level = this;

    }



    //LEVEL TRAVERSAL

    public void goUp(){
        currentMap = currentMap.up;
        player.worldX = currentMap.southPlayerX;
        player.worldY = currentMap.southPlayerY;
    }
    public void goDown(){
        currentMap = currentMap.down;
        player.worldX = currentMap.northPlayerX;
        player.worldY = currentMap.northPlayerY;
    }
    public void goLeft(){
        currentMap = currentMap.left;
        player.worldX = currentMap.eastPlayerX;
        player.worldY = currentMap.eastPlayerY;
    }
    public void goRight(){
        currentMap = currentMap.right;
        player.worldX = currentMap.westPlayerX;
        player.worldY = currentMap.westPlayerY;
    }
    public void goTo(Map map){currentMap = map;}



}
