package modelos;

import org.json.JSONArray;
import org.json.JSONObject;
import utiles.Seleccionable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CategoriaProducto implements Seleccionable<String> {
    private String nombre;
    private HashSet<Producto> coleccionProductos= new HashSet<>();

    public CategoriaProducto(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashSet<Producto> getColeccionProductos() {
        return coleccionProductos;
    }

    public void setColeccionProductos(HashSet<Producto> coleccionProductos) {
        this.coleccionProductos = coleccionProductos;
    }

    // --- Métodos toJson y fromJson ---
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nombre", nombre);

        JSONArray productosArray = new JSONArray();
        for (Producto p : coleccionProductos) {
            productosArray.put(p.toJson()); // delega en Producto
        }
        json.put("productos", productosArray);

        return json;
    }

    public static CategoriaProducto fromJson(JSONObject json) {
        CategoriaProducto categoria = new CategoriaProducto(json.getString("nombre"));

        JSONArray productosArray = json.getJSONArray("productos");
        HashSet<Producto> productos = new HashSet<>();
        for (int i = 0; i < productosArray.length(); i++) {
            JSONObject prodJson = productosArray.getJSONObject(i);
            productos.add(Producto.fromJson(prodJson)); // delega en Producto
        }
        categoria.setColeccionProductos(productos);

        return categoria;
    }


    @Override
    public String getClave() {
        return getNombre();
    }

    @Override
    public String toString() {
        return "- " + nombre + '\n';
    }

    // Hascode y equals

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(!(o instanceof CategoriaProducto)) return false;
        CategoriaProducto cat= (CategoriaProducto) o;
        return nombre.equalsIgnoreCase(cat.getNombre());
    }
}
