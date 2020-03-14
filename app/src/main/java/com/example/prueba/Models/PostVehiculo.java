package com.example.prueba.Models;

public class PostVehiculo {

    private String IDVEHICULO;
    private String IDCLIEPROV;
    private String NROMOTOR;
    private String NROCHASIS;
    private String IDCOLOR;
    private String IDMARCA;
    private String IDMODELOVEHICULO;
    private String ANIO;
    private String VIN;
    private String aniomodelo;

    public PostVehiculo(String IDVEHICULO, String IDCLIEPROV, String NROMOTOR, String NROCHASIS, String IDCOLOR, String IDMARCA, String IDMODELOVEHICULO, String ANIO, String VIN, String aniomodelo) {
        this.IDVEHICULO = IDVEHICULO;
        this.IDCLIEPROV = IDCLIEPROV;
        this.NROMOTOR = NROMOTOR;
        this.NROCHASIS = NROCHASIS;
        this.IDCOLOR = IDCOLOR;
        this.IDMARCA = IDMARCA;
        this.IDMODELOVEHICULO = IDMODELOVEHICULO;
        this.ANIO = ANIO;
        this.VIN = VIN;
        this.aniomodelo = aniomodelo;
    }

    public String getIDVEHICULO() {
        return IDVEHICULO;
    }

    public void setIDVEHICULO(String IDVEHICULO) {
        this.IDVEHICULO = IDVEHICULO;
    }

    public String getIDCLIEPROV() {
        return IDCLIEPROV;
    }

    public void setIDCLIEPROV(String IDCLIEPROV) {
        this.IDCLIEPROV = IDCLIEPROV;
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

    public String getIDCOLOR() {
        return IDCOLOR;
    }

    public void setIDCOLOR(String IDCOLOR) {
        this.IDCOLOR = IDCOLOR;
    }

    public String getIDMARCA() {
        return IDMARCA;
    }

    public void setIDMARCA(String IDMARCA) {
        this.IDMARCA = IDMARCA;
    }

    public String getIDMODELOVEHICULO() {
        return IDMODELOVEHICULO;
    }

    public void setIDMODELOVEHICULO(String IDMODELOVEHICULO) {
        this.IDMODELOVEHICULO = IDMODELOVEHICULO;
    }

    public String getANIO() {
        return ANIO;
    }

    public void setANIO(String ANIO) {
        this.ANIO = ANIO;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getAniomodelo() {
        return aniomodelo;
    }

    public void setAniomodelo(String aniomodelo) {
        this.aniomodelo = aniomodelo;
    }
}
