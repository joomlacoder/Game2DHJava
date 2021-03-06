package xyz.ignatyev.graphics;

import xyz.ignatyev.utils.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Andrej on 24.01.2016.
 */
public class TextureAtlas {
    BufferedImage image;

    public TextureAtlas(String imageName){
        image = ResourceLoader.loadImage(imageName);
    }

    public BufferedImage cut(int x, int y, int w, int h){
        return image.getSubimage(x, y, w, h);
    }
}
