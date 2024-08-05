/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author LENOVO
 */
public class Producto {
    private int idProducto;
    private String codigo;
    private String nombre;
    private String fechaVence;

    public Producto() {
    }

    public Producto(int idProducto, String codigo, String nombre, String fechaVence) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaVence = fechaVence;
    }
    
}
