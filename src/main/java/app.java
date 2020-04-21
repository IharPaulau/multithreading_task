import models.Sea;
import models.bay.Port;
import models.ships.Ship;
import models.ships.ShipClassifier;
import models.ships.ShipMission;

import java.util.concurrent.Semaphore;

public class App {
    private static Port wilhelmshaven = new Port(10);
    private static Sea northSea = new Sea();
    private static  Semaphore semaphore = new Semaphore(2, true);

    public static void main(String[] args) {
        northSea.ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.ships.add(new Ship(ShipClassifier.LARGE, ShipMission.FOR_UNLOAD, 20, wilhelmshaven, semaphore));
        northSea.ships.add(new Ship(ShipClassifier.LITTLE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.ships.add(new Ship(ShipClassifier.LITTLE, ShipMission.FOR_UNLOAD, 5, wilhelmshaven, semaphore));
        northSea.ships.add(new Ship(ShipClassifier.LARGE, ShipMission.FOR_UNLOAD, 10, wilhelmshaven, semaphore));
        northSea.ships.add(new Ship(ShipClassifier.LARGE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.ships.add(new Ship(ShipClassifier.LARGE, ShipMission.FOR_UNLOAD, 20, wilhelmshaven, semaphore));

        for (Ship ship : northSea.ships) {
            ship.start();
        }
    }
}
