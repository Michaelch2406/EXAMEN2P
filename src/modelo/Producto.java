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
    private double precio;
    private int cantStock;
    private String tipo;
    private int idProveedor;

    public Producto() {
    }

    public Producto(int idProducto, String codigo, String nombre, String fechaVence, double precio, int cantStock, String tipo, int idProveedor) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaVence = fechaVence;
        this.precio = precio;
        this.cantStock = cantStock;
        this.tipo = tipo;
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(String fechaVence) {
        this.fechaVence = fechaVence;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantStock() {
        return cantStock;
    }

    public void setCantStock(int cantStock) {
        this.cantStock = cantStock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

}
