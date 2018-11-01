package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("135432"));         //expected output: []
    }

    private static Set<Integer> getRadix(String s) {
        Set<Integer> resultSet = new HashSet<>();
        for (int i = 2; i < 37; i++) {
            try {
                BigInteger number = new BigInteger(s);
                String avers = number.toString(i);
                String revers = new StringBuilder(avers).reverse().toString();
                if (avers.equals(revers)) {
                    resultSet.add(i);
                }
            } catch (NumberFormatException e){
            }
        }
        return resultSet;
    }
}