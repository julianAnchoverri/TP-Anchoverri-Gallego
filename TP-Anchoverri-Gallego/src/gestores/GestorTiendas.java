package gestores;

import modelos.CategoriaProducto;
import modelos.CategoriaTienda;
import modelos.Producto;
import modelos.Tienda;
import org.json.JSONObject;
import utiles.ElementoNoEncontradoException;
import utiles.ElementoYaExisteException;
import utiles.JsonSerializable;
import utiles.Seleccionable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

public class GestorTiendas implements JsonSerializable {
    HashMap<String, CategoriaTienda> coleccionCategoriasTiendas= new HashMap<>();

    public GestorTiendas() {
    }

    // Getters y setters

    public HashMap<String, CategoriaTienda> getColeccionCategoriasTiendas() {
        return coleccionCategoriasTiendas;
    }

    // Metodos para listar y seleccionar

    public <T extends Seleccionable<K>,K> T buscarPorClaveGenerico (Collection<T> listado, String nuevo) throws ElementoYaExisteException {
        for (T elemento : listado) {
            if (elemento.getClave() instanceof String && ((String) elemento.getClave()).equalsIgnoreCase(nuevo)) {
                return elemento;
            }
        }
        throw new ElementoYaExisteException("Ya existe " + nuevo );
    }

    public <T extends Seleccionable<K>,K> void listarGenerico(Collection<T> listado){
        for (T elemento : listado) {
            System.out.println(elemento.toString());
        }
    }

    public <T extends Seleccionable<K>,K> T seleccionarGenerico (Collection<T> listado, String opcion) throws ElementoNoEncontradoException{
        for (T elemento : listado) {
            if (elemento.getClave() instanceof String && ((String) elemento.getClave()).equalsIgnoreCase(opcion)) {
                return elemento;
            }
        }
        throw new ElementoNoEncontradoException("No existe " + opcion );
    }



    public void listarCategoriasTiendas(){
        System.out.println("==== Categorias de tiendas ====");
        listarGenerico(coleccionCategoriasTiendas.values());
    }
    public CategoriaTienda seleccionarCategoriasTiendas(String opcion) throws ElementoNoEncontradoException{
        return seleccionarGenerico(coleccionCategoriasTiendas.values(), opcion);
    }

    public void listarTiendas(CategoriaTienda cat){
        // algo para actualizar la valoracion actualizarValoracion();
        // algo para verificar/actualizar ordenes de compra invalidas;
        System.out.println("==== Tiendas ====");
        listarGenerico(cat.getColeccionTiendas());
    }
    public Tienda seleccionarTiendas(CategoriaTienda cat, String opcion) throws ElementoNoEncontradoException{
        return seleccionarGenerico(cat.getColeccionTiendas(), opcion);
    }

    public void listarCategoriasProductos(Tienda tienda){
        System.out.println("==== Categorias de productos ====");
        listarGenerico(tienda.getColeccionCategoriasProductos().values());
    }
    public CategoriaProducto seleccionarCategoriasProductos(Tienda tienda, String opcion) throws ElementoNoEncontradoException{
        return seleccionarGenerico(tienda.getColeccionCategoriasProductos().values(), opcion);
    }

    public void listarProductos(CategoriaProducto cat){
        System.out.println("==== Productos ====");
        listarGenerico(cat.getColeccionProductos());
    }
    public Producto seleccionarProductos(CategoriaProducto cat, String opcion) throws ElementoNoEncontradoException{
        return seleccionarGenerico(cat.getColeccionProductos(), opcion);
    }

    // Agregar y elimiar elementos de la coleccion general

    public void agregarTienda(Tienda t, CategoriaTienda cat) throws ElementoYaExisteException{
        buscarPorClaveGenerico(cat.getColeccionTiendas(),t.getClave());
        cat.getColeccionTiendas().add(t);
    }
    public void eliminarTienda(String tienda, CategoriaTienda cat) throws ElementoNoEncontradoException{
        Tienda t= seleccionarGenerico(cat.getColeccionTiendas(),tienda);
        cat.getColeccionTiendas().remove(t);
    }

    public void agregarCategoriaProducto(CategoriaProducto cat, Tienda t) throws ElementoYaExisteException{
        buscarPorClaveGenerico(t.getColeccionCategoriasProductos().values(),cat.getClave());
        t.getColeccionCategoriasProductos().put(cat.getClave(), cat);
    }
    public void eliminarCategoriaProducto(String categoria, Tienda t) throws ElementoNoEncontradoException{
        CategoriaProducto cat= seleccionarGenerico(t.getColeccionCategoriasProductos().values(),categoria);
        t.getColeccionCategoriasProductos().remove(cat.getClave());
    }

    public void agregarProducto(Producto producto, CategoriaProducto cat) throws ElementoYaExisteException{
        buscarPorClaveGenerico(cat.getColeccionProductos(),producto.getClave());
        cat.getColeccionProductos().add(producto);
    }
    public void eliminarProducto(String producto, CategoriaProducto cat) throws ElementoNoEncontradoException{
        Producto p= seleccionarGenerico(cat.getColeccionProductos(),producto);
        cat.getColeccionProductos().remove(p);
    }

    // Metodo crear tienda

    public Tienda crearTienda(String id, String nombre) throws ElementoYaExisteException {
        Tienda t = new Tienda(id, nombre);
        return t;
    }

    public void cambiarNombreTienda(String nuevo, Tienda t, CategoriaTienda cat) throws ElementoYaExisteException{
        buscarPorClaveGenerico(cat.getColeccionTiendas(), nuevo);
        t.setNombre(nuevo);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        JSONObject categoriasJson = new JSONObject();
        for (String clave : coleccionCategoriasTiendas.keySet()) {
            CategoriaTienda categoria = coleccionCategoriasTiendas.get(clave);
            categoriasJson.put(clave, categoria.toJson());
        }

        json.put("coleccionCategoriasTiendas", categoriasJson);
        return json;
    }


}
