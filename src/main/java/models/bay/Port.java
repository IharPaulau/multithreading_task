package models.bay;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Port {
    private Queue<Pier> piers = new ConcurrentLinkedQueue<>();
    public Storage storage = new Storage();

    public Port() {
        piers.add(new Pier("#1"));
        piers.add(new Pier("#2"));

        System.out.println(String.format("this port have %d piers, and %d containers on the storage",
                piers.size(), storage.getContainersOnTheStorage()));

    }

    public Queue<Pier> getPiers() {
        return piers;
    }
}
