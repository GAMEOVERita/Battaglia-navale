import java.util.Scanner;

public class Player {
    private String nick;
    private int numShips;
    private Ship[] ship;
    private Slot map[][];

    public String getNick() {
        return nick;
    }

    public Player(String nick, int numShips,int mapDimension) {
        this.nick = nick;
        this.numShips = numShips;
        this.ship = new Ship[numShips];
        this.map = new Slot[mapDimension][mapDimension];
    }

    public Slot[][] getMap() {
        return map;
    }


    public void initializationShip() {
        for (int i = 0; i < this.numShips; i++) {
        	ship[i] = new Ship(i + 2);
        }
    }

    public int getNumShips() {
        return numShips;
    }

    public void setNumShips(int numShips) {
        this.numShips = numShips;
    }

    public Ship[] getShip() {
        return ship;
    }

    public void setShip(Ship[] ship) {
        this.ship = ship;
    }

    public char chooseDirection() throws InstantiationError {
        char direction;
        Scanner sc = new Scanner(System.in);
        System.out.println("choose if the ship is going to be horizontal [o] or vertical [v]");
        direction = sc.next().charAt(0);
        //changes uppercaser in lowercase
        if (direction < 91)
        	direction += 32;
        if (direction != 'v' && direction != 'o')
            throw new InstantiationError("Symbol for direction doesn't match");
        return direction;
    }

    public void populateMap(int DIM) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                this.map[i][j] = new Slot('~');
            }
        }
    }

    public void printMap(int DIM) {
        System.out.print("\t");
        for (int i = 0; i < DIM; i++)
            System.out.print(i + 1 + "\t");
        for (int i = 0; i < DIM; i++) {
            //stampa y
            System.out.print("\n" + (i + 1) + "\t");
            //stampa x
            for (int j = 0; j < DIM; j++) {
                System.out.print(this.map[i][j].getContent() + "\t");
            }
        }
        System.out.print("\n");
    }

    public int choosePosition(char direction, int DIM, int numShips, boolean isPosX, Scanner sc) throws IndexOutOfBoundsException {
        int Position;
        Position = sc.nextInt();
        if (isPosX && (Position > DIM || Position < 1 || direction == 'o' && Position > DIM - numShips + 1))
            throw new IndexOutOfBoundsException("index out of bounds");
        if (!isPosX && ((Position > DIM || Position < 1 || direction == 'v' && Position > DIM - numShips + 1))) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return Position;

    }

    public boolean hasFoundShip(int posX, int posY) {
        return map[posY - 1][posX - 1].getContent() == '+';
    }

    public void showMap(int DIM) {
        System.out.print("\t");
        for (int i = 0; i < DIM; i++)
            System.out.print(i + 1 + "\t");
        for (int i = 0; i < DIM; i++) {
            //stampa y
            System.out.print("\n" + (i + 1) + "\t");
            //stampa x
            for (int j = 0; j < DIM; j++) {
                if (this.map[i][j].isShown()) {
                    System.out.print(this.map[i][j].getContent() + "\t");
                }
                else{
                    System.out.print("?\t");
                }
            }
        }
        System.out.print("\n");
    }


}
