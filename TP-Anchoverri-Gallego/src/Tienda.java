import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

public class Tienda {
    private String id;
    private String nombre;
    private Set<Producto> productos;
    private Set<Resenia> resenias;
    private String idVendedor;
    private double fiabilidad;
    private LocalDate fechaCreacion; // fecha en que se creó la tienda

    public Tienda(String id, String nombre, String idVendedor) {
        this.id = id;
        this.nombre = nombre;
        this.idVendedor = idVendedor;
        this.productos = new HashSet<>();
        this.resenias = new HashSet<>();
        this.fiabilidad = 0.0;
        this.fechaCreacion = LocalDate.now();
    }

    // Metodo para calcular fiabilidad
    public void calcularFiabilidad() {
        if (resenias.isEmpty()) {
            fiabilidad = 0.0;
            return;
        }
        int suma = 0;
        for (Resenia r : resenias) {
            suma += r.getPuntaje();
        }
        fiabilidad = (double) suma / resenias.size();
    }

    // Metodo para calcular antigüedad en años
    public int getAntiguedad() {
        return Period.between(fechaCreacion, LocalDate.now()).getYears();
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<Producto> getProductos() { return productos; }
    public void setProductos(Set<Producto> productos) { this.productos = productos; }

    public Set<Resenia> getResenias() { return resenias; }
    public void setResenias(Set<Resenia> resenias) {
        this.resenias = resenias;
        calcularFiabilidad();
    }

    public String getIdVendedor() { return idVendedor; }
    public void setIdVendedor(String idVendedor) { this.idVendedor = idVendedor; }

    public double getFiabilidad() { return fiabilidad; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @Override
    public String toString() {
        return "Tienda{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", productos=" + productos.size() +
                ", resenias=" + resenias.size() +
                ", fiabilidad=" + fiabilidad +
                ", antiguedad=" + getAntiguedad() + " años" +
                '}';
    }
}
