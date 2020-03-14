package com.example.prueba.Models;

public class PostOrdenTrabajo {

    private String periodo;
    private String idsucursal;
    private String fechainicio;
    //private String idmoneda;
    private String idresponsable;
    private String serie;
    private String tcambio;
    private String idclieprov;
    private String idvehiculo;
    private String kilometraje;
    //private String idfpago;
    //private String idactividad;
    //private String garantia;
    //private String idpropietario;


    public PostOrdenTrabajo(String periodo, String idsucursal, String fechainicio, String idresponsable, String serie, String tcambio, String idclieprov, String idvehiculo, String kilometraje) {
        this.periodo = periodo;
        this.idsucursal = idsucursal;
        this.fechainicio = fechainicio;
        this.idresponsable = idresponsable;
        this.serie = serie;
        this.tcambio = tcambio;
        this.idclieprov = idclieprov;
        this.idvehiculo = idvehiculo;
        this.kilometraje = kilometraje;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(String idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getIdresponsable() {
        return idresponsable;
    }

    public void setIdresponsable(String idresponsable) {
        this.idresponsable = idresponsable;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTcambio() {
        return tcambio;
    }

    public void setTcambio(String tcambio) {
        this.tcambio = tcambio;
    }

    public String getIdclieprov() {
        return idclieprov;
    }

    public void setIdclieprov(String idclieprov) {
        this.idclieprov = idclieprov;
    }

    public String getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(String idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }
}
