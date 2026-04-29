package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    static Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class);

    public void GestorEventos(Evento... eventos){
        Objects.requireNonNull(eventos, "El evento no puede ser nulo.");
        for (Evento evento : Evento.values()) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor,Evento... eventos){
        for (Evento evento : Evento.values()) {
            receptores.get(evento).add(receptor);
        }
    }

    public void desuscribir(ReceptorEventos receptor,Evento... eventos){
        for (Evento evento : Evento.values()) {
            receptores.get(evento).remove(receptor);
        }
    }

    public void notificar(Evento evento){
        for (ReceptorEventos receptor : receptores.get(evento)) {
            receptor.actualizar(evento);
        }
    }
}

