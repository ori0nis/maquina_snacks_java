package maquina_snacks_mejorada.servicio;

import maquina_snacks_mejorada.dominio.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks{
    private static final List<Snack> snacks; // Al definir la Lista snacks como final, lo que hacemos es evitar que se
    // le pueda asignar otra Lista. Pero el contenido de la Lista SÍ puede cambiar.

    // No agregamos un constructor (eso es para objetos), sino un bloque estático que sirve para inicializar atributos
    // de tipo estático:
    static {
        snacks = new ArrayList<>(); // Inicializamos la lista aquí para que sea realmente estática.
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Refresco", 50));
        snacks.add(new Snack("Sandwich", 120));
    }

    public void agregarSnack(Snack snack) {
        snacks.add(snack);
    }

    public void mostrarSnacks() {
        var inventarioSnacks = "";

        for(var snack : snacks) {
            inventarioSnacks += snack.toString() + "\n";
        }

        System.out.println("--- Snacks en el inventario ---");
        System.out.println(inventarioSnacks);
    }

    public List<Snack> getSnacks() {
        return snacks;
    }
}
