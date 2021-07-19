package Utils;

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
    private Date fEntrada;
    private Date fSalida;
    private String cliente;
    private String trab;

    public Registro(String serial, String desc, String coment, String tipo, Date fEntrada, Date fSalida, String cliente, String nTrab, String aTrab) throws ParseException {
        this.serial = serial;
        this.desc = desc;
        this.coment = coment;
        if(tipo.toLowerCase(Locale.ROOT) != "pc" && tipo.toLowerCase(Locale.ROOT) != "video" && tipo.toLowerCase(Locale.ROOT) != "red" && tipo.toLowerCase(Locale.ROOT) != "otros" ){
            //Hay que gestionar el que de error
        }
        else{
            this.tipo = tipo;
        }

        //DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date fechaHoy = format.parse(timeStamp);
        if(fEntrada.before(fechaHoy)){
            this.fEntrada = fEntrada;
        }
        else{
            //Gestionar el error
        }
        if(fSalida.before(fechaHoy)){
            this.fSalida = fSalida;
        }
        else{
            //Gestionar el error
        }
        this.cliente = cliente;
        this.trab = nTrab + " " + aTrab; //Junta nombre y apellido en una misma variable
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

    public Date getfEntrada() {
        return fEntrada;
    }

    public void setfEntrada(Date fEntrada) {
        this.fEntrada = fEntrada;
    }

    public Date getfSalida() {
        return fSalida;
    }

    public void setfSalida(Date fSalida) {
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
