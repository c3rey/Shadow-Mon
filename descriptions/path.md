## Path

A series of [GamePoints](gamepoint.md) that an Entity can travel between. As of right now, 
Paths and Entities should have a 1:1 relationship (Multiple Entities shouldn't be assigned to one path, 
and en Entity cannot be assigned to more than one path at a time)

|   Variable   |   Type    | Description                                                                                                                                                       |
|:------------:|:---------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| currentPoint | GamePoint | The GamePoint that the Entity following this Path is approaching.                                                                                                 |
|     type     |    int    | An int representing the type of Path this is; either represents a One-way Path or a Continuous Path                                                               |
|   reversed   |  boolean  | A boolean representing whether the Path is reversed or not; A path can only be reversed if it is continuous, and only when its Entity reaches the last GamePoint. |

__next()__

Returns the current GamePoint's next point. In a one-way path, this will always be currentPoint.next. 
In a continuous path, this will return the currentPoint's prev value (if reversed), and will set reversed to true if 
it's called at the end of the path.
