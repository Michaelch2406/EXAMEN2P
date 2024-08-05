/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author LENOVO
 */
public class ProductoControlador {
    private Producto producto;
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado;
    
    public boolean insertarProducto(Producto producto, int idProveedor) {
        try {
            String consultaSQL = "INSERT INTO producto (Pr_Codigo, Pr_Nombre, Pr_FechaVence, Pr_Precio, Pr_CantidadStock, Pr_Tipo, Pro_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, producto.getCodigo());
            ejecutar.setString(2, producto.getNombre());
            ejecutar.setString(4, producto.getFechaVence());
            ejecutar.setDouble(5, producto.getPrecio());
            ejecutar.setInt(6, producto.getCantStock());
            ejecutar.setString(7, producto.getTipo());
            ejecutar.setInt(8, idProveedor);

            int filasAfectadas = ejecutar.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e);
            return false;
        }
    }

    public List<Producto> listarProductosPorProveedor(int idProveedor) {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            String consultaSQL = "SELECT * FROM producto WHERE Pro_Id = ?";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, idProveedor);
            resultado = ejecutar.executeQuery();

            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(resultado.getInt("Pr_Id"));
                producto.setCodigo(resultado.getString("Pr_Codigo"));
                producto.setNombre(resultado.getString("Pr_Nombre"));
                producto.setFechaVence(resultado.getString("Pr_FechaVence"));
                producto.setPrecio(resultado.getDouble("Pr_Precio"));
                producto.setCantStock(resultado.getInt("Pr_CantidadStock"));
                producto.setTipo(resultado.getString("Pr_Tipo"));
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e);
        }
        return listaProductos;
    }
    
    public String generarCodigoProducto() {
        try {
            String consultaSQL = "SELECT Pr_Codigo FROM producto ORDER BY Pr_Id DESC LIMIT 1;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            if (resultado.next()) {
                String ultimoCodigo = resultado.getString("Pr_Codigo");
                int numeroActual = Integer.parseInt(ultimoCodigo.substring(1));
                int nuevoNumero = numeroActual + 1;
                return String.format("P%04d", nuevoNumero);
            } else {
                return "P0001";
            }
        } catch (SQLException e) {
            System.out.println("Error al generar el código de solicitud: " + e);
        }
        return null; // Devuelve null en caso de error
    }
}
