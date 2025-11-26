package modelos;

public class Vendedor extends Usuario {

    public Vendedor(String nombre, String apellido, String email, String nombreUsuario, String contrasenia) {
        super(nombre, apellido, email, nombreUsuario, contrasenia);
        this.rol=RolUsuario.VENDEDOR;
    }

    @Override
    public String toString() {
        return "//////////  "+ nombreUsuario +"  //////////\n" +
                "Nombre: " + nombre + "\n"+
                "Apellido: " + apellido + "\n" +
                "Email: " + email + "\n" +
                "///////////////////////////////////////";
    }

}
