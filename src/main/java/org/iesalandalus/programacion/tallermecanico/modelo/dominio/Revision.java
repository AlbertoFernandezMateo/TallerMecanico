package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30F;
    private static final float PRECIO_DIA = 10F;
    private static final float PRECIO_MATERIAL = 1.5F;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFormatoInicio(fechaInicio);
        fechaFin = null;
        horas = 0;
        precioMaterial = 0;
    }
    public Revision(Revision revision){
        Objects.requireNonNull(revision, "La revision no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        vehiculo = revision.vehiculo;
        fechaInicio = revision.fechaInicio;
        fechaFin = revision.fechaFin;
        horas = revision.horas;
        precioMaterial = revision.precioMaterial;
    }

    public Cliente getCliente(){return cliente;}

    private void setCliente(Cliente cliente){
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }
    public  Vehiculo getVehiculo(){return vehiculo;}

    private void  setVehiculo(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo,"El vehiculo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio(){return fechaInicio;}

    private void setFechaInicio(LocalDate fechaInicio){
        Objects.requireNonNull(fechaInicio, "La fecha no de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }
}
