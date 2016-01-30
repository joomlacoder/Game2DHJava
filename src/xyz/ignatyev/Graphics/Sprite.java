package xyz.ignatyev.graphics;

import xyz.ignatyev.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Andrej on 25.01.2016.
 */
public class Sprite {
    private SpriteSheet sheet;
    private float scale;
    private BufferedImage image;

    public Sprite(SpriteSheet sheet, float scale){
        this.sheet = sheet;
        this.scale = scale;
        this.image = sheet.getSprite(0);
        image = Utils.reSize(image, (int)(image.getWidth() * scale), (int)(image.getHeight() * scale));
    }

    public void render(Graphics g, float x, float y){
        g.drawImage(image, (int)x, (int)y, null);
    }
}
