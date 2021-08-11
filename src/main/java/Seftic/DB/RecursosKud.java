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
        ResultSet rs = dbController.execSQL("SELECT cant FROM Producto WHERE nombre LIKE '" + nombreProducto + "';");

        //Conseguir la cantidad del producto
        if(rs.next()){
            cant = rs.getInt("cant");
        }

        //Si el producto se mete al almacen
        if(entrada.equals("Entrada")){
            cant = cant + cantMod;
        }

        //Si el producto sale del almacen
        else{
            cant = cant - cantMod;
        }

        dbController.execSQL("UPDATE Producto SET cant = " + cant + " WHERE nombre LIKE '" + nombreProducto + "';");
    }

    public List<String> getTrabajadores() throws SQLException {
        List<String> trabajadores = new ArrayList<>();
        ResultSet rs = dbController.execSQL("SELECT * from Trabajador;");
        while(rs.next()){
            String s = rs.getString("nombre");
            trabajadores.add(s);
        }
        return trabajadores;
    }

    public Boolean comprobarStock(String text, int parseInt) throws SQLException {
        int cantidad = 0;
        ResultSet rs = dbController.execSQL("SELECT cant FROM Producto WHERE Producto.nombre LIKE '"+text+"';");
        while(rs.next()){
            cantidad = rs.getInt("cant");
            System.out.println("Cantidad: " + cantidad);
        }
        return cantidad >= parseInt;
    }

    public List<Producto> getAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        ResultSet rs = dbController.execSQL("SELECT * FROM Producto;");
        while(rs.next()){
            Producto p = getProducto(rs);
            productos.add(p);
        }
        return productos;
    }

    public boolean comprobarNombre(String text) throws SQLException {
        ResultSet rs = dbController.execSQL("SELECT nombre FROM Producto where nombre LIKE '" + text + "';");
        return rs.next();
    }

    public void añadirProducto(String nombre,String desc, int cant,String tipo) {
        dbController.execSQL("INSERT INTO Producto VALUES("+cant+",'" + tipo + "','" + nombre + "','" + desc + "');");
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

    public List<Producto> getProductoPorNombre(String text) throws SQLException {
        return getListaProductos("SELECT * FROM Producto WHERE nombre LIKE '%" +text+"%';");
    }

    public void borrarProducto(String serial) {
        String request = "DELETE FROM Producto WHERE nombre LIKE '" + serial + "';";
        dbController.execSQL(request);
    }

    public void borrarRegistro(String nombre, String serial, String trabajador, String check, String fEntrada, int cantidad) throws SQLException {
        int cant= 0;

        //Coger el stock que tiene en ese momento el producto
        String request = "SELECT cant FROM Producto WHERE nombre LIKE '" + nombre + "';";
        ResultSet rs = dbController.execSQL(request);
        while(rs.next()){
            cant = rs.getInt("cant");
        }

        //Borrar el registro
        dbController.execSQL("DELETE FROM Registrar WHERE nombreProducto LIKE '" + nombre + "' AND nTrab LIKE '" + trabajador + "' AND fEntrada LIKE '" + fEntrada + "';");

        if(check.equals("Salida")){ //Se ha devuelto al almacén
            cant = cant + cantidad;
        }
        else{ //Se ha vuelto a sacar del almacén
            cant = cant - cantidad;
        }

        //Actualizar el stock del producto
        dbController.execSQL("UPDATE Producto SET cant=" + cant + " WHERE nombre LIKE '" + nombre + "';");
    }

    public Producto getProductoUnico(String s) throws SQLException {
        Producto p = new Producto("",0,"","");
        ResultSet rs = dbController.execSQL("SELECT * FROM Producto WHERE nombre LIKE '%" + s + "%';");
        if(rs.next()){
            p = getProducto(rs);
        }
        return p;
    }

    public List<Registro> buscarPorCliente(String text) throws SQLException, ParseException {
        return getListaRegistros("SELECT * FROM Registrar JOIN Producto ON Registrar.nombreProducto=Producto.nombre WHERE cliente LIKE '%" + text + "%';");

    }

    public List<Registro> buscarPorTrabajador(String s) throws SQLException, ParseException {
        return getListaRegistros("SELECT * FROM Registrar JOIN Producto ON Registrar.nombreProducto=Producto.nombre WHERE nTrab LIKE '%" + s + "%';");
    }

    public List<Registro> buscarPorNombre(String s) throws SQLException, ParseException {
        return getListaRegistros("SELECT * FROM Registrar JOIN Producto ON Registrar.nombreProducto = Producto.nombre WHERE Registrar.nombreProducto LIKE '%" + s + "%';");
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
        ResultSet rs = dbController.execSQL("SELECT * FROM Registrar WHERE nombreProducto LIKE '" + text + "'");
        return rs.next();
    }

    public String getRegistro(String nombre) throws SQLException {
        String sol = "No hay registro";
        ResultSet rs = dbController.execSQL("SELECT fEntrada FROM Registrar WHERE nTrab LIKE '" + nombre + "';");
        while(rs.next()){ //Pasa por todos y coge el último
            sol = rs.getString("fEntrada");
        }
        return sol;
    }

    public void borrarTrabajador(String nombre) {
        dbController.execSQL("DELETE FROM Trabajador WHERE nombre LIKE '" + nombre + "';");
    }

    public void añadirTrabajador(String text) {
        dbController.execSQL("INSERT INTO Trabajador VALUES ('" + text + "');");
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
        ResultSet rs = dbController.execSQL("SELECT nombre FROM Trabajador WHERE nombre LIKE '" + text + "';");
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