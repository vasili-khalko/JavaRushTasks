package com.javarush.task.task36.task3601;


import java.util.List;

/**
 * Created by Basillio on 30.10.2018.
 */
public class Model {
    private static Service service = new Service();
    public List<String> getStringDataList() {
        return service.getData();
    }
}
