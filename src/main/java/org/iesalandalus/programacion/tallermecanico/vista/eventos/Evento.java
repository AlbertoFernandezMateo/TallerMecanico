package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(11,"Insertar cliente."),
    BUSCAR_CLIENTE(12, "Buscar cliente."),
    BORRAR_CLIENTE(13, "Borrar cliente."),
    LISTAR_CLIENTE(14, "Listar cliente."),
    MODIFICAR_CLIENTE(15, "Modificar cliente."),
    //-
    INSERTAR_VEHICULO(21, "Insertar vehiculo"),
    BUSCAR_VEHICULO(22, "Buscar vehiculo."),
    BORRAR_VEHICULO(23, "Borrar vehiculo."),
    LISTAR_VEHICULOS(24, "Listar vehiculo."),
    //-
    INSERTAR_TRABAJO(31, "Insertar revisión."),
    INSERTAR_MECANICO(32,"Insertar Mecanico"),
    BUSCAR_TRABAJO(33, "Buscar revisión."),
    BORRAR_TRABAJO(34, "Borrar revisión."),
    LISTAR_TRABAJOS(35, "Listar revisión."),
    LISTAR_TRABAJOS_CLIENTE(36, "Listar revisión de clientes"),
    LISTAR_TRABAJOS_VEHICULO(37, "Listar revisión de vehiculos."),
    ANADIR_HORAS_TRABAJO(38, "Añadir horas revisión."),
    ANADIR_PRECIO_MATERIAL_TRABAJO(39, "Añadir precio material revisión"),
    CERRAR_TRABAJO(40, "Cerrar revisión."),
    SALIR(0,"Salir");

    private final int codigo;
    private final String texto;
    private static final Map<Integer, Evento> opciones = new HashMap<>();

    static {
        for (Evento evento : values()) {
            opciones.put(evento.codigo, evento);

        }
    }
    private Evento(int codigo, String texto){
        this.codigo = codigo;
        this.texto = texto;
    }
    public static boolean esValido(int codigo) { return opciones.containsKey(codigo);}

    public static Evento get(int codigo) {
        if (!esValido(codigo)) {
            throw new IllegalArgumentException("El numero de la opción no es correcto.");
        }
        return opciones.get(codigo);
    }

    @Override
    public String toString() {
        return (texto);
    }
}
