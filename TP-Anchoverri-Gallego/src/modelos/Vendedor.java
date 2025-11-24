package modelos;

public class Vendedor extends Usuario {
    private boolean activo = true;

    public Vendedor(String id, String nombre, String apellido, String email, String nombreUsuario, String contrasenia, boolean activo) {
        super(id, nombre, apellido, email, nombreUsuario, contrasenia);
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                ", id='" + id + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                "} ";
    }


}
