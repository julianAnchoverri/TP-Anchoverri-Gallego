package modelos;

public class Cliente extends Usuario {

    private String direccion;

    public Cliente(String nombre, String apellido, String email, String nombreUsuario, String contrasenia, String direccion) {
        super(nombre, apellido, email, nombreUsuario, contrasenia);
        this.rol=RolUsuario.CLIENTE;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "//////////  "+ nombreUsuario +"  //////////\n" +
                "Nombre: " + nombre + "\n"+
                "Apellido: " + apellido + "\n" +
                "Email: " + email + "\n" +
                "Direccion: " + direccion + "\n" +
                "///////////////////////////////////////";
    }


}
