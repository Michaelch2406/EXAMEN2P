/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ProductoControlador;
import controlador.ProveedorControlador;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author LENOVO
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);
        ProveedorControlador pc = new ProveedorControlador();
        ProductoControlador pcr = new ProductoControlador();

        while (true) {
            System.out.println("Elija la opción que necesite:\n"
                    + "1. Crear Proveedor\n"
                    + "2. Crear Producto\n"
                    + "3. Listar Proveedores\n"
                    + "4. Listar Productos por Proveedor\n"
                    + "0. Salir");
            int opcion = es.nextInt();
            es.nextLine(); // Consumir el salto de línea residual

            switch (opcion) {
                case 1 ->
                    crearProveedor(es, pc);
                case 2 ->
                    crearProducto(es, pcr);
                case 3 ->
                    pc.listarProveedores();
                case 4 ->
                    listarProductosPorProveedor(es, pcr);
                case 0 -> {
                    System.out.println("Gracias por usar el servicio!");
                    return;
                }
                default ->
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }
    }

    private static void crearProveedor(Scanner es, ProveedorControlador pc) {
        Proveedor proveedor = new Proveedor();

        System.out.println("Ingrese el nombre de la empresa: ");
        proveedor.setNombreEmpresa(es.nextLine());
        System.out.println("Ingrese su nombre: ");
        proveedor.setNombre(es.nextLine());
        System.out.println("Ingrese su apellido: ");
        proveedor.setApellido(es.nextLine());
        System.out.println("Ingrese el número de cédula: ");
        proveedor.setCedula(es.nextLine());
        System.out.println("Ingrese su teléfono: ");
        proveedor.setTelefono(es.nextLine());
        System.out.println("Ingrese su dirección: ");
        proveedor.setDireccion(es.nextLine());

        boolean exito = pc.insertarProveedor(proveedor);

        if (exito) {
            System.out.println("Proveedor insertado exitosamente.");
        } else {
            System.out.println("Error al insertar proveedor.");
        }
    }

    private static void crearProducto(Scanner es, ProductoControlador pcr) {
        Producto producto = new Producto();

        System.out.println("Ingrese el ID del proveedor: ");
        int idProveedor = es.nextInt();
        es.nextLine(); // Consumir el salto de línea residual
        System.out.println("Ingrese el nombre del producto: ");
        producto.setNombre(es.nextLine());
        System.out.println("Ingrese la fecha de vencimiento: ");
        producto.setFechaVence(es.nextLine());
        System.out.println("Ingrese el precio: ");
        producto.setPrecio(es.nextDouble());
        System.out.println("Ingrese la cantidad en stock: ");
        producto.setCantStock(es.nextInt());
        es.nextLine(); // Consumir el salto de línea residual
        System.out.println("Ingrese el tipo de producto: ");
        producto.setTipo(es.nextLine());

        boolean exito = pcr.crearProducto(producto, idProveedor);

        if (exito) {
            System.out.println("Producto insertado exitosamente.");
        } else {
            System.out.println("Error al insertar producto.");
        }
    }

    private static void listarProductosPorProveedor(Scanner es, ProductoControlador pcr) {
        System.out.println("Ingrese el codigo del proveedor:");
        int idProveedor = es.nextInt();
        es.nextLine(); // Consumir el salto de línea residual

        ArrayList<Producto> productos = (ArrayList<Producto>) pcr.listarProductosPorProveedor(idProveedor);

        if (productos.isEmpty()) {
            System.out.println("No hay productos para el proveedor con ID: " + idProveedor);
        } else {
            for (Producto producto : productos) {
                System.out.println("ID: " + producto.getIdProducto());
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Fecha de Vencimiento: " + producto.getFechaVence());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Cantidad en Stock: " + producto.getCantStock());
                System.out.println("Tipo: " + producto.getTipo());
                System.out.println("-------------");
            }
        }
    }

}
