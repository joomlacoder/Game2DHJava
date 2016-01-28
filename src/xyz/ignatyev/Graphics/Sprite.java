package xyz.ignatyev.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Andrej on 25.01.2016.
 */
public class Sprite {
    private SpriteSheet sheet;
    private float scale;

    public Sprite(SpriteSheet sheet, float scale){
        this.sheet = sheet;
        this.scale = scale;
    }

    public void render(Graphics g, float x, float y){
        BufferedImage image = sheet.getSprite(0);
        g.drawImage(image, (int)x, (int)y, (image.getWidth() * (int)scale), (image.getHeight() * (int)scale), null);
    }
}
