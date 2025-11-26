package gestores;

import modelos.Cliente;
import modelos.OrdenDeCompra;
import modelos.Producto;

import java.util.HashSet;
import java.util.Set;

// LA CLASE CARRITO SIRVE COMO INTERMEDIARIO ENTRE CLIENTE Y ORDEN DE COMPRA.
// GENERA UNA ORDEN DE COMPRA DE LOS PRODUCTOS QUE SE LE AGREGUEN.
public class Carrito {
    private Cliente cliente;          // o guardar solo el id si la orden tiene solo idCliente
    private Set<Producto> productos;   // productos seleccionados

    public Carrito(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new HashSet<>();
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

    public Cliente getCliente() {
        return cliente;
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    // Genera una orden de compra a partir del carrito
    public OrdenDeCompra generarOrdenDeCompra(String idOrden) {
        OrdenDeCompra orden = new OrdenDeCompra(idOrden, cliente);
        orden.setProductos(new HashSet<>(productos)); // copia los productos

        // Calcula el total y lo pone en la orden
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        orden.setTotal(total);

        return orden;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "Cliente='" + cliente.getNombreUsuario() + '\'' +
                ", productos=" + productos.size() +
                '}';
    }
}
