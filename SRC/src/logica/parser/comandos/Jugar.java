package logica.parser.comandos;

import controlador.ControladorConsola;
import logica.Constantes;
import logica.factorias.FactoriaJuego;
import logica.factorias.FactoriaJuegoComplica;
import logica.factorias.FactoriaJuegoConecta4;
import logica.factorias.FactoriaJuegoGravity;
import logica.factorias.FactoriaJuegoReversi;
import logica.parser.Comando;

public class Jugar implements Comando {

    private FactoriaJuego factoria;

    public Jugar() {
    }

    public Jugar(FactoriaJuego factoria) {
        this.factoria = factoria;
    }

    @Override
    public void execute(ControladorConsola control) {
        control.reset(this.factoria);
    }

    @Override
    public Comando parsear(String[] cadena) {

        if (cadena.length == 2) {
            if (!cadena[0].equalsIgnoreCase("jugar")) {
                return null;
            } else {
                switch (cadena[1]) {

                    case "gr":
                        return new Jugar(new FactoriaJuegoGravity());
                    case "co":
                        return new Jugar(new FactoriaJuegoComplica());
                    case "c4":
                        return new Jugar(new FactoriaJuegoConecta4());
                    case "re":
                        return new Jugar(new FactoriaJuegoReversi());
                    default:
                        return null;

                }
            }
        } else if (cadena.length == 4) {
            if (cadena[0].equalsIgnoreCase("jugar")
                    && cadena[1].equals("gr")) {

                try {
                    Constantes.ALTOGR = Integer.parseInt(cadena[2]);
                    Constantes.ANCHOGR = Integer.parseInt(cadena[3]);
                } catch (NumberFormatException e) {
                    return null;
                }

                return new Jugar(new FactoriaJuegoGravity());

            } else {
                return null;
            }

        } else {
            return null;
        }

    }

    @Override
    public String textoAyuda() {
        return "JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.";
    }

}
