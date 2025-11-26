package gestores;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.*;
import modelos.*;

public class GestorVendedoresFactory implements JsonFactory<GestorVendedores> {

    @Override
    public GestorVendedores fromJson(JSONObject json) {
        GestorVendedores gestor = new GestorVendedores();

        // -------------------------------
        // Reconstruir vendedores
        // -------------------------------
        if (json.has("coleccionVendedores")) {
            JSONArray vendedoresArray = json.getJSONArray("coleccionVendedores");
            for (int i = 0; i < vendedoresArray.length(); i++) {
                JSONObject vendedorJson = vendedoresArray.getJSONObject(i);
                Vendedor v = Vendedor.fromJson(vendedorJson); // cada modelo tiene su propio fromJson
                gestor.agregarVendedor(v);
            }
        }

        // -------------------------------
        // Reconstruir tiendas por vendedor
        // -------------------------------
        if (json.has("coleccionTiendasVendedor")) {
            JSONObject tiendasObj = json.getJSONObject("coleccionTiendasVendedor");

            for (String key : tiendasObj.keySet()) {
                JSONArray tiendasArray = tiendasObj.getJSONArray(key);

                for (int i = 0; i < tiendasArray.length(); i++) {
                    JSONObject tiendaJson = tiendasArray.getJSONObject(i);
                    Tienda t = Tienda.fromJson(tiendaJson);

                    // Buscar vendedor existente en la colección
                    Vendedor vendedor = null;
                    for (Vendedor v : gestor.getColeccionVendedores()) {
                        if (v.getNombreUsuario().equals(key)) {
                            vendedor = v;
                            break;
                        }
                    }

                    // Si el vendedor existe, le agregamos la tienda
                    if (vendedor != null) {
                        gestor.agregarTiendaA(vendedor, t);
                    }
                }
            }
        }

        return gestor;
    }
}



