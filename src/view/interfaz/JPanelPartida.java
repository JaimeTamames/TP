package view.interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelPartida extends JPanel {

    private JButton jButtonDeshacer;
    private JButton jButtonReiniciar;

    public JPanelPartida() {

        this.init();
    }

    public JButton getJButtonDeshacer() {
        return jButtonDeshacer;
    }

    public JButton getJButtonReiniciar() {
        return jButtonReiniciar;
    }

    private void init() {

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(260, 120));
        this.setMinimumSize(new Dimension(260, 120));
        this.setBorder(BorderFactory.createTitledBorder("Partida"));

        this.jButtonDeshacer = new JButton("Deshacer");
        this.jButtonDeshacer.setIcon(new ImageIcon("recursos/deshacer.png"));
        this.jButtonDeshacer.setPreferredSize(new Dimension(150, 50));

        this.jButtonReiniciar = new JButton("Reiniciar");
        this.jButtonReiniciar.setIcon(new ImageIcon("recursos/reiniciar.png"));
        this.jButtonReiniciar.setPreferredSize(new Dimension(150, 50));

        this.add(this.jButtonDeshacer);
        this.add(this.jButtonReiniciar);

        this.setVisible(true);

    }

}
