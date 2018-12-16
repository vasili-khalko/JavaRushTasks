package com.javarush.task.task28.task2810;


import com.javarush.task.task28.task2810.model.Provider;

/**
 * Created by Basillio on 01.12.2018.
 */
public class Aggregator {
    public static void main(String[] args){
        Provider provider = new Provider(null);
        Controller controller = new Controller(provider);
        controller.scan();


    }
}
