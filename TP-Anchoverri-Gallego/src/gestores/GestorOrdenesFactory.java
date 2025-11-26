package gestores;

import org.json.JSONArray;
import org.json.JSONObject;
import utiles.JsonFactory;
import modelos.*;

public class GestorOrdenesFactory implements JsonFactory<GestorOrdenes> {

    @Override
    public GestorOrdenes fromJson(JSONObject json) {
        GestorOrdenes gestor = new GestorOrdenes();

        if (json.has("coleccionOrdenesTiendas")) {
            JSONObject ordenesObj = json.getJSONObject("coleccionOrdenesTiendas");

            for (String claveTienda : ordenesObj.keySet()) {
                JSONArray ordenesArray = ordenesObj.getJSONArray(claveTienda);

                for (int i = 0; i < ordenesArray.length(); i++) {
                    JSONObject ordenJson = ordenesArray.getJSONObject(i);
                    OrdenDeCompra orden = OrdenDeCompra.fromJson(ordenJson);

                    // Crear tienda mínima con id y nombre iguales
                    Tienda tienda = new Tienda(claveTienda, claveTienda);

                    gestor.agregarOrdenA(tienda, orden);
                }
            }
        }

        return gestor;
    }
}
