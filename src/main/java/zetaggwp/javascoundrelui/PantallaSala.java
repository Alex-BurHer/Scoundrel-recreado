package zetaggwp.javascoundrelui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PantallaSala extends JPanel {

    private JButton botonCarta1 = new JButton();
    private JButton botonCarta2 = new JButton();
    private JButton botonCarta3 = new JButton();
    private JButton botonCarta4 = new JButton();
    private JButton botonSkip = new JButton("Saltar Sala");

    public PantallaSala(JPanel mainPanel) {
        setBackground(Color.black);
        add(crearCaja());
        botonCarta1.setMargin(new Insets(0, 0, 0, 0));
        botonCarta2.setMargin(new Insets(0, 0, 0, 0));
        botonCarta3.setMargin(new Insets(0, 0, 0, 0));
        botonCarta4.setMargin(new Insets(0, 0, 0, 0));
        botonCarta1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.seleccionarCarta(0);
                botonCarta1.setEnabled(false);
                controlCarta(mainPanel);
            }
        });
        botonCarta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.seleccionarCarta(1);
                botonCarta2.setEnabled(false);
                controlCarta(mainPanel);
            }
        });
        botonCarta3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.seleccionarCarta(2);
                botonCarta3.setEnabled(false);
                controlCarta(mainPanel);
            }
        });
        botonCarta4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.seleccionarCarta(3);
                botonCarta4.setEnabled(false);
                controlCarta(mainPanel);
            }
        });
        botonSkip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaScoundrelUI.saltarSala();
                JavaScoundrelUI.controlGUIPartida();
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
        cajaHorizontal1.add(botonCarta1);
        cajaHorizontal1.add(Box.createHorizontalStrut(anchoPantalla / 16));
        cajaHorizontal1.add(botonCarta2);
        cajaHorizontal1.add(Box.createHorizontalStrut(anchoPantalla / 16));
        cajaHorizontal1.add(botonCarta3);
        cajaHorizontal1.add(Box.createHorizontalStrut(anchoPantalla / 16));
        cajaHorizontal1.add(botonCarta4);
        Box cajaHorizontal2 = Box.createHorizontalBox();
        cajaHorizontal2.add(botonSkip);
        cajaVertical.add(Box.createVerticalStrut(altoPantalla / 16));
        cajaVertical.add(cajaHorizontal1);
        cajaVertical.add(Box.createVerticalStrut(altoPantalla / 16));
        cajaVertical.add(cajaHorizontal2);
        return cajaVertical;
    }

    public void asignarCartaBoton(ArrayList<JButton> botones) {
        for (int i = 0; i < botones.size(); i++) {
            try {
                botones.get(i).setIcon(new ImageIcon(ImageIO.read(getFileFromResourceAsStream("cards/" + JavaScoundrelUI.sala.get(i) + ".png"))));
            }
            catch(IOException ex) {
                System.exit(0);
            }
            catch(IndexOutOfBoundsException ex) {
                try {
                    botones.get(i).setIcon(new ImageIcon(ImageIO.read(getFileFromResourceAsStream("cards/blank.png"))));
                    botones.get(i).setEnabled(false);
                }
                catch (IOException exception) {
                    System.exit(0);
                }
                
            }
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
    
    public ArrayList<JButton> getBotones() {
        ArrayList<JButton> listaBotones = new ArrayList<>();
        for (Component c : getComponents()) {
            if (c instanceof Box) {
                for (Component c2 : ((Box) c).getComponents()) {
                    if (c2 instanceof Box) {
                        for (Component c3 : ((Box) c2).getComponents()) {
                            if (c3 instanceof JButton) {
                                if (!((JButton) c3).getText().equalsIgnoreCase("Saltar Sala")) {
                                    listaBotones.add((JButton) c3);
                                }
                            }
                        }
                    }
                }
            }
        }
        return listaBotones;
    }

    public void desabilitarBotones() {
        botonCarta1.setEnabled(false);
        botonCarta2.setEnabled(false);
        botonCarta3.setEnabled(false);
        botonCarta4.setEnabled(false);
        botonSkip.setEnabled(false);
    }

    public void abilitarBotones() {
        botonCarta1.setEnabled(true);
        botonCarta2.setEnabled(true);
        botonCarta3.setEnabled(true);
        botonCarta4.setEnabled(true);
    }

    public void checkSaltado() {
        if (JavaScoundrelUI.jugador.isSaltadoSalaPrevia()) {
            botonSkip.setEnabled(false);
        } else {
            botonSkip.setEnabled(true);
        }
    }

    public void checkPalo(JPanel mainPanel) {
        if (JavaScoundrelUI.seleccionada.getPalo() == 'c') {
            JavaScoundrelUI.curar(JavaScoundrelUI.seleccionada);
        } else if (JavaScoundrelUI.seleccionada.getPalo() == 'd') {
            JavaScoundrelUI.equiparArma(JavaScoundrelUI.seleccionada);
        } else {
            MainMenu_V1 main = new MainMenu_V1();
            main.switchPanel(mainPanel, "Lucha");
            JavaScoundrelUI.menu.getPantallaLucha().asignarCartaBoton();
            JavaScoundrelUI.menu.getPantallaLucha().checkArma();
            JavaScoundrelUI.menu.getPanelGuardado().setVisible(false);
        }
    }

    public void controlCarta(JPanel mainPanel) {
        JavaScoundrelUI.menu.getPanelGuardado().desactivarGuardado();
        botonSkip.setEnabled(false);
        checkPalo(mainPanel);
        JavaScoundrelUI.tempSala.remove(JavaScoundrelUI.seleccionada);
        JavaScoundrelUI.checkSala();
        JavaScoundrelUI.menu.getPanelDatos().updateLabels();
        JavaScoundrelUI.checkResultado();
    }

}
