package zetaggwp.javascoundrelui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaTitulo extends JPanel {

    private JButton botonJugar = new JButton("Jugar");
    private JButton botonSalir = new JButton("Salir");

    public PantallaTitulo(JPanel mainPanel) {
        setBackground(Color.black);
        add(crearCaja());
        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu_V1 main = new MainMenu_V1();
                main.switchPanel(mainPanel, "Sala");
                JavaScoundrelUI.jugarPartida();
            }
        });
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private Box crearCaja() {

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamañoPantalla = pantalla.getScreenSize();
        int altoPantalla = tamañoPantalla.height;

        Box cajaVertical = Box.createVerticalBox();
        Box cajaHorizontal1 = Box.createHorizontalBox();
        cajaHorizontal1.add(botonJugar);
        Box cajaHorizontal2 = Box.createHorizontalBox();
        cajaHorizontal2.add(botonSalir);
        cajaVertical.add(Box.createVerticalStrut(altoPantalla / 8));
        cajaVertical.add(cajaHorizontal1);
        cajaVertical.add(Box.createVerticalStrut(altoPantalla / 12));
        cajaVertical.add(cajaHorizontal2);
        return cajaVertical;
    }

}
