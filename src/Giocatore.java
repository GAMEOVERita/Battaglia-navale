import java.util.Scanner;

public class Giocatore {
    private String nick;
    private Mappa mappa;

    public Giocatore(String nick) {
        this.nick = nick;
    }

    public char sceltaDirezione() {
        char direzione;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\ninserire se la nave sarà Verticale o Orizziontale");
            direzione = sc.next().charAt(0);
            if (direzione < 91)
                direzione += 32;
        } while (direzione != 'v' && direzione != 'o');
        return direzione;
    }

}
