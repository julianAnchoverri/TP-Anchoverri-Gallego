package gestores;

import modelos.Cliente;
import modelos.OrdenDeCompra;
import modelos.Producto;

import java.util.HashSet;
import java.util.Set;

// LA CLASE CARRITO SIRVE COMO INTERMEDIARIO ENTRE CLIENTE Y ORDEN DE COMPRA.

public class Carrito {
    private Set<Producto> productos; // productos seleccionados
    private double total;

    public Carrito() {
        this.productos = new HashSet<>();
        this.total=0;
    }

    // Métodos para manejar productos
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void quitarProducto(Producto p) {
        productos.remove(p);
    }

    public void vaciarCarrito() {
        productos.clear();
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void calcularTotal() {
        for (Producto p : productos) {
            total += p.getPrecio();
        }
    }

}
