package zetaggwp.javascoundrelui;

import javax.swing.*;
import java.awt.*;

public class PanelDatos extends JPanel {

    private JLabel HPLabel = new JLabel();
    private JLabel armaLabel = new JLabel();
    private JLabel monstruoLabel = new JLabel();
    private JLabel restantesLabel = new JLabel();
    private JLabel resultadoLabel = new JLabel();

    public PanelDatos() {
        setBackground(Color.black);
        setVisible(false);
        add(HPLabel);
        add(armaLabel);
        add(monstruoLabel);
        add(restantesLabel);
        add(resultadoLabel);
        resultadoLabel.setVisible(false);
        HPLabel.setForeground(Color.WHITE);
        armaLabel.setForeground(Color.WHITE);
        monstruoLabel.setForeground(Color.WHITE);
        restantesLabel.setForeground(Color.WHITE);
        resultadoLabel.setForeground(Color.WHITE);
    }

    public void updateLabels() {
        HPLabel.setVisible(true);
        armaLabel.setVisible(true);
        monstruoLabel.setVisible(true);
        restantesLabel.setVisible(true);
        resultadoLabel.setVisible(false);
        HPLabel.setText("HP = " + JavaScoundrelUI.jugador.getHP());
        armaLabel.setText("Arma = " + JavaScoundrelUI.jugador.getArmaEquipada());
        if (JavaScoundrelUI.jugador.getÚltimoMonstruo() == 15) {
            monstruoLabel.setText("Monstruo = Ninguno");
        } else {
            monstruoLabel.setVisible(true);
            monstruoLabel.setText("Monstruo = " + JavaScoundrelUI.jugador.getÚltimoMonstruo());
        }
        restantesLabel.setText("Cartas Restantes = " + (JavaScoundrelUI.baraja.size() + JavaScoundrelUI.tempSala.size()));
    }

    public void resultadoPartida(int resultado) {
        HPLabel.setVisible(false);
        armaLabel.setVisible(false);
        monstruoLabel.setVisible(false);
        resultadoLabel.setVisible(true);
        if (resultado == 0) {
            resultadoLabel.setText("HAS PERDIDO");
        } else {
            resultadoLabel.setText("HAS GANADO");
        }
    }

}
