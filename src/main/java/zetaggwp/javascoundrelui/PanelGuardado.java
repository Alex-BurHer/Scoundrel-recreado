package zetaggwp.javascoundrelui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGuardado extends JPanel {

    private JButton botonGuardar = new JButton("Guardar");
    private JButton botonCargar = new JButton("Cargar");
    private JButton botonReiniciar = new JButton("Reiniciar");

    public PanelGuardado(JPanel mainPanel) {
        setBackground(Color.black);
        setVisible(false);
        add(botonGuardar);
        add(botonCargar);
        add(botonReiniciar);
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.guardar();
            }
        });
        botonCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.cargar();
            }
        });
        botonReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.menu.getPanelDatos().setVisible(false);
                JavaScoundrelUI.menu.getPanelGuardado().setVisible(false);
                JavaScoundrelUI.menu.switchPanel(mainPanel, "Titulo");
            }
        });
    }

    public void activarGuardado() {
        botonCargar.setEnabled(true);
        botonGuardar.setEnabled(true);
    }
    
    public void desactivarGuardado() {
        botonCargar.setEnabled(false);
        botonGuardar.setEnabled(false);
    }
    
}
