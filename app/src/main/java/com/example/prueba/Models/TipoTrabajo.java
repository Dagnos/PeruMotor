package com.example.prueba.Models;

import androidx.annotation.NonNull;

public class TipoTrabajo {
    private String IDACTIVIDAD;
    private String DESCRIPCION;

    public TipoTrabajo(String IDACTIVIDAD, String DESCRIPCION) {
        this.IDACTIVIDAD = IDACTIVIDAD;
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getIDACTIVIDAD() {
        return IDACTIVIDAD;
    }

    public void setIDACTIVIDAD(String IDACTIVIDAD) {
        this.IDACTIVIDAD = IDACTIVIDAD;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    @Override
    public String toString() {
        return DESCRIPCION;
    }
}
