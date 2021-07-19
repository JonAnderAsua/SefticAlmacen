package model;

import java.util.Date;

public class Registro {
    private String serial;
    private String desc;
    private String coment;
    private String tipo;
    private Date fechaIn;
    private Date fechaOut;
    private String cliente;
    private String nTrab;
    private String aTrab;

    public Registro(String serial, String desc, String coment, String tipo, Date fechaIn, Date fechaOut, String cliente, String nTrab, String aTrab) {
        this.serial = serial;
        this.desc = desc;
        this.coment = coment;
        this.tipo = tipo;
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.cliente = cliente;
        this.nTrab = nTrab;
        this.aTrab = aTrab;
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

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaOut = fechaOut;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getnTrab() {
        return nTrab;
    }

    public void setnTrab(String nTrab) {
        this.nTrab = nTrab;
    }

    public String getaTrab() {
        return aTrab;
    }

    public void setaTrab(String aTrab) {
        this.aTrab = aTrab;
    }
}
