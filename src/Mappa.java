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

    private boolean isFree(int posX, int posY, int dimNave, char direzione, int DIM) {
        //controllo direzione

        if (direzione == 'v'){ //se verticale
            // x fissa y mobile
            for (int i = 0; i < dimNave; i++){
                if (mappa[posY-1+i][posX-1].getContenuto() == '+'){
                    System.out.println("LA NAVE INTERSECA CON UN'ALTRA GIA' ESISTENTE");
                    return false;
                }
            }
        }else{//se orizzontale
            // x mobile y fissa
            for (int i = 0; i < dimNave; i++){
                if (mappa[posY-1][posX-1+i].getContenuto() == '+'){
                    System.out.println("LA NAVE INTERSECA CON UN'ALTRA GIA' ESISTENTE");
                    return false;
                }
            }
        }
        return true;
    }

    public void sceltaPosizione(char direzione, int DIM, int dimNave){
        int posY, posX;
        boolean isValid;
        Scanner sc = new Scanner(System.in);
        do{
            do {
                isValid = true;
                //richiesta x
                try {
                    System.out.println("inserire la x");
                    posX = sc.nextInt();
                }catch(Exception InputMismatchException){
                    System.out.println("valore invalido");
                    sc.nextLine();
                    posX=-1;
                }
                //controllo x
                if ((direzione == 'o' && posX > DIM - dimNave +1 ) || (posX < 1 || posX >DIM)){
                    isValid = false;
                }
            }while(!isValid);
            do{
                isValid = true;
                //richiesta y
                try{
                    System.out.println("inserire la y");
                    posY = sc.nextInt();
                }catch(Exception InputMismatchException){
                    System.out.println("valore invalido");
                    sc.nextLine();
                    posY=-1;
                }
                //controllo y
                if ((direzione == 'v' && posY > DIM - dimNave +1 ) || (posY < 1 || posY >DIM)){
                    isValid = false;
                }
                //controllo
            }while(!isValid);//x < 0, x > DIM ; x ; x + dim nave > DIM
        }while(!this.isFree(posX, posY, dimNave, direzione, DIM));
        inserisciNave(dimNave, direzione, posX, posY);
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

}
