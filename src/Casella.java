public class Casella {
    private char contenuto;
    private boolean isShown;


    public Casella(char contenuto) {
        this.contenuto = contenuto;
        this.isShown = false;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }

    public char getContenuto() {
        return contenuto;
    }

    public void setContenuto(char contenuto) {
        this.contenuto = contenuto;
    }

}
