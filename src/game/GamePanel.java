package game;

import game.world.World;

import javax.swing.JPanel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    public static final int originalTileSize = 16;
    public static final int scale = 3;

    public static final int tileSize = originalTileSize * scale; //48 by 48
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public static final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public static final int FPS = 60;

    public static Font gameFont;

    KeyHandler keyH;
    Thread gameThread;
    World world = new World(this);

    public GamePanel() {
        keyH = World.keyH;
        setFont();

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

        double drawInterval = 1000000000.00 /FPS; //16,666,666.667 nanoseconds or 0.01667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            update();
            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; //because Thread.sleep() accepts millisecond arguments, we convert nanos to millis

                if (remainingTime < 0){ //in case updating and repainting takes more than the allotted time
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            }catch (InterruptedException e){
                System.err.println("InterruptedException in Gamepanel");
            }
        }

    }

    public void update(){

        world.update();

    }

    public void paintComponent (Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(gameFont);

        world.draw(g2);

        g2.dispose();
    }

    private void setFont(){
        try (FileInputStream inputStream = new FileInputStream(
                "C:\\Users\\Genny\\IdeaProjects\\ShadowMon\\res\\src\\font\\ui-font.ttf")){
            gameFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            gameFont = gameFont.deriveFont(6f);
        } catch (IOException | FontFormatException e) {
            System.err.println(e.getClass() + "in GamePanel");
        }
    }



}
