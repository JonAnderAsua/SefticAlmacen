package Seftic.model;

public class Producto {
    private String serial;
    private String desc;
    private String comentario;
    private int cantidad;
    private String tipo;

    public Producto(String serial, String desc, String comentario, int cantidad, String tipo) {
        this.serial = serial;
        this.desc = desc;
        this.comentario = comentario;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
