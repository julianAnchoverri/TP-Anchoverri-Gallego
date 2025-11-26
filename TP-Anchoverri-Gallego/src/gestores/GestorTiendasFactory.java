package gestores;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.*;
import modelos.*;

public class GestorTiendasFactory implements JsonFactory<GestorTiendas> {

    @Override
    public GestorTiendas fromJson(JSONObject json) {
        GestorTiendas gestor = new GestorTiendas();

        // -------------------------------
        // Reconstruir categorías de tiendas
        // -------------------------------
        if (json.has("coleccionCategoriasTiendas")) {
            JSONObject categoriasObj = json.getJSONObject("coleccionCategoriasTiendas");

            for (String key : categoriasObj.keySet()) {
                JSONObject categoriaJson = categoriasObj.getJSONObject(key);

                // ⚠️ Cada CategoriaTienda debe tener su propio fromJson(JSONObject)
                CategoriaTienda categoria = CategoriaTienda.fromJson(categoriaJson);

                gestor.getColeccionCategoriasTiendas().put(key, categoria);
            }
        }

        return gestor;
    }
}
