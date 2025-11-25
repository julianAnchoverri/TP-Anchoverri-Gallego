package modelos;

import utiles.Seleccionable;

import java.time.LocalDate;
import java.util.Objects;

public class Producto implements Seleccionable<String> {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private boolean disponible;
    private LocalDate fechaPublicacion;


    public Producto(String id, String nombre, String descripcion,double precio, int stock, boolean disponible, LocalDate fechaPublicacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion= descripcion;
        this.precio = precio;
        this.stock = stock;
        this.disponible = disponible;
        this.fechaPublicacion= fechaPublicacion;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        this.stock = stock;
        this.disponible = stock > 0; // actualiza disponibilidad automatico
    }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    @Override
    public String getClave() {
        return getNombre();
    }

    @Override
    public String toString() {
        return "//////////  "+ nombre +"  //////////\n" +
                "Id: "+ id + "\n" +
                "Descripcion: " + descripcion + "\n"+
                "Precio: " + precio + "\n" +
                "Stock: " + stock + "\n" +
                "fechaPublicacion: " + fechaPublicacion + "\n" +
                "///////////////////////////////////////";
    }

    // Hascode y equals

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(!(o instanceof Producto)) return false;
        Producto p= (Producto) o;
        return nombre.equalsIgnoreCase(p.getNombre());
    }

}
