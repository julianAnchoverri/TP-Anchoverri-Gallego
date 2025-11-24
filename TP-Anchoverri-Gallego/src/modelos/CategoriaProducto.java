package modelos;

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

    @Override
    public String getClave() {
        return getNombre();
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
