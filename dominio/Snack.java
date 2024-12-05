package maquina_snacks_mejorada.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {
    private static int contadorSnacks = 0; // Variable para saber los snacks creados
    private int idSnack; // Relacionada con contadorSnacks
    private String nombre;
    private double precio;

    public Snack() { // El constructor debe estar vacío porque estamos creando un JavaBean
        this.idSnack = ++contadorSnacks;
    }

    public Snack(String nombre, double precio) {
        this(); // Mandamos a llamar al constructor vacío para poder implementar la lógica
        // de idSnack. Esta llamada siempre debe ser la primera sentencia.
        this.nombre = nombre;
        this.precio = precio;
    }

    public static int getContadorSnacks() {
        return contadorSnacks;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "idSnack=" + idSnack +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    public String escribirSnack() {
        return idSnack + "," + nombre + "," + precio;
    }

    // equals y hashCode ayudan a hacer comparaciones entre objetos de manera más fácil.
    // equals compara el contenido y hashCode asigna un número a cada objeto, que luego compara.
    // Estos métodos se suelen introducir cuando los elementos de la clase se van a meter
    // dentro de una lista.

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
