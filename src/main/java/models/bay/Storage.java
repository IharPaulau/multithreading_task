package models.bay;

public class Storage {
    private int maxCapacity = 100;
    private int containersOnTheStorage = 90;

    public Storage() {
    }

    public int unloadStorage() {
        return containersOnTheStorage--;
    }

    public int loadStorage() {
        return containersOnTheStorage++;
    }

    public int getContainersOnTheStorage() {
        return containersOnTheStorage;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
