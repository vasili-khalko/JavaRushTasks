package com.javarush.task.task29.task2909.human;


public class Teacher extends UniversityPerson {
    private int numberOfStudents;
    private boolean isProfessor;

    public Teacher(String name, int age, int numberOfStudents) {
        super(name, age);
        this.numberOfStudents = numberOfStudents;
    }

    public void live() {
        teach();
    }

    public void teach() {
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean professor) {
        isProfessor = professor;
    }


    @Override
    public String getPosition() {
        return "Преподаватель";
    }
}