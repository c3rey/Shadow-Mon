## KeyHandler

Used to handle player input from a keyboard. Designed so that the player can only move in one direction at a time.

On a key pressed event of either W, A, S, or D, the boolean variable representing that button 
will be set to true, while all 3 others will be set to false. This ensures that only one direction can be moved in at a time.
On a key released event, the aforementioned variable will be set to false.