package com.example.prueba.Models;

public class Factura {

      private String  IDCLIEPROV;
      private String DIRECCION;
      private String RAZON_SOCIAL;

    public Factura(String IDCLIEPROV, String DIRECCION, String RAZON_SOCIAL) {
        this.IDCLIEPROV = IDCLIEPROV;
        this.DIRECCION = DIRECCION;
        this.RAZON_SOCIAL = RAZON_SOCIAL;
    }

    public String getIDCLIEPROV() {
        return IDCLIEPROV;
    }

    public void setIDCLIEPROV(String IDCLIEPROV) {
        this.IDCLIEPROV = IDCLIEPROV;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getRAZON_SOCIAL() {
        return RAZON_SOCIAL;
    }

    public void setRAZON_SOCIAL(String RAZON_SOCIAL) {
        this.RAZON_SOCIAL = RAZON_SOCIAL;
    }
}
