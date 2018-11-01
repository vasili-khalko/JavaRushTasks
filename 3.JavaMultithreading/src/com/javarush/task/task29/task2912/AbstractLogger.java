package com.javarush.task.task29.task2912;

/**
 * Created by Basillio on 19.02.2018.
 */
public abstract class AbstractLogger  implements Logger{
    int level;
    Logger next;

    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }

    @Override
    public void setNext(Logger next) {
        this.next = next;
    }
}
