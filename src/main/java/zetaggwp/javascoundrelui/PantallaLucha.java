package zetaggwp.javascoundrelui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class PantallaLucha extends JPanel {

    private JLabel labelCarta = new JLabel();
    private JButton botonMano = new JButton("Luchar a Mano");
    private JButton botonArma = new JButton("Luchar con Arma");

    public PantallaLucha(JPanel mainPanel) {
        setBackground(Color.black);
        add(crearCaja());
        botonMano.setMargin(new Insets(0, 0, 0, 0));
        botonArma.setMargin(new Insets(0, 0, 0, 0));
        botonMano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.lucharAMano(JavaScoundrelUI.seleccionada);
                MainMenu_V1 main = new MainMenu_V1();
                JavaScoundrelUI.menu.getPanelGuardado().setVisible(true);
                JavaScoundrelUI.menu.getPanelDatos().updateLabels();
                JavaScoundrelUI.checkHP();
                main.switchPanel(mainPanel, "Sala");
            }
        });
        botonArma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.lucharConArma(JavaScoundrelUI.seleccionada);
                MainMenu_V1 main = new MainMenu_V1();
                JavaScoundrelUI.menu.getPanelGuardado().setVisible(true);
                JavaScoundrelUI.menu.getPanelDatos().updateLabels();
                JavaScoundrelUI.checkHP();
                main.switchPanel(mainPanel, "Sala");

            }
        });

    }

    private Box crearCaja() {

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamañoPantalla = pantalla.getScreenSize();
        int altoPantalla = tamañoPantalla.height;
        int anchoPantalla = tamañoPantalla.width;

        Box cajaVertical = Box.createVerticalBox();
        Box cajaHorizontal1 = Box.createHorizontalBox();
        cajaHorizontal1.add(labelCarta);
        Box cajaHorizontal2 = Box.createHorizontalBox();
        cajaHorizontal2.add(botonMano);
        cajaHorizontal2.add(Box.createHorizontalStrut(anchoPantalla / 8));
        cajaHorizontal2.add(botonArma);
        cajaVertical.add(Box.createVerticalStrut(altoPantalla / 16));
        cajaVertical.add(cajaHorizontal1);
        cajaVertical.add(Box.createVerticalStrut(altoPantalla / 16));
        cajaVertical.add(cajaHorizontal2);
        return cajaVertical;
    }

    public void asignarCartaBoton() {
        try {
            labelCarta.setIcon(new ImageIcon(ImageIO.read(getFileFromResourceAsStream("cards/" + JavaScoundrelUI.seleccionada + ".png"))));
        }
        catch (IOException ex) {
            System.exit(0);
        }
    }
    
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    public void checkArma() {
        if (JavaScoundrelUI.jugador.getArmaEquipada() == 0 || JavaScoundrelUI.jugador.getÚltimoMonstruo() <= JavaScoundrelUI.seleccionada.getValor()) {
            botonArma.setEnabled(false);
        } else {
            botonArma.setEnabled(true);
        }
    }

}
