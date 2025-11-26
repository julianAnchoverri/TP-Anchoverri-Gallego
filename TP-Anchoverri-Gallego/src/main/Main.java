package main;

import gestores.*;
import modelos.*;
import utiles.ElementoNoEncontradoException;
import utiles.ElementoYaExisteException;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    Scanner scanner= new Scanner(System.in); //DEBERIA SER EL UNICO SCANNER DEL PROYECTO. Todo dato por teclado se ingresa desde el main y se pasa como parametro
    GestorTiendas gt= new GestorTiendas();
    Vendedor vendedor = new Vendedor("Julian","Anchoverri","asdasd@gmail.com","Julo390","123Abc");
    Cliente cliente = new Cliente("Juanpi","Gallego","opiopiop@gmail.com","JuanpyG25","Abc123","Calle 123");
    GestorVendedores gv= new GestorVendedores();
    GestorClientes gc= new GestorClientes();
    GestorOrdenes go= new GestorOrdenes();
    public final int maximaCantidadOrdenes=50;
    public final int maximaCantidadHoras=72;

    private <T> T devolverObjeto(Class<T> tipo){

        while (true) {
            // ==== Selección de categoría de tienda ====
            CategoriaTienda categoriaTienda = null;
            while (categoriaTienda == null) {
                gt.listarCategoriasTiendas();
                System.out.println("Ingrese la opción que desea seleccionar, o F para volver al menú anterior");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("F")) break;

                try {
                    categoriaTienda = gt.seleccionarCategoriasTiendas(opcion);
                } catch (ElementoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (categoriaTienda == null) break; // usuario eligió volver
            if (tipo == CategoriaTienda.class) return (T) categoriaTienda;

            // ==== Selección de tienda ====
            Tienda tienda = null;
            while (tienda == null) {
                gt.listarTiendas(categoriaTienda);
                System.out.println("Ingrese la opción que desea seleccionar, o F para volver al menú anterior");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("F")) break;

                try {
                    tienda = gt.seleccionarTiendas(categoriaTienda, opcion);
                } catch (ElementoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tienda == null) continue; // vuelve a seleccionar categoría de tienda
            if (tipo == Tienda.class) return (T) tienda;

            // ==== Selección de categoría de producto ====
            CategoriaProducto categoriaProducto = null;
            while (categoriaProducto == null) {
                gt.listarCategoriasProductos(tienda);
                System.out.println("Ingrese la opción que desea seleccionar, o F para volver al menú anterior");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("F")) break;

                try {
                    categoriaProducto = gt.seleccionarCategoriasProductos(tienda, opcion);
                } catch (ElementoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (categoriaProducto == null) continue; // vuelve a seleccionar tienda
            if (tipo == CategoriaProducto.class) return (T) categoriaProducto;

            // ==== Selección de producto ====
            Producto producto = null;
            while (producto == null) {
                gt.listarProductos(categoriaProducto);
                System.out.println("Ingrese la opción que desea seleccionar, o F para volver al menú anterior");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("F")) break;

                try {
                    producto = gt.seleccionarProductos(categoriaProducto, opcion);
                } catch (ElementoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (producto == null) continue; // vuelve a seleccionar categoría de producto
            if (tipo == Producto.class) return (T) producto;
        }
        return null;
    }

    public void mostrarMenuVendedor() {
        while (true){
            System.out.println("\n===== MENÚ DEL VENDEDOR =====");
            System.out.println("\tBienvenido, " + vendedor.getNombreUsuario());
            System.out.println("\t-----------------------------");
            System.out.println("1. Ver perfil");
            System.out.println("2. Ver mis tiendas");
            System.out.println("3. Crear nueva tienda");
            System.out.println("4. Modificar tienda");
            System.out.println("5. Eliminar tienda");
            System.out.println("6. modificar orden de compra");
            System.out.println("7. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Opción inválida. Ingrese un número: ");
                scanner.next();
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println(vendedor.toString());
                    break;
                case 2:
                    for(Tienda t: gv.obtenerTiendasDe(vendedor)){
                        System.out.println("- "+t.toString());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("En que categoria desea registrar la tienda?");
                        CategoriaTienda categoria= devolverObjeto(CategoriaTienda.class);
                        System.out.print("Ingrese ID de la tienda: ");
                        String id = scanner.nextLine();
                        System.out.print("Ingrese nombre de la tienda: ");
                        String nombre = scanner.nextLine();
                        Tienda tienda = gt.crearTienda(id,nombre);
                        gt.agregarTienda(tienda, categoria);
                        gv.agregarVendedor(vendedor);
                        gv.agregarTiendaA(vendedor,tienda);
                        System.out.println("Tienda creada: " + tienda.getNombre());
                    } catch (ElementoYaExisteException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    CategoriaTienda cat= devolverObjeto(CategoriaTienda.class);
                    gt.listarTiendas(cat);
                    Tienda t;
                    while (true) {
                        System.out.println("Ingrese el nombre de la tienda");
                        try {
                            t = gt.seleccionarTiendas(cat, scanner.nextLine());
                            break;
                        } catch (ElementoNoEncontradoException e) {
                            System.out.println("Error: "+e.getMessage());
                        }
                    }
                    while (true) {
                        System.out.println("Ingrese cual de los siguientes atributos quiere modificar:\n" +
                                "1. Nombre de la tienda (Actual: " + t.getNombre() + ")\n" +
                                "2. Limite de ordenes de compra simultaneas (Actual: " + t.getLimiteDeOrdenes() + ")\n" +
                                "3. Tiempo para que expire una orden de compra (Actual: " + t.getLimiteHorasOrden() + ")\n" +
                                "4. Volver");
                        int eleccion = scanner.nextInt();
                        scanner.nextLine();
                        switch (eleccion) {
                            case 1:
                                while (true) {
                                    System.out.println("Ingrese el nuevo nombre");
                                    String nuevoNombre = scanner.nextLine();
                                    try {
                                        gt.cambiarNombreTienda(nuevoNombre, t, cat);
                                        System.out.println("Nombre actualizado");
                                        break;
                                    } catch (ElementoYaExisteException e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                }
                                break;
                            case 2:
                                while (true) {
                                    System.out.println("Ingrese el nuevo limite (Maximo: " + maximaCantidadOrdenes + ")");
                                    int nuevoLimite = scanner.nextInt();
                                    scanner.nextLine();
                                    if (1 <= nuevoLimite && nuevoLimite <= maximaCantidadOrdenes) {
                                        t.setLimiteDeOrdenes(nuevoLimite);
                                        System.out.println("Limite actualizado");
                                        // actualizar ordenes??? creo que no porque solo influye en el cliente que quiera hacer una
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                while (true) {
                                    System.out.println("Ingrese el nuevo limite (Maximo: " + maximaCantidadHoras + ")");
                                    int nuevoLimite = scanner.nextInt();
                                    scanner.nextLine();
                                    if (1 <= nuevoLimite && nuevoLimite <= maximaCantidadHoras) {
                                        t.setLimiteHorasOrden(nuevoLimite);
                                        System.out.println("Limite actualizado");
                                        // actualizar ordenes??? creo que no porque solo influye en el cliente que quiera hacer una
                                        break;
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Volviendo ...");
                                return;
                            default:
                                System.out.println("Opción inválida, intente nuevamente.");
                        }
                    }
                    break;
                case 5:
                    try {
                        CategoriaTienda cate= devolverObjeto(CategoriaTienda.class);
                        gt.listarTiendas(cate);
                        System.out.println("Ingrese el nombre de la tienda: ");
                        gt.eliminarTienda(scanner.nextLine(), cate);
                        System.out.println("Tienda eliminada");
                    }catch (ElementoNoEncontradoException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    modificarOrden(); // pendiente
                    break;
                case 7:
                    System.out.println("Volviendo al menú principal...");
                    return;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } ;
    }

    public void mostrarMenuCliente() {
        while (true){
            System.out.println("\n===== MENÚ DEL CLIENTE =====");
            System.out.println("Bienvenido, " + cliente.getNombreUsuario());
            System.out.println("-----------------------------");
            System.out.println("1. Ver perfil");
            System.out.println("2. Ver el catalogo");
            System.out.println("3. Ver mis compras");
            System.out.println("4. Ver mis reseñas");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Opción inválida. Ingrese un número: ");
                scanner.next();
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println(cliente.toString());
                    break;
                case 2:
                    Producto p = devolverObjeto(Producto.class);
                    if(p==null){
                        break;
                    }
                    System.out.println("seleccione una opcion: ");
                    System.out.println("1. hacer una orden de compra: ");
                    System.out.println("2. hacer reseña: ");
                    System.out.println("3. salir: ");
                    int seleccion;
                    do {
                        seleccion = scanner.nextInt();
                        switch (seleccion) {
                            case 1:
                                System.out.println("Seleccione nuevamente la tienda: ");
                                Tienda t= devolverObjeto(Tienda.class);


                                System.out.println("orden generada");
                                seleccion = 3;
                                break;
                            case 2:
                                try {
                                    crearResenia(p);
                                } catch (PuntuacionInvalidaException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("Reseña finalizada");
                                seleccion = 3;
                                break;
                            case 3:
                                System.out.println("Volviendo al menú principal...");
                                return;
                            default:
                                System.out.println("opcion invalida, vuelva a ingresar");
                        }

                    } while(seleccion != 3);
                    break;
                case 3:
                    Carrito carrito= new Carrito();
                    System.out.println("=== Agrege al carrito solo productos de la misma tienda ===");
                    while (true){
                        System.out.println("== Menu de seleccion == \n Va a seleccionar el producto que quiere agregar al carrito (Ingrese F para terminar)");
                        Producto producto= devolverObjeto(Producto.class);
                        if(producto==null) break;
                        carrito.agregarProducto(producto);
                    }
                    carrito.calcularTotal();
                    if (carrito.getTotal()==0){
                        System.out.println("Carrito vacio. Operacion cancelada");
                        break;
                    }
                    System.out.println("== Menu de seleccion == \n Va a seleccionar la tienda de la que son sus productos (Ingrese F para terminar)");
                    Tienda tienda;
                    do {
                        tienda = devolverObjeto(Tienda.class);
                    }while (tienda == null);
                    if(go.getColeccionOrdenesTiendas().get(tienda.getClave()).size() >= tienda.getLimiteDeOrdenes()) {
                        System.out.println("Se alcanzó el límite de órdenes activas en la tienda. Operacion cancelada");
                        break;
                    }
                    LocalDate fecha = LocalDate.now();
                    String fechaStr = fecha.toString();// para crear id unico
                    OrdenDeCompra orden= go.crearOrden(cliente.getNombreUsuario()+fechaStr, carrito);
                    go.agregarOrdenA(tienda,orden);
                    gc.agregarOrdenA(cliente,orden);




                case 3:
                    System.out.println("Compras de "+ cliente.getNombreCompleto());
                    verCompras();
                    break;

                case 4:
                    System.out.println("Reseñas de " +  cliente.getNombreCompleto());
                    listarComentarios();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    return;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    public static void main(String[] args) {



    }
}