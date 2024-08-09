package game.thing.entity;

import game.thing.Thing;

import java.awt.image.BufferedImage;

public class Entity extends Thing {
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int updateCount = 0;
    public int spriteCount = 0;
}
