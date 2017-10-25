package logica.parser.comandos;

import exceptions.*;
import controlador.ControladorConsola;
import logica.parser.Comando;

public class Deshacer implements Comando {

    @Override
    public void execute(ControladorConsola control) {
        control.undo();
    }

    @Override
    public Comando parsear(String[] cadena) {
        if (cadena.length == 1 && cadena[0].equalsIgnoreCase("deshacer")) {
            return new Deshacer();
        } else {
            return null;
        }
    }

    @Override
    public String textoAyuda() {
        return "DESHACER: deshace el último movimiento hecho en la partida.";
    }

}
