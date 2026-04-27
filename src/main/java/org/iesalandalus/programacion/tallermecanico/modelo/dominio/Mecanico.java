package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;

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
    public void anadirPrecioMaterial (float precioMaterial) throws TallerMecanicoExcepcion {
        if (precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que el trabajo está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }
    public float getPrecioEspecifico(){
        return (getHoras() * FACTOR_HORA) + (getPrecioMaterial() * FACTOR_PRECIO_MATERIAL);

    }

    @Override
    public String toString() {
        return String.format("Mecánico, precioMaterial=%s)", precioMaterial);
    }
}
