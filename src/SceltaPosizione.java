import java.util.Scanner;

public class SceltaPosizione {

    public static void sceltaPosizione(char direzione, int DIM, int dimNave, Map map){
        int posY, posX;
        boolean isValid;
        Scanner sc = new Scanner(System.in);
        do{
            do {
                isValid = true;
                //richiesta x
                try {
                    System.out.println("insert the value for x");
                    posX = sc.nextInt();
                }catch(Exception InputMismatchException){
                    System.out.println("WARNING: value is invalid");
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
                    System.out.println("insert the value for y");
                    posY = sc.nextInt();
                }catch(Exception InputMismatchException){
                    System.out.println("WARNING: the value is invalid");
                    sc.nextLine();
                    posY=-1;
                }
                //controllo y
                if ((direzione == 'v' && posY > DIM - dimNave +1 ) || (posY < 1 || posY >DIM)){
                    isValid = false;
                }
                //controllo
            }while(!isValid);//x < 0, x > DIM ; x ; x + dim nave > DIM
        }while(!isFree(posX, posY, dimNave, direzione, DIM, map));
        map.insertShip(dimNave, direzione, posX, posY);
    }
    
    private static boolean isFree(int posX, int posY, int dimNave, char direzione, int DIM, Map map) {
        //controllo direzione

        if (direzione == 'v'){ //if the ship is vertical
            // y is mobile
            for (int i = 0; i < dimNave; i++){
            	
                if (map.getMap(posY-1+i, posX-1).getContenuto() == '+'){
                    System.out.println("WARNING: the ship intersect with an already placed ship");
                    return false;
                }
            }
        }else{//if the ship is horizontal
            // x is mobile
            for (int i = 0; i < dimNave; i++){
                if (map.getMap(posY-1, posX-1+i).getContenuto() == '+'){
                    System.out.println("WARNING: the ship intersect with an already placed ship");
                    return false;
                }
            }
        }
        return true;
    }

}
