import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Giocatore winner = null;
        Scanner sc = new Scanner(System.in);
        boolean flag;
        int DIM_MAPPA = 0;
        //chose map dimension
        do {
            System.out.println("Inserire la dimensione della mappa (da 5 a 7)");
            flag = true;
            try {
                DIM_MAPPA = sc.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("Il valore deve essere intero");
                sc.nextLine();
                flag = false;
            }
        } while (!flag || DIM_MAPPA < 5 || DIM_MAPPA > 7);
        sc.nextLine();

        Giocatore giocatori[] = new Giocatore[2];


        //scelta navi
        for (int j = 0; j < giocatori.length; j++) {
            String nickInserito;
            System.out.println("inserire il nickName del " + (j + 1) + " giocatore");
            nickInserito = sc.nextLine();
            giocatori[j] = new Giocatore(nickInserito, DIM_MAPPA - 3, DIM_MAPPA);
            giocatori[j].inizializzaNavi();
            giocatori[j].popolaMappa(DIM_MAPPA);
            for (int i = 0; i < giocatori[j].getNumNavi(); i++) {
                //direction
                //giocatore 1
                do {
                    System.out.println("Turno di " + giocatori[j].getNick());
                    flag = true;
                    try {
                        giocatori[j].getNavi()[i].setDirezione(giocatori[j].scegliDirezione());
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
                                giocatori[j].getNavi()[i].setPosX(giocatori[j].scegliPosizione(giocatori[j].getNavi()[i].getDirezione(), DIM_MAPPA, giocatori[j].getNavi()[i].getDimensione(), true, sc));
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
                            giocatori[j].getNavi()[i].setPosY(giocatori[j].scegliPosizione(giocatori[j].getNavi()[i].getDirezione(), DIM_MAPPA, giocatori[j].getNavi()[i].getDimensione(), false, sc));
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
                        giocatori[j].getNavi()[i].isFree(giocatori[j].getMappa());
                    } catch (OccupiedSlotException e) {
                        System.out.println("la nave interseca con un'altra");
                        flag = false;
                    }


                } while (!flag);

                giocatori[j].getNavi()[i].insert(giocatori[j].getMappa());
                giocatori[j].visualizzaMappa(DIM_MAPPA);

            }
            sc.nextLine();
        }
        //attaca nave


        do {
            for (int i = 0; i < giocatori.length; i++) {
                //giocatore 1
                giocatori[i].mostraMappa(DIM_MAPPA);
                int indovinaPosX = 0;
                int indovinaPosY = 0;

                do {
                    flag = true;

                    System.out.println(giocatori[(i == 0) ? i + 1 : i - 1].getNick() + " inserire la x... ");
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
                giocatori[i].getMappa()[indovinaPosY - 1][indovinaPosX - 1].setShown(true);
                if (giocatori[i].hasFoundNave(indovinaPosX, indovinaPosY)) {
                    giocatori[i].getMappa()[indovinaPosY - 1][indovinaPosX - 1].setContenuto('x');
                    System.out.println("nave affondata");
                } else {
                    System.out.println("colpo a vuoto");
                }
                winner = giocatori[(i == 0) ? i + 1 : i - 1];
                System.out.println(i);
                flag = true;
                for (int k = 0; k < DIM_MAPPA; k++) {
                    for (int j = 0; j < DIM_MAPPA; j++) {
                        if (giocatori[i].getMappa()[k][j].getContenuto() == '+') {
                            flag = false;
                        }
                    }
                }
                giocatori[i].visualizzaMappa(DIM_MAPPA);
                if(flag)
                    break;
            }
        } while (!flag);
        System.out.println("il vincitore è " + winner.getNick());
    }
}
