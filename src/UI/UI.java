package UI;

import game.GamePanel;
import UI.inventory.Inventory;
import game.thing.Thing;
import game.world.World;
import game.thing.entity.Player;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    Player player;
    Inventory inventory;

    private final Prompt[] prompts;

    public UI(){
        player = World.player;
        inventory = World.player.inventory;

        int currentPromptNumber = 2;
        prompts = new Prompt[currentPromptNumber];

        setPrompts();
    }

    public void update(){
        prompts[Prompt.INTERACT].isActive = checkPrompts(World.thingArray, 0);
            // checks all Things to determine if interactPrompt should be displayed
        inventory.update();
    }

    public void draw(Graphics2D g2){
        displayIntroPrompt();
        drawPrompts(g2);
        inventory.draw(g2);
    }




    private void setPrompts(){
        prompts[0] = new Prompt(Prompt.INTERACT);
        prompts[1] = new Prompt(Prompt.TRAVERSAL);
    }

    private void displayIntroPrompt() {displayTraversalPrompt(true);}

    private boolean checkPrompts(ArrayList<Thing> thingArray, int index){
        boolean displayPrompt = false;


        if (player.interactArea.intersects(thingArray.get(index).interactArea) && thingArray.get(index).getClass() != Player.class){ //if the Player's interactArea intersects that of the Thing at thingArray.get(index)...
            displayPrompt = true; //display the interact Prompt

        } else if (index < thingArray.size() - 1) { //if not, and we aren't at the last entry in thingArray...
            displayPrompt = checkPrompts(thingArray, index + 1); //check the next entry
        }

        return displayPrompt;
    }


    private void displayTraversalPrompt(boolean condition) {prompts[1].isActive = condition;}


    private void drawPrompts(Graphics2D g2){
        for(Prompt prompt : prompts){
            if (prompt.isActive){
                int promptSize = 30;
                switch (prompt.type){
                    case Prompt.INTERACT:
                        g2.drawImage(prompt.promptImage, (player.worldX + ((GamePanel.tileSize - promptSize) / 2)), (player.worldY + (GamePanel.tileSize + 8)), promptSize, promptSize, null);
                        break;
                    case Prompt.TRAVERSAL:
                        g2.drawImage(prompt.promptImage, -50, 360, 300, 420, null);
                }

            }
        }
    }

}
