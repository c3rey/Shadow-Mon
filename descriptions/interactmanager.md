## InteractManager

This class controls interactions between different [Things](thing.md), such as between [Keys](key.md) and [LockedDoors](lockeddoor.md).

\
__use(RetrievableGameObject object)__

Carries out a method to use object depending on the type of [Usable](usable.md) 
[RetrievableGameObject](retrievablegameobject.md) it is.

\
__tryKey(Key key)__

Searches through DoorManager's doors array for a door that matches the keyCode of the 
Key passed as an argument.