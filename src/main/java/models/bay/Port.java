package models.bay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Port {
    public List<Pier> piers = new ArrayList<>();
   public Storage storage = new Storage();


    public Port() {
        piers.add(new Pier());
        piers.add(new Pier());
    }




}
