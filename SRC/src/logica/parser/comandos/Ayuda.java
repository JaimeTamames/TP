package logica.parser.comandos;

import controlador.ControladorConsola;
import logica.parser.Comando;
import logica.parser.ParserAyudaComandos;

public class Ayuda implements Comando {

    @Override
    public void execute(ControladorConsola control) {
        System.out.println(ParserAyudaComandos.AyudaComandos());
    }

    @Override
    public Comando parsear(String[] cadena) {

        if (cadena.length == 1 && cadena[0].equalsIgnoreCase("ayuda")) {
            return new Ayuda();
        } else {
            return null;
        }
    }

    @Override
    public String textoAyuda() {
        return "AYUDA: muestra esta ayuda.";
    }

}
