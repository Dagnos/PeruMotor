package com.example.prueba.Models;

import androidx.annotation.NonNull;

public class FormaPago {

    private String IDFPAGO;
    private String DESCRIPCION;


    public String getIDFPAGO() {
        return IDFPAGO;
    }

    public void setIDFPAGO(String IDFPAGO) {
        this.IDFPAGO = IDFPAGO;
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
