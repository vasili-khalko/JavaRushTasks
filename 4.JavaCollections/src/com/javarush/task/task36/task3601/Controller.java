package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by Basillio on 30.10.2018.
 */
public class Controller {
    private static Model model = new Model();
    public List<String> onDataListShow() {
        return model.getStringDataList();
    }
}
