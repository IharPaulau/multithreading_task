package models.ships;

import models.Sea;
import models.bay.Port;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class ShipTest {
    private Port testPort;
    private Sea testSea = new Sea();
    private Semaphore semaphore;

    @Before
    public void init() {
        semaphore = new Semaphore(2, true);
    }

    @Test
    public void shouldHaveExpectedNumberContainersOnStorage_whenAllShipsSailAway() {
        testPort = new Port(50, 100, 2);
        fillListByTestShips();
        startTesting();
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(50, testPort.getStorage().getContainersOnTheStorage());
    }

    @Test
    public void shouldContainNoMoreThanMaximumPermissibleValueOnTheStorage_whenShipsUnloading() {
        testPort = new Port(99, 100,2);
        Ship testShip = createNewShip(ShipMission.FOR_UNLOAD, 10);
        testShip.start();
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(testPort.getStorage().getContainersOnTheStorage(), testPort.getStorage().getMaxCapacity());
    }

    private void startTesting() {
        for (Ship ship : testSea.getShips()) {
            ship.start();
        }
    }

    private void fillListByTestShips() {
        testSea.addShips(createNewShip(ShipMission.FOR_UNLOAD, 0));
        testSea.addShips(createNewShip(ShipMission.FOR_LOAD, 10));
        testSea.addShips(createNewShip(ShipMission.FOR_UNLOAD, 10));
        testSea.addShips(createNewShip(ShipMission.FOR_LOAD, 0));
        testSea.addShips(createNewShip(ShipMission.FOR_UNLOAD, 10));
        testSea.addShips(createNewShip(ShipMission.FOR_LOAD, 0));
    }

    private Ship createNewShip(ShipMission shipMission, int containersOnTheBoard){
        return new Ship(ShipClassifier.MIDDLE, shipMission, containersOnTheBoard, testPort, semaphore);
    }
}