package modelos;

import utiles.Seleccionable;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tienda implements Seleccionable<String> {
    private String id;
    private String nombre;
    private Set<Resenia> resenias; // cambiar : Esto va en el gestor
    private int cantidadVentas= 0;
    private String valoracion;
    private LocalDate fechaCreacion; // fecha en que se creó la tienda
    private int limiteDeOrdenes=10;
    private int limiteHorasOrden=24; // no son final para que puedan ser modificadas segun la necesidad del vendedor

    private HashMap<String,CategoriaProducto> coleccionCategoriasProductos= new HashMap<>();

    public Tienda() {}

    public Tienda(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.valoracion = "Baja (Tienda nueva)";;
        this.fechaCreacion = LocalDate.now();
    }

    // Metodo para calcular fiabilidad

    /*public void calcularFiabilidad() {
        if (resenias.isEmpty()) { // cambiar
            valoracion = 0.0;
            return;
        }
        int suma = 0;
        for (Resenia r : resenias) { // cambiar
            suma += r.getPuntaje();
        }
        valoracion = (double) suma / resenias.size(); //cambiar
    }*/

    // Metodo para calcular antigüedad en años
    public int getAntiguedad() {
        return Period.between(fechaCreacion, LocalDate.now()).getYears(); // cambiar
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }


    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public int getCantidadVentas() {
        return cantidadVentas;
    }
    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public int getLimiteDeOrdenes() {
        return limiteDeOrdenes;
    }
    public void setLimiteDeOrdenes(int limiteDeOrdenes) {
        this.limiteDeOrdenes = limiteDeOrdenes;
    }

    public int getLimiteHorasOrden() {
        return limiteHorasOrden;
    }
    public void setLimiteHorasOrden(int limiteHorasOrden) {
        this.limiteHorasOrden = limiteHorasOrden;
    }

    public String getValoracion() { return valoracion; }
    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public HashMap<String, CategoriaProducto> getColeccionCategoriasProductos() { return coleccionCategoriasProductos; }
    public void setColeccionCategoriasProductos(HashMap<String, CategoriaProducto> coleccionCategoriasProductos) {
        this.coleccionCategoriasProductos = coleccionCategoriasProductos;
    }

    @Override
    public String getClave() {
        return getNombre();
    }

    @Override
    public String toString() {
        return "//////////  "+ nombre +"  //////////\n" +
                "Id: "+ id + "\n" +
                "Valoracion: " + valoracion + "\n"+
                "Cantidad de ventas: " + cantidadVentas + "\n" +
                "Fecha de creacion: " + fechaCreacion + "\n" +
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
        if(!(o instanceof Tienda)) return false;
        Tienda t= (Tienda) o;
        return nombre.equalsIgnoreCase(t.getNombre());
    }


}
