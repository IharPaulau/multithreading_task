package models.bay;


import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Port {
    private Queue<Pier> piers = new ConcurrentLinkedQueue<>();
    public Storage storage = new Storage();

    public Port() {
        piers.add(new Pier("#1"));
        piers.add(new Pier("#2"));
    }

    public Queue<Pier> getPiers() {
        return piers;
    }
}
