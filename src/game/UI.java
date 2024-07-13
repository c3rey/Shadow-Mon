package game;

import game.entity.Player;
import game.prompt.PromptManager;

import java.awt.*;

public class UI {
    GamePanel gp;
    PromptManager promptManager;
    Player.Inventory inventory;

    UI(GamePanel gp){
        this.gp = gp;
        promptManager = gp.world.promptM;
        inventory = gp.world.player.inventory;
    }

    void update(){
        inventory.update();
    }

    void draw(Graphics2D g2){
        promptManager.displayIntroPrompt();
        promptManager.displayPrompts(g2);
        inventory.draw(g2);
    }

}
