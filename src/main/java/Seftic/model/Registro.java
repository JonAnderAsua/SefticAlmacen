package Seftic.model;

import java.text.ParseException;

public class Registro {
    private String serial;
    private String desc;
    private String coment;
    private String tipo;
    private String fecha;
    private String entrada;
    private String cliente;
    private String trab;
    private int cantMod;

    public Registro(String serial, String desc, String coment, String tipo, String fEntrada, String entrada, String cliente, String trab, int cantModif) throws ParseException {
        this.serial = serial;
        this.desc = desc;
        this.coment = coment;
        this.tipo=tipo;
        this.fecha = fEntrada;
        this.entrada = entrada;
        this.cliente = cliente;
        this.trab = trab;
        this.cantMod = cantModif;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDesc() {
        return desc;
    }

    public String getComent() {
        return coment;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTrab() {
        return trab;
    }

    public int getCantMod() {
        return cantMod;
    }

    public String getFecha() {return this.fecha;}

    public String getEntrada() {return this.entrada;}

}
