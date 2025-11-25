package modelos;

import utiles.Seleccionable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class CategoriaTienda implements Seleccionable<String> {
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
