package game;

import game.inventory.Inventory;
import game.thing.Thing;
import game.world.World;
import game.thing.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    World world;
    Player player;
    Inventory inventory;

    public static final int PLAYER = 1;
    public static final int INTERFACE = 2;

    private final Prompt[] prompts;

    public UI(World world){
        this.world = world;
        player = world.player;
        inventory = world.player.inventory;

        int currentPromptNumber = 2;
        prompts = new Prompt[currentPromptNumber];
        setPrompts();
    }

    public void update(){
        prompts[Prompt.INTERACT].isActive = checkPrompts(world.thingArray, 0); // checks all Things to determine if interactPrompt should be displayed
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



    public void displayTraversalPrompt(boolean condition) {prompts[1].isActive = condition;}

    public void drawPrompts(Graphics2D g2){
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

    public static class Prompt {
        BufferedImage promptImage;
        int type;
        public boolean isActive;

        public static final int INTERACT = 0;
        public static final int TRAVERSAL = 1;


        private Prompt(int type){
            this.type = type;
            setPromptImage();
        }

        private void setPromptImage(){ //sets prompt image based on objectNum
            try {
                switch (type){
                    case INTERACT:
                        promptImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\prompt\\keyboard-e.png"));
                        break;
                    case TRAVERSAL:
                        promptImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\prompt\\keyboard-wasd.png.png"));
                }
            } catch (IOException e) {
                System.err.println("IOException in setPromptImage");
            }
        }
    }
}
