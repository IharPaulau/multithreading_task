package models.bay;

public class Storage {
    private int maxCapacity = 100;
    private int containersOnTheStorage;

    public Storage(int containersOnTheStorage) {
        this.containersOnTheStorage = containersOnTheStorage;
    }

    public void unloadStorage() {
        containersOnTheStorage--;
    }

    public void loadStorage() {
        containersOnTheStorage++;
    }

    public int getContainersOnTheStorage() {
        return containersOnTheStorage;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
