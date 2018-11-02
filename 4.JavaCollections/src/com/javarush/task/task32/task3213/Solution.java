package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {

        StringBuilder result = new StringBuilder("");
        try {
        int ch;
        while((ch = reader.read()) >= 0) {
            int charShift = ch + key;
            result.append((char) charShift);
        }}
        catch (Exception e){

        }
        return result.toString();
    }
}
