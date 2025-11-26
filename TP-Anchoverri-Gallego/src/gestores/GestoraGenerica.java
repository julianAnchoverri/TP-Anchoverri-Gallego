package gestores;

import modelos.Cliente;

import java.util.HashMap;
import java.util.Map;

public class GestoraGenerica <T>{
    Map<String, T> coleccionGenerica;
    public GestoraGenerica()
    {
        coleccionGenerica = new HashMap<>();
    }
}
