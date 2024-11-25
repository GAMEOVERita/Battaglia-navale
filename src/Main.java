import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag;
        int DIM_MAPPA = 0;
        //chose map dimension
        do {
            System.out.println("Inserire la dimensione della mappa (da 5 a 7");
            flag = true;
            try {
                DIM_MAPPA = sc.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Il valore deve essere intero");
                sc.nextLine();
                flag = false;
            }
        } while(!flag || DIM_MAPPA < 5 || DIM_MAPPA > 7);

        Casella[][] mappa;
        Giocatore giocatore = new Giocatore("dummy", DIM_MAPPA - 3);
        giocatore.inizializzaNavi();
        mappa = new Casella[DIM_MAPPA][DIM_MAPPA];

        giocatore.popolaMappa(mappa, DIM_MAPPA);
        giocatore.visualizzaMappa(mappa, DIM_MAPPA);
        for (int i = 0; i < giocatore.getNumNavi(); i++) {
            //direction
            do {
                flag = true;
                try {
                    giocatore.getNavi()[i].setDirezione(giocatore.scegliDirezione());
                } catch (InstantiationError e) {
                    System.out.println("Direction must be either 'o' or 'v' ");
                    flag = false;
                }
            } while (!flag);


            do {
                //posX
                do {
                    System.out.println("Inserisci la posizione X");
                    do {
                        flag = true;
                        try {
                            giocatore.getNavi()[i].setPosX(giocatore.scegliPosizione(giocatore.getNavi()[i].getDirezione(), DIM_MAPPA, giocatore.getNavi()[i].getDimensione(), true, sc));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("la nave non rientra nei limiti della mappa");
                            flag = false;
                        } catch (Exception InputMismatchException) {
                            sc.nextLine();
                            System.out.println("devi inserire un numero intero");
                            flag = false;
                        }
                    } while (!flag);
                    //posY

                    System.out.println("Inserisci la posizione Y");

                    try {
                        giocatore.getNavi()[i].setPosY(giocatore.scegliPosizione(giocatore.getNavi()[i].getDirezione(), DIM_MAPPA, giocatore.getNavi()[i].getDimensione(), false, sc));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("la nave non rientra nei limiti della mappa");
                        flag = false;
                    } catch (Exception InputMismatchException) {
                        System.out.println("il valore deve essere intero");
                        flag = false;
                        sc.nextLine();
                    }
                } while (!flag);

                try {
                    giocatore.getNavi()[i].isFree(mappa);
                } catch (OccupiedSlotException e) {
                    System.out.println("la nave interseca con un'altra");
                    flag = false;
                }
            } while (!flag);

            giocatore.getNavi()[i].insert(mappa);
            giocatore.visualizzaMappa(mappa, DIM_MAPPA);

        }

    }
}