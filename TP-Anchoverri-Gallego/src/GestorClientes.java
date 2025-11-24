import java.util.HashMap;
import java.util.Map;

public class GestorClientes {
    private Map<String, Cliente> clientesPorId;

    public GestorClientes() {
        this.clientesPorId = new HashMap<>();
    }

    // Agregar cliente
    public void agregarCliente(Cliente cliente)
            throws ContraseniaInvalidaException, ClienteYaExisteException, EmailInvalidoException, NombreUsuarioDuplicadoException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        validarContrasenia(cliente.getContrasenia());
        validarEmail(cliente.getEmail());
        validarNombreUsuario(cliente.getNombreUsuario());

        if (clientesPorId.containsKey(cliente.getId())) {
            throw new ClienteYaExisteException("El cliente con ID " + cliente.getId() + " ya existe.");
        }
        clientesPorId.put(cliente.getId(), cliente);
    }

    // Eliminar cliente
    public void eliminarCliente(String id) throws ClienteNoEncontradoException {
        if (!clientesPorId.containsKey(id)) {
            throw new ClienteNoEncontradoException("No existe cliente con ID " + id);
        }
        clientesPorId.remove(id);
    }

    // Buscar cliente por id
    public Cliente buscarClientePorId(String id) throws ClienteNoEncontradoException {
        Cliente cliente = clientesPorId.get(id);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("No existe cliente con ID " + id);
        }
        return cliente;
    }

    // Buscar cliente por nombre de usuario
    public Cliente buscarPorNombreUsuario(String nombreUsuario) throws ClienteNoEncontradoException {
        for (Cliente c : clientesPorId.values()) {
            if (c.getNombreUsuario().equals(nombreUsuario)) {
                return c;
            }
        }
        throw new ClienteNoEncontradoException("No existe cliente con nombre de usuario " + nombreUsuario);
    }


    // Listar todos los clientes
    public Map<String, Cliente> getClientes() {
        return clientesPorId;
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
        for (Cliente c : clientesPorId.values()) {
            if (c.getNombreUsuario().equals(nombreUsuario)) {
                throw new NombreUsuarioDuplicadoException("El nombre de usuario ya está en uso.");
            }
        }
    }


    // actualizacion de datosssss
    public void actualizarEmail(String idCliente, String nuevoEmail)
            throws ClienteNoEncontradoException, EmailInvalidoException {
        Cliente cliente = buscarClientePorId(idCliente);
        validarEmail(nuevoEmail);
        cliente.setEmail(nuevoEmail);
    }

    public void actualizarContrasenia(String idCliente, String nuevaContrasenia)
            throws ClienteNoEncontradoException, ContraseniaInvalidaException {
        Cliente cliente = buscarClientePorId(idCliente);
        validarContrasenia(nuevaContrasenia);
        cliente.setContrasenia(nuevaContrasenia);
    }

    public void actualizarNombre(String idCliente, String nuevoNombre, String nuevoApellido)
            throws ClienteNoEncontradoException {
        Cliente cliente = buscarClientePorId(idCliente);
        cliente.setNombre(nuevoNombre);
        cliente.setApellido(nuevoApellido);
    }

    public void actualizarNombreUsuario(String idCliente, String nuevoNombreUsuario)
            throws ClienteNoEncontradoException, NombreUsuarioDuplicadoException {
        validarNombreUsuario(nuevoNombreUsuario);
        Cliente cliente = buscarClientePorId(idCliente);
        cliente.setNombreUsuario(nuevoNombreUsuario);
    }


}
