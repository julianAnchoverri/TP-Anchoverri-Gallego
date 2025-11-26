package gestores;

import modelos.Cliente;
import modelos.Resenia;
import modelos.Tienda;
import utiles.ElementoYaExisteException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorResenias {
    private HashMap<String, ArrayList<Resenia>> coleccionReseniasTiendas;

    public GestorResenias() {
        this.coleccionReseniasTiendas = new HashMap<>();
    }

    // Getters y Setters
    public HashMap<String, ArrayList<Resenia>> getColeccionReseniasTiendas() { return coleccionReseniasTiendas; }
    public void setColeccionReseniasTiendas(HashMap<String, ArrayList<Resenia>> coleccionReseniasTiendas) { this.coleccionReseniasTiendas = coleccionReseniasTiendas; }

    // Agregar resenia a una tienda
    public void agregarReseniaA(Tienda tienda, Resenia resenia) {
        if (!coleccionReseniasTiendas.containsKey(tienda.getClave())) {
            coleccionReseniasTiendas.put(tienda.getClave(), new ArrayList<Resenia>());
        }
        coleccionReseniasTiendas.get(tienda.getClave()).add(resenia);
    }

    // Obtener resenias de una tienda
    public ArrayList<Resenia> obtenerReseniasDe(Tienda tienda) {
        if(coleccionReseniasTiendas.isEmpty()) System.out.println("La tienda no tiene reseñas");
        if (!coleccionReseniasTiendas.containsKey(tienda.getClave())) {
            return new ArrayList<Resenia>();
        }
        return coleccionReseniasTiendas.get(tienda.getClave());
    }

    public Resenia crearResenia(String id, String texto, int puntuacion) {
        Resenia r = new Resenia(id,texto,puntuacion);
        return r;
    }

    public String calcularValoracion(Tienda tienda) {
        if (this.coleccionReseniasTiendas.isEmpty()) {
            return ("Sin valoraciones");
        }
        double suma = 0;
        ArrayList<Resenia> arr= coleccionReseniasTiendas.get(tienda.getClave());
        for (Resenia r: arr) {
            suma += r.getPuntuacion();
        }
        double promedioPuntaje = suma / arr.size();
        int cantidadVentas = tienda.getCantidadVentas();
        double credibilidad = Math.sqrt(cantidadVentas) / (Math.sqrt(cantidadVentas) + 5); //funcion para que las primeras ventas impacten menos y que el impacto no crezca linealmente
        double puntajeFinal = promedioPuntaje * credibilidad;
        String valoracion;
        if (puntajeFinal < 1.5) valoracion = "Bajo";
        else if (puntajeFinal < 3) valoracion = "Medio";
        else if (puntajeFinal < 4.5) valoracion = "Alto";
        else valoracion = "Excelente";
        return valoracion;
    }
}
