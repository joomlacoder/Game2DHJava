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

/**
 *Client.class :
 * Консольное приложение, должно иметь октрытую функцию принимающую ввод с консоли
 * -set <text> отправляет текст на сервер
 * -get получает последний отправленный текст и выводит на консоль
 *
 * Server.class :
 * Должен хранить строковое поле и получать set и get запросы от клиента
 */