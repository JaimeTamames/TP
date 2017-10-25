/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modosDeJuego;

/**
 *
 * @author rulo
 */
public interface ModoJuego {
    // hebra que puede ser detenida
    void comenzar();
    void terminar();
    void deshacerpulsado();
    void tableroPulsado(int f, int c);
}
