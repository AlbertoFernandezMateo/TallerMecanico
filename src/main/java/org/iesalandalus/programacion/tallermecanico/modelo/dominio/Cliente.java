package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.awt.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private static String ER_NOMBRE = "A-Z{1} \s a-z";
    private static String ER_DNI = "0-9{8} [A-Z]";
    private static String ER_TELEFONO = "0-9{9}";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setDni(dni);
        setTelefono(telefono);
        setNombre(nombre);
    }
    public Cliente(Cliente cliente){
        Objects.requireNonNull(cliente,"Cliente no puede ser nulo");
        nombre = cliente.nombre;
        dni = cliente.dni;
        telefono = cliente.telefono;
    }

    private boolean comprobarLetraDni(String dni) {
        String letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = Integer.parseInt(dni.substring(0,8)) % 23;
        return (dni.charAt(8) == letraCalculada.charAt(resto));
    }

    public static Cliente get (String dni){
        return new Cliente("Pedro",dni,"627842325");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        if (!nombre.matches(ER_NOMBRE)){
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        Objects.requireNonNull(dni,"No puede ser nulo.");
        if (!dni.matches(ER_DNI)){
            throw new IllegalArgumentException("No puede ser nulo.");
        }
        if(!comprobarLetraDni(dni)){
            throw new IllegalArgumentException("No puede ser nulo.");
        }
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono,"No puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("No puede ser nulo.");
        }
        this.telefono = telefono;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}
