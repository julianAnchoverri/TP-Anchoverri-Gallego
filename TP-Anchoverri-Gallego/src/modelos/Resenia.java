package modelos;

import org.json.JSONObject;
import utiles.JsonSerializable;

import java.time.LocalDate;
import java.util.Objects;

public class Resenia implements JsonSerializable {
    private String id;
    private String texto;
    private int puntuacion; // 1 a 5
    private LocalDate fecha;
    private int idCliente;

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

    // --- Métodos toJson y fromJson ---
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("texto", texto);
        json.put("puntuacion", puntuacion);
        json.put("fecha", fecha.toString()); // LocalDate como String ISO
        return json;
    }

    public static Resenia fromJson(JSONObject json) {
        Resenia r = new Resenia(
                json.getString("id"),
                json.getString("texto"),
                json.getInt("puntuacion")
        );
        // se reconstruye la fecha desde el JSON
        r.fecha = LocalDate.parse(json.getString("fecha"));
        return r;
    }



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

