package com.arr.internationalcollege;

public class Course {
    private String name;
    private double fee;
    private int hours;

    public Course(String name, double fee, int hours) {
        this.name = name;
        this.fee = fee;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public double getFee() {
        return fee;
    }

    public int getHours() {
        return hours;
    }
}
