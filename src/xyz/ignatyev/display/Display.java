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

    public static void create(int width, int hight){
        create(width, hight, "no Title");
    }

    public static void create(int width, int hight, String title){
        create(width, hight, title, 0xFF000000);
    }

    public static void create(int width, int hight, String title, int _clearCalor){
        create(width, hight, title, clearColor, 6);
    }

    public static void create(int width, int hight, String title, int _clearCalor, int numberBuffers){
        create(width, hight, title, clearColor, numberBuffers, false);
    }

    public static void create(int width, int hight, String title, int _clearCalor, int numberBuffers, boolean reSize){

        if(created){
            return;
        }

        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();

        Dimension size = new Dimension(width, hight);
        content.setPreferredSize(size);
        content.setBackground(new Color(_clearCalor));

        window.setResizable(reSize);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearCalor;

        content.createBufferStrategy(numberBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;
    }

    public static void clear(){
        Arrays.fill(bufferData, clearColor);
    }

    public static void setTitle(String title){
        window.setTitle(title);
    }

    public static Graphics2D getGraphics(){
        return (Graphics2D)bufferGraphics;
    }

    public static void destroy(){
        if(!created){
            return;
        }
        window.dispose();
    }

    public static void addInputListener(Input imputListener){
        window.add(imputListener);
    }

    public static void swapBuffers(){
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }
}
