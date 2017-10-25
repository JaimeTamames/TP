package logica.parser.comandos;

import controlador.ControladorConsola;
import logica.parser.Comando;

public class Poner implements Comando {

    @Override
    public void execute(ControladorConsola control) {
        control.ejecutarMovimiento();
    }

    @Override
    public Comando parsear(String[] cadena) {
        if (cadena.length == 1 && cadena[0].equalsIgnoreCase("poner")) {
            return new Poner();
        } else {
            return null;
        }
    }

    @Override
    public String textoAyuda() {
        return "PONER: utilizalo para poner la siguiente ficha.";
    }

}
