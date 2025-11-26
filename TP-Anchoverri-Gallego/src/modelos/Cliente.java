package modelos;

import org.json.JSONObject;

public class Cliente extends Usuario {
    private String direccion;

    public Cliente(String nombre, String apellido, String email, String nombreUsuario, String contrasenia, String direccion) {
        super(nombre, apellido, email, nombreUsuario, contrasenia);
        this.rol = RolUsuario.CLIENTE;
        this.direccion = direccion;
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // Convierte el objeto a JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nombre", nombre);
        json.put("apellido", apellido);
        json.put("email", email);
        json.put("nombreUsuario", nombreUsuario);
        json.put("contrasenia", contrasenia);
        json.put("rol", rol.toString());
        json.put("direccion", direccion);
        return json;
    }

    // Crea un Cliente desde JSON
    public static Cliente fromJson(JSONObject json) {
        return new Cliente(
                json.getString("nombre"),
                json.getString("apellido"),
                json.getString("email"),
                json.getString("nombreUsuario"),
                json.getString("contrasenia"),
                json.getString("direccion")
        );
    }

    @Override
    public String toString() {
        return "//////////  " + nombreUsuario + "  //////////\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Email: " + email + "\n" +
                "Direccion: " + direccion + "\n" +
                "///////////////////////////////////////";
    }
}