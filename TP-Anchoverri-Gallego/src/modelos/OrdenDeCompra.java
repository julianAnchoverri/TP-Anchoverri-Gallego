package modelos;

import java.util.HashSet;
import java.util.Set;

public class OrdenDeCompra {
    private String id;
    private Cliente cliente; /// O GUARDAR SOLO EL ID DEL CLIENTE PARA SER MAS LIVIANO CUANDO LO PASAS A JSON
    private Set<Producto> productos;
    private EstadoOrdenEnum estado;

    public OrdenDeCompra(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.productos = new HashSet<>();
        this.estado = EstadoOrdenEnum.PENDIENTE; // por defecto
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Set<Producto> getProductos() { return productos; }
    public void setProductos(Set<Producto> productos) { this.productos = productos; }

    public EstadoOrdenEnum getEstado() { return estado; }
    public void setEstado(EstadoOrdenEnum estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "OrdenDeCompra{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente.getNombre() + " " + cliente.getApellido() +
                ", productos=" + productos.size() +
                ", estado=" + estado +
                '}';
    }
}
