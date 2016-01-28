package xyz.ignatyev.Game;

import xyz.ignatyev.Graphics.Sprite;
import xyz.ignatyev.Graphics.SpriteSheet;
import xyz.ignatyev.Graphics.TextureAtlas;
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
    public static final String      ATLAS_FILE_NAME     = "textur_atlas.png";

    private Input input;
    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private TextureAtlas atlas;
    private SpriteSheet sheet;
    private Sprite sprite;


    //test
    int x = 0, y = 0;
    float speed = 3;

    public Game(){
        running = false;
        Display.create(WIDTH, HIGHT, TITLE, CLEAR_COLOR, NUMBER_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        sheet = new SpriteSheet(atlas.cut(8 * 16, 5 * 16, 16 * 2, 16), 2, 16);//Дописать пиксели
        sprite = new Sprite(sheet, 1);
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
        if (input.getKey(KeyEvent.VK_UP))
            y -= speed;

        if (input.getKey(KeyEvent.VK_DOWN))
            y += speed;

        if (input.getKey(KeyEvent.VK_LEFT))
            x -= speed;

        if (input.getKey(KeyEvent.VK_RIGHT))
            x += speed;
    }

    private void render(){
        Display.clear();
        sprite.render(graphics, x, y);
        Display.swapBuffers();
    }

    private void cleanUp(){
        Display.destroy();
    }

    public void run() {

        int fps = 0;
        int upd = 0;
        int updl = 0;

        long count = 0;

        float delta = 0;

        long lastTime = Time.get();
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND) {
                Display.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }

    }
}
