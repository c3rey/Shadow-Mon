package game.thing.entity;

import game.world.World;
import java.util.ArrayList;

public class EntityManager {
    Player player;
    public static ArrayList<Entity> entityArray;

    public EntityManager(){
        player = World.player;
        entityArray = new ArrayList<>();

        entityArray.add(player);
    }

    public void update(){
        for (Entity entity : entityArray){
            entity.update();
        }
    }
}
