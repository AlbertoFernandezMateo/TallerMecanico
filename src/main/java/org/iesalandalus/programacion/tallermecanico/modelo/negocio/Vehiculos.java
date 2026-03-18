package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {
    List<Vehiculo> listaVehiculos;

    public Vehiculos(){
        listaVehiculos = new ArrayList<>();
    }
    public List<Vehiculo> get(){
        return listaVehiculos;
    }
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (listaVehiculos.contains(vehiculo)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        listaVehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        return (listaVehiculos.contains(vehiculo)) ? vehiculo : null;
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        Vehiculo buscado = buscar(vehiculo);

        if (!listaVehiculos.contains(buscado)){
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        listaVehiculos.remove(buscado);

    }
}
