package com.t2012e.entity;

import com.t2012e.reflection.myannotation.Id;
import com.t2012e.reflection.myannotation.Table;

@Table(name = "sinhvien")
public class Student {
    @Id(autoIncrement = true)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
