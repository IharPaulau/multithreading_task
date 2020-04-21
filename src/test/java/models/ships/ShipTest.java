package models.ships;


import models.Sea;
import models.bay.Port;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

public class ShipTest {
    private Port test_port;
    private Sea test_sea;
    private static Semaphore semaphore;

    @Before
    public void init() {
        test_sea = new Sea();
        semaphore = new Semaphore(2, true);
    }

    @Test
    public void shouldHaveExpectedNumberContainersOnStorage_whenAllShipsSailAway() {
        test_port = new Port(20);
        fillListByTestShips();
        startTesting();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(30, test_port.storage.getContainersOnTheStorage());
    }

    @Test
    public void shouldContainNoMoreThanMaximumPermissibleValueOnTheStorage_whenShipsUnloading() {
        test_port = new Port(99);
        fillListByTestShips();
        startTesting();
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(test_port.storage.getContainersOnTheStorage() == test_port.storage.getMaxCapacity());
    }

    private void startTesting() {
        for (Ship ship : test_sea.ships) {
            ship.start();
        }
    }

    private void fillListByTestShips() {
        test_sea.ships.add(new Ship(ShipClassifier.MIDDLE, ShipMission.FOR_LOAD, 0, test_port, semaphore));
        test_sea.ships.add(new Ship(ShipClassifier.LARGE, ShipMission.FOR_UNLOAD, 20, test_port, semaphore));
    }
}