import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Giocatore p1 = new Giocatore("dummy");
        int DIM;
        char direzione;
        do {

            try {
                System.out.println("insert map size (between 5 and 12 both included)...");
                DIM = sc.nextInt();
            }catch(Exception e){
                System.out.println("WARNING: the value is not valid, retry");
                DIM = 0;
                sc.nextLine();
            }
        } while (DIM < 5 || DIM >12);
        Map map = new Map(DIM);


        map.printMap();
        for (int i = 2; i <= 5 ; i++) {
            for (int j = 0; j < DIM - 1 - i; j++) {
                System.out.print("\nShip "+(j+1)+" out of "+(DIM-1-i)+" which is "+i+" cells long");
                direzione = p1.sceltaDirezione();
                SceltaPosizione.sceltaPosizione(direzione, DIM, i, map);
                map.printMap();
            }
        }
    }
}