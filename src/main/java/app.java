import models.Sea;
import models.bay.Port;
import models.ships.Ship;
import models.ships.ShipClassifier;
import models.ships.ShipMission;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) {
        Port wilhelmshaven = createPort(20, 100, 2);
        Sea northSea = new Sea();
        Semaphore semaphore = new Semaphore(2, true);

        northSea.addShips(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.addShips(new Ship(ShipClassifier.LARGE, ShipMission.FOR_UNLOAD, 20, wilhelmshaven, semaphore));
        northSea.addShips(new Ship(ShipClassifier.LITTLE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.addShips(new Ship(ShipClassifier.LITTLE, ShipMission.FOR_UNLOAD, 5, wilhelmshaven, semaphore));
        northSea.addShips(new Ship(ShipClassifier.LARGE, ShipMission.FOR_UNLOAD, 10, wilhelmshaven, semaphore));
        northSea.addShips(new Ship(ShipClassifier.LARGE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.addShips(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, wilhelmshaven, semaphore));
        northSea.addShips(new Ship(ShipClassifier.LARGE, ShipMission.FOR_UNLOAD, 20, wilhelmshaven, semaphore));

        for (Ship ship : northSea.getShips()) {
            ship.start();
        }
    }

    private static Port createPort(int initialNumberOfContainers, int maxCapacity, int initialNumberOfPiers) {
        return new Port(initialNumberOfContainers, maxCapacity, initialNumberOfPiers);
    }
}
