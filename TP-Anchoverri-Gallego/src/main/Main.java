package main;

import gestores.GestorTiendas;
import modelos.CategoriaProducto;
import modelos.CategoriaTienda;
import modelos.Producto;
import modelos.Tienda;
import utiles.ElementoNoEncontradoException;
import utiles.Seleccionable;

import java.util.Scanner;

public class Main {

    Scanner scanner= new Scanner(System.in); //DEBERIA SER EL UNICO SCANNER DEL PROYECTO. Todo dato por teclado se ingresa desde el main y se pasa como parametro
    GestorTiendas gt= new GestorTiendas();

    private <T> T devolverObjeto(Class<T> tipo){

        while (true) {
            gt.listarCategoriasTiendas();
            System.out.println("Ingrese la opcion que desea seleccionar, o F para volver al menu anterior");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("F")) break;
            CategoriaTienda categoriaTienda = null;
            try {
                categoriaTienda = gt.seleccionarCategoriasTiendas(opcion);
            } catch (ElementoNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
            if(tipo==CategoriaTienda.class) return (T) categoriaTienda;
            while (true) {
                gt.listarTiendas(categoriaTienda);
                System.out.println("Ingrese la opcion que desea seleccionar, o F para volver al menu anterior");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("F")) break;
                Tienda tienda = null;
                try {
                    tienda = gt.seleccionarTiendas(categoriaTienda, opcion);
                } catch (ElementoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
                if(tipo==Tienda.class) return (T) tienda;

                while (true) {
                    gt.listarCategoriasProductos(tienda);
                    System.out.println("Ingrese la opcion que desea seleccionar, o F para volver al menu anterior");
                    opcion = scanner.nextLine();
                    if (opcion.equalsIgnoreCase("F")) break;
                    CategoriaProducto categoriaProducto = null;
                    try {
                        categoriaProducto = gt.seleccionarCategoriasProductos(tienda, opcion);
                    } catch (ElementoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    if(tipo==CategoriaProducto.class) return (T) categoriaProducto;
                    while (true) {
                        gt.listarProductos(categoriaProducto);
                        System.out.println("Ingrese la opcion que desea seleccionar, o F para volver al menu anterior");
                        opcion = scanner.nextLine();
                        if (opcion.equalsIgnoreCase("F")) break;
                        Producto producto = null;
                        try {
                            producto = gt.seleccionarProductos(categoriaProducto, opcion);
                        } catch (ElementoNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                        return (T) producto;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {



    }
}