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
    private String fecha;
    private String entrada;
    private String cliente;
    private String trab;
    private int cantMod;

    public Registro(String serial, String desc, String coment, String tipo, String fEntrada, String entrada, String cliente, String trab, int cantModif) throws ParseException {
        this.serial = serial;
        this.desc = desc;
        this.coment = coment;
        if (tipo.toLowerCase(Locale.ROOT) != "pc" && tipo.toLowerCase(Locale.ROOT) != "video" && tipo.toLowerCase(Locale.ROOT) != "red" && tipo.toLowerCase(Locale.ROOT) != "otros") {
            //Hay que gestionar el que de error
        } else {
            this.tipo = tipo;
        }
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
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

    public int getCantMod() {
        return cantMod;
    }

    public void setCantMod(int cantMod) {
        this.cantMod = cantMod;
    }
}
