package models.bay;


import org.apache.log4j.Logger;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Port {
    private static final Logger LOGGER = Logger.getLogger(Port.class);
    private Queue<Pier> piers = new ConcurrentLinkedQueue<>();
    public Storage storage;

    public Port(int containersOnTheStorage) {
        storage = new Storage(containersOnTheStorage);
        piers.add(new Pier("#1"));
        piers.add(new Pier("#2"));

        LOGGER.info(String.format("this port have %d piers, and %d containers on the storage",
                piers.size(), storage.getContainersOnTheStorage()));

    }

    public Queue<Pier> getPiers() {
        return piers;
    }
}
