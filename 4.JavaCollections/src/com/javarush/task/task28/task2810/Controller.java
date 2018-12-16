package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Basillio on 03.12.2018.
 */
public class Controller {
    private Provider[] providers;


    public Controller(Provider ...providers) {
        this.providers = providers;
        if (providers.length == 0 || providers == null){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public void scan() {
        ArrayList<Vacancy> allVacancies = new ArrayList<>();
        try {
            for(Provider provider : providers){
                allVacancies.addAll(provider.getJavaVacancies(""));
            }
        }catch (NullPointerException e){

        }
        System.out.println(allVacancies.size());
    }
}
