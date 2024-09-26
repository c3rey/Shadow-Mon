## LockedDoor

A Door that can only be opened when paired with a Key with the same keyCode.

| Variable |  Type   | Description                                         |
|:--------:|:-------:|-----------------------------------------------------|
| isLocked | boolean | Whether the LockedDoor is locked or not.            |
| keyCode  |   int   | The code shared between the LockedDoor and its Key. |

\
\
\
__tryKey(Key key)__

Checks to see whether the LockeDDoor's keyCode matches the given Key's keyCode. Returns
true and calls unlock() if so, and false if not.

\
__unlock()__

Sets isLocked to false and plays a door unlocking sound effect.