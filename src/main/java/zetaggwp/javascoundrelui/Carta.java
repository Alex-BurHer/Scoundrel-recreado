package zetaggwp.javascoundrelui;

import java.io.Serializable;

public class Carta implements Serializable {
    private int valor;
    private char palo;

    public Carta(int valor, char palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public char getPalo() {
        return palo;
    }

    public void setPalo(char palo) {
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "["+valor+palo+"]";
    }    
}
