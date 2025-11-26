package gestores;

import modelos.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;


public class GestorOrdenes {
    private Map<String, OrdenDeCompra> ordenes;

    public GestorOrdenes() {
        this.ordenes = new HashMap<>();
    }

    // Guardar una orden
    public void guardarOrden(OrdenDeCompra orden) {
        if (orden == null || orden.getId() == null) {
            throw new IllegalArgumentException("Orden inválida");
        }
        ordenes.put(orden.getId(), orden);
    }

    // Buscar una orden por ID
    public OrdenDeCompra buscarOrden(String id) {
        return ordenes.get(id);
    }

    // Eliminar una orden por ID
    public boolean eliminarOrden(String id) {
        return ordenes.remove(id) != null;
    }

    // Listar todas las órdenes
    public List<OrdenDeCompra> listarOrdenes() {
        return new ArrayList<>(ordenes.values());
    }

    // Listar órdenes de un cliente específico
    public List<OrdenDeCompra> listarOrdenesPorCliente(Cliente cliente) {
        List<OrdenDeCompra> resultado = new ArrayList<>();
        for (OrdenDeCompra o : ordenes.values()) {
            if (o.getCliente().equals(cliente)) {
                resultado.add(o);
            }
        }
        return resultado;
    }

    // Cantidad total de órdenes
    public int cantidadOrdenes() {
        return ordenes.size();
    }

    @Override
    public String toString() {
        return "GestorOrdenes{" +
                "totalOrdenes=" + ordenes.size() +
                '}';
    }
}
