package com.example.prueba.Models;

import java.util.List;

public class Data {


    List<Usuario> data;


    public List<Usuario> getData() {
        return data;
    }

    public void setData(List<Usuario> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }
}
