package gestores;

import org.json.JSONObject;
import utiles.JsonFactory;
import utiles.JsonSerializable;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestorArchivosJson {
    public static void guardarEnArchivo(JsonSerializable objeto, String rutaArchivo) {
        try {
            JSONObject json = objeto.toJson();
            Files.writeString(Path.of(rutaArchivo), json.toString(2), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando " + rutaArchivo + ": " + e.getMessage(), e);
        }
    }

    public static <T> T leerDesdeArchivo(String rutaArchivo, JsonFactory<T> factory) {
        try {
            String contenido = Files.readString(Path.of(rutaArchivo), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(contenido);
            return factory.fromJson(json);
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo " + rutaArchivo + ": " + e.getMessage(), e);
        }
    }

}

