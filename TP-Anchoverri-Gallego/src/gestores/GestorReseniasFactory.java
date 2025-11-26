package gestores;
import modelos.*;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.*;

public class GestorReseniasFactory implements JsonFactory<GestorResenias> {

    @Override
    public GestorResenias fromJson(JSONObject json) {
        GestorResenias gestor = new GestorResenias();

        // -------------------------------
        // Reconstruir reseñas por tienda
        // -------------------------------
        if (json.has("coleccionReseniasTiendas")) {
            JSONObject reseniasObj = json.getJSONObject("coleccionReseniasTiendas");

            for (String claveTienda : reseniasObj.keySet()) {
                JSONArray reseniasArray = reseniasObj.getJSONArray(claveTienda);

                for (int i = 0; i < reseniasArray.length(); i++) {
                    JSONObject reseniaJson = reseniasArray.getJSONObject(i);
                    Resenia resenia = Resenia.fromJson(reseniaJson);
                    // ⚠️ Cada modelo debe tener su propio fromJson

                    // Creamos una tienda mínima con la clave (id y nombre iguales)
                    Tienda tienda = new Tienda(claveTienda, claveTienda);

                    gestor.agregarReseniaA(tienda, resenia);
                }
            }
        }

        return gestor;
    }
}
