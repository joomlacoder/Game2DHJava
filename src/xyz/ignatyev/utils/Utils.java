package xyz.ignatyev.utils;

import java.awt.image.BufferedImage;

/**
 * Created by Andrej on 30.01.2016.
 */
public class Utils {
    public static BufferedImage reSize (BufferedImage image, int width, int hieght){
        BufferedImage newImage = new BufferedImage(width, hieght, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0, 0, width, hieght, null);

        return newImage;
    }
}
