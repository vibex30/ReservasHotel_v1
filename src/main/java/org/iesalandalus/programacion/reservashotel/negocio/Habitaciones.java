package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {
    private int capacidad;
    private int tamano;
    private Habitacion[] listaHabitaciones;

    // Constructor
    public Habitaciones(int capacidad) {
        this.capacidad = capacidad;
        this.tamano = 0;
        this.listaHabitaciones = new Habitacion[capacidad];
    }


    public Habitacion[] get(TipoHabitacion tipoHabitacion) {
        if (listaHabitaciones == null)
            throw new NullPointerException("Error, la lista no puede ser nula");
        Habitacion[] copia = new Habitacion[this.capacidad];


        int puntero = 0;

        for (Habitacion elemento : listaHabitaciones)
            if (elemento.getTipoHabitacion() == tipoHabitacion) {
                copia[puntero] = new Habitacion(elemento);
                puntero++;

            }

        return copia;


    }




    // Método para obtener la lista de habitaciones
    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    // Método para realizar una copia profunda de la lista de habitaciones
    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copia = new Habitacion[capacidad];
        for (int i = 0; i < tamano; i++) {
            copia[i] = listaHabitaciones[i];
        }
        return copia;
    }

    // Método para obtener el tamaño actual de la lista
    public int getTamano() {
        return tamano;
    }

    // Método para obtener la capacidad de la lista
    public int getCapacidad() {
        return capacidad;
    }

    // Método para insertar una habitacion
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException{

        if (tamanoSuperado(tamano)) {
            throw new IllegalStateException("ERROR: Se ha superado el tamaño permitido.");
        }
        if(buscarIndice(habitacion)!=-1){
            throw new OperationNotSupportedException("Error not supor");
        }
        if (buscarIndice(habitacion) == -1) {
            listaHabitaciones[tamano++] = habitacion;
        }

    }

    // Método para buscar el índice de una habitacion
    private int buscarIndice(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (listaHabitaciones[i].equals(habitacion)) {
                return i;
            }
        }
        return -1;
    }

    // Método para verificar si el tamaño ha sido superado
    private boolean tamanoSuperado(int indice) {
        return indice >= capacidad;
    }

    // Método para buscar una habitacion
    public Habitacion buscar(Habitacion habitacion) {
        int indice = buscarIndice(habitacion);
        return (indice != -1) ? listaHabitaciones[indice] : null;
    }

    // Método para borrar una habitacion
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {

        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;

        }
//AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII ES
        if(buscarIndice(habitacion)!=-1){
            throw new OperationNotSupportedException("Error not suporTEEEEEEEDDDD");
        }


    }

    // Método para desplazar una posición hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            listaHabitaciones[i] = listaHabitaciones[i + 1];
        }
    }

}
