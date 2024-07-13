package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, oPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();


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
        if(code == KeyEvent.VK_E){
            ePressed = true;
        }
        if (code == KeyEvent.VK_O){
            oPressed = true;
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
            ePressed = false;
        }
        if (code == KeyEvent.VK_O){
            oPressed = false;
        }
    }
}
