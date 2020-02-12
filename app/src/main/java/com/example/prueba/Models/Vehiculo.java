package com.example.prueba.Models;

public class Vehiculo {

    private String IDVEHICULO;
    private String DSMARCA;
    private String DSMODELO;
    private String ANIO;
    private String DSCOLOR;
    private String PLACA;
    private String CODIGOTDP;
    private String NROMOTOR;
    private String NROCHASIS;

    public Vehiculo(String IDVEHICULO, String DSMARCA, String DSMODELO, String ANIO, String DSCOLOR, String PLACA, String CODIGOTDP, String NROMOTOR, String NROCHASIS) {
        this.IDVEHICULO = IDVEHICULO;
        this.DSMARCA = DSMARCA;
        this.DSMODELO = DSMODELO;
        this.ANIO = ANIO;
        this.DSCOLOR = DSCOLOR;
        this.PLACA = PLACA;
        this.CODIGOTDP = CODIGOTDP;
        this.NROMOTOR = NROMOTOR;
        this.NROCHASIS = NROCHASIS;
    }

    public String getIDVEHICULO() {
        return IDVEHICULO;
    }

    public void setIDVEHICULO(String IDVEHICULO) {
        this.IDVEHICULO = IDVEHICULO;
    }

    public String getDSMARCA() {
        return DSMARCA;
    }

    public void setDSMARCA(String DSMARCA) {
        this.DSMARCA = DSMARCA;
    }

    public String getDSMODELO() {
        return DSMODELO;
    }

    public void setDSMODELO(String DSMODELO) {
        this.DSMODELO = DSMODELO;
    }

    public String getANIO() {
        return ANIO;
    }

    public void setANIO(String ANIO) {
        this.ANIO = ANIO;
    }

    public String getDSCOLOR() {
        return DSCOLOR;
    }

    public void setDSCOLOR(String DSCOLOR) {
        this.DSCOLOR = DSCOLOR;
    }

    public String getPLACA() {
        return PLACA;
    }

    public void setPLACA(String PLACA) {
        this.PLACA = PLACA;
    }

    public String getCODIGOTDP() {
        return CODIGOTDP;
    }

    public void setCODIGOTDP(String CODIGOTDP) {
        this.CODIGOTDP = CODIGOTDP;
    }

    public String getNROMOTOR() {
        return NROMOTOR;
    }

    public void setNROMOTOR(String NROMOTOR) {
        this.NROMOTOR = NROMOTOR;
    }

    public String getNROCHASIS() {
        return NROCHASIS;
    }

    public void setNROCHASIS(String NROCHASIS) {
        this.NROCHASIS = NROCHASIS;
    }
}
