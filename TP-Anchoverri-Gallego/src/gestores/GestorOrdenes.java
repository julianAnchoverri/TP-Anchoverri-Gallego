package gestores;

import modelos.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;


public class GestorOrdenes {
    private Map<String, ArrayList<OrdenDeCompra>> coleccionOrdenesTiendas;

    public GestorOrdenes() {
        this.coleccionOrdenesTiendas = new HashMap<>();
    }

    // Getters y Setters
    public Map<String, ArrayList<OrdenDeCompra>> getColeccionOrdenesTiendas() { return coleccionOrdenesTiendas; }
    public void setColeccionOrdenesTiendas(Map<String, ArrayList<OrdenDeCompra>> coleccionOrdenesTiendas) { this.coleccionOrdenesTiendas = coleccionOrdenesTiendas; }

    // Agregar orden a una tienda
    public void agregarOrdenA(Tienda tienda, OrdenDeCompra orden) {
        if (!coleccionOrdenesTiendas.containsKey(tienda.getClave())) {
            coleccionOrdenesTiendas.put(tienda.getClave(), new ArrayList<OrdenDeCompra>());
        }
        coleccionOrdenesTiendas.get(tienda.getClave()).add(orden);
    }

    // Obtener ordenes de una tienda
    public ArrayList<OrdenDeCompra> obtenerOrdenesDe(Tienda tienda) {
        if(coleccionOrdenesTiendas.isEmpty()) System.out.println("La tienda no tiene ordenes de compra");
        if (!coleccionOrdenesTiendas.containsKey(tienda.getClave())) {
            return new ArrayList<OrdenDeCompra>();
        }
        return coleccionOrdenesTiendas.get(tienda.getClave());
    }

    public OrdenDeCompra crearOrden(String id, Carrito carrito) {
        OrdenDeCompra o = new OrdenDeCompra(id, carrito);
        return o;
    }

    public Carrito crearCarrito(){
        return new Carrito();
    }

    // Cantidad total de órdenes
    public int cantidadOrdenes() {
        return coleccionOrdenesTiendas.size();
    }

    @Override
    public String toString() {
        return "GestorOrdenes{" +
                "totalOrdenes=" + coleccionOrdenesTiendas.size() +
                '}';
    }
}
