package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {
    public static void main(String[] args) {
        int count = 3;
        moveRing('A', 'B', 'C', count);
    }

    public static void moveRing(char a, char b, char c, int count) {
        if (count==1){
            System.out.println(String.format("from %s to %s",a,b));
        }else {
            moveRing(a, c, b, count - 1);
            System.out.println(String.format("from %s to %s",a,b));
            moveRing(c,b,a,count-1);
        }
    }
}