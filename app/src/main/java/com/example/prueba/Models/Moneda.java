package com.example.prueba.Models;


import androidx.annotation.NonNull;

public class Moneda {
    private String IDMONEDA;
    private String DESCRIPCION;

    public Moneda(String IDMONEDA, String DESCRIPCION) {
        this.IDMONEDA = IDMONEDA;
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getIDMONEDA() {
        return IDMONEDA;
    }

    public void setIDMONEDA(String IDMONEDA) {
        this.IDMONEDA = IDMONEDA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    @NonNull
    @Override
    public String toString() {
        return DESCRIPCION;
    }
}
