package game.pathing;

import java.awt.*;

public class Path {
    public GamePoint currentPoint;

    int type;
    boolean reversed = false;

    public static final int ONE_WAY = 0;
    public static final int CONTINUOUS = 1;

    public Path(int type, GamePoint... points){
        this.type = type;

        GamePoint firstPoint = null;
        GamePoint highlightedPoint = null;
        for(GamePoint point : points){
            if (point.prev == null){
                firstPoint = point; //sets the first point to highlightedPoint
                highlightedPoint = point;
            } else {
                assert firstPoint != null;
                if (firstPoint.next == null){ //this must be the second point. If firstpoint was the only point the program would never reach this point
                    highlightedPoint = point;
                    firstPoint.next = highlightedPoint; //doubly links the first and second points
                    highlightedPoint.prev = firstPoint;
                }else{
                    point.prev = highlightedPoint; //links the current point and highlighted point (which is guaranteed to be the point behind the current point), then highlights the current point
                    highlightedPoint.next = point;
                    highlightedPoint = point;
                }
            }
        }

        currentPoint = firstPoint;
    }

    public GamePoint next(){
        if (type == ONE_WAY){
            currentPoint = currentPoint.next; //in a one-way path, the next point is simply the current point's next point
        }else if(type == CONTINUOUS){
            if (currentPoint.next == null){ //in a continuous path, once the entity reaches the end, it will follow the path in reverse
                reversed = true;
            }
            if (reversed){
                if(currentPoint.prev == null){ //if the entity reaches the end of a reversed path, it will go back to following the path normally
                    reversed = false;
                    currentPoint = currentPoint.next;
                    return currentPoint;
                }

                currentPoint = currentPoint.prev; //otherwise, the next currentPoint is currentPoint's previous point

            }else{
                currentPoint = currentPoint.next; //if continuous loop is currently not reversed, it behaves like a one-way path
            }
        }
        return currentPoint;
    }


    public static class GamePoint extends Point {
        public GamePoint next;
        public GamePoint prev;

        GamePoint(int x, int y){
            super(x, y);
        }
    }
}
