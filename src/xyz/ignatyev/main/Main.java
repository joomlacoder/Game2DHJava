package xyz.ignatyev.main;

import xyz.ignatyev.Game.Game;
import xyz.ignatyev.display.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * C reated by Andrej on 22.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        Game tanks = new Game();
        tanks.start();
    }
}
