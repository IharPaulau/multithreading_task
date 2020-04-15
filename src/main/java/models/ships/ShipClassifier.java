package models.ships;

public enum ShipClassifier {
    LITTLE(100), MIDDLE(500), LARGE(1000);

    ShipClassifier(int capacity) {
        this.capacity = capacity;
    }

    private int capacity;

    public int getCapacity() {
        return capacity;
    }
}