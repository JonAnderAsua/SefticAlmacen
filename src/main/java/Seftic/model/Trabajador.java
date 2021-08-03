package Seftic.model;

import Seftic.DB.RecursosKud;
import java.sql.SQLException;

public class Trabajador {

    private String nombre;
    private String ultimoRegistro;
    private RecursosKud rk = RecursosKud.getInstance();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUltimoRegistro() {
        return ultimoRegistro;
    }

    public void setUltimoRegistro(String ultimoRegistro) {
        this.ultimoRegistro = ultimoRegistro;
    }

    public Trabajador(String nombre)  {
        this.nombre = nombre;
        try {
            this.ultimoRegistro = rk.getRegistro(nombre);
        } catch (SQLException throwables) {
            this.ultimoRegistro = "No hay registro";
        }
    }
}
