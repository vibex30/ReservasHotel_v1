package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Arrays;


public class Reservas {
    private int capacidad;
    private int tamano;
    private Reserva[] listaReservas;

    // Constructor
    public Reservas(int capacidad) {
        this.capacidad = capacidad;
        this.tamano = 0;
        this.listaReservas = new Reserva[capacidad];
    }

    // Método para obtener la lista de reservas
    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    // Método para realizar una copia profunda de la lista de reservas
    private Reserva[] copiaProfundaReservas() {
        Reserva[] copia = new Reserva[capacidad];
        for (int i = 0; i < tamano; i++) {
            copia[i] = listaReservas[i];
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

    // Método para insertar una reserva
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null)
                throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        if (tamano>getCapacidad()) {
            throw new OperationNotSupportedException("ERROR: Se ha superado el tamaño permitido.");
        }
        if (buscarIndice(reserva) == -1) {
            listaReservas[tamano++] = reserva;
        }
    }

    // Método para buscar el índice de una reserva
    private int buscarIndice(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("ERROR: El índice de la  reserva no puede ser nulo.");
        for (int i = 0; i < tamano; i++) {
            if (listaReservas[i].equals(reserva)) {
                return i;
            }
        }
        return -1;
    }

    // Método para verificar si el tamaño ha sido superado
    private boolean tamanoSuperado(int indice) {
        return indice >= capacidad;
    }
    private boolean capacidadSuperada(int indice) {
        if (indice > capacidad)
            return true;
        return false;
    }
    // Método para buscar una reserva
    public Reserva buscar(Reserva reserva) {
        if(reserva==null)
            throw new NullPointerException("no se puede borrar una reserva nula ");
        //int indice = buscarIndice(reserva);
        //return (indice != -1) ? listaReservas[indice] : null;
        int busqueda = buscarIndice(reserva);
        if (busqueda == -1)
            return null;
        return reserva;
    }

    // Método para borrar una reserva
    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        if (reserva == null)
            throw new NullPointerException("ERROR: La reserva a buscar no puede ser nula.");
        if (buscar(reserva) == null)
            throw new IllegalArgumentException("ERROR: La reserva a buscar es nula.");
        int indice = buscarIndice(reserva);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    // Método para desplazar una posición hacia la izquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            listaReservas[i] = listaReservas[i + 1];
        }
    }

    public Reserva[] getReservas(Huesped huesped) {
        Reserva[] reservasHuesped = new Reserva[this.tamano];
        int j = 0;
        for (int i = 0; i < this.tamano; i++) {
            if (this.listaReservas[i].getHuesped().equals(huesped)) {
                reservasHuesped[j] = this.listaReservas[i];
                j++;
            }
        }
        return Arrays.copyOf(reservasHuesped, j);
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        Reserva[] reservasTipoHabitacion = new Reserva[this.tamano];
        int j = 0;
        for (int i = 0; i < this.tamano; i++) {
            if (this.listaReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion[j] = this.listaReservas[i];
                j++;
            }
        }
        return Arrays.copyOf(reservasTipoHabitacion, j);

    }







    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        Reserva[] reservasFuturasHabitacion = new Reserva[this.tamano];
        int j = 0;
        for (int i = 0; i < this.tamano; i++) {
            //verifico si la reserva pertenece a la habitación específicada y si la fecha de inicio de la reserva es posterior a la fecha actual
            if (this.listaReservas[i].getHabitacion().equals(habitacion) && this.listaReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturasHabitacion[j] = this.listaReservas[i];
                j++;
            }
        }
        return Arrays.copyOf(reservasFuturasHabitacion, j);
    }

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha){
        if(reserva==null)
            throw new NullPointerException("Reserva nula");
        if(fecha==null)
            throw new NullPointerException("Fecha nula");
        if(fecha.isBefore(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isAfter(reserva.getFechaFinReserva().atStartOfDay()))
            throw new IllegalArgumentException("La fecha de inicio  de reserva no puede ser posterior al check in");


        reserva.setCheckIn(fecha);


    }

    public void realizarCheckOut (Reserva reserva, LocalDateTime fecha){
        if(reserva==null)
            throw new NullPointerException("check out nulo");
        if(fecha==null)
            throw new NullPointerException("Fecha nula111111");
        if(fecha.isAfter(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isBefore(reserva.getFechaFinReserva().atStartOfDay()))
            throw new IllegalArgumentException("La fecha de inicio  de reserva no puede ser posterior al check in");

        reserva.setCheckOut(fecha);
    }





}



