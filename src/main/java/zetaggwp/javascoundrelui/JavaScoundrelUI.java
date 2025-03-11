package zetaggwp.javascoundrelui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class JavaScoundrelUI {

    private static final int DERROTA = 0;
    private static final int VICTORIA = 1;
    public static ArrayList<Carta> baraja = new ArrayList<>();
    public static ArrayList<Carta> sala = new ArrayList<>();
    public static ArrayList<Carta> tempSala = new ArrayList<>();
    public static Jugador jugador = new Jugador(20, 0);
    public static Carta seleccionada;
    public static MainMenu_V1 menu;

    public static void main(String[] args) {
        menu = new MainMenu_V1();
        menu.setVisible(true);
    }

    public static void jugarPartida() {
        generarBaraja();
        generarSala();
        jugador.reset();
        controlGUIPartida();
    }

    public static void generarBaraja() {
        baraja.clear();
        tempSala.clear();
        sala.clear();
        for (int picas = 2; picas <= 14; picas++) {
            baraja.add(new Carta(picas, 'p'));
        }
        for (int treboles = 2; treboles <= 14; treboles++) {
            baraja.add(new Carta(treboles, 't'));
        }
        for (int diamantes = 2; diamantes <= 10; diamantes++) {
            baraja.add(new Carta(diamantes, 'd'));
        }
        for (int corazones = 2; corazones <= 10; corazones++) {
            baraja.add(new Carta(corazones, 'c'));
        }
        Collections.shuffle(baraja);
    }

    public static void checkSala() {
        if (tempSala.size() <= 1) {
            sala.clear();
            sala.addAll(tempSala);
            generarSala();
            controlGUIPartida();
        }
    }

    public static void checkResultado() {
        if (jugador.getHP() < 1) {
            menu.getPanelDatos().resultadoPartida(JavaScoundrelUI.DERROTA);
            menu.getPantallaSala().desabilitarBotones();
        } else if (baraja.isEmpty() && sala.isEmpty() && jugador.getHP() > 1) {
            menu.getPanelDatos().resultadoPartida(JavaScoundrelUI.VICTORIA);
            menu.getPantallaSala().desabilitarBotones();
        }
    }

    public static void generarSala() {
        if (sala.size() < 4) {
            if (baraja.size() > 3) {
                while (sala.size() < 4) {
                    Carta carta = baraja.getFirst();
                    sala.add(carta);
                    baraja.remove(carta);
                }
            } else {
                while (!baraja.isEmpty()) {
                    Carta carta = baraja.getFirst();
                    sala.add(carta);
                    baraja.remove(carta);
                }
            }
        }
        tempSala.clear();
        tempSala.addAll(sala);
        jugador.setBebidoPocionSala(false);
        jugador.setSaltadoSalaPrevia(false);
    }

    public static void saltarSala() {
        for (int i = sala.size()-1; i >= 0; i--) {
            baraja.addLast(sala.get(i));
            sala.remove(i);
        }
        generarSala();
        jugador.setSaltadoSalaPrevia(true);
    }

    public static void seleccionarCarta(int index) {
        seleccionada = sala.get(index);
    }

    public static void lucharAMano(Carta monstruo) {
        jugador.setHP(jugador.getHP() - monstruo.getValor());
    }

    public static void lucharConArma(Carta monstruo) {
        int valorMonstruoReal = monstruo.getValor() - jugador.getArmaEquipada();
        if (valorMonstruoReal < 0) {
            valorMonstruoReal = 0;
        }
        jugador.setHP(jugador.getHP() - valorMonstruoReal);
        jugador.setÚltimoMonstruo(monstruo.getValor());
    }

    public static void curar(Carta pocion) {
        if (!jugador.isBebidoPocionSala()) {
            jugador.setHP(jugador.getHP() + pocion.getValor());
        }
        jugador.setBebidoPocionSala(true);
    }

    public static void equiparArma(Carta arma) {
        jugador.setArmaEquipada(arma.getValor());
        jugador.setÚltimoMonstruo(15);
    }

    public static void guardar() {
        try {
            ObjectOutputStream obOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ScoundrelSave.bin")));
            obOut.writeObject(baraja);
            obOut.writeObject(sala);
            obOut.writeObject(jugador);
            obOut.close();
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public static void cargar() {
        try {
            ObjectInputStream obIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ScoundrelSave.bin")));
            try {
                baraja = (ArrayList<Carta>) obIn.readObject();
                sala = (ArrayList<Carta>) obIn.readObject();
                jugador = (Jugador) obIn.readObject();
                obIn.close();
                controlGUIPartida();
            } catch (ClassNotFoundException ex) {
                System.exit(0);
            }
            obIn.close();
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public static void controlGUIPartida() {
        menu.getPanelDatos().updateLabels();
        menu.getPanelGuardado().setVisible(true);
        menu.getPanelGuardado().activarGuardado();
        menu.getPanelDatos().setVisible(true);
        menu.getPantallaSala().abilitarBotones();
        menu.getPantallaSala().asignarCartaBoton(menu.getPantallaSala().getBotones());
        menu.getPantallaSala().checkSaltado();
    }
}
