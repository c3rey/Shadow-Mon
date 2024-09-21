package UI;


import UI.inventory.Inventory;
import game.thing.object.RetrievableGameObject;
import game.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Option {

    public BufferedImage image, highlightedImage;
    public boolean highlighted;

    Inventory.InventorySlot inventorySlot;
    ArrayList<Option> options;

    public int screenX, screenY;
    public int optionSlotWidth, optionSlotHeight;
    public int type;

    public static final int USE = 0;
    public static final int INSPECT = 1;

    public Option(Inventory.InventorySlot inventorySlot, int type) {
        this.type = type;
        this.inventorySlot = inventorySlot;
        options = inventorySlot.options;

        setOptionImage();
        setOption();
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        switch (type){
            case USE:
                if (!highlighted){
                    g2.drawImage(image, screenX, screenY, optionSlotWidth, optionSlotHeight, null);
                    g2.drawString("Use", screenX + 5, screenY + 5 + optionSlotHeight /2);
                }else{
                    g2.drawImage(highlightedImage, screenX, screenY, optionSlotWidth, optionSlotHeight, null);
                    g2.drawString("Use", screenX + 5, screenY + 5 + optionSlotHeight /2);
                }
                break;
            case INSPECT:
                if (!highlighted){
                    g2.drawImage(image, screenX, screenY, optionSlotWidth, optionSlotHeight, null);
                    g2.drawString("Inspect", screenX + 5, screenY + 5 + optionSlotHeight /2);
                }else{
                    g2.drawImage(highlightedImage, screenX, screenY, optionSlotWidth, optionSlotHeight, null);
                    g2.drawString("Inspect", screenX + 5, screenY + 5 + optionSlotHeight /2);
                }
                break;
        }
    }


    private void setOptionImage(){
        try{
            image = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\option\\optionslot.png"));
            highlightedImage = ImageIO.read(new File("C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\player\\inventory\\option\\highlightedoptionslot.png"));
        }catch (IOException e){
            System.err.println("IOException in Option");
        }
    }

    private void setOption(){
        optionSlotWidth = image.getWidth() * 2;
        optionSlotHeight = image.getHeight() * 2;

        screenX = inventorySlot.slotX + inventorySlot.slotWidth/2 +10;
        screenY = inventorySlot.slotY + inventorySlot.slotHeight/2 +5 + (options.size() * optionSlotHeight) + 5;
            /*
            Each instance of Option will call setOption(). Therefore, as Options are instantiated into InventorySlot.options,
            options.size() will increase, allowing it to be used to draw Options relative to where they are in the
            ArrayList.
             */
    }




    public void function(){
        RetrievableGameObject slotObject = inventorySlot.currentObject;
        switch (type){
            case USE:
                World.intrM.use(slotObject);
                break;

            case INSPECT:
                System.out.println(slotObject.description);
                break;
        }
    }
}