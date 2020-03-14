package com.example.prueba.Models;

import androidx.annotation.NonNull;

public class Sucursales {
    private String IDSUCURSAL;
    private String DESCRIPCION;

    public Sucursales(String IDSUCURSAL, String DESCRIPCION) {
        this.IDSUCURSAL = IDSUCURSAL;
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getIDSUCURSAL() {
        return IDSUCURSAL;
    }

    public void setIDSUCURSAL(String IDSUCURSAL) {
        this.IDSUCURSAL = IDSUCURSAL;
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
