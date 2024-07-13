package game;

import game.prompt.PromptManager;
import game.world.Level;
import game.world.LevelManager;
import game.object.ObjectManager;
import game.world.tile.TileManager;
import game.entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    public static final int originalTileSize = 16;
    public static final int scale = 3;

    public static final int tileSize = originalTileSize * scale; //48 by 48
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public static final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    KeyHandler keyH;
    Thread gameThread;
    World world = new World(this);
    UI UI = new UI(this);

    public GamePanel() {
        keyH = world.keyH;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000.00 /FPS; //16,666,666.667 or 0.01667 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){

                update();
                repaint();
                delta--;

            }

            if (timer >= 1000000000){
                timer = 0;
            }

        }
    }

    public void update(){

        world.update();
        UI.update();

    }

    public void paintComponent (Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        world.draw(g2);
        UI.draw(g2);

        g2.dispose();
    }



}
