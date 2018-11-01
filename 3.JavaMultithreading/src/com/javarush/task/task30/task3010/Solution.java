package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try {
            String result = "incorrect";
            for (int i = 2; i < 37; i++) {
                try {
                    BigInteger number = new BigInteger(args[0], i);
                    result = String.valueOf(i);
                    break;
                } catch (NumberFormatException e) {}
            }
            System.out.println(result);
        }catch (Exception e){}
    }
}