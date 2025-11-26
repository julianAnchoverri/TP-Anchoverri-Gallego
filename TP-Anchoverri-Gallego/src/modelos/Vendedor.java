package modelos;

import gestores.GestorJson;
import utiles.*;
import org.json.JSONObject;

public class Vendedor extends Usuario implements JsonSerializable {

    public Vendedor(String nombre, String apellido, String email, String nombreUsuario, String contrasenia) {
        super(nombre, apellido, email, nombreUsuario, contrasenia);
        this.rol = RolUsuario.VENDEDOR;
    }

    // Convierte el objeto a JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nombre", nombre);
        json.put("apellido", apellido);
        json.put("email", email);
        json.put("nombreUsuario", nombreUsuario);
        json.put("contrasenia", contrasenia);
        json.put("rol", rol.toString());
        return json;
    }

    // Crea un Vendedor desde JSON
    public static Vendedor fromJson(JSONObject json) {
        return new Vendedor(
                json.getString("nombre"),
                json.getString("apellido"),
                json.getString("email"),
                json.getString("nombreUsuario"),
                json.getString("contrasenia")
        );
    }

    @Override
    public String toString() {
        return "//////////  " + nombreUsuario + "  //////////\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Email: " + email + "\n" +
                "///////////////////////////////////////";
    }
}
