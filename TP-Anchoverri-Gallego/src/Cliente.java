public class Cliente extends Usuario {

    private String direccion;

    public Cliente(String id, String nombre, String apellido, String email, String nombreUsuario, String contrasenia, String direccion) {
        super(id, nombre, apellido, email, nombreUsuario, contrasenia);
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
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                "} " + super.toString();
    }


}
