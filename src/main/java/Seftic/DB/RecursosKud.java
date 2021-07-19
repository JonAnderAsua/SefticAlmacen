package Seftic.DB;

import Seftic.model.Registro;

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
        String request = "SELECT Registrar.serial, Registrar.nTrab, Registrar.aTrab, fEntrada, fSalida, cliente, desc, coment, tipo, cantMod FROM Registrar, Producto";
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            String serial = rs.getString("serial");
            String desc = rs.getString("desc");
            String coment = rs.getString("coment");
            String tipo = rs.getString("tipo");
            String fEntrada = rs.getString("fEntrada");
            String fSalida = rs.getString("fSalida");
            String cliente = rs.getString("cliente");
            String nTrab = rs.getString("nTrab");
            String aTrab = rs.getString("aTrab");
            int cantMod = rs.getInt("cantMod");
            String trab = nTrab + " " + aTrab;
            Registro r = new Registro(serial,desc,coment,tipo,fEntrada,fSalida,cliente,trab,cantMod);
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
        String request = "SELECT * from Trabajadores;";
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            String s = rs.getString("nombre");
            trabajadores.add(s);
        }
        return trabajadores;
    }
}
