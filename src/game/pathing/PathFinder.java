package game.pathing;

import game.thing.entity.Entity;

import java.util.ArrayList;

public class PathFinder {
    ArrayList<Entity> trackedEntities = new ArrayList<>();

    void setToPath(Entity entity, Path path){
        entity.currentPath = path;
        entity.intendedPosition = path.currentPoint;
        trackedEntities.add(entity);
    }

    public void updatePaths(){ //UPDATES PATH FOR ENTITIES FOLLOWING A ONE DIRECTIONAL PATH, NOT BACK AND FORTH
        for (int i = 0; i < trackedEntities.size(); i++){
            Entity entity = trackedEntities.get(i);
            if (entity.worldX == entity.intendedPosition.x && entity.worldY == entity.intendedPosition.y){  //if entity has reached its intended position...
                Path.GamePoint nextPoint = entity.currentPath.next();


                if (nextPoint == null){ //if this is the last point in the Path, set entity's currentPath to null and stop tracking the entity. (continuous paths can't return null)
                    trackedEntities.remove(entity);
                }

                entity.intendedPosition = nextPoint; //sets intendedPosition to the path's next point. if null, entity will end pathfinding.
            }
        }
    }
}

