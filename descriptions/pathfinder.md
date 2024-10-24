## PathFinder

Responsible for setting [Entities](entity.md) onto [Paths](path.md) and for guiding Entities along their given Paths.

|     Variable      |       Type        | Description                                                                                   |
|:-----------------:|:-----------------:|-----------------------------------------------------------------------------------------------|
|  trackedEntities  | ArrayList<Entity> | An ArrayList representing the current number of Entities that are currently following a Path. |

\
\
\

__setToPath(Entity entity, Path path)__

Sets the given entity's currentPath to the given Path and adds the entity to trackedEntities.
Also sets the Entity's intendedPosition to the first point of the given path to cause it to begin following that path point by point.

\
__updatePaths()__

Loops through trackedEntities. For each entity in trackedEntities, checks to see whether that entity has
reached its current IntendedPosition. If so, sets the entity's intendedPosition to its path's next point.

If that point is null, the entity is removed from trackedEntities. Entities with a null intendedPosition stand idly,
except for the [Player](player.md) who regains full movement control over their character.