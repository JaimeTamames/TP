package logica.jugadores.humanos;

import java.util.Scanner;
import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;

public class JugadorHumanoGravity extends Jugador {

    private Scanner sc;

    public JugadorHumanoGravity(Scanner sc) {
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
