package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

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

    // M�todo para obtener la lista de reservas
    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    // M�todo para realizar una copia profunda de la lista de reservas
    private Reserva[] copiaProfundaReservas() {
        Reserva[] copia = new Reserva[capacidad];
        for (int i = 0; i < tamano; i++) {
            copia[i] = listaReservas[i];
        }
        return copia;
    }

    // M�todo para obtener el tama�o actual de la lista
    public int getTamano() {
        return tamano;
    }

    // M�todo para obtener la capacidad de la lista
    public int getCapacidad() {
        return capacidad;
    }

    // M�todo para insertar una reserva
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null)
                throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        if (tamano>getCapacidad()) {
            throw new OperationNotSupportedException("ERROR: Se ha superado el tama�o permitido.");
        }
        if (buscarIndice(reserva) == -1) {
            listaReservas[tamano++] = reserva;
        }
    }

    // M�todo para buscar el �ndice de una reserva
    private int buscarIndice(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("ERROR: El �ndice de la  reserva no puede ser nulo.");
        for (int i = 0; i < tamano; i++) {
            if (listaReservas[i].equals(reserva)) {
                return i;
            }
        }
        return -1;
    }

    // M�todo para verificar si el tama�o ha sido superado
    private boolean tamanoSuperado(int indice) {
        return indice >= capacidad;
    }
    private boolean capacidadSuperada(int indice) {
        if (indice > capacidad)
            return true;
        return false;
    }
    // M�todo para buscar una reserva
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

    // M�todo para borrar una reserva
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

    // M�todo para desplazar una posici�n hacia la izquierda
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
            //verifico si la reserva pertenece a la habitaci�n espec�ficada y si la fecha de inicio de la reserva es posterior a la fecha actual
            if (this.listaReservas[i].getHabitacion().equals(habitacion) && this.listaReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturasHabitacion[j] = this.listaReservas[i];
                j++;
            }
        }
        return Arrays.copyOf(reservasFuturasHabitacion, j);
    }

}



