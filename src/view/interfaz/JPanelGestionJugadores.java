package view.interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logica.modosDeJuego.TipoTurno;

public class JPanelGestionJugadores extends JPanel {

    private JLabel jLabelJugadorBlancas;
    private JComboBox jComboBoxJugadorBlancas;
    private JLabel jLabelJugadorNegras;
    private JComboBox jComboBoxJugadorNegras;

    public JPanelGestionJugadores() {
        this.initComponents();
    }

    public JComboBox getjComboBoxJugadorBlancas() {
        return jComboBoxJugadorBlancas;
    }

    public JComboBox getjComboBoxJugadorNegras() {
        return jComboBoxJugadorNegras;
    }

    public void initComponents() {

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(350, 100));
        this.setMinimumSize(new Dimension(350, 100));
        this.setBorder(BorderFactory.createTitledBorder("Gestion de jugadores"));

        TipoTurno tiposJugadores[] = {TipoTurno.HUMANO, TipoTurno.AUTOMATICO};

        this.jLabelJugadorBlancas = new JLabel("Blancas");
        this.jComboBoxJugadorBlancas = new JComboBox(tiposJugadores);

        this.jLabelJugadorNegras = new JLabel("Negras");
        this.jComboBoxJugadorNegras = new JComboBox(tiposJugadores);

        this.jComboBoxJugadorBlancas.setBounds(this.getWidth() / 2 - 75, 30, 150, 30);
        this.jComboBoxJugadorNegras.setBounds(this.getWidth() / 2 - 75, 30, 150, 30);

        this.add(this.jLabelJugadorBlancas);
        this.add(this.jComboBoxJugadorBlancas);
        this.add(this.jLabelJugadorNegras);
        this.add(this.jComboBoxJugadorNegras);

        this.setVisible(true);
    }
}
