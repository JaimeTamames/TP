package logica.parser.comandos;

import controlador.ControladorConsola;
import logica.Ficha;
import logica.parser.Comando;

public class PonJugador implements Comando {

    private Ficha color;
    private String tipoJugador;

    public PonJugador() {
    }

    public PonJugador(Ficha color, String tipoJugador) {
        this.color = color;
        this.tipoJugador = tipoJugador;
    }

    @Override
    public void execute(ControladorConsola control) {
        control.ponerJugador(this.color, this.tipoJugador);
    }

    @Override
    public Comando parsear(String[] cadena) {

        Ficha clr;
        String tj;

        if (cadena.length == 3 && cadena[0].equalsIgnoreCase("jugador")) {

            if (cadena[1].equalsIgnoreCase("blancas")) {
                clr = Ficha.BLANCAS;
            } else if (cadena[1].equalsIgnoreCase("negras")) {
                clr = Ficha.NEGRAS;
            } else {
                return null;
            }

            if (cadena[2].equalsIgnoreCase("humano")) {
                tj = "humano";
            } else if (cadena[2].equalsIgnoreCase("aleatorio")) {
                tj = "aleatorio";
            } else {
                return null;
            }

            return new PonJugador(clr, tj);

        } else {
            return null;
        }
    }

    @Override
    public String textoAyuda() {
        return "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.";
    }

}
