package com.example.newspape.bean;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String cate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", cate='" + cate + '\'' +
                '}';
    }
}
