package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()  {
        int symbolType;
        char[] result = new char[8];
        List<Integer> symbolTypeArray = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            symbolType = ThreadLocalRandom.current().nextInt(0, 3);
            switch (symbolType){
                case 0: {
                    result[i] = getNumber();
                    symbolTypeArray.add(0);
                    break;
                }
                case 1: {
                    result[i] = getAtoZtoUpperCase();
                    symbolTypeArray.add(1);
                    break;
                }
                default: {
                    result[i] = getAtoZtoLowerCase();
                    symbolTypeArray.add(2);
                    break;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            if (!symbolTypeArray.contains(0)){
                result[i + 6] = getNumber();
                symbolTypeArray.add(0);
            } else {
                if (!symbolTypeArray.contains(1)){
                    result[i + 6] = getAtoZtoUpperCase();
                    symbolTypeArray.add(1);
                } else{
                    result[i + 6] = getAtoZtoLowerCase();
                    symbolTypeArray.add(2);
                }
            }

        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(new String(result).getBytes("UTF-8"));
        } catch (IOException e) {

        }
        return baos;
    }

    private static char getCharFromRange(int min, int max){
        return  (char) ThreadLocalRandom.current().nextInt(min, max);

    }

    private static char getNumber(){
        return getCharFromRange(48, 58);
    }

    private static char getAtoZtoUpperCase(){
        return getCharFromRange(65,91);
    }

    private static char getAtoZtoLowerCase(){
        return getCharFromRange(97, 123);
    }
}