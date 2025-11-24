import java.util.HashSet;
import java.util.Set;

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

    // Genera una orden de compra a partir del carrito
    public OrdenDeCompra generarOrdenDeCompra(String idOrden) {
        OrdenDeCompra orden = new OrdenDeCompra(idOrden, cliente);
        orden.setProductos(new HashSet<>(productos)); // copia los productos
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
