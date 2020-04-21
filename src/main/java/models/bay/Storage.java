package models.bay;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {

    private int containersOnTheStorage;
    private int maxCapacity;
    private Lock lock = new ReentrantLock();

    public Storage(int containersOnTheStorage, int maxCapacity) {
        this.containersOnTheStorage = containersOnTheStorage;
        this.maxCapacity = maxCapacity;
    }

    public void unloadStorage() {
        lock.lock();
        containersOnTheStorage--;
        lock.unlock();
    }

    public void loadStorage() {
        lock.lock();
        containersOnTheStorage++;
        lock.unlock();
    }

    public int getContainersOnTheStorage() {
        return containersOnTheStorage;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
