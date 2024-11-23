public class Nave {
    private char stato;
    private char direzione;
    private int dimensione;
    private int posX;
    private int posY;

    public Nave(char stato, char direzione, int dimensione) {
        this.stato = stato;
        this.direzione = direzione;
        this.dimensione = dimensione;
    }

    public Nave(int dimensione) {
        this.dimensione = dimensione;
        this.stato = '+';
        this.direzione = 'o';
        this.posX = 0;
        this.posY = 0;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public char getStato() {
        return stato;
    }

    public void setStato(char stato) {
        this.stato = stato;
    }

    public char getDirezione() {
        return direzione;
    }

    public void setDirezione(char direzione) {
        this.direzione = direzione;
    }

    public int getDimensione() {
        return dimensione;
    }

    public void setDimensione(int dimensione) {
        this.dimensione = dimensione;
    }

    public void insert(Casella[][] mappa) {


        for (int i = 0; i < this.dimensione; i++) {
            //se orizzontale itera x
            if (this.direzione == 'o') {
                mappa[this.posY - 1][this.posX + i - 1].setContenuto('+');
            }
            //se verticale itera y
            else {
                mappa[this.posY + i - 1][posX - 1].setContenuto('+');
            }

        }


    }

    public void isFree(Casella[][] mappa, int DIM) throws OccupiedSlotException {
        if (this.direzione == 'v') {
            for (int i = this.posY-1; i < this.dimensione; i++) {
                if (mappa[i][this.posX-1].getContenuto() == '+')
                    throw new OccupiedSlotException("The ship intersecate with an another ship");

            }
        }
            else{
                for (int i = this.posX-1; i < this.dimensione; i++) {
                    if (mappa[this.posY-1][i].getContenuto() == '+')
                        throw new OccupiedSlotException("The ship intersecate with an another ship");

                }
            }

    }
}
