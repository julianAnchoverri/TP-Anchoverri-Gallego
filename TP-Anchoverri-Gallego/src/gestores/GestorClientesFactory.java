package gestores;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.*;
import modelos.*;

public class GestorClientesFactory implements JsonFactory<GestorClientes> {

    @Override
    public GestorClientes fromJson(JSONObject json) {
        GestorClientes gestor = new GestorClientes();

        // -------------------------------
        // Reconstruir clientes
        // -------------------------------
        if (json.has("coleccionClientes")) {
            JSONArray clientesArray = json.getJSONArray("coleccionClientes");
            for (int i = 0; i < clientesArray.length(); i++) {
                JSONObject clienteJson = clientesArray.getJSONObject(i);
                Cliente c = Cliente.fromJson(clienteJson); // cada modelo tiene su propio fromJson
                try {
                    gestor.agregarCliente(c);
                } catch (Exception e) {
                    // Podés decidir si ignorar o loguear la excepción
                    System.err.println("Error al agregar cliente: " + e.getMessage());
                }
            }
        }

        // -------------------------------
        // Reconstruir reseñas por cliente
        // -------------------------------
        if (json.has("coleccionReseniasCliente")) {
            JSONObject reseniasObj = json.getJSONObject("coleccionReseniasCliente");

            for (String key : reseniasObj.keySet()) {
                JSONArray reseniasArray = reseniasObj.getJSONArray(key);
                for (int i = 0; i < reseniasArray.length(); i++) {
                    JSONObject reseniaJson = reseniasArray.getJSONObject(i);
                    Resenia r = Resenia.fromJson(reseniaJson);

                    // Buscar cliente existente
                    Cliente cliente = null;
                    for (Cliente c : gestor.getColeccionClientes()) {
                        if (c.getNombreUsuario().equals(key)) {
                            cliente = c;
                            break;
                        }
                    }

                    if (cliente != null) {
                        gestor.agregarReseniaA(cliente, r);
                    }
                }
            }
        }

        // -------------------------------
        // Reconstruir órdenes por cliente
        // -------------------------------
        if (json.has("coleccionOrdenesCliente")) {
            JSONObject ordenesObj = json.getJSONObject("coleccionOrdenesCliente");

            for (String key : ordenesObj.keySet()) {
                JSONArray ordenesArray = ordenesObj.getJSONArray(key);
                for (int i = 0; i < ordenesArray.length(); i++) {
                    JSONObject ordenJson = ordenesArray.getJSONObject(i);
                    OrdenDeCompra o = OrdenDeCompra.fromJson(ordenJson);

                    // Buscar cliente existente
                    Cliente cliente = null;
                    for (Cliente c : gestor.getColeccionClientes()) {
                        if (c.getNombreUsuario().equals(key)) {
                            cliente = c;
                            break;
                        }
                    }

                    if (cliente != null) {
                        gestor.agregarOrdenA(cliente, o);
                    }
                }
            }
        }

        return gestor;
    }
}
