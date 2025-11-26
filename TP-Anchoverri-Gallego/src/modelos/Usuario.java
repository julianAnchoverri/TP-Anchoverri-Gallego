package modelos;

import java.util.Objects;

public abstract class Usuario {
    protected String nombreUsuario;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String contrasenia;
    protected RolUsuario rol;

    public Usuario(String nombre, String apellido, String email, String nombreUsuario, String contrasenia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }

    // Hascode y equals
    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario,rol);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(!(o instanceof Usuario)) return false;
        Usuario aux= (Usuario) o;
        return (nombreUsuario.equalsIgnoreCase(aux.getNombreUsuario()) && rol==aux.getRol()) ;
    }

}
