package com.example.sasohan.Model;

public class giukDateModel {
    long date;
    String name;

    public giukDateModel(long date, String name) {
        this.date = date;
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
