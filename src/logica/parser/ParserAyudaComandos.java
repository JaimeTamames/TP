/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.parser;

import logica.parser.comandos.Salir;
import logica.parser.comandos.Poner;
import logica.parser.comandos.Deshacer;
import logica.parser.comandos.PonJugador;
import logica.parser.comandos.Ayuda;
import logica.parser.comandos.Jugar;
import logica.Constantes;

public class ParserAyudaComandos {

    private static Comando[] comandos = {new Ayuda(), new Jugar(), new Salir(), new PonJugador(), new Deshacer(), new Poner()};

    public static String AyudaComandos() {
        String txt = "";
        for (Comando c : comandos) {
            txt += c.textoAyuda() + Constantes.SALTO_LINEA;
        }

        return txt;
    }

    public static Comando parsea(String cadena[]) {
        for (Comando c : comandos) {
            if (c.parsear(cadena) != null) {
                return c.parsear(cadena);
            }
        }

        return null;
    }
}
