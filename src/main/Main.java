package main;

import controlador.Controlador;
import java.util.Scanner;
import logica.Partida;
import logica.TipoJuego;
import logica.reglas.ReglasJuego;
import logica.reglas.ReglasJuegoConecta4;

/**
 * Contiene el metodo main del programa
 */
public class Main {

    public static void main(String args[]) {
        ReglasJuego juego = new ReglasJuegoConecta4();
        Controlador c = new Controlador(new Partida(juego), new Scanner(System.in), TipoJuego.CONECTA4);
        c.run();
    }

}
