import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        //chose map dimension
        final int DIM_MAPPA = 5;
        final int NUM_NAVI = DIM_MAPPA-3;
        Scanner sc = new Scanner(System.in);
        Casella[][] mappa;
        Giocatore giocatore = new Giocatore("dummy");
        boolean flag = true;
        Nave nave = new Nave('+', 'v' ,3);
        Nave[] navi = new Nave[NUM_NAVI];
        mappa = new Casella[DIM_MAPPA][DIM_MAPPA];

        giocatore.popolaMappa(mappa, DIM_MAPPA);
        giocatore.visualizzaMappa(mappa, DIM_MAPPA);

        for (int i = 0; i < NUM_NAVI ; i++){
            navi[i] = new Nave(i + 2);
        }
        for(int i = 0 ; i < NUM_NAVI ; i++) {
                //direction
                do {
                    flag = true;
                    try {
                        navi[i].setDirezione(giocatore.scegliDirezione());
                    } catch (InstantiationError e) {
                        System.out.println("Direction must be either 'o' or 'v' ");
                        flag = false;
                    }
                } while (!flag);


                do {
                    //posX

                    System.out.println("Inserisci la posizione X");
                    do {
                        flag = true;
                        try {
                            navi[i].setPosX(giocatore.scegliPosizione(navi[i].getDirezione(), DIM_MAPPA, navi[i].getDimensione(), true, sc ));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("la nave non rientra nei limiti della mappa");
                            flag = false;
                        } catch (Exception InputMismatchException){
                            sc.nextLine();
                            System.out.println("devi inserire un numero intero");
                            flag = false;
                        }
                    } while (!flag);
                    //posY

                    System.out.println("Inserisci la posizione Y");
                    do {
                        flag = true;
                        try {
                            navi[i].setPosY(giocatore.scegliPosizione(navi[i].getDirezione(), DIM_MAPPA, navi[i].getDimensione(), false, sc));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("la nave non rientra nei limiti della mappa");
                            flag = false;
                        } catch (Exception InputMismatchException){
                            System.out.println("il valore deve essere intero");
                            flag =  false;
                            sc.nextLine();
                        }
                    } while (!flag);

                    flag = true;
                    try {
                        navi[i].isFree(mappa, DIM_MAPPA); //non funge
                    } catch (OccupiedSlotException e) {
                        System.out.println("la nave interseca con un'altra");
                        flag = false;
                    }
                } while (!flag);

                navi[i].insert(mappa);
                giocatore.visualizzaMappa(mappa, DIM_MAPPA);

        }

    }
}