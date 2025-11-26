package modelos;

import gestores.Carrito;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.JsonSerializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrdenDeCompra implements JsonSerializable {
    private String id;
    private Carrito carrito;
    private EstadoOrdenEnum estado;
    private LocalDateTime fecha;


    public OrdenDeCompra(String id, Carrito carrito) {
        this.id = id;
        this.carrito= carrito;
        this.estado = EstadoOrdenEnum.PENDIENTE;// por defecto
        this.fecha= LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Carrito getCarrito() { return carrito; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    public EstadoOrdenEnum getEstado() { return estado; }
    public void setEstado(EstadoOrdenEnum estado) { this.estado = estado; }

    public LocalDateTime getFecha() { return fecha; }

    // --- Métodos toJson y fromJson ---
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("estado", estado.toString());
        json.put("fecha",fecha);

        // Serializar carrito manualmente
        JSONObject carritoJson = new JSONObject();
        JSONArray productosArray = new JSONArray();
        for (Producto p : carrito.getProductos()) {
            productosArray.put(p.toJson()); // delega en Producto
        }
        carritoJson.put("productos", productosArray);

        // recalcular total antes de guardarlo
        carrito.calcularTotal();
        carritoJson.put("total", carrito.getTotal());

        json.put("carrito", carritoJson);

        return json;
    }

    public static OrdenDeCompra fromJson(JSONObject json) {
        JSONObject carritoJson = json.getJSONObject("carrito");
        JSONArray productosArray = carritoJson.getJSONArray("productos");

        Carrito carrito = new Carrito();
        for (int i = 0; i < productosArray.length(); i++) {
            JSONObject prodJson = productosArray.getJSONObject(i);
            carrito.agregarProducto(Producto.fromJson(prodJson));
        }
        carrito.calcularTotal();

        OrdenDeCompra orden = new OrdenDeCompra(json.getString("id"), carrito);
        orden.setEstado(EstadoOrdenEnum.valueOf(json.getString("estado")));

        return orden;
    }

    @Override
    public String toString() {
        return "//////////  OrdenDeCompra  //////////\n" +
                "Id: "+ id + "\n" +
                "Estado: " + estado + "\n"+
                "Fecha: " + fecha + "\n" +
                "///////////////////////////////////////";
    }
}
