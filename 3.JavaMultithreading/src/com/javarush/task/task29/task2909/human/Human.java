package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    protected Size size;


    private List<Human> children = new ArrayList<>();

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }


    public class Size {
        public int height;
        public int weight;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    private BloodGroup bloodGroup;

    public void addChild(Human child) {
        children.add(child);
    }

    public void removeChild(Human child){
        children.remove(child);
    }



    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public String getPosition() {
        return "Человек";
    }


    public void printData() {
        System.out.println(this.getPosition() + ": " + this.name);
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;

    }

    public void live() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public int getId() {
        return id;
    }


    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }
}