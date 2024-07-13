package game.entity;

import game.Thing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends Thing {
    public int screenX, screenY;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int spriteCount = 0;
    public int spriteNum = 0;

    public boolean collisionOn = false;
}
