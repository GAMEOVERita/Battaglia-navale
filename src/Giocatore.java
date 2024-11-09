import java.util.Scanner;

public class Giocatore {
    private String nickname;
    private Map map;

    public Giocatore(String nickname) {
        this.nickname = nickname;
    }

    public char sceltaDirezione() {
        char direzione;
        Scanner sc = new Scanner(System.in);
        boolean isValidated = true;
        do {
        	if (!isValidated) {
        		System.out.print("\nWARNING: are you retarded? you have to put either 'v' or 'o'");
        	}
            System.out.println("\nchoose if the ship is going to be placed vertically of orizzontaly ('v' and 'o') ");
            direzione = sc.next().charAt(0);
            if (direzione < 91)
                direzione += 32;
            isValidated = false;
        } while (direzione != 'v' && direzione != 'o');
        return direzione;
    }

}
