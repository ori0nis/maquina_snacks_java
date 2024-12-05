package maquina_snacks_mejorada.servicio;

import maquina_snacks_mejorada.dominio.Snack;
import java.util.List;

public interface IServicioSnacks {
    public abstract void agregarSnack(Snack snack);
    public abstract void mostrarSnacks();
    List<Snack> getSnacks();
}
