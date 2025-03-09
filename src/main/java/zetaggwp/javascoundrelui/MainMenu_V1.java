package zetaggwp.javascoundrelui;

import javax.swing.*;
import java.awt.*;

public class MainMenu_V1 extends JFrame {

    private JPanel mainPanel;

    private PantallaTitulo pantallaTitulo;
    private PantallaSala pantallaSala;
    private PanelDatos panelDatos;
    private PanelGuardado panelGuardado;
    private PantallaLucha pantallaLucha;

    public MainMenu_V1() {
        setTitle("Scoundrel in Java");
        setResizable(false);
        setLayout(new BorderLayout());
        Dimension tamañoPantalla = getTamañoPantalla();

        setSize(tamañoPantalla.width / 2, tamañoPantalla.height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        pantallaTitulo = new PantallaTitulo(mainPanel);
        pantallaSala = new PantallaSala(mainPanel);
        panelDatos = new PanelDatos();
        panelGuardado = new PanelGuardado(mainPanel);
        pantallaLucha = new PantallaLucha(mainPanel);

        mainPanel.add(pantallaTitulo, "Titulo");
        mainPanel.add(pantallaSala, "Sala");
        mainPanel.add(pantallaLucha, "Lucha");

        add(panelDatos, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(panelGuardado, BorderLayout.SOUTH);

    }

    public void switchPanel(Container container, String panelName) {
        CardLayout card = (CardLayout) (container.getLayout());
        card.show(container, panelName);
    }

    public Dimension getTamañoPantalla() {
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        return pantalla.getScreenSize();
    }

    public PantallaTitulo getPantallaTitulo() {
        return pantallaTitulo;
    }

    public void setPantallaTitulo(PantallaTitulo pantallaTitulo) {
        this.pantallaTitulo = pantallaTitulo;
    }

    public PantallaSala getPantallaSala() {
        return pantallaSala;
    }

    public void setPantallaSala(PantallaSala pantallaSala) {
        this.pantallaSala = pantallaSala;
    }

    public PanelDatos getPanelDatos() {
        return panelDatos;
    }

    public void setPanelDatos(PanelDatos panelDatos) {
        this.panelDatos = panelDatos;
    }

    public PanelGuardado getPanelGuardado() {
        return panelGuardado;
    }

    public void setPanelGuardado(PanelGuardado panelGuardado) {
        this.panelGuardado = panelGuardado;
    }

    public PantallaLucha getPantallaLucha() {
        return pantallaLucha;
    }

    public void setPantallaLucha(PantallaLucha pantallaLucha) {
        this.pantallaLucha = pantallaLucha;
    }

}
