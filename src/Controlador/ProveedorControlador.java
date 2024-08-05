/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class ProveedorControlador {
    private Proveedor proveedor;
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar(); //UPCASTING (Connection) 
    PreparedStatement ejecutar; //Ayuda a ejecutar la consulta que nosostros enviemos
    ResultSet resultado;
    
    public void crearProveedor(Proveedor p){
        try { //Exception que lanza la consulta
            //String estático -> dinámicos que son los gets
            String consultaSQL="INSERT INTO personas(Per_Nombre,Per_Apellido,Per_Cedula,Per_Clave,Per_Telefono,Per_Correo,Per_Direccion) VALUES ('"+p.getNombre()+"','"+p.getApellido()+"','"+p.getCedula()+"','"+p.getClave()+"','"+p.getTelefono()+"','"+p.getCorreo()+"','"+p.getDireccion()+"');";
                    //'"+p.getNombres()+"' PARA QUE EL USUARIO INGRESE DATOS
                    ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL); //UPCASTING tipo de objeto (PreparedStatement)
                    //DAR CLICK EN EL PLAY ES DECIR EJECUTAR LA CONSULTA
                    int res=ejecutar.executeUpdate(); 
                    if(res>0){
                        System.out.println("La persona ha sido creada con éxito");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }else{
                        System.out.println("Favor ingrese correctamente los datos solicitados: ");
                        ejecutar.close(); //Siempre cierro mi conlsuta
                    }
                    
        } catch (SQLException e) { //Captura el error el (e)
            System.out.println("Por favor, Comuníquese con el Administrador, gracias!!"+e);
        } //Captura el error y permite que la consola se siga ejecutando
        
 
    }
    public int buscarIdProveedor(String cedula){
            try {
                String consultaSQL="select Pro_Id from proveedor where Pro_Cedula='"+cedula+"';";
                ejecutar=(PreparedStatement)connection.prepareCall(consultaSQL);
                resultado=ejecutar.executeQuery();
                if(resultado.next()){
                    int idPersona=resultado.getInt("Per_Id"); //Lista Estática
                    return idPersona;
                }else{
                    System.out.println("Ingrese una cédula válida");
                }
            } catch (Exception e) {
                System.out.println("Por favor, comuníquese con el administrador"+e);
            }
            return 0;
    }
    
    public boolean insertarProveedor(Proveedor proveedor){
        try {
            String consultaSQL = "INSERT INTO proveedor (Pro_Codigo, Pro_NombreEmpresa, Pro_Nombre, Pro_Apellido, Pro_Cedula, Pro_Clave, Pro_Telefono, Pro_Direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, proveedor.getCodigo());
            ejecutar.setString(2, proveedor.getNombreEmpresa());
            ejecutar.setString(3, proveedor.getNombre());
            ejecutar.setString(4, proveedor.getApellido());
            ejecutar.setString(5, proveedor.getCedula());
            ejecutar.setString(6, proveedor.getClave());
            ejecutar.setString(7, proveedor.getTelefono());
            ejecutar.setString(8, proveedor.getDireccion());

            int filasAfectadas = ejecutar.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar proveedor: " + e);
            return false;
        }
    }

    public void listarProveedores(){
        List<Proveedor> listaProveedores = new ArrayList<>();
        try {
            String consultaSQL = "SELECT * FROM proveedor";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();

            while (resultado.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(resultado.getInt("Pro_Id"));
                proveedor.setCodigo(resultado.getString("Pro_Codigo"));
                proveedor.setNombreEmpresa(resultado.getString("Pro_NombreEmpresa"));
                proveedor.setNombre(resultado.getString("Pro_Nombre"));
                proveedor.setApellido(resultado.getString("Pro_Apellido"));
                proveedor.setCedula(resultado.getString("Pro_Cedula"));
                proveedor.setClave(resultado.getString("Pro_Clave"));
                proveedor.setTelefono(resultado.getString("Pro_Telefono"));
                proveedor.setDireccion(resultado.getString("Pro_Direccion"));
                listaProveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e);
        }
    }
    
}
