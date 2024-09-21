package UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Prompt {
    BufferedImage promptImage;
    int type;
    public boolean isActive;

    public static final int INTERACT = 0;
    public static final int TRAVERSAL = 1;


    Prompt(int type) {
        this.type = type;
        setPromptImage();
    }

    private void setPromptImage() { //sets prompt image based on objectNum
        try {
            switch (type) {
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
