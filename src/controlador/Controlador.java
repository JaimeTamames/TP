package controlador;

import java.util.Scanner;
import logica.Constantes;
import logica.Ficha;
import logica.Partida;
import logica.TipoJuego;
import logica.movimiento.Movimiento;
import logica.movimiento.MovimientoComplica;
import logica.movimiento.MovimientoConecta4;
import logica.reglas.ReglasJuego;
import logica.reglas.ReglasJuegoComplica;
import logica.reglas.ReglasJuegoConecta4;

/**
 * Controla la ejecucion de la aplicacion. Pregunta al usuario que quiere hacer
 * y actualiza la partida en torno a lo que indique.
 *
 */
public class Controlador {

    private Partida partida;
    private Scanner in;
    private TipoJuego tipoJuego;

    /**
     *
     * Instancia los parametros de la clase dando asi comienzo al conecta4.
     *
     * @param in lector de la entrada estandar
     * @param tipoJuego tipo de juego inicial
     */
    public Controlador(Scanner in, TipoJuego tipoJuego) {
        this.in = in;
        this.tipoJuego = tipoJuego;
        ReglasJuego reglas;
        if (this.tipoJuego == TipoJuego.CONECTA4) {
            reglas = new ReglasJuegoConecta4();
        } else {
            reglas = new ReglasJuegoComplica();
        }

        this.partida = new Partida(reglas);
    }

    /**
     * Realiza la simulacion del juego, solicitando comandos al usuario hasta
     * que este finalice. Una ves se finaliza le informa al usuario del
     * resultado final.
     */
    public void run() {
        // variable que continua el bucle hasta que el usuario pide salir
        boolean salir = false;

        // Se ejecuta la partida hasta que alguien gane o el usuario pida salir
        while (!salir && !this.partida.isTerminada()) {
            // Se muestra el estado de la partida
            System.out.println("Tipo de juego: " + this.tipoJuego.toString());
            System.out.println(this.partida.toString());
            System.out.print(Constantes.SALTO_LINEA);
            // Se muestran las opciones a elegir
            System.out.println("0.- jugar");
            System.out.println("1.- salir");
            System.out.println("2.- poner");
            System.out.println("3.- deshacer");
            System.out.println("4.- reiniciar");
            System.out.println("5.- ayuda");
            System.out.print("Por favor, introduzca la accion que desea realizar: ");

            switch (this.in.next()) {
                case "jugar":
                    System.out.print("Selecciona el tipo de juego (C4/CO): ");
                    switch (this.in.next()) {
                        case "CO":
                            this.tipoJuego = TipoJuego.COMPLICA;
                            this.partida.resetear(new ReglasJuegoComplica());
                            break;
                        case "C4":
                            this.tipoJuego = TipoJuego.CONECTA4;
                            this.partida.resetear(new ReglasJuegoConecta4());
                            break;
                        default:
                            System.err.println("Error, opcion no valida");
                            break;
                    }
                    break;
                // Si el usuario inserta salir se finaliza el bucle
                case "salir":
                    salir = true;
                    break;
                // Si el usuario inserta poner se le pregunta que columna y se ejecuta eñ movimiento
                case "poner":
                    System.out.print(Constantes.SALTO_LINEA);
                    System.out.print("Selecciona la columna donde deseas introducir la ficha: ");

                    Movimiento mv;

                    if (this.tipoJuego == TipoJuego.COMPLICA) {
                        mv = new MovimientoComplica(this.in.nextInt() - 1, this.partida.getTurno());
                    } else {
                        mv = new MovimientoConecta4(this.in.nextInt() - 1, this.partida.getTurno());
                    }

                    if (!this.partida.ejecutaMovimiento(mv)) {
                        System.err.println("Jugada erronea");
                    }
                    break;
                // Se deshace el ultimo movimiento de la partida, restaurando el tablero a la jugada anterior
                case "deshacer":
                    if (!this.partida.deshacer()) {
                        System.err.println("No se puede deshacer la jugada");
                    }
                    break;
                // Se reinicia por completo el juego y la partida
                case "reiniciar":
                    this.partida.resetear(this.partida.getReglas());
                    break;
                // Se muestra la ayuda
                case "ayuda":
                    System.out.print(Constantes.SALTO_LINEA);
                    System.out.println("·El juego por defecto comienza en modo Conecta 4, si deseas");
                    System.out.println("  cambiar el modo de juego debes introducir el comando jugar");
                    System.out.println("  y introducir CO para jugar a Complica o C4 para jugar a Conecta4.");
                    System.out.println("·El comando poner te permite poner las fichas en las columnas deseadas.");
                    System.out.println("·El comando deshacer desahe los movimientos sin limite de veces.");
                    System.out.println("·El comando reiniciar vuelve a iniciar una partida nueva.");
                    System.out.print(Constantes.SALTO_LINEA);
                    break;
                // En caso de que no se introduzca una selccion valida se notifica y se pide de nuevo
                default:
                    System.out.println("Seleccion incorrecta");
                    break;
            }
            System.out.print(Constantes.SALTO_LINEA);

        }

        // Muestra la partida finalizada
        System.out.print(Constantes.SALTO_LINEA);
        System.out.println(this.partida.toString());
        System.out.print(Constantes.SALTO_LINEA);

        // Si se ha salido por parte del usuario y no por acabar la partida se muestra
        if (salir) {
            System.out.println("Adios");
        }

        // Comprueba si la partida esta terminada en empate o ha ganado alguien y lo muestra
        if (this.partida.isTerminada()) {
            if (this.partida.getGanador() == Ficha.VACIA) {
                System.out.println("La partida ha finalizado en empate");
            } else {
                System.out.println("Enhorabuena, el ganador es: "
                        + this.partida.getGanador().toString());
            }
        }

    }
}
