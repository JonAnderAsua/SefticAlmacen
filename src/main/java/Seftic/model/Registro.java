package Seftic.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Registro {
    private String serial;
    private String desc;
    private String coment;
    private String tipo;
    private String fEntrada;
    private String fSalida;
    private String cliente;
    private String trab;
    private int cantMod;

    public Registro(String serial, String desc, String coment, String tipo, String fEntrada, String fSalida, String cliente, String nTrab, String aTrab, int cantModif) throws ParseException {
        this.serial = serial;
        this.desc = desc;
        this.coment = coment;
        if(tipo.toLowerCase(Locale.ROOT) != "pc" && tipo.toLowerCase(Locale.ROOT) != "video" && tipo.toLowerCase(Locale.ROOT) != "red" && tipo.toLowerCase(Locale.ROOT) != "otros" ){
            //Hay que gestionar el que de error
        }
        else{
            this.tipo = tipo;
        }
        this.fEntrada = fEntrada;
        this.fSalida = fSalida;

        this.cliente = cliente;
        this.trab = nTrab + " " + aTrab; //Junta nombre y apellido en una misma variable
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

    public String getfEntrada() {
        return fEntrada;
    }

    public void setfEntrada(String fEntrada) {
        this.fEntrada = fEntrada;
    }

    public String getfSalida() {
        return fSalida;
    }

    public void setfSalida(String fSalida) {
        this.fSalida = fSalida;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTrab() {
        return trab;
    }

    public void setTrab(String trab) {
        this.trab = trab;
    }
}
