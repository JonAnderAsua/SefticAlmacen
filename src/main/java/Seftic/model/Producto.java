package Seftic.model;

public class Producto {
    private String serial;
    private String desc;
    private int cantidad;
    private String tipo;

    public Producto(String serial, String desc,  int cantidad, String tipo) {
        this.serial = serial;
        this.desc = desc;
        this.cantidad = cantidad;
        this.tipo = tipo;
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


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
