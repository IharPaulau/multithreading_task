package models.ships;

public enum ShipClassifier {
    LITTLE(5), MIDDLE(10), LARGE(20);
    private int capacity;

    ShipClassifier(int capacity) {
        this.capacity = capacity;
    }



    public int getCapacity() {
        return capacity;
    }
}