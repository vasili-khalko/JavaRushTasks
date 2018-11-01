package com.javarush.task.task32.task3201;


import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile file = new RandomAccessFile(args[0], "rw");
        int count = Integer.parseInt(args[1]);
        if (file.length() < count){
            count = (int) file.length();
        }
        for (int i = 0; i < args[2].length(); i++) {
            file.seek(count+i);
            file.write(args[2].getBytes()[i]);
        }
        file.close();

    }
}
