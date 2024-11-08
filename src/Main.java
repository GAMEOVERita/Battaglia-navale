import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Giocatore p1 = new Giocatore("dummy");
        int DIM;
        char direzione;
        do {

            try {
                System.out.println("inserire la dimensione della mappa (non minore di 5 o maggiore di 12)...");
                DIM = sc.nextInt();
            }catch(Exception e){
                System.out.println("valore non valido, riprova");
                DIM = 0;
                sc.nextLine();
            }
        } while (DIM < 5 || DIM >12);
        Mappa mappa = new Mappa(DIM);


        mappa.stampaMappa();
        for (int i = 2; i <= 5 ; i++) {
            for (int j = 0; j < DIM - 1 - i; j++) {
                System.out.print("\n"+(j+1)+"' NAVE DI "+(DIM-1-i)+" DA "+i+" POSTI");
                direzione = p1.sceltaDirezione();
                mappa.sceltaPosizione(direzione, DIM, i);
                mappa.stampaMappa();
            }
        }
    }
}

