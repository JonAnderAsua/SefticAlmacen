package Seftic.model;

public class Producto {

    private String nombre;
    private int cantidad;
    private String tipo;
    private String desc;

    public Producto(String nombre,   int cantidad, String tipo, String desk) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.desc = desk;
    }


    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDesc(){return desc;}
}
