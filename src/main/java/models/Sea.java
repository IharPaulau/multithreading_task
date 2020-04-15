package models;

import models.bay.Port;
import models.ships.Ship;
import models.ships.ShipClassifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Sea {
    public List<Ship> ships = new ArrayList<>();

    public Sea(Port Wilhelmshaven){
        Semaphore semaphore = new Semaphore(2, true);
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE,0, Wilhelmshaven, semaphore));


    }


}
