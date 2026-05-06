package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO("Mecánico"),
    REVISION("Revisión");

    private String nombre;

    private  TipoTrabajo(String nombre){}
    public static TipoTrabajo get(Trabajo trabajo){
        return get(trabajo);
    }
}
