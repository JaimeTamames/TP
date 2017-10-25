package logica.parser.comandos;

import controlador.ControladorConsola;
import logica.parser.Comando;

public class Salir implements Comando {

    @Override
    public void execute(ControladorConsola control) {
        control.finalizar();
    }

    @Override
    public Comando parsear(String[] cadena) {
        if (cadena.length == 1 && cadena[0].equalsIgnoreCase("salir")) {
            return new Salir();
        } else {
            return null;
        }
    }

    @Override
    public String textoAyuda() {
        return "SALIR: termina la aplicacion.";
    }

}
