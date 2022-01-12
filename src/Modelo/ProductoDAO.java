package Modelo;

import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final String SQL_SELECT = "select * from producto";
    private static final String SQL_INSERT = "INSERT INTO producto (id_producto, nombre_producto, codigo_producto, descripcion_producto,dimensiones_producto,valor_producto,id_categoria) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto SET codigo_producto=?, nombre_producto = ?, descripcion_producto = ?,dimensiones_producto = ?,valor_producto = ?,id_categoria = ? WHERE id_producto = ?";
    private static final String SQL_DELETE = "DELETE from producto WHERE id_producto = ?";

   
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        String sqlSelect = "select * from producto";
        List<Producto> lista = new ArrayList<>();
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setProId(rs.getInt(1));
                producto.setProNombre(rs.getString(2));
                producto.setProCodigo(rs.getString(3));
                producto.setProDescripcion(rs.getString(4));
                producto.setProDimensiones(rs.getString(5));
                producto.setProValor(rs.getFloat(6));
                producto.setCatId(rs.getInt(7));
                
                lista.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("error al listar: " + e);
        }
        return lista;
    }// fin metodo listar

    public Producto obtenerProducto(Producto producto) {
        //Producto producto= null;
        try {
            con = conexion.Conectar();
            // permite consultar el producto correspondiente al ID ingresado como parametro 
            ps = con.prepareStatement("SELECT id_producto, nombre_producto,descripcion_producto, dimensiones_producto, valor_producto, id_categoria FROM producto where codigo_producto = ?");
            ps.setInt(1, producto.getProId());
            rs = ps.executeQuery();
            rs.next();            
            String codigoProducto = rs.getString(1);
            String nombreProducto = rs.getString(2);            
            String descripcionProducto = rs.getString(3);
            String dimensionesProducto = rs.getString(4);
            Float valorProducto = rs.getFloat(5);
            int idCategoria = rs.getInt(6);
            
            producto = new Producto();
            producto.setProNombre(nombreProducto);
            producto.setProCodigo(codigoProducto);
            producto.setProDescripcion(descripcionProducto);
            producto.setProDimensiones(dimensionesProducto);
            producto.setProValor(valorProducto);
            producto.setCatId(idCategoria);
            
        } catch (SQLException ex) {
            System.out.println("error al obtenerProducto: " + ex);
        }
        return producto;

    }

    public int agregarProducto(Producto producto) {

        int registros = 0;
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1, producto.getProId());
            ps.setString(2, producto.getProNombre());
            ps.setString(3, producto.getProCodigo());
            ps.setString(4, producto.getProDescripcion());
            ps.setString(5, producto.getProDimensiones());
            ps.setDouble(6, producto.getProValor());
            ps.setInt(7, producto.getCatId());            
            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error al agregar: " + ex);
        } finally {
            //conexion.close(stmt);
            //conexion.close(conn);

        }
        return registros;
    }

    public int actualizarProducto(Producto producto) {
         int registros = 0;

        try {
            con = conexion.Conectar();;
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, producto.getProCodigo());
            ps.setString(2, producto.getProNombre());                 
            ps.setString(3, producto.getProDescripcion());
            ps.setString(4, producto.getProDimensiones());
            ps.setDouble(5, producto.getProValor());
            ps.setInt(6, producto.getCatId()); 
            ps.setInt(7, producto.getProId()); 
            
            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error al actualizar: " + ex);
        } finally {
            //conexionBD.close(stmt);
            //conexionBD.close(conn);

        }
        return registros;
    }

    public int eliminarProducto(Producto producto) {
                
        int registros = 0;

        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(SQL_DELETE);            
            ps.setInt(1, producto.getProId()); 
            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error al eliminar: " + ex);
        } finally {
            //conexionBD.close(stmt);
            //conexionBD.close(conn);

        }
        return registros;
    }//fin metodo borrar

}// fin de la clase
