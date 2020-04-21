import models.Sea;
import models.bay.Port;
import models.ships.Ship;
import models.ships.ShipClassifier;
import models.ships.ShipMission;
import org.apache.log4j.Logger;
import java.util.concurrent.Semaphore;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) {
        Port wilhelmshaven = createPort(0, 100, 2);
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
        LOGGER.info(String.format("Making a port with %d piers, a storage with %d containers and a maximum capacity of %d ", initialNumberOfPiers,
                initialNumberOfContainers, maxCapacity));
        return new Port(initialNumberOfContainers, maxCapacity, initialNumberOfPiers);
    }
}
