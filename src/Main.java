import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player winner = null;
        Scanner sc = new Scanner(System.in);
        boolean flag;
        int DIM_MAP = 0;
        //chose map dimension
        do {
            System.out.println("Insert the map size (choose a number between 5 and 7 both included)");
            flag = true;
            try {
                DIM_MAP = sc.nextInt();
            } catch (Exception InputMismatchException) {
                System.out.println("the value must be an integer number");
                sc.nextLine();
                flag = false;
            }
        } while (!flag || DIM_MAP < 5 || DIM_MAP > 7);
        sc.nextLine();

        Player player[] = new Player[2];


        //scelta navi
        for (int j = 0; j < player.length; j++) {
            String nickname;
            System.out.println("please write the nickname of player " + (j + 1));
            nickname = sc.nextLine();
            player[j] = new Player(nickname, DIM_MAP - 3, DIM_MAP);
            player[j].initializationShip();
            player[j].populateMap(DIM_MAP);
            for (int i = 0; i < player[j].getNumShips(); i++) {
                //direction
                //giocatore 1
                do {
                    System.out.println(player[j].getNick() + "'s turn");
                    flag = true;
                    try {
                        player[j].getShip()[i].setDirection(player[j].chooseDirection());
                    } catch (InstantiationError e) {
                        System.out.println("Direction must be either 'o' or 'v' ");
                        flag = false;
                    }
                } while (!flag);


                do {
                    //posX
                    do {
                        System.out.println("please insert the number for the X axis");
                        do {
                            flag = true;
                            try {
                                player[j].getShip()[i].setPosX(player[j].choosePosition(player[j].getShip()[i].getDirection(), DIM_MAP, player[j].getShip()[i].getDimension(), true, sc));
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("the ship can't be placed outside of the game's map");
                                flag = false;
                            } catch (Exception InputMismatchException) {
                                sc.nextLine();
                                System.out.println("the value must be an integer number");
                                flag = false;
                            }
                        } while (!flag);
                        //posY

                        System.out.println("please insert the number for the X axis");

                        try {
                            player[j].getShip()[i].setPosY(player[j].choosePosition(player[j].getShip()[i].getDirection(), DIM_MAP, player[j].getShip()[i].getDimension(), false, sc));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("the ship can't be placed outside of the game's map");
                            flag = false;
                        } catch (Exception InputMismatchException) {
                            System.out.println("the value must be an integer number");
                            flag = false;
                            sc.nextLine();
                        }
                    } while (!flag);

                    try {
                        player[j].getShip()[i].isFree(player[j].getMap());
                    } catch (OccupiedSlotException e) {
                        System.out.println("the ship can't overlap with an already existant ship");
                        flag = false;
                    }


                } while (!flag);

                player[j].getShip()[i].insert(player[j].getMap());
                player[j].printMap(DIM_MAP);

            }
            sc.nextLine();
        }
        //attaca nave


        do {
            for (int i = 0; i < player.length; i++) {
                //giocatore 1
                player[i].showMap(DIM_MAP);
                int guessPosX = 0;
                int guessPosY = 0;

                do {
                    flag = true;

                    System.out.println(player[(i == 0) ? i + 1 : i - 1].getNick() + "please write the X...");
                    try {
                        guessPosX = sc.nextInt();
                    } catch (Exception InputMismatchException) {
                        sc.nextLine();
                        System.out.println("the value must be an integer number");
                        flag = false;
                    }
                } while (!flag || guessPosX > DIM_MAP || guessPosX < 1);
                do {
                    flag = true;
                    System.out.println("...and the Y coordinate where you want to shoot");
                    try {
                        guessPosY = sc.nextInt();
                    } catch (Exception InputMismatchException) {
                        sc.nextLine();
                        System.out.println("the value must be an integer number");
                        flag = false;
                    }
                } while (!flag || guessPosY > DIM_MAP || guessPosY < 0);
                player[i].getMap()[guessPosY - 1][guessPosX - 1].setShown(true);
                if (player[i].hasFoundShip(guessPosX, guessPosY)) {
                    player[i].getMap()[guessPosY - 1][guessPosX - 1].setContent('x');
                    System.out.println("the enemy ship has been hit");
                } else {
                    System.out.println("blank shot");
                }
                winner = player[(i == 0) ? i + 1 : i - 1];
                System.out.println(i);
                flag = true;
                for (int k = 0; k < DIM_MAP; k++) {
                    for (int j = 0; j < DIM_MAP; j++) {
                        if (player[i].getMap()[k][j].getContent() == '+') {
                            flag = false;
                        }
                    }
                }
                player[i].printMap(DIM_MAP);
                if(flag)
                    break;
            }
        } while (!flag);
        System.out.println("THE WINNER IS " + winner.getNick());
    }
}
