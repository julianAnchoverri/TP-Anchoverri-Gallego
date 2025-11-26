package gestores;

import org.json.JSONObject;
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
}

