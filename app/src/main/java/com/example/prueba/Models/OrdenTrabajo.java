package com.example.prueba.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdenTrabajo {

    private String NUMERO;
    private String IDVEHICULO;
    private String RAZON_SOCIAL;
    private String FECHAINICIO;
    private String FECHAFINAL;

    public OrdenTrabajo(String NUMERO, String IDVEHICULO, String RAZON_SOCIAL, String FECHAINICIO, String FECHAFINAL) {
        this.NUMERO = NUMERO;
        this.IDVEHICULO = IDVEHICULO;
        this.RAZON_SOCIAL = RAZON_SOCIAL;
        this.FECHAINICIO = FECHAINICIO;
        this.FECHAFINAL = FECHAFINAL;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getIDVEHICULO() {
        return IDVEHICULO;
    }

    public void setIDVEHICULO(String IDVEHICULO) {
        this.IDVEHICULO = IDVEHICULO;
    }

    public String getRAZON_SOCIAL() {
        return RAZON_SOCIAL;
    }

    public void setRAZON_SOCIAL(String RAZON_SOCIAL) {
        this.RAZON_SOCIAL = RAZON_SOCIAL;
    }

    public String getFECHAINICIO() {
        return FECHAINICIO;
    }

    public void setFECHAINICIO(String FECHAINICIO) {
        this.FECHAINICIO = FECHAINICIO;
    }

    public String getFECHAFINAL() {
        return FECHAFINAL;
    }

    public void setFECHAFINAL(String FECHAFINAL) {
        this.FECHAFINAL = FECHAFINAL;
    }
}