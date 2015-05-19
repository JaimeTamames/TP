package logica.parser;

import controlador.ControladorConsola;

public interface Comando {

    public void execute(ControladorConsola control);

    public Comando parsear(String[] cadena);

    public String textoAyuda();
}
