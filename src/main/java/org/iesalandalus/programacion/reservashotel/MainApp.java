package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;


import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class MainApp {
    public static final int CAPACIDAD=1;
    private static Habitaciones habitaciones;
    private static Reservas reservas;
    private static Huespedes huespedes;
    public static boolean salir =false;
    private static void ejecutarOpcion(Opcion opcion){

        switch (opcion){
            case SALIR : salir=true;
                break;
            case ANULAR_RESERVA : anularReserva();
                break;
            case BORRAR_HUESPED : borrarHuesped();
                break;
            case BUSCAR_HUESPED : buscarHuesped();
                break;
            case INSERTAR_HUESPED : insertarHuesped();
                break;
            case INSERTAR_HABITACION : insertarHabitacion();
                break;
            case INSERTAR_RESERVA : insertarReserva();
                break;
            case MOSTRAR_HABITACIONES : mostrarHabitaciones();
                break;
            case MOSTRAR_HUESPEDES : mostrarHuespedes();
                break;
            case MOSTRAR_RESERVAS : mostrarReservas();
                break;
            case BUSCAR_HABITACION : buscarHabitacion();
                break;
            case BORRAR_HABITACION: borrarHabitacion();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                TipoHabitacion tipo= Consola.leerTipoHabitacion();
                System.out.println("Fecha inicio: ");
                LocalDate fechaIni=Consola.leerFecha(Entrada.cadena()) ;

                System.out.println("Fecha fin: ");
                LocalDate fechaFin= Consola.leerFecha(Entrada.cadena());

                consultarDisponibilidad(Consola.leerTipoHabitacion(), fechaIni, fechaFin);
                break;

        }
    }

    private static void insertarHuesped(){

        try{
            huespedes.insertar(Consola.leerHuesped());
            System.out.println("Ha insertado un hu�sped");

        }catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e){

            System.out.println(e.getMessage());

        }

    }


    private static void buscarHuesped(){

        try {
            huespedes.buscar(Consola.leerHuesped());
            System.out.println("He buscado un hu�sped");

        }catch (NullPointerException|IllegalArgumentException e){

            System.out.println(e.getMessage());
        }

    }

    private static void borrarHuesped(){

        try {
            huespedes.borrar(Consola.leerHuesped());
            System.out.println("He borrado un hu�sped");

        }catch (OperationNotSupportedException e){

            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHuespedes(){
        try{
            for(Huesped cliente: huespedes.get())
                System.out.println(cliente);
        }catch (NullPointerException|IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void insertarHabitacion(){
        try{
            habitaciones.insertar(Consola.leerHabitacion());
            System.out.println("Ha insertado una habitaci�n");

        }catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e){

            System.out.println(e.getMessage());

        }



    }

    private static void buscarHabitacion(){
        try {
            habitaciones.buscar(Consola.leerHabitacionPorIdentificador());
            System.out.println("He buscado una habitaci�n");

        }catch (NullPointerException|IllegalArgumentException e){

            System.out.println(e.getMessage());
        }
    }

    private static void borrarHabitacion(){
        try {
            habitaciones.borrar(Consola.leerHabitacionPorIdentificador());
            System.out.println("He borrado una  habitaci�n");

        }catch (OperationNotSupportedException e){

            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHabitaciones(){
        try{
            for(Habitacion cliente: habitaciones.get())
                System.out.println(habitaciones);
        }catch (NullPointerException|IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertarReserva() {
        Reserva reservaQueQuiero = Consola.leerReserva();


        if (consultarDisponibilidad(reservaQueQuiero.getHabitacion().getTipoHabitacion(), reservaQueQuiero.getFechaInicioReserva(), reservaQueQuiero.getFechaFinReserva()) == null){
            System.out.println("Ho hay habitacion");
        } else {


            try {
                reservas.insertar(reservaQueQuiero);
                System.out.println("Ha insertado una reserva");

            } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {

                System.out.println(e.getMessage());

            }
        }

    }

    private void listarReservas(Huesped huesped){
        try{
            for(Reserva reserva: reservas.getReservas(huesped))
                System.out.println(reserva);

        }catch (NullPointerException|IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }


    private void listaReserva(TipoHabitacion tipoHabitacion){
        try{
            for(Reserva reserva: reservas.getReservas(tipoHabitacion))
                System.out.println(reserva);

        }catch (NullPointerException|IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


    }

    private Reserva[] getReservasAnulables(Reserva[] reservasAAnular){
        return null;
    }



    private static void anularReserva(){
        try {
            reservas.borrar(Consola.leerReserva());
            System.out.println("He anulado una reserva");

        }catch (OperationNotSupportedException e){

            System.out.println(e.getMessage());
        }

    }


    private static void mostrarReservas(){

        try{
            for(Reserva reserva1: reservas.get())
                System.out.println(reserva1);
        }catch (NullPointerException|IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }



    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva)
        {
            boolean tipoHabitacionEncontrada = false;
            Habitacion habitacionDisponible = null;
            int numElementos = 0;

            Habitacion[] habitacionesTipoSolicitado = habitaciones.get(tipoHabitacion);

            if (habitacionesTipoSolicitado == null)
                return habitacionDisponible;

            for (int i = 0; i < habitacionesTipoSolicitado.length && !tipoHabitacionEncontrada; i++) {

                if (habitacionesTipoSolicitado[i] != null) {
                    Reserva[] reservasFuturas = reservas.getReservasFuturas(habitacionesTipoSolicitado[i]);
                    numElementos = getNumElementosNoNulos(reservasFuturas);

                    if (numElementos == 0) {
                        //Si la primera de las habitaciones encontradas del tipo solicitado no tiene reservas en el futuro,
                        // quiere decir que est� disponible.
                        habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                        tipoHabitacionEncontrada = true;
                    } else {

                        //Ordenamos de mayor a menor las reservas futuras encontradas por fecha de fin de la reserva.
                        // Si la fecha de inicio de la reserva es posterior a la mayor de las fechas de fin de las reservas
                        // (la reserva de la posici�n 0), quiere decir que la habitaci�n est� disponible en las fechas indicadas.

                        Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                    mostrar(reservasFuturas);*/

                        if (fechaInicioReserva.isAfter(reservasFuturas[0].getFechaFinReserva())) {
                            habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                            tipoHabitacionEncontrada = true;
                        }

                        if (!tipoHabitacionEncontrada) {
                            //Ordenamos de menor a mayor las reservas futuras encontradas por fecha de inicio de la reserva.
                            // Si la fecha de fin de la reserva es anterior a la menor de las fechas de inicio de las reservas
                            // (la reserva de la posici�n 0), quiere decir que la habitaci�n est� disponible en las fechas indicadas.

                            Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaInicioReserva));

                        /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                        mostrar(reservasFuturas);*/

                            if (fechaFinReserva.isBefore(reservasFuturas[0].getFechaInicioReserva())) {
                                habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                tipoHabitacionEncontrada = true;
                            }
                        }

                        //Recorremos el array de reservas futuras para ver si las fechas solicitadas est�n alg�n hueco existente entre las fechas reservadas
                        if (!tipoHabitacionEncontrada) {
                            for (int j = 1; j < reservasFuturas.length && !tipoHabitacionEncontrada; j++) {
                                if (reservasFuturas[j] != null && reservasFuturas[j - 1] != null) {
                                    if (fechaInicioReserva.isAfter(reservasFuturas[j - 1].getFechaFinReserva()) &&
                                            fechaFinReserva.isBefore(reservasFuturas[j].getFechaInicioReserva())) {

                                        habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                        tipoHabitacionEncontrada = true;
                                    }
                                }
                            }
                        }


                    }
                }
            }

            return habitacionDisponible;

    }

    private static int getNumElementosNoNulos(Reserva[] reservas) {
        int numero=0;
        for (int i =0; i<reservas.length;i++){
            if (reservas[i]!=null)
                numero++;
        }
        return numero;
    }





    public static void main(String[] args) {

        do{
           Consola.mostrarMenu();
           ejecutarOpcion(Consola.elegirOpcion());
        }while(!salir);


    }
}
