package gestores;

import modelos.CategoriaProducto;
import modelos.CategoriaTienda;
import modelos.Producto;
import modelos.Tienda;
import utiles.ElementoNoEncontradoException;
import utiles.Seleccionable;

import java.util.Collection;
import java.util.HashMap;

public class GestorTiendas{
    HashMap<String, CategoriaTienda> coleccionCategoriasTiendas= new HashMap<>();

    public GestorTiendas() {
    }

    // Getters y setters

    public HashMap<String, CategoriaTienda> getColeccionCategoriasTiendas() {
        return coleccionCategoriasTiendas;
    }

    // Metodos para listar y seleccionar

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
        throw new ElementoNoEncontradoException("No existe " + opcion + ". Intente nuevamente");
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





}
