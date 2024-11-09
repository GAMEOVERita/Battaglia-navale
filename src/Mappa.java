import java.util.Scanner;

public class Mappa {
    private int DIM = 5;
    private Casella[][] mappa /*= new Casella[DIM][DIM]*/;


    public Mappa(int DIM) {
        this.DIM = DIM;
        this.mappa = new Casella[DIM][DIM];
        //POPOLA MAPPA
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++)
                mappa[i][j] = new Casella('~', false);
        }
    }

    public Casella getMappa(int y, int x) { // DA SISTEMARE
    	return mappa[y][x];
    }
    


    public void stampaMappa() {
        System.out.print("\t");
        for (int i = 0; i < DIM; i++)
            System.out.print(i + 1 + "\t");
        for (int i = 0; i < DIM; i++) {
            //stampa y
            System.out.print("\n" + (i + 1) + "\t");
            //stampa x
            for (int j = 0; j < DIM; j++) {
                System.out.print(mappa[i][j] + "\t");
            }
        }
    }

    private void inserisciNave(int dimNave, char direzione,int posX, int posY) {
        for (int i = 0; i < dimNave; i++) {
            if (direzione == 'v')
                mappa[posY + i - 1][posX - 1].setContenuto('+');
            else {
                mappa[posY - 1][posX + i - 1].setContenuto('+');
            }
        }
    }

    public void attacco(int y, int x) {
    	mappa[y][x].setContenuto('*');
    }
    
    private boolean isWon() {
    	for (int i = 0; i < DIM; i++) {
        	for (int j = 0; j < DIM; j++) {
        		if (mappa[i][j].getContenuto() == '+') {
        			return false;
        		}
        	}
    	}
    	return true;
    }
}