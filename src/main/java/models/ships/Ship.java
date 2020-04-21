package models.ships;

import models.bay.Pier;
import models.bay.Port;
import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.Semaphore;

import static models.ships.ShipMission.*;

public class Ship extends Thread {
    private static final Logger LOGGER = Logger.getLogger(Ship.class);
    private ShipClassifier shipClassifier;
    private ShipMission shipMission;
    private int containersOnTheBoard;
    private Port port;
    private Pier pier;
    private Semaphore semaphore;
    private Queue<Pier> allPiers;

    public Ship(ShipClassifier shipClassifier, ShipMission shipMission, int containersOnTheBoard, Port port, Semaphore semaphore) {
        this.shipClassifier = shipClassifier;
        this.containersOnTheBoard = containersOnTheBoard;
        this.port = port;
        this.semaphore = semaphore;
        this.shipMission = shipMission;
    }

    private boolean isFullyLoaded() {
        return containersOnTheBoard == shipClassifier.getCapacity();
    }

    private boolean isEmpty() {
        return containersOnTheBoard == 0;
    }

    @Override
    public void run() {
        this.allPiers = port.getPiers();
        try {
            semaphore.acquire();
            goToPier(allPiers);
            sailAway();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            semaphore.release();
        }
    }

    private void goToPier(Queue<Pier> allPiers) throws InterruptedException {
        pier = allPiers.poll();
        LOGGER.info(String.format("Ship '%s' find free pier '%s' and moored to him", this.getName(), pier.getPierId()));
        checkerTypeOfWork();
    }

    private void checkerTypeOfWork() throws InterruptedException {
        if (shipMission == FOR_LOAD) {
            loadShip();
        } else {
            unloadShip();
        }
    }

    private void unloadShip() throws InterruptedException {
        LOGGER.info(String.format("Ship '%s' starts unloading containers...", this.getName()));
        while (!isEmpty() & !isInterrupted()) {
            if (freeSpaceAvailable()) {
                port.getStorage().loadStorage();
                containersOnTheBoard--;
                LOGGER.info(String.format("Ship '%s' unloaded one container.", this.getName()));
                sleep(100);
            } else
                waitFreeSpaceOrSailAway();
        }
        if (!isInterrupted())
            LOGGER.info(String.format("Ship '%s' now fully unloaded.", this.getName()));
    }

    private void loadShip() throws InterruptedException {
        LOGGER.info(String.format("Ship '%s' starts loading containers...", this.getName()));
        while (!isFullyLoaded() & !isInterrupted()) {
            if (containersAvailable()) {
                port.getStorage().unloadStorage();
                containersOnTheBoard++;
                LOGGER.info(String.format("Ship '%s' loaded one container.", this.getName()));
                sleep(100);
            } else
                waitContainersOrSailAway();
        }
        if (!isInterrupted())
            LOGGER.info(String.format("Ship '%s' now fully loaded.", this.getName()));
    }

    private void waitContainersOrSailAway() throws InterruptedException {
        LOGGER.warn(String.format("Not enough containers! Ship '%s' start to waiting...", this.getName()));
        for (int i = 0; i < 5; i++) {
            sleep(500);
            if (containersAvailable()) return;
        }
        if (!containersAvailable()) {
            LOGGER.warn(String.format("Time is up and  ship '%s' needs to return without waiting for the required number of containers", this.getName()));
            interrupt();
        }
    }

    private void waitFreeSpaceOrSailAway() throws InterruptedException {
        LOGGER.warn(String.format("Not enough free space! Ship '%s' start to waiting...", this.getName()));
        for (int i = 0; i < 5; i++) {
            sleep(500);
            if (freeSpaceAvailable()) return;
        }
        if (!freeSpaceAvailable()) {
            LOGGER.warn(String.format("Time is up and ship '%s' needs to return without waiting for the required free space for containers", this.getName()));
            interrupt();
        }
    }

    private boolean containersAvailable() {
        return port.getStorage().getContainersOnTheStorage() != 0;
    }

    private boolean freeSpaceAvailable() {
        return port.getStorage().getContainersOnTheStorage() != port.getStorage().getMaxCapacity();
    }

    private void sailAway() {
        LOGGER.info(String.format("Ship '%s' sail away with %d containers, now pier '%s' is free. Containers on storage - %d", this.getName(),
                this.containersOnTheBoard, pier.getPierId(), port.getStorage().getContainersOnTheStorage()));
        allPiers.add(pier);
        this.interrupt();
    }
}