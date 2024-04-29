
package es.albarregas.beans;

import java.io.Serializable;

/**
 *
 * @author Mario
 */
public class Libro implements Serializable {
    
    private String nombre;
    private double precio;
    private int cantidad;
    private double precioTotal;
    
    public Libro(){
    }

    public Libro(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.precioTotal = this.precio * this.cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal() {
        this.precioTotal = this.cantidad * this.precio;
    }
    
    
    
}
