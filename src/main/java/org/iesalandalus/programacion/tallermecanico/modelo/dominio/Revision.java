package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Revision extends Trabajo {
    private final float FACTOR_HORA=35f;

    public  Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechainicio){
        super(cliente, vehiculo, fechainicio);

    }

    public  Revision(Revision revision){
        super(revision);

    }
    @Override
    public float getPrecioEspecifico(){
        return getHoras() * FACTOR_HORA;
    }

    @Override
    public String toString() {
        return String.format("Revision (FACTOR_HORA=%s)", FACTOR_HORA);
    }
}
