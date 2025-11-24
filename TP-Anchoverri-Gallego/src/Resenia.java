import java.time.LocalDate;
import java.util.Objects;

public class Resenia {
    private String comentario;
    private int puntaje; // 1 a 5
    private Cliente autor;
    private LocalDate fecha;

    public Resenia(String comentario, int puntaje, Cliente autor, LocalDate fecha) {
        if (puntaje < 1 || puntaje > 5) {
            throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5.");
        }
        this.comentario = comentario;
        this.puntaje = puntaje;
        this.autor = autor;
        this.fecha = fecha;
    }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public int getPuntaje() { return puntaje; }
    public void setPuntaje(int puntaje) {
        if (puntaje < 1 || puntaje > 5) {
            throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5.");
        }
        this.puntaje = puntaje;
    }

    public Cliente getAutor() { return autor; }
    public void setAutor(Cliente autor) { this.autor = autor; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Resenia resenia)) return false;
        return puntaje == resenia.puntaje && Objects.equals(comentario, resenia.comentario) && Objects.equals(autor, resenia.autor) && Objects.equals(fecha, resenia.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comentario, puntaje, autor, fecha);
    }

    @Override
    public String toString() {
        return "Resenia{" +
                "comentario='" + comentario + '\'' +
                ", puntaje=" + puntaje +
                ", autor=" + autor.getNombre() + " " + autor.getApellido() +
                ", fecha=" + fecha +
                '}';
    }

}

