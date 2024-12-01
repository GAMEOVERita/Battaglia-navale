public class Ship {
    private char state; //COMPLETAMENTE INUTILE
    private char direction;
    private int dimension;
    private int posX;
    private int posY;


    public Ship(int dimensionn) {
        this.dimension = dimensionn;
        this.state = '+';
        this.direction = 'o';
        this.posX = 0;
        this.posY = 0;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public char getState() {
        return state;
    }

    public void setState(char stato) {
        this.state = stato;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direzione) {
        this.direction = direzione;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimensione) {
        this.dimension = dimensione;
    }

    public void insert(Slot[][] map) {


        for (int i = 0; i < this.dimension; i++) {
            //se orizzontale itera x
            if (this.direction == 'o') {
                map[this.posY - 1][this.posX + i - 1].setContent('+');
            }
            //se verticale itera y
            else {
                map[this.posY + i - 1][posX - 1].setContent('+');
            }

        }


    }

    public void isFree(Slot[][] map) throws OccupiedSlotException {
        if (this.direction == 'v') {
            for (int i = this.posY-1; i < this.dimension; i++) {
                if (map[i][this.posX-1].getContent() == '+')
                    throw new OccupiedSlotException("The ship intersecate with an another ship");

            }
        }
            else{
                for (int i = this.posX-1; i < this.dimension; i++) {
                    if (map[this.posY-1][i].getContent() == '+')
                        throw new OccupiedSlotException("The ship intersecate with an another ship");

                }
            }

    }
}
