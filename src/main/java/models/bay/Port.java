package models.bay;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Port {
    private Queue<Pier> piers = new ConcurrentLinkedQueue<>();
    private Storage storage;

    public Port(int containersOnTheStorage, int maxCapacity, int numberOfPiers) {
        storage = new Storage(containersOnTheStorage, maxCapacity);
        pierMaker(numberOfPiers);
    }

    private void pierMaker(int amount) {
        for (int i = 1; i < amount + 1; i++) {
            piers.add(new Pier("#" + i));
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public Queue<Pier> getPiers() {
        return piers;
    }
}
