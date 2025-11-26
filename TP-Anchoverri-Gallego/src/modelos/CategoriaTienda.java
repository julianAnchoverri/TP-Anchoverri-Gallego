package modelos;

import org.json.JSONArray;
import org.json.JSONObject;
import utiles.JsonSerializable;
import utiles.Seleccionable;

import java.util.HashSet;
import java.util.Objects;

public class CategoriaTienda implements Seleccionable<String>, JsonSerializable {
    private String nombre;
    private HashSet<Tienda> coleccionTiendas= new HashSet<>();

    public CategoriaTienda(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashSet<Tienda> getColeccionTiendas() {
        return coleccionTiendas;
    }
    public void setColeccionTiendas(HashSet<Tienda> coleccionTiendas) {
        this.coleccionTiendas = coleccionTiendas;
    }

    @Override
    public String getClave() {
        return getNombre();
    }

    @Override
    public String toString() {
        return "- " + nombre + '\n';
    }

    // --- Métodos toJson y fromJson ---
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nombre", nombre);

        JSONArray tiendasArray = new JSONArray();
        for (Tienda t : coleccionTiendas) {
            tiendasArray.put(t.toJson()); // delega en Tienda
        }
        json.put("tiendas", tiendasArray);

        return json;
    }

    public static CategoriaTienda fromJson(JSONObject json) {
        CategoriaTienda categoria = new CategoriaTienda(json.getString("nombre"));

        JSONArray tiendasArray = json.getJSONArray("tiendas");
        HashSet<Tienda> tiendas = new HashSet<>();
        for (int i = 0; i < tiendasArray.length(); i++) {
            JSONObject tiendaJson = tiendasArray.getJSONObject(i);
            tiendas.add(Tienda.fromJson(tiendaJson)); // delega en Tienda
        }
        categoria.setColeccionTiendas(tiendas);

        return categoria;
    }


    // Hascode y equals

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(!(o instanceof CategoriaTienda)) return false;
        CategoriaTienda cat= (CategoriaTienda) o;
        return nombre.equalsIgnoreCase(cat.getNombre());
    }
}
