package org.iesalandalus.programacion.tallermecanico.modelo.negocio;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private final List<Trabajo>  coleccionRevision;

    public Revisiones(){
        coleccionRevision = new ArrayList<>();
    }
    public List<Trabajo> get(){
        return  new ArrayList<>(coleccionRevision);
    }
    public List<Trabajo> get(Cliente cliente){
        List<Trabajo> revisionesCliente = new ArrayList<>();
        for (Trabajo revision : coleccionRevision){
            if (revision.getCliente().equals(cliente)){
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> revisionesVehiculo = new ArrayList<>();
        for (Trabajo revision : coleccionRevision) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Trabajo revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevision.add(revision);
    }
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision)throws TallerMecanicoExcepcion {
        for (Trabajo revision : coleccionRevision) {
            if (!revision.estaCerrada()) {
                if (revision.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    public Trabajo anadirHoras(Trabajo revision, int horas) throws TallerMecanicoExcepcion{
        Trabajo revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirHoras(horas);
        return revisionEncontrada;
    }
    private Trabajo getRevision(Trabajo revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        Trabajo revisionEncontrada = buscar(revision);
        if(revisionEncontrada == null){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        return revisionEncontrada;
    }
    public Trabajo anadirPrecioMaterial(Trabajo revision, float precioMaterial) throws TallerMecanicoExcepcion{
        Trabajo revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirPrecioMaterial(precioMaterial);
        return revisionEncontrada;
    }
    public Trabajo cerrar(Trabajo revision, LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Trabajo revisionEncontrada = getRevision(revision);
        revisionEncontrada.cerrar(fechaFin);
        return revisionEncontrada;
    }
    public Trabajo buscar(Trabajo revision){
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        int indice = coleccionRevision.indexOf(revision);
        return (indice == -1) ?  null : coleccionRevision.get(indice);
    }
    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        if(!coleccionRevision.contains(revision)){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        coleccionRevision.remove(revision);

    }

}


