package com.example.bottomnavigation.pojo_classes;

import java.util.ArrayList;

public class patients {
    String id;
    String slot_date;
    String name;
    String contact;
    String slot_index;
    String Age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlot_date() {
        return slot_date;
    }

    public void setSlot_date(String slot_date) {
        this.slot_date = slot_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSlot_index() {
        return slot_index;
    }

    public void setSlot_index(String slot_index) {
        this.slot_index = slot_index;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public patients(String id, String slot_date, String name, String contact, String slot_index, String age) {
        this.id = id;
        this.slot_date = slot_date;
        this.name = name;
        this.contact = contact;
        this.slot_index = slot_index;
        Age = age;
    }
}
