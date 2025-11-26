package modelos;

import gestores.Carrito;

import java.util.HashSet;
import java.util.Set;

public class OrdenDeCompra {
    private String id;
    private Carrito carrito;
    private EstadoOrdenEnum estado;
    private double total;

    public OrdenDeCompra(String id, Carrito carrito) {
        this.id = id;
        this.carrito= carrito;
        this.estado = EstadoOrdenEnum.PENDIENTE;// por defecto
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Carrito getCarrito() { return carrito; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    public EstadoOrdenEnum getEstado() { return estado; }
    public void setEstado(EstadoOrdenEnum estado) { this.estado = estado; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "OrdenDeCompra{" +
                "id='" + id + '\'' +
                ", productos=" + productos.size() +
                ", estado=" + estado +
                '}';
    }
}
