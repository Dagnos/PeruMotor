package com.example.prueba.Models;

public class NumeroOT {

    private String IDDOCUMENTO;
    private String SERIE;
    private String NUMERO;

    public NumeroOT(String IDDOCUMENTO, String SERIE, String NUMERO) {
        this.IDDOCUMENTO = IDDOCUMENTO;
        this.SERIE = SERIE;
        this.NUMERO = NUMERO;
    }

    public String getIDDOCUMENTO() {
        return IDDOCUMENTO;
    }

    public void setIDDOCUMENTO(String IDDOCUMENTO) {
        this.IDDOCUMENTO = IDDOCUMENTO;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }
}
