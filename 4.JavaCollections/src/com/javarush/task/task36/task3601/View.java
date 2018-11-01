package com.javarush.task.task36.task3601;

/**
 * Created by Basillio on 30.10.2018.
 */
public class View {
    private static Controller controller = new Controller();
    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
