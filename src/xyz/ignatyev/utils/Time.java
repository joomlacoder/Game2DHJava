package xyz.ignatyev.utils;

/**
 * C reated by Andrej on 23.01.2016.
 */
public class Time {
    public static final long SECOND = 1000000000;

    public static long get(){
        return System.nanoTime();
    }
}

