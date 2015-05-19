package main;

import controlador.ControladorConsola;
import controlador.ControladorGUI;
import java.util.Collection;
import java.util.Scanner;
import logica.Constantes;
import logica.Partida;
import logica.factorias.*;
import org.apache.commons.cli.*;

/**
 * Contiene el metodo main del programa
 */
public class Main {

    public static void parserArguments(String[] args) throws ParseException {

        FactoriaJuego f = new FactoriaJuegoConecta4();

        Options cmdLineOptions;
        cmdLineOptions = new Options();

        // -h --help <help>
        Option helpOP = OptionBuilder.
                withArgName("help").
                hasOptionalArg().
                withDescription("Muestra el conjunto de comandos disponibles.").
                withLongOpt("help").
                create('h');

        // -g --game <game>
        Option gameOP = OptionBuilder.
                withArgName("game").
                hasArg().
                withDescription("Tipo de juego (c4, co, gr). Por defecto c4.").
                withLongOpt("game").
                create('g');

        // -x --tamX <tamX>
        Option tamXOP = OptionBuilder.
                withArgName("tamX").
                hasArgs().
                withDescription("Establece el tamaño de las columnas solo para Gravity.").
                withLongOpt("tamX").
                create('x');

        // -y --tamY <tamY>
        Option tamYOP = OptionBuilder.
                withArgName("tamY").
                hasArg().
                withDescription("Establece el tamaño de las filas solo para Gravity.").
                withLongOpt("tamY").
                create('y');

        //-u --ui <ui>
        Option tipoOP = OptionBuilder.
                withArgName("ui").
                hasOptionalArg().
                withDescription("Tipo de interfaz (console, window). Por defecto, console.").
                withLongOpt("ui").
                create('u');

        cmdLineOptions.addOption(helpOP);
        cmdLineOptions.addOption(gameOP);
        cmdLineOptions.addOption(tamXOP);
        cmdLineOptions.addOption(tamYOP);
        cmdLineOptions.addOption(tipoOP);

        // Creamos el parser
        CommandLineParser parser = new PosixParser();
        CommandLine lines = parser.parse(cmdLineOptions, args);

        if (lines.hasOption("help")) {
            Collection l = cmdLineOptions.getOptions();
            for (Object ob : l) {
                Option op = (Option) ob;
                System.out.println("-" + op.getOpt() + "/" + op.getLongOpt() + ": " + op.getDescription());
            }
        }

        if (lines.hasOption("game")) {
            if ("gr".equals(lines.getOptionValue("game")) && lines.hasOption("tamX") && lines.hasOption("tamY")) {
                Constantes.ANCHOGR = Integer.valueOf(lines.getOptionValue("tamX"));
                Constantes.ALTOGR = Integer.valueOf(lines.getOptionValue("tamY"));
            }

            switch (lines.getOptionValue("game", "c4")) {
                case "c4":
                    f = new FactoriaJuegoConecta4();
                    break;
                case "co":
                    f = new FactoriaJuegoComplica();
                    break;
                case "gr":
                    f = new FactoriaJuegoGravity();
                    break;
                case "rv":
                    f = new FactoriaJuegoReversi();
                    break;
                default:
                    System.err.println("Valor no valido para juego.");

            }
        }

        if (lines.hasOption("ui")) {

            switch (lines.getOptionValue("ui", "console")) {
                case "console":
                    ControladorConsola c = new ControladorConsola(f, new Scanner(System.in), new Partida(f.crearReglas()));
                    c.run();
                    break;
                case "window":
                    ControladorGUI cGUI = new ControladorGUI(f, new Partida(f.crearReglas()));
                    break;
                default:
                    System.err.println("Valor no valido para tipo de visualizacion.");
            }
        } else {
            ControladorConsola c = new ControladorConsola(f, new Scanner(System.in), new Partida(f.crearReglas()));
            c.run();
        }

    }

    public static void main(String args[]) {

        try {
            parserArguments(args);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

}
