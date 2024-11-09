import java.util.Scanner;

public class SceltaPosizione {

    public static void sceltaPosizione(char direzione, int DIM, int dimNave, Mappa mappa){
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
        }while(!isFree(posX, posY, dimNave, direzione, DIM, mappa));
        mappa.inserisciNave(dimNave, direzione, posX, posY); // REFERENCE NON VISIBILE (è private all'interno di Mappa.java e va mpdificata)
    }
    
    private static boolean isFree(int posX, int posY, int dimNave, char direzione, int DIM, Mappa mappa) {
        //controllo direzione

        if (direzione == 'v'){ //se verticale
            // x fissa y mobile
            for (int i = 0; i < dimNave; i++){
            	
                if (mappa.getMappa(posY-1+i, posX-1).getContenuto() == '+'){  //UGUALE A SOTTO
                    System.out.println("LA NAVE INTERSECA CON UN'ALTRA GIA' ESISTENTE");
                    return false;
                }
            }
        }else{//se orizzontale
            // x mobile y fissa
            for (int i = 0; i < dimNave; i++){
                if (mappa.getMappa(posY-1, posX-1+i).getContenuto() == '+'){  // DA SISTEMARE
                    System.out.println("LA NAVE INTERSECA CON UN'ALTRA GIA' ESISTENTE");
                    return false;
                }
            }
        }
        return true;
    }

}
