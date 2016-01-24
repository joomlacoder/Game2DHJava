package xyz.ignatyev.display;

import xyz.ignatyev.IO.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

/**
 * C reated by Andrej on 22.01.2016.
 */
public abstract class Display {
    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;
    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    private static BufferStrategy bufferStrategy;


    public static void create(){
        create(400, 400);
    }

    public static void create(int width, int height){
        create(width, height, "no Title");
    }

    public static void create(int width, int height, String title){
        create(width, height, title, 0xFF000000);
    }

    public static void create(int width, int height, String title, int _clearColor){
        create(width, height, title, _clearColor, 6);
    }

    public static void create(int width, int height, String title, int _clearColor, int numberBuffers){
        create(width, height, title, _clearColor, numberBuffers, false);
    }

    public static void create(int width, int height, String title, int _clearColor, int numberBuffers, boolean reSize){

        if (created)
            return;

        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setResizable(reSize);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearColor;

        content.createBufferStrategy(numberBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;

    }



    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public static void destroy() {

        if (!created)
            return;

        window.dispose();

    }

    public static void setTitle(String title) {

        window.setTitle(title);

    }

    public static void addInputListener(Input inputListener) {
        window.add(inputListener);
    }
}