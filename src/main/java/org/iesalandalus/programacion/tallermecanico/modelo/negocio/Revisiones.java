package org.iesalandalus.programacion.tallermecanico.modelo.negocio;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.io.ObjectStreamException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private final List<Revision>  coleccionRevision;

    public Revisiones(){
        coleccionRevision = new ArrayList<>();
    }
    public List<Revision> get(){
        return  new ArrayList<>(coleccionRevision);
    }
    public List<Revision> get(Cliente cliente){
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : coleccionRevision){
            if (revision.getCliente().equals(cliente)){
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }
    public List<Vehiculo> get(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevision) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
    }
    public void insertar(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "No se puede insertar una revision nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevision.add(revision);
    }
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision)throws TallerMecanicoExcepcion {
        for (Revision revision : coleccionRevision) {
            if (!revision.estaCerrada()) {
                if (revision.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revision en curso.");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehiculo está actualmente en revisión.");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene una revision posterior.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El vehiculo tiene una revision posterior.");
                }
            }
        }
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion{
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirHoras(horas);
        return revisionEncontrada;
    }
    private Revision getRevision(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No puedo operar sobre una revision nula.");
        Revision revisionEncontrada = buscar(revision);
        if(revisionEncontrada == null){
            throw new TallerMecanicoExcepcion("No existe ninguna revision igual.");
        }
        return revisionEncontrada;
    }
    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirPrecioMaterial(precioMaterial);
        return revisionEncontrada;
    }
    public Revision cerrar(Revision revision,LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.cerrar(fechaFin);
        return revisionEncontrada;
    }
    public Revision buscar(Revision revision){
        Objects.requireNonNull(revision, "No puedo buscar una revision nula.");
        int indice = coleccionRevision.indexOf(revision);
        return (indice == -1) ?  null : coleccionRevision.get(indice);
    }
    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No puedo borrar una revision nula.");
        if(!coleccionRevision.contains(revision)){
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        coleccionRevision.remove(revision);

    }

}


