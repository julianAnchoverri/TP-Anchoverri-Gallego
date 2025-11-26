package gestores;

import modelos.*;
import org.json.JSONArray;
import org.json.JSONObject;
import utiles.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestorClientes implements JsonSerializable{
    private Map<String,Cliente> coleccionClientes;


    public GestorClientes() {
        this.coleccionClientes= new ArrayList<>();
        this.coleccionReseniasCliente = new HashMap<>();
        this.coleccionOrdenesCliente = new HashMap<>();
    }

    // Getters y Setters

    public ArrayList<Cliente> getColeccionClientes() { return coleccionClientes; }
    public void setColeccionClientes(ArrayList<Cliente> coleccionClientes) { this.coleccionClientes = coleccionClientes; }

    public HashMap<String, ArrayList<Resenia>> getColeccionReseniasCliente() { return coleccionReseniasCliente; }
    public void setColeccionReseniasCliente(HashMap<String, ArrayList<Resenia>> coleccionReseniasCliente) { this.coleccionReseniasCliente = coleccionReseniasCliente; }

    public HashMap<String, ArrayList<OrdenDeCompra>> getColeccionOrdenesCliente() { return coleccionOrdenesCliente; }
    public void setColeccionOrdenesCliente(HashMap<String, ArrayList<OrdenDeCompra>> coleccionOrdenesCliente) { this.coleccionOrdenesCliente = coleccionOrdenesCliente; }


    // Agregar cliente

    public void agregarCliente(Cliente cliente)
            throws ContraseniaInvalidaException, ElementoYaExisteException, EmailInvalidoException, NombreUsuarioDuplicadoException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        validarContrasenia(cliente.getContrasenia());
        validarEmail(cliente.getEmail());
        validarNombreUsuario(cliente.getNombreUsuario());

        if (coleccionClientes.contains(cliente)) {
            throw new ElementoYaExisteException("Ya existe " + cliente.getNombreUsuario());
        }
        coleccionClientes.add(cliente);
    }

    // Eliminar cliente
    public void eliminarCliente(String nombre) throws ElementoNoEncontradoException {
        Cliente cliente= buscarPorNombreUsuario(nombre);
        coleccionClientes.remove(cliente);
    }


    // Buscar cliente por nombre de usuario
    public Cliente buscarPorNombreUsuario(String nombreUsuario) throws ElementoNoEncontradoException {
        for (Cliente c : coleccionClientes) {
            if (c.getNombreUsuario().equals(nombreUsuario)) {
                return c;
            }
        }
        throw new ElementoNoEncontradoException("No existe cliente con nombre de usuario " + nombreUsuario);
    }

    // Agregar resenia a un cliente
    public void agregarReseniaA(Cliente cliente, Resenia resenia) {
        if (!coleccionReseniasCliente.containsKey(cliente.getNombreUsuario())) {
            coleccionReseniasCliente.put(cliente.getNombreUsuario(), new ArrayList<>());
        }
        coleccionReseniasCliente.get(cliente.getNombreUsuario()).add(resenia);
    }

    // Obtener resenias de un cliente
    public ArrayList<Resenia> obtenerReseniasDe(Cliente cliente) {
        if(coleccionReseniasCliente.isEmpty()) System.out.println("El cliente no tiene reseñas");
        if (!coleccionReseniasCliente.containsKey(cliente.getNombreUsuario())) {
            return new ArrayList<Resenia>();
        }
        return coleccionReseniasCliente.get(cliente.getNombreUsuario());
    }

    // Agregar orden a un cliente
    public void agregarOrdenA(Cliente cliente, OrdenDeCompra orden) {
        if (!coleccionOrdenesCliente.containsKey(cliente.getNombreUsuario())) {
            coleccionOrdenesCliente.put(cliente.getNombreUsuario(), new ArrayList<OrdenDeCompra>());
        }
        coleccionOrdenesCliente.get(cliente.getNombreUsuario()).add(orden);
    }

    // Obtener ordenes de un cliente
    public ArrayList<OrdenDeCompra> obtenerOrdenesDe(Cliente cliente) {
        if(coleccionOrdenesCliente.isEmpty()) System.out.println("El cliente no tiene ordenes de compra");
        if (!coleccionOrdenesCliente.containsKey(cliente.getNombreUsuario())) {
            return new ArrayList<OrdenDeCompra>();
        }
        return coleccionOrdenesCliente.get(cliente.getNombreUsuario());
    }


    // Validacionesssssss
    private void validarEmail(String email) throws EmailInvalidoException {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new EmailInvalidoException("Formato de email inválido: " + email);
        }
    }

    private void validarContrasenia(String contrasenia) throws ContraseniaInvalidaException {
        if (contrasenia.length() < 8) {
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos 8 caracteres.");
        }
        boolean tieneMayuscula = contrasenia.chars().anyMatch(Character::isUpperCase);
        if (!tieneMayuscula) {
            throw new ContraseniaInvalidaException("La contraseña debe contener al menos una letra mayúscula.");
        }
    }

    private void validarNombreUsuario(String nombreUsuario) throws NombreUsuarioDuplicadoException {
        for (Cliente c : coleccionClientes) {
            if (c.getNombreUsuario().equals(nombreUsuario)) {
                throw new NombreUsuarioDuplicadoException("El nombre de usuario ya está en uso.");
            }
        }
    }


    // actualizacion de datosssss

    public void actualizarEmail(String nombre, String nuevoEmail)
            throws ElementoNoEncontradoException, EmailInvalidoException {
        Cliente cliente = buscarPorNombreUsuario(nombre);
        validarEmail(nuevoEmail);
        cliente.setEmail(nuevoEmail);
    }

    public void actualizarContrasenia(String nombre, String nuevaContrasenia)
            throws ElementoNoEncontradoException, ContraseniaInvalidaException {
        Cliente cliente = buscarPorNombreUsuario(nombre);
        validarContrasenia(nuevaContrasenia);
        cliente.setContrasenia(nuevaContrasenia);
    }

    public void actualizarNombre(String nombre, String nuevoNombre, String nuevoApellido)
            throws ElementoNoEncontradoException {
        Cliente cliente = buscarPorNombreUsuario(nombre);
        cliente.setNombre(nuevoNombre);
        cliente.setApellido(nuevoApellido);
    }

    public void actualizarNombreUsuario(String nombre, String nuevoNombreUsuario)
            throws ElementoNoEncontradoException, NombreUsuarioDuplicadoException {
        validarNombreUsuario(nuevoNombreUsuario);
        Cliente cliente = buscarPorNombreUsuario(nombre);
        cliente.setNombreUsuario(nuevoNombreUsuario);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        // Serializar clientes
        JSONArray clientesArray = new JSONArray();
        for (Cliente c : coleccionClientes) {
            clientesArray.put(c.toJson());
        }
        json.put("coleccionClientes", clientesArray);

        // Serializar reseñas por cliente
        JSONObject reseniasObj = new JSONObject();
        for (String claveCliente : coleccionReseniasCliente.keySet()) {
            JSONArray reseniasArray = new JSONArray();
            for (Resenia r : coleccionReseniasCliente.get(claveCliente)) {
                reseniasArray.put(r.toJson());
            }
            reseniasObj.put(claveCliente, reseniasArray);
        }
        json.put("coleccionReseniasCliente", reseniasObj);

        // Serializar órdenes por cliente
        JSONObject ordenesObj = new JSONObject();
        for (String claveCliente : coleccionOrdenesCliente.keySet()) {
            JSONArray ordenesArray = new JSONArray();
            for (OrdenDeCompra o : coleccionOrdenesCliente.get(claveCliente)) {
                ordenesArray.put(o.toJson());
            }
            ordenesObj.put(claveCliente, ordenesArray);
        }
        json.put("coleccionOrdenesCliente", ordenesObj);

        return json;
    }



}
