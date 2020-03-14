package com.example.prueba.Models;

import androidx.annotation.NonNull;

public class TipoOrden {

    private String id;
    private String tipoOrden;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }


    @NonNull
    @Override
    public String toString() {
        return tipoOrden;
    }
}
