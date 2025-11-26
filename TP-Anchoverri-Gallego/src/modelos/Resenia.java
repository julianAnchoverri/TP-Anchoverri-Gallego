package modelos;

import java.time.LocalDate;
import java.util.Objects;

public class Resenia {
    private String id;
    private String texto;
    private int puntuacion; // 1 a 5
    private LocalDate fecha;

    public Resenia(String id, String texto, int puntuacion) {
        this.id= id;
        this.texto = texto;
        this.puntuacion = puntuacion;
        this.fecha = LocalDate.now();
    }

    //Getters y Setters
    public String getId() { return id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

    public LocalDate getFecha() { return fecha; }


    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(!(o instanceof Resenia)) return false;
        Resenia r= (Resenia) o;
        return (id.equals(r.getId()));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "///////////////////////////////////////\n" +
                "Id: "+ id + "\n" +
                "Descripcion: " + texto + "\n"+
                "Puntuacion: " + puntuacion + "\n" +
                "Fecha: " + fecha + "\n" +
                "///////////////////////////////////////";
    }

}

