package com.example.prueba.Models;

public class CambioFecha {

    private String T_VENTA;
    private String FECHA2;

    public CambioFecha(String t_VENTA, String FECHA2) {
        T_VENTA = t_VENTA;
        this.FECHA2 = FECHA2;
    }

    public String getT_VENTA() {
        return T_VENTA;
    }

    public void setT_VENTA(String t_VENTA) {
        T_VENTA = t_VENTA;
    }

    public String getFECHA2() {
        return FECHA2;
    }

    public void setFECHA2(String FECHA2) {
        this.FECHA2 = FECHA2;
    }
}
