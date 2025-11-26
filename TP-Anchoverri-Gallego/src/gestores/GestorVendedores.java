package gestores;

import modelos.Tienda;
import modelos.Vendedor;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.JsonSerializable;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorVendedores implements JsonSerializable {
    private ArrayList<Vendedor> coleccionVendedores = new ArrayList<>();
    private HashMap<String, ArrayList<Tienda>> coleccionTiendasVendedor = new HashMap<>();

    public GestorVendedores() {}

    // Getters y setters

    public ArrayList<Vendedor> getColeccionVendedores() { return coleccionVendedores; }
    public void setColeccionVendedores(ArrayList<Vendedor> coleccionVendedores) {this.coleccionVendedores = coleccionVendedores; }

    public HashMap<String, ArrayList<Tienda>> getColeccionTiendasVendedor() { return coleccionTiendasVendedor; }
    public void setColeccionTiendasVendedor(HashMap<String, ArrayList<Tienda>> coleccionTiendasVendedor) { this.coleccionTiendasVendedor = coleccionTiendasVendedor; }


    // Metodos

    // Agregar un vendedor
    public void agregarVendedor(Vendedor vendedor) {
        if(!coleccionVendedores.contains(vendedor)) {
            coleccionVendedores.add(vendedor);
        }
    }

    // Agregar tienda a un vendedor
    public void agregarTiendaA(Vendedor vendedor, Tienda tienda) {
        if (!coleccionTiendasVendedor.containsKey(vendedor.getNombreUsuario())) {
            coleccionTiendasVendedor.put(vendedor.getNombreUsuario(), new ArrayList<Tienda>());
        }
        coleccionTiendasVendedor.get(vendedor.getNombreUsuario()).add(tienda);
    }

    // Obtener tiendas de un vendedor
    public ArrayList<Tienda> obtenerTiendasDe(Vendedor vendedor) {
        if(coleccionTiendasVendedor.isEmpty()) System.out.println("El vendedor no tiene tiendas");
        if (!coleccionTiendasVendedor.containsKey(vendedor.getNombreUsuario())) {
            return new ArrayList<Tienda>();
        }
        return coleccionTiendasVendedor.get(vendedor.getNombreUsuario());
    }

    //--metodos from y to json--
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        // Serializar vendedores
        JSONArray vendedoresArray = new JSONArray();
        for (Vendedor v : coleccionVendedores) {
            vendedoresArray.put(v.toJson()); // cada Vendedor debe implementar JsonSerializable
        }
        json.put("coleccionVendedores", vendedoresArray);

        // Serializar tiendas por vendedor
        JSONObject tiendasObj = new JSONObject();
        for (String key : coleccionTiendasVendedor.keySet()) {
            JSONArray tiendasArray = new JSONArray();
            for (Tienda t : coleccionTiendasVendedor.get(key)) {
                tiendasArray.put(t.toJson()); // cada Tienda también debe implementar JsonSerializable
            }
            tiendasObj.put(key, tiendasArray);
        }
        json.put("coleccionTiendasVendedor", tiendasObj);

        return json;
    }

}
