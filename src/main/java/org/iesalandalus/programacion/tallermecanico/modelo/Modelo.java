package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public Modelo(){comenzar();}
    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void terminar(){
        System.out.println("Modelo terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion{
        clientes.insertar(new Cliente((cliente)));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Trabajo revision) throws TallerMecanicoExcepcion {
        Cliente cliente = clientes.buscar(revision.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(revision.getVehiculo());
        revisiones.insertar(new Trabajo(cliente,vehiculo,revision.getFechaInicio()));
    }
    public Cliente buscar(Cliente cliente){
        cliente = Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual.");
        return new Cliente(cliente);

    }
    public Vehiculo buscar(Vehiculo vehiculo){
        vehiculo = Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe un vehiculo igual.");
        return vehiculo;
    }
    public Trabajo buscar(Trabajo revision){
        revision = Objects.requireNonNull(revisiones.buscar(revision), "No existe una revision igual.");
        return new Trabajo(revision);

    }
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion{
        return new Cliente(clientes.modificar(cliente, nombre, telefono));
    }
    public Trabajo anadirHoras(Trabajo revision, int horas) throws TallerMecanicoExcepcion{
        return new Trabajo(revisiones.anadirHoras(revision, horas));
    }
    public Trabajo anadirPrecioMaterial(Trabajo revision, float precioMaterial)throws TallerMecanicoExcepcion{
        return new Trabajo(revisiones.anadirPrecioMaterial(revision, precioMaterial));
    }
    public Trabajo cerrar(Trabajo revision, LocalDate fechaFin) throws TallerMecanicoExcepcion{
        return new Trabajo(revisiones.cerrar(revision, fechaFin));
    }
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion{
        List<Trabajo> revisionesCliente = revisiones.get(cliente);
        for (Trabajo revision : revisionesCliente){
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        List<Trabajo> revisionesVehiculo = revisiones.get(vehiculo);
        for (Trabajo revision : revisionesVehiculo){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }
    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion{
        revisiones.borrar(revision);
    }
    public List<Cliente> getClientes(){
        List<Cliente> copiaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()){
            copiaClientes.add(new Cliente(cliente));
        }
        return copiaClientes;
    }
    public List<Vehiculo> getVehiculos() {return vehiculos.get();}
    public List<Trabajo> getRevisiones(){
        List<Trabajo> copiaRevisiones = new ArrayList<>();
        for (Trabajo revision : revisiones.get()){
            copiaRevisiones.add(new Trabajo(revision));
        }
        return copiaRevisiones;
    }
    public List<Trabajo> getRevisiones(Cliente cliente) {
        List<Trabajo> revisionesCliente = new ArrayList<>();
        for (Trabajo revision : revisiones.get(cliente)){
            revisionesCliente.add(new Trabajo(revision));
        }
        return revisionesCliente;
    }
    public List<Trabajo> getRevisiones(Vehiculo vehiculo) {
        List<Trabajo> revisionesVehiculo = new ArrayList<>();
        for (Trabajo revision : revisiones.get(vehiculo)){
            revisionesVehiculo.add(new Trabajo(revision));
        }
        return revisionesVehiculo;
    }

}
