package game.world;


public class Level {
    public Map currentMap;

    public Level(){}

    public void setStartingMap(Map startingMap){
        currentMap = startingMap;
    }

    //ADD METHODS

    void addUp(Map originMap, Map addedMap){
        if (originMap.up != null){
            Map oldMap;
            oldMap = originMap.up;

            addedMap.up = oldMap.up;
            addedMap.left = oldMap.left;
            addedMap.right = oldMap.right;
        }

        originMap.up = addedMap;
        addedMap.down = originMap;

    }

    void addDown(Map originMap, Map addedMap){
        if (originMap.down != null){
            Map oldMap;
            oldMap = originMap.down;

            addedMap.down = oldMap.down;
            addedMap.left = oldMap.left;
            addedMap.right = oldMap.right;
        }

        originMap.up = addedMap;
        addedMap.down = originMap;

    }

    void addLeft(Map originMap, Map addedMap){
        if (originMap.left != null){
            Map oldMap;
            oldMap = originMap.left;

            addedMap.up = oldMap.up;
            addedMap.down = oldMap.down;
            addedMap.left = oldMap.left;
        }

        originMap.left = addedMap;
        addedMap.right = originMap;

    }

    void addRight(Map originMap, Map addedMap){
        if (originMap.right != null){
            Map oldMap;
            oldMap = originMap.right;

            addedMap.up = oldMap.up;
            addedMap.down = oldMap.down;
            addedMap.right = oldMap.right;
        }

        originMap.right = addedMap;
        addedMap.left = originMap;

    }



    //LEVEL TRAVERSAL

    public void goUp(){
        currentMap = currentMap.up;
    }
    public void goDown(){
        currentMap = currentMap.down;
    }
    public void goLeft(){
        currentMap = currentMap.left;
    }
    public void goRight(){
        currentMap = currentMap.right;
    }
    public void goTo(Map map){currentMap = map;}



}
