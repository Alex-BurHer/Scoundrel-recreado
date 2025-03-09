package zetaggwp.javascoundrelui;

import java.io.Serializable;

public class Jugador implements Serializable {

    private int MaxHP;
    private int HP;
    private int armaEquipada;
    private int últimoMonstruo;
    private boolean saltadoSalaPrevia;
    private boolean bebidoPocionSala;

    public Jugador() {
        this.últimoMonstruo = 15;
        this.saltadoSalaPrevia = false;
        this.bebidoPocionSala = false;
    }
    
    public Jugador(int MaxHP) {
        this.MaxHP = MaxHP;
        this.HP = MaxHP;
        this.últimoMonstruo = 15;
        this.saltadoSalaPrevia = false;
        this.bebidoPocionSala = false;
    }
    
    public Jugador(int MaxHP,int HP) {
        this.MaxHP = MaxHP;
        this.HP = HP;
        this.últimoMonstruo = 15;
        this.saltadoSalaPrevia = false;
        this.bebidoPocionSala = false;
    }

    public Jugador(int MaxHP,int HP, int armaEquipada, int últimoMonstruo) {
        this.MaxHP = MaxHP;
        this.HP = HP;
        this.armaEquipada = armaEquipada;
        this.últimoMonstruo = últimoMonstruo;
        this.saltadoSalaPrevia = false;
        this.bebidoPocionSala = false;
    }
    
    public int getMaxHP() {
        return MaxHP;
    }

    public void setMaxHP(int MaxHP) {
        this.MaxHP = MaxHP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
        if (this.HP > this.MaxHP) {
            this.HP = this.MaxHP;
        }
        if (this.HP < 0) {
            this.HP = 0;
        }
    }

    public int getArmaEquipada() {
        return armaEquipada;
    }

    public void setArmaEquipada(int armaEquipada) {
        this.armaEquipada = armaEquipada;
    }

    public int getÚltimoMonstruo() {
        return últimoMonstruo;
    }

    public void setÚltimoMonstruo(int últimoMonstruo) {
        this.últimoMonstruo = últimoMonstruo;
    }

    public boolean isSaltadoSalaPrevia() {
        return saltadoSalaPrevia;
    }

    public void setSaltadoSalaPrevia(boolean saltadoSalaPrevia) {
        this.saltadoSalaPrevia = saltadoSalaPrevia;
    }

    public boolean isBebidoPocionSala() {
        return bebidoPocionSala;
    }

    public void setBebidoPocionSala(boolean bebidoPocionSala) {
        this.bebidoPocionSala = bebidoPocionSala;
    }

    @Override
    public String toString() {
        if (últimoMonstruo == 15) {
            return '[' + "HP=" + HP + ", Arma Equipada=" + armaEquipada + ']';
        } else {
            return '[' + "HP=" + HP + ", Arma Equipada=" + armaEquipada + ", Último Monstruo=" + últimoMonstruo + ']';
        }

    }
    
    public void reset() {
        setHP(getMaxHP());
        setArmaEquipada(0);
        setÚltimoMonstruo(15);
        this.saltadoSalaPrevia = false;
        this.bebidoPocionSala = false;
    }
    
}
