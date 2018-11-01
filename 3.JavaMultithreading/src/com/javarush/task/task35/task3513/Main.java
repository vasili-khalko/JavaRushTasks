package com.javarush.task.task35.task3513;

import javax.swing.*;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

/**
 * Created by Basillio on 14.03.2018.
 */
public class Main {
    public static void main(String[] args){
        Model model = new Model();
        Controller controller = new Controller(model);
        JFrame game = new JFrame();
        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(450, 500);
        game.setResizable(false);
        game.add(controller.getView());
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    }


}
