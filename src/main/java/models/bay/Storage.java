package models.bay;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {

    private int containersOnTheStorage;
    private Lock lock = new ReentrantLock();

    public Storage(int containersOnTheStorage) {
        this.containersOnTheStorage = containersOnTheStorage;
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
        return 100;
    }
}
