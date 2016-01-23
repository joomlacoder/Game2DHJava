package xyz.ignatyev.Game;

import xyz.ignatyev.IO.Input;
import xyz.ignatyev.display.Display;
import xyz.ignatyev.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * C reated by Andrej on 23.01.2016.
 */
public class Game implements Runnable {
    public static int               WIDTH               = 800;
    public static int               HIGHT               = 600;
    public static String            TITLE               = "Tanks";
    public static int               CLEAR_COLOR         = 0xFF000000;
    public static int               NUMBER_BUFFERS      = 3;

    public static final float       UPDATE_RATE         = 60.0f;
    public static final float       UPDATE_INTERVAL     = Time.SECOND/UPDATE_RATE;
    public static final long        IDLE_TIME           = 1;


    private Input input;
    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;

    public Game(){
        running = false;
        Display.create(WIDTH, HIGHT, TITLE, CLEAR_COLOR, NUMBER_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;
        running = false;

        try {
            gameThread.join();
        }catch (InterruptedException e){
            System.err.println("Join gameThread: ");
            e.printStackTrace();
        }

        cleanUp();
    }

    private void update(){
        if(input.getKey(KeyEvent.VK_UP));//--
        if(input.getKey(KeyEvent.VK_DOWN));//++
        if(input.getKey(KeyEvent.VK_LEFT));//-
        if(input.getKey(KeyEvent.VK_RIGHT));//+

    }

    private void render(){
        Display.clear();
        graphics.setColor(Color.cyan);

        Display.swapBuffers();
    }

    private void cleanUp(){
        Display.destroy();
    }

    @Override
    public void run() {
        int fps = 0;
        int upd = 0;
        int updLups = 0;
        long count = 0;

        float delta = 0;
        long lastTime =  Time.get();

        while (running){
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;
            count +=elapsedTime;

            boolean render = false;
            delta += elapsedTime/UPDATE_INTERVAL;

            while (delta > 1){
                update();
                delta--;
                upd++;
                if(render){
                    updLups++;
                }else {
                    render = true;
                }
            }

            if(render) {
                render();
                fps++;
            }else {
                try {
                    Thread.sleep(IDLE_TIME);
                }catch (InterruptedException e){
                    System.err.println("Sleep Run: ");
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND){
                Display.setTitle(TITLE + " || FPS: " + fps + " | Update: " + upd + " | UpdateL: " + updLups);
                fps = 0;
                upd = 0;
                updLups = 0;
                count = 0;
            }
        }
    }
}
