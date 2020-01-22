package com.example.prueba;

public class ModelClass {
    String OrdTrabajo;
    String Propietario;
    String Placa;
    String Fecha;
    String FechaEntrega;

    public ModelClass(String ordTrabajo, String propietario, String placa, String fecha, String fechaEntrega) {
        OrdTrabajo = ordTrabajo;
        Propietario = propietario;
        Placa = placa;
        Fecha = fecha;
        FechaEntrega = fechaEntrega;
    }

    public String getOrdTrabajo() {
        return OrdTrabajo;
    }

    public void setOrdTrabajo(String ordTrabajo) {
        OrdTrabajo = ordTrabajo;
    }

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String propietario) {
        Propietario = propietario;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }
}
