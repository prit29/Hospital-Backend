package com.example.bottomnavigation.pojo_classes;

public class Medicine {

    String medicine;
    int a,b,c;

    public Medicine() {
    }

    public Medicine(String medicine, int a, int b, int c) {
        this.medicine = medicine;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
