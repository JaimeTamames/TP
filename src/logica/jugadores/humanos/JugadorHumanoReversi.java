package logica.jugadores.humanos;

import java.util.Scanner;
import logica.Ficha;
import logica.jugadores.Jugador;
import logica.tablero.Tablero;

public class JugadorHumanoReversi extends Jugador {

    private Scanner sc;

    public JugadorHumanoReversi(Scanner sc) {
        this.sc = sc;
    }

    @Override
    protected void obtenFilaColumna(Tablero tab, Ficha color) {
        System.out.print("Introduce la fila: ");
        this.fila = sc.nextInt() - 1;
        System.out.print("Introduce la columna: ");
        this.columna = sc.nextInt() - 1;
        sc.nextLine();
    }

}
