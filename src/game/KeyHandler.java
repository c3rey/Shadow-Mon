package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, playerEPressed, interfaceEPressed, iPressed, backspacePressed, enterPressed;
    public int mode;

    public static final int PLAYER = 1;
    public static final int INTERFACE = 2;

    public KeyHandler(){
        mode = PLAYER;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (mode == PLAYER){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_E){
                playerEPressed = true;
            }
        }

        if (mode == INTERFACE){
            if(code == KeyEvent.VK_W){
                upPressed = true;
                downPressed = false; leftPressed = false; rightPressed = false;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
                upPressed = false; leftPressed = false; rightPressed = false;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
                upPressed = false; downPressed = false; rightPressed = false;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
                upPressed = false; downPressed = false; leftPressed = false;
            }
            if (code == KeyEvent.VK_BACK_SPACE){
                backspacePressed = true;
            }
            if(code == KeyEvent.VK_E){
                interfaceEPressed = true;
            }
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
        }


        if (code == KeyEvent.VK_I){
            iPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_E){
            playerEPressed = false;
            interfaceEPressed = false;
        }
        if (code == KeyEvent.VK_I){
            iPressed = false;
        }
        if (code == KeyEvent.VK_BACK_SPACE){
            backspacePressed = false;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }
}
