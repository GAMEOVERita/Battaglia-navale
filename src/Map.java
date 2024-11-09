import java.util.Scanner;

public class Map {
    private int DIM = 5;
    public Casella[][] map /*= new Casella[DIM][DIM]*/;


    public Map(int DIM) {
        this.DIM = DIM;
        this.map = new Casella[DIM][DIM];
        //POPOLA MAP
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++)
                map[i][j] = new Casella('~', false);
        }
    }

    public Casella getMap(int y, int x) { // DA SISTEMARE
    	return map[y][x];
    }
    


    public void printMap() {
        System.out.print("\t");
        for (int i = 0; i < DIM; i++)
            System.out.print(i + 1 + "\t");
        for (int i = 0; i < DIM; i++) {
            //stampa y
            System.out.print("\n" + (i + 1) + "\t");
            //stampa x
            for (int j = 0; j < DIM; j++) {
                System.out.print(map[i][j] + "\t");
            }
        }
    }

    public void insertShip(int dimNave, char direzione,int posX, int posY) {
        for (int i = 0; i < dimNave; i++) {
            if (direzione == 'v')
                map[posY + i - 1][posX - 1].setContenuto('+');
            else {
                map[posY - 1][posX + i - 1].setContenuto('+');
            }
        }
    }

    public void attack(int y, int x) {
    	map[y][x].setContenuto('*');
    }
    
    public boolean isWon() {
    	for (int i = 0; i < DIM; i++) {
        	for (int j = 0; j < DIM; j++) {
        		if (map[i][j].getContenuto() == '+') {
        			return false;
        		}
        	}
    	}
    	return true;
    }
}