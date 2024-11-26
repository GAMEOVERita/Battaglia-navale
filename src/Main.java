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
        } while (!flag || DIM_MAPPA < 5 || DIM_MAPPA > 7);

        Casella[][] mappa;
        Giocatore giocatore1 = new Giocatore("dummy", DIM_MAPPA - 3);
        Giocatore giocatore2 = new Giocatore("dummy2", DIM_MAPPA - 3);
        giocatore1.inizializzaNavi();
        mappa = new Casella[DIM_MAPPA][DIM_MAPPA];

        giocatore1.popolaMappa(mappa, DIM_MAPPA);
        giocatore2.popolaMappa(mappa, DIM_MAPPA);
        giocatore1.visualizzaMappa(mappa, DIM_MAPPA);

        //inserisci nave
        for (int i = 0; i < giocatore1.getNumNavi(); i++) {
            //direction
            do {
                flag = true;
                try {
                    giocatore1.getNavi()[i].setDirezione(giocatore1.scegliDirezione());
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
                            giocatore1.getNavi()[i].setPosX(giocatore1.scegliPosizione(giocatore1.getNavi()[i].getDirezione(), DIM_MAPPA, giocatore1.getNavi()[i].getDimensione(), true, sc));
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
                        giocatore1.getNavi()[i].setPosY(giocatore1.scegliPosizione(giocatore1.getNavi()[i].getDirezione(), DIM_MAPPA, giocatore1.getNavi()[i].getDimensione(), false, sc));
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
                    giocatore1.getNavi()[i].isFree(mappa);
                } catch (OccupiedSlotException e) {
                    System.out.println("la nave interseca con un'altra");
                    flag = false;
                }
            } while (!flag);

            giocatore1.getNavi()[i].insert(mappa);
            giocatore1.visualizzaMappa(mappa, DIM_MAPPA);
        }

        //attaca nave

        do {
            giocatore1.mostraMappa(mappa,DIM_MAPPA);
            flag = true;
            int indovinaPosX = 0;
            int indovinaPosY = 0;

            do {
                flag = true;
                System.out.println(giocatore2.getNick() + " inserire la x... ");
                try {
                    indovinaPosX = sc.nextInt();
                } catch (Exception InputMismatchException) {
                    sc.nextLine();
                    System.out.println("Il numero deve essere intero");
                    flag = false;
                }
            } while (!flag || indovinaPosX > DIM_MAPPA || indovinaPosX < 1);
            do {
                flag = true;
                System.out.println("...e la y di dove si vuole provare ad attaccare");
                try {
                    indovinaPosY = sc.nextInt();
                } catch (Exception InputMismatchException) {
                    sc.nextLine();
                    System.out.println("Il numero deve essere intero");
                    flag = false;
                }
            } while (!flag || indovinaPosY > DIM_MAPPA || indovinaPosY < 0);
            mappa[indovinaPosY-1][indovinaPosX-1].setShown(true);
            if (giocatore1.hasFoundNave(indovinaPosX, indovinaPosY, mappa)){
                mappa[indovinaPosY-1][indovinaPosX-1].setContenuto('x');
                System.out.println("nave affondata");
            }
            else{
                System.out.println("colpo a vuoto");
            }

                for (int i = 0; i < DIM_MAPPA; i++) {
                    for (int j = 0; j < DIM_MAPPA; j++)
                        if (mappa[i][j].getContenuto() == '+') {
                            flag = false;
                        }
                }
        } while (!flag);

    }
}