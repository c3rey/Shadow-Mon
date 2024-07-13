package game.prompt;

import game.GamePanel;
import game.World;
import game.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PromptManager {
    World world;
    Player player;

    private final Prompt[] prompts;

    public PromptManager(World world){
        this.world = world;
        player = world.player;

        int currentPromptNumber = 2;
        prompts = new Prompt[currentPromptNumber];
        setPrompts();
    }

    public void setPrompts(){
        prompts[0] = new Prompt(Prompt.INTERACT);
        prompts[1] = new Prompt(Prompt.TRAVERSAL);
    }

    public void displayIntroPrompt() {displayTraversalPrompt(true);}

    public void displayInteractPrompt(boolean condition) {prompts[0].isActive = condition;}

    public void displayTraversalPrompt(boolean condition) {prompts[1].isActive = condition;}

    public void displayPrompts(Graphics2D g2){
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
        public static final int INTERACT = 0;
        public static final int TRAVERSAL = 1;
        public boolean isActive;


        private Prompt(int type){
            this.type = type;
            setPromptImage();
        }

        private void setPromptImage(){ //sets prompt image based on type
            try {
                switch (type){
                    case INTERACT:
                        promptImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\prompt\\keyboard-e.png"));
                        break;
                    case TRAVERSAL:
                        promptImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\game\\prompt\\keyboard-wasd.png.png"));
                }
            } catch (IOException e) {
                System.err.println("IOException in setPromptImage");;
            }
        }
    }
}
