import java.util.Scanner;

public class Giocatore {
    private String nick;
    private int numNavi;
    public Nave[] navi;

    public String getNick() {
        return nick;
    }

    public Giocatore(String nick, int numNavi) {
        this.nick = nick;
        this.numNavi = numNavi;
        this.navi = new Nave[numNavi];
    }

    public void inizializzaNavi() {
        for (int i = 0; i < this.numNavi; i++) {
            navi[i] = new Nave(i + 2);
        }
    }

    public int getNumNavi() {
        return numNavi;
    }

    public void setNumNavi(int numNavi) {
        this.numNavi = numNavi;
    }

    public Nave[] getNavi() {
        return navi;
    }

    public void setNavi(Nave[] navi) {
        this.navi = navi;
    }

    public char scegliDirezione() throws InstantiationError {
        char direzione;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserici la dimensione della nave (o = orizzontale -- v = verticale)");
        direzione = sc.next().charAt(0);
        //changes uppercaser in lowercase
        if (direzione < 91)
            direzione += 32;
        if (direzione != 'v' && direzione != 'o')
            throw new InstantiationError("Symbol for direction doesn't match");
        return direzione;
    }

    public void popolaMappa(Casella[][] mappa, int DIM) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                mappa[i][j] = new Casella('~');
            }
        }
    }

    public void visualizzaMappa(Casella[][] mappa, int DIM) {
        System.out.print("\t");
        for (int i = 0; i < DIM; i++)
            System.out.print(i + 1 + "\t");
        for (int i = 0; i < DIM; i++) {
            //stampa y
            System.out.print("\n" + (i + 1) + "\t");
            //stampa x
            for (int j = 0; j < DIM; j++) {
                System.out.print(mappa[i][j].getContenuto() + "\t");
            }
        }
        System.out.print("\n");
    }

    public int scegliPosizione(char direzione, int DIM, int dimNave, boolean isPosX, Scanner sc) throws IndexOutOfBoundsException {
        int posizione;
        posizione = sc.nextInt();
        if (isPosX && (posizione > DIM || posizione < 1 || direzione == 'o' && posizione > DIM - dimNave + 1))
            throw new IndexOutOfBoundsException("index out of bounds");
        if (!isPosX && ((posizione > DIM || posizione < 1 || direzione == 'v' && posizione > DIM - dimNave + 1))) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return posizione;

    }

    public boolean hasFoundNave(int posX, int posY, Casella[][] mappa) {
        return mappa[posY - 1][posX - 1].getContenuto() == '+';
    }

    public void mostraMappa(Casella[][] mappa, int DIM) {
        System.out.print("\t");
        for (int i = 0; i < DIM; i++)
            System.out.print(i + 1 + "\t");
        for (int i = 0; i < DIM; i++) {
            //stampa y
            System.out.print("\n" + (i + 1) + "\t");
            //stampa x
            for (int j = 0; j < DIM; j++) {
                if (mappa[i][j].isShown()) {
                    System.out.print(mappa[i][j].getContenuto() + "\t");
                }
                else{
                    System.out.print("?\t");
                }
            }
        }
        System.out.print("\n");
    }


}
