package maquina_snacks_mejorada.presentacion;

import maquina_snacks_mejorada.dominio.Snack;
import maquina_snacks_mejorada.servicio.IServicioSnacks;
import maquina_snacks_mejorada.servicio.ServicioSnacksArchivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks() {
        var salir = false;
        Scanner consola = new Scanner(System.in);
        // Creamos el objeto para obtener el servicio de snacks (los métodos ya no son estáticos):
        IServicioSnacks servicioSnacks = new ServicioSnacksArchivos();
        // Creamos la lista de productos de tipo Snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("--- Máquina de snacks ---");
        servicioSnacks.mostrarSnacks(); // Mostrar inventario disponible

        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos, servicioSnacks);
            } catch(Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            } finally {
                System.out.println(); // Para imprimir un salto de línea en cada iteración
            }
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                Menu:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar nuevo snack
                4. Inventario de snacks
                5. Salir
                Elige una opción: \s""");

        // Leemos y retornamos la opción del usuario
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola,
                                            List<Snack> productos, IServicioSnacks servicioSnacks) {
        var salir = false;
        switch (opcion) {
            case 1 -> comprarSnack(consola, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnacks);
            case 4 -> listarInventarioSnacks(consola, servicioSnacks);
            case 5 -> {
                System.out.println("Nos vemos!");
                salir = true;
            }
            default -> System.out.println("Opción inválida " + opcion);
        }
        return salir;
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks) {
        System.out.print("Qué snack quieres comprar (id)? ");
        var idSnack = Integer.parseInt(consola.nextLine());
        // Primero validamos que el snack existe:
        var snackEncontrado = false;
        for(var snack : servicioSnacks.getSnacks()){
            if (idSnack == snack.getIdSnack()) {
                productos.add(snack);
                System.out.println("Ok, snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado) {
            System.out.println("Id de snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos) {
        var ticket = "*** Ticket de venta ***";
        var total = 0.0;
        for(var producto : productos) {
            ticket += "\n\t- " + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnacks) {
        System.out.print("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        servicioSnacks.agregarSnack(new Snack(nombre, precio)); // Esto crea un nuevo snack y lo
        // añade directamente a la lista snacks (creada en Snacks)
        System.out.println("Tu snack se ha agregado correctamente");
        servicioSnacks.mostrarSnacks();
    }

    private static void listarInventarioSnacks(Scanner consola, IServicioSnacks servicioSnacks){
        servicioSnacks.mostrarSnacks();
    }
}
