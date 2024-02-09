package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
//TODO QUITAR TODOS LOS NUMEROS
    SALIR("1.-Salir"),
    INSERTAR_HUESPED("2.-Insertar huesped"),
    BUSCAR_HUESPED("3.-Buscar huesped"),
    BORRAR_HUESPED("4.-Borrar huesped"),
    MOSTRAR_HUESPEDES("5.-Mostrar huesped"),
    INSERTAR_HABITACION("6.-Insertar habiatación"),
    BUSCAR_HABITACION("7.-Buscar habitación"),
    BORRAR_HABITACION("8.-Borrar habitación"),
    MOSTRAR_HABITACIONES("Mostrar habitaciones"),
    INSERTAR_RESERVA("Insertar reserva"),
    ANULAR_RESERVA("Anular reserva"),
    MOSTRAR_RESERVAS("Mostrar reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad");



    private String mensajeAmostrar;

    private Opcion(String mensajeAmostrar){
        this.mensajeAmostrar=mensajeAmostrar;
    }

    @Override
    public String toString() {
        return
                1+ordinal()+".-" + mensajeAmostrar;
    }


}
