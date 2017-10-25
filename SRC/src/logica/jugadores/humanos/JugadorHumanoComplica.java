package logica.jugadores.humanos;

import java.util.Scanner;
import logica.Ficha;
import logica.tablero.Tablero;
import logica.jugadores.Jugador;

public class JugadorHumanoComplica extends Jugador {

    private Scanner sc;

    public JugadorHumanoComplica(Scanner sc) {
        this.sc = sc;
    }

    @Override
    protected void obtenFilaColumna(Tablero tab, Ficha color) {
        System.out.print("Introduce la columna: ");
        int columna = sc.nextInt();
        sc.nextLine();
        this.columna = columna - 1;
    }

}
