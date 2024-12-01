public class Slot {
    private char content;
    private boolean isShown;


    public Slot(char content) {
        this.content = content;
        this.isShown = false;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }

    public char getContent() {
        return content;
    }

    public void setContent(char content) {
        this.content = content;
    }

}
