package xyz.ignatyev.game.level;

import xyz.ignatyev.graphics.SpriteSheet;
import xyz.ignatyev.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Andrej on 30.01.2016.
 */
public class Tile {
    private BufferedImage image;
    private TileType type;

    protected Tile(BufferedImage image, int scale, TileType type){//SpriteSheet sheet для анимации
        this.type = type;
        this.image = Utils.reSize(image, (int) image.getWidth()*scale, (int) image.getWidth()*scale);
    }

    protected void render(Graphics2D g, int x, int y){
        g.drawImage(image, x, y, null);
    }

    protected TileType type(){
        return type;
    }
}
