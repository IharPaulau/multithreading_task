package models;

import models.bay.Port;
import models.ships.Ship;
import models.ships.ShipClassifier;
import models.ships.ShipMission;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Sea {
    public List<Ship> ships = new ArrayList<>();

    public Sea(Port Wilhelmshaven){
        Semaphore semaphore = new Semaphore(2, true);
        ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, Wilhelmshaven, semaphore));
        ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, Wilhelmshaven, semaphore));

//        ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_UNLOAD, 299, Wilhelmshaven, semaphore));

    }


}
