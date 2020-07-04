package com.example.dbdemo;

public class Echarts {
    private Integer value;
    private String name;

    public Echarts(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

    public Echarts() {}

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
