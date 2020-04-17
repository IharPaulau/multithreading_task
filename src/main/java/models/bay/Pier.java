package models.bay;

public class Pier {

    private boolean empty = true;
    private String pierId = "";

    public Pier(String pierId) {
        this.pierId = pierId;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getPierId() {
        return pierId;
    }
}
