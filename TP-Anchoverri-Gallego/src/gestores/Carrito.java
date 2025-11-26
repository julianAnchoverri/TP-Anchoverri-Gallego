package gestores;

import modelos.Cliente;
import modelos.OrdenDeCompra;
import modelos.Producto;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.JsonSerializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LA CLASE CARRITO SIRVE COMO INTERMEDIARIO ENTRE CLIENTE Y ORDEN DE COMPRA.

public class Carrito implements JsonSerializable {
    private List<Producto> productos; // productos seleccionados
    private double total;

    public Carrito() {
        this.productos = new ArrayList<>();
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

    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {}

    public void calcularTotal() {
        for (Producto p : productos) {
            total += p.getPrecio();
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        // Serializar productos
        JSONArray productosArray = new JSONArray();
        for (Producto p : productos) {
            productosArray.put(p.toJson()); // cada Producto sabe serializarse
        }
        json.put("productos", productosArray);

        // Serializar total
        json.put("total", total);

        return json;
    }


}
