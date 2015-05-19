package view.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JFrameVistaGUI extends JFrame {

    private JButton jButtonAleatorio;
    private JButton jButtonSalir;
    private JPanelTablero jPanelTablero;
    private JLabel jLabelTurno;
    private JPanelPartida jPanelPartida;
    private JPanelGestionJugadores jPanelGestionJugadores;
    private JPanelCambioJuego jPanelCambioJuego;

    private JPanel jPanelCenter;

    public JFrameVistaGUI(int rows, int columns) {

        this.init(rows, columns);
    }

    public JButton getjButtonAleatorio() {
        return jButtonAleatorio;
    }

    public JButton getjButtonSalir() {
        return jButtonSalir;
    }

    public JPanelTablero getjPanelTablero() {
        return jPanelTablero;
    }

    public JLabel getjLabelTurno() {
        return jLabelTurno;
    }

    public JPanelPartida getjPanelPartida() {
        return jPanelPartida;
    }

    public JPanelGestionJugadores getjPanelGestionJugadores() {
        return jPanelGestionJugadores;
    }

    public JPanelCambioJuego getjPanelCambioJuego() {
        return jPanelCambioJuego;
    }

    private void init(int rows, int columns) {
        // Asignamos layout al panel interno del jframe
        this.getContentPane().setLayout(new BorderLayout());
        // Por defecto terminamos la ejecucion del programa al cerrar la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instanciamos los paneles que iran en las zonas este y sur
        this.jPanelCenter = new JPanel();
        JPanel jpanelEast = new JPanel();
        JPanel jpanelSouth = new JPanel();
        // Asignamos layout a los paneles este y sur
        this.jPanelCenter.setLayout(new BoxLayout(this.jPanelCenter, BoxLayout.Y_AXIS));
        jpanelEast.setLayout(new BoxLayout(jpanelEast, BoxLayout.Y_AXIS));
        jpanelSouth.setLayout(new FlowLayout());

        // instanciamos el boton de aleatorio
        this.jButtonAleatorio = new JButton("Aleatorio");
        // asignamos icono al boton
        this.jButtonAleatorio.setIcon(new ImageIcon("recursos/aleatorio.png"));
        // dimensionamos el boton
        this.jButtonAleatorio.setPreferredSize(new Dimension(150, 50));
        // lo añadimos al panel sur
        jpanelSouth.add(this.jButtonAleatorio);

        // instanciamos el boton salir
        this.jButtonSalir = new JButton("Salir");
        // asignamos un icono al boton
        this.jButtonSalir.setIcon(new ImageIcon("recursos/salir.png"));
        // dimensionamos el boton
        this.jButtonSalir.setPreferredSize(new Dimension(150, 50));
        // lo añadimos al panel sur
        jpanelSouth.add(this.jButtonSalir);

        // instanciamos el tablero de juego
        this.jPanelTablero = new JPanelTablero(rows, columns);
        this.jPanelCenter.add(jPanelTablero);

        this.jLabelTurno = new JLabel("JUGADOR", SwingConstants.CENTER);
        this.jLabelTurno.setBorder(BorderFactory.createEtchedBorder());
        this.jPanelCenter.add(jLabelTurno);

        this.jPanelPartida = new JPanelPartida();
        jpanelEast.add(jPanelPartida);
        this.jPanelGestionJugadores = new JPanelGestionJugadores();
        jpanelEast.add(jPanelGestionJugadores);
        this.jPanelCambioJuego = new JPanelCambioJuego();
        jpanelEast.add(jPanelCambioJuego);

        // creamos un panel de margen
        this.getContentPane().add(new JPanel(), BorderLayout.WEST);
        // asignamos los paneles con sus componentes en las diferentes posiciones del panel contenedor
        this.getContentPane().add(this.jPanelCenter, BorderLayout.CENTER);
        this.getContentPane().add(jpanelEast, BorderLayout.EAST);
        this.getContentPane().add(jpanelSouth, BorderLayout.SOUTH);

        this.setLocation(100, 100);
        this.setResizable(false);
        this.pack();

    }

    public void resetTable(int rows, int columns) {

        this.jPanelTablero.reset(rows, columns);
        this.jPanelCenter.setPreferredSize(new Dimension(this.jPanelTablero.getPreferredSize().width, this.jPanelTablero.getPreferredSize().height + this.jLabelTurno.getHeight()));
        this.pack();
    }
}
