package main;

import controlador.Controlador;
import java.util.Scanner;
import logica.TipoJuego;

/**
 * Contiene el metodo main del programa
 */
public class Main {

    public static void main(String args[]) {
        Controlador c = new Controlador(new Scanner(System.in), TipoJuego.CONECTA4);
        c.run();
    }

}
