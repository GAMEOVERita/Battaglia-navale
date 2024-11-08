public class Casella {
    private char contenuto;
    private boolean isDiscovered;

    public Casella(char contenuto, boolean isDiscovered) {
        this.contenuto = contenuto;
        this.isDiscovered = isDiscovered;
    }

    public String toString() {
        return "" + this.contenuto;
    }

    public void setContenuto(char contenuto) {
        this.contenuto = contenuto;
    }

    public char getContenuto() {
        return contenuto;
    }
}
