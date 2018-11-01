package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder result = new StringBuilder("");
        if (a > b) {
            for (int i = a; i > b ; i--) {
                result.append(i + " ");
            }
        } else {
            if (a < b) {

                for (int i = a; i < b; i++) {
                    result.append(i + " ");
                }
            }
        }
        result.append(b);
        return result.toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}