package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo {
    private final float FACTOR_HORA=30f;
    private final float FACTOR_PRECIO_MATERIAL=1.5f;
    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente, vehiculo, fechaInicio);


    }
    public  Mecanico(Mecanico mecanico){
        super(mecanico);

    }

    @Override
    public float getPrecioMaterial() {
        return precioMaterial;
    }
    public void anadirPrecioMaterial(float precioMaterial){

    }
    public float getPrecioEspecifico(){
        return (getHoras() * FACTOR_HORA) * FACTOR_PRECIO_MATERIAL;

    }

    @Override
    public String toString() {
        return String.format("Mecánico (FACTOR_HORA=%s, FACTOR_PRECIO_MATERIAL=%s, precioMaterial=%s)", FACTOR_HORA, FACTOR_PRECIO_MATERIAL, precioMaterial);
    }
}
