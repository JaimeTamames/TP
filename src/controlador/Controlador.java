package controlador;

import logica.Ficha;
import logica.Partida;
import logica.Constantes;
import java.util.Scanner;
import logica.TipoJuego;

/**
 * Controla la ejecucion de la aplicacion. Pregunta al usuario que quiere hacer
 * y actualiza la partida en torno a lo que indique.
 *
 */
public class Controlador {

    private Partida partida;
    private Scanner in;
    private TipoJuego juego;

    /**
     *
     * Instancia los parametros de la clase dando asi comienzo al conecta4.
     *
     * @param partida nueva partida del juego conecta4
     * @param in lector de la entrada estandar
     */
    public Controlador(Partida partida, Scanner in, TipoJuego juego) {
        this.partida = partida;
        this.in = in;
        this.juego = juego;
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
            System.out.println(this.partida.toString());
            System.out.print(Constantes.SALTO_LINEA);
            // Se muestran las opciones a elegir
            System.out.println("1.- salir");
            System.out.println("2.- poner");
            System.out.println("3.- deshacer");
            System.out.println("4.- reiniciar");
            System.out.print("Por favor, introduzca la accion que desea realizar: ");

            switch (this.in.next()) {
                // Si el usuario inserta salir se finaliza el bucle
                case "salir":
                    salir = true;
                    break;
                // Si el usuario inserta poner se le pregunta que columna y se ejecuta eñ movimiento
                case "poner":
                    System.out.print(Constantes.SALTO_LINEA);
                    System.out.print("Selecciona la columna donde deseas introducir la ficha: ");
                    if(true){ //(!this.partida.ejecutaMovimiento(this.in.nextInt())) {
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
                // En caso de que no se introduzca una selccion valida se notifica y se pide de nuevo
                default:
                    System.out.println("Seleccion incorrecta");
                    break;
            }

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
