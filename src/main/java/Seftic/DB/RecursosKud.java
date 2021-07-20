package Seftic.DB;

import Seftic.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecursosKud {

    private static RecursosKud instancia = new RecursosKud();
    private DBController dbController = DBController.getController();

    public static RecursosKud getInstance(){return instancia;}

    private RecursosKud(){}

    public List<Registro> getRecursos() throws SQLException, ParseException {
        List<Registro> solucion = new ArrayList<>();
        String request = "SELECT Registrar.serial, Registrar.nTrab, fEntrada, fSalida, cliente, desc, coment, tipo, cantMod FROM Registrar, Producto";
        ResultSet rs = dbController.execSQL(request);
        if(rs == null){
            System.exit(0);
        }
        while(rs.next()){
            String serial = rs.getString("serial");
            String desc = rs.getString("desc");
            String coment = rs.getString("coment");
            String tipo = rs.getString("tipo");
            String fEntrada = rs.getString("fEntrada");
            String fSalida = rs.getString("fSalida");
            String cliente = rs.getString("cliente");
            String nTrab = rs.getString("nTrab");
            int cantMod = rs.getInt("cantMod");
            Registro r = new Registro(serial,desc,coment,tipo,fEntrada,fSalida,cliente,nTrab,cantMod);
            solucion.add(r);
        }
        return solucion;
    }

    public void añadirRegistro(Registro r) {
        String request = "insert into Registrar values('"+r.getSerial()+"','"+r.getTrab()+"','"+r.getfEntrada()+"','"+r.getfSalida()+"','"+r.getCliente()+"','"+r.getCantMod()+"');";
        dbController.execSQL(request);
    }

    public List<String> getTrabajadores() throws SQLException {
        List<String> trabajadores = new ArrayList<>();
        String request = "SELECT * from Trabajador;";
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            String s = rs.getString("nombre");
            trabajadores.add(s);
        }
        return trabajadores;
    }

    public Boolean comprobarStock(String text, int parseInt) throws SQLException {
        int cantidad = 0;

        String request = "SELECT cant FROM Producto WHERE Producto.serial LIKE '"+text;
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            cantidad = rs.getInt("cant");
        }
        return cantidad >= parseInt;
    }

    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String request = "SELECT * FROM Producto;";

        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            String serial = rs.getString("serial");
            String desc = rs.getString("desc");
            String coment = rs.getString("coment");
            int cant = rs.getInt("cant");
            String tipo = rs.getString("tipo");
            Producto p = new Producto(serial,desc,coment,cant,tipo);
            productos.add(p);
        }
        return productos;
    }
}
