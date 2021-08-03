package Seftic.DB;

import Seftic.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RecursosKud {

    private static RecursosKud instancia = new RecursosKud();
    private DBController dbController = DBController.getController();

    public static RecursosKud getInstance(){return instancia;}

    private RecursosKud(){}

    public List<Registro> getRecursos() throws SQLException, ParseException {
        List<Registro> solucion = new ArrayList<>();
        String request = "SELECT nombreProducto,Registrar.serial, Registrar.nTrab, fEntrada, entrada, cliente, desc, coment, tipo, cantMod,nombreProducto FROM Registrar JOIN Producto ON nombreProducto = nombre";
        ResultSet rs = dbController.execSQL(request);

        while(rs.next()){
            Registro r = getRegistro(rs);
            solucion.add(r);
        }
        return solucion;
    }

    public void añadirRegistro(Registro r) throws SQLException {
        String request = "insert into Registrar values('"+r.getSerial()+"','"+r.getTrab()+"','"+r.getFecha()+"','"+r.getCliente()+"',"+r.getCantMod()+",'"+r.getComent()+"','"+r.getEntrada()+"','" + r.getNombreProducto() + "');";
        dbController.execSQL(request);
        actualizarRegistro(r.getNombreProducto(),r.getEntrada(),r.getCantMod());
    }

    private void actualizarRegistro(String nombreProducto, String entrada, int cantMod) throws SQLException {
        int cant = 0;
        String request = "SELECT cant FROM Producto WHERE nombre LIKE '" + nombreProducto + "';";
        ResultSet rs = dbController.execSQL(request);
        if(rs.next()){
            cant = rs.getInt("cant");
        }

        if(entrada.equals("Entrada")){
            cant = cant + cantMod;
        }
        else{
            cant = cant - cantMod;
        }

        request = "UPDATE Producto SET cant = " + cant + " WHERE nombre LIKE '" + nombreProducto + "';";
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
        String request = "SELECT cant FROM Producto WHERE Producto.nombre LIKE '"+text+"';";
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            cantidad = rs.getInt("cant");
            System.out.println("Cantidad: " + cantidad);
        }
        return cantidad >= parseInt;
    }

    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String request = "SELECT * FROM Producto;";

        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            Producto p = getProducto(rs);
            productos.add(p);
        }
        return productos;
    }

    public boolean comprobarNombre(String text) throws SQLException {
        String request = "SELECT serial FROM Producto where nombre LIKE '" + text + "';";
        ResultSet rs = dbController.execSQL(request);
        return rs.next();
    }

    public void añadirProducto(String nombre,String desc, int cant,String tipo) {
        String request = "INSERT INTO Producto VALUES("+cant+",'" + tipo + "','" + nombre + "','" + desc + "');";
        dbController.execSQL(request);
    }

    public List<Producto> getProductoPorStock(String value) throws SQLException {
        String request = "SELECT * FROM Producto WHERE cant > 0;";
        if(value.equals("No")){
            request = "SELECT * FROM Producto WHERE cant <= 0;";
        }
        return getListaProductos(request);
    }

    private List<Producto> getListaProductos(String request) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            Producto p = getProducto(rs);
            productos.add(p);
        }
        return productos;
    }

    public List<Producto> getProductoPorNombre(String text, String value) throws SQLException {
        String request = "SELECT * FROM Producto WHERE cant > 0 AND nombre LIKE '" +text+"';";
        if(value.equals("No")){
            request = "SELECT * FROM Producto WHERE cant <= 0 AND nombre LIKE '" +text+"';";
        }
        return getListaProductos(request);
    }

    public void borrarProducto(String serial) {
        String request = "DELETE FROM Producto WHERE nombre LIKE '" + serial + "';";
        dbController.execSQL(request);
    }

    public void borrarRegistro(String nombre, String serial, String trabajador, String check, String fEntrada, int cantidad) throws SQLException {
        int cant= 0;

        //Coger el stock que tiene en ese momento el producto
        String request = "SELECT cant FROM Producto WHERE serial LIKE '" + serial + "';";
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            cant = rs.getInt("cant");
            System.out.println("Cantidad = " + cant);

        }

        //Borrar el registro
        request = "DELETE FROM Registrar WHERE nombreProducto LIKE '" + nombre + "' AND nTrab LIKE '" + trabajador + "' AND fEntrada LIKE '" + fEntrada + "';";
        System.out.println(request);
        dbController.execSQL(request);

        if(check.equals("Salida")){ //Se ha devuelto al almacén
            cant = cant + cantidad;
            System.out.println("Salida: " + cant );
        }
        else{ //Se ha vuelto a sacar del almacén
            cant = cant - cantidad;
            System.out.println("Entrada: " + cant );

        }

        //Actualizar el stock del producto
        request = "UPDATE Producto SET cant=" + cant + " WHERE serial LIKE '" + serial + "';";
        dbController.execSQL(request);
    }

    public Producto getProductoUnico(String s) throws SQLException {
        Producto p = new Producto("",0,"","");
        String request = "SELECT * FROM Producto WHERE nombre LIKE '%" + s + "%';";
        ResultSet rs = dbController.execSQL(request);
        if(rs.next()){
            p = getProducto(rs);
        }
        return p;
    }

    public List<Registro> buscarPorCliente(String text) throws SQLException, ParseException {
        String request = "SELECT * FROM Registrar JOIN Producto ON Registrar.nombreProducto=Producto.nombre WHERE cliente LIKE '%" + text + "%';";
        return getListaRegistros(request);
    }

    public List<Registro> buscarPorTrabajador(String s) throws SQLException, ParseException {
        String request = "SELECT * FROM Registrar JOIN Producto ON Registrar.nombreProducto=Producto.nombre WHERE nTrab LIKE '%" + s + "%';";
        return getListaRegistros(request);
    }

    public List<Registro> buscarPorNombre(String s) throws SQLException, ParseException {
        String request = "SELECT * FROM Registrar JOIN Producto ON Registrar.nombreProducto = Producto.nombre WHERE Registrar.nombre LIKE '%" + s + "%';";
        return getListaRegistros(request);
    }

    private List<Registro> getListaRegistros(String request) throws SQLException, ParseException {
        List<Registro> lista = new ArrayList<>();
        ResultSet rs = dbController.execSQL(request);
        if(rs != null){
            while(rs.next()){
                Registro r = getRegistro(rs);
                lista.add(r);
            }
        }
        return lista;
    }

    public boolean existeProducto(String text) throws SQLException {
        String request = "SELECT * FROM Registrar WHERE nombreProducto LIKE '" + text + "'";
        ResultSet rs = dbController.execSQL(request);
        if(rs.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public String getRegistro(String nombre) throws SQLException {
        String sol = "No hay registro";
        System.out.println(nombre);
        String request = "SELECT fEntrada FROM Registrar WHERE nTrab LIKE '" + nombre + "';";
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){ //Pasa por todos y coge el último
            sol = rs.getString("fEntrada");
        }
        return sol;
    }

    public void borrarTrabajador(String nombre) {
        String request = "DELETE FROM Trabajador WHERE nombre LIKE '" + nombre + "';";
        dbController.execSQL(request);
    }

    public void añadirTrabajador(String text) {
        String request = "INSERT INTO Trabajador VALUES ('" + text + "');";
        dbController.execSQL(request);
    }

    public List<String> getAllProductosString() throws SQLException {
        List<Producto> listaProducto = getAllProductos();
        List<String> lista = new ArrayList<>();
        for(int i = 0 ; i < listaProducto.size() ; i++){
            lista.add(listaProducto.get(i).getNombre());
        }
        return lista;
    }

    public boolean existeElTrabajador(String text) throws SQLException {
        String request = "SELECT nombre FROM Trabajador WHERE nombre LIKE '" + text + "';";
        ResultSet rs = dbController.execSQL(request);
        return rs.next();
    }

    private Registro getRegistro(ResultSet rs) throws SQLException, ParseException {
        String nombreProducto = rs.getString("nombreProducto");
        String serial = rs.getString("serial");
        String desc = rs.getString("desc");
        String coment = rs.getString("coment");
        String tipo = rs.getString("tipo");
        String fEntrada = rs.getString("fEntrada");
        String entrada = rs.getString("entrada");
        String cliente = rs.getString("cliente");
        String nTrab = rs.getString("nTrab");
        int cantMod = rs.getInt("cantMod");
        return new Registro(nombreProducto,serial,desc,coment,tipo,fEntrada,entrada,cliente,nTrab,cantMod);
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        int cant = rs.getInt("cant");
        String tipo = rs.getString("tipo");
        String desc = rs.getString("desc");
        return new Producto(nombre,cant,tipo,desc);
    }
}