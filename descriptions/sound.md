## Sound

Controls all sounds in the game.

|  Variable   | Type | Description                                              |
|:-----------:|:----:|----------------------------------------------------------|
| walkingClip | Clip | The Clip played when the [Player](player.md) is walking. |

\
\
\
__update()__

Plays and resets walkingClip depending on whether the Player is walking or not.

\
__playObjectRetrieved()__

Plays the sound effect for retrieving a [RetrievableGameObject](retrievablegameobject.md).

\
__playDoorOpening()__

Plays the sound effect for opening a [Door](door.md).

\
__playDoorUnlock()__

Plays the sound effect for unlocking a [LockedDoor](door.md).

\
__playDoorLocked()__

Plays the sound effect for attempting to open a locked LockedDoor.

\
__getWalkingClip()__

Returns the clip played while the Player is walking.

\
__getClip(String pathname)__

Uses the pathname argument to open a new Clip using a .wav file.
