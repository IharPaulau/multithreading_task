package models.ships;

import models.bay.Pier;
import models.bay.Port;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static models.ships.ShipMission.*;


public class Ship extends Thread {
    private Lock lock = new ReentrantLock();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void goToPier(Queue<Pier> allPiers) throws InterruptedException {
        pier = allPiers.poll();
        System.out.println(this.getName() + " find free pier " + pier.getPierId() + " and moored to him");
        checkWhatToDo();
    }

    private void checkWhatToDo() throws InterruptedException {
        lock.lock();
        if (shipMission == FOR_LOAD) {
            loadShip();
        } else unloadShip();
        sailAway();
        lock.unlock();
    }

    private void unloadShip() throws InterruptedException {
        System.out.println(this.getName() + " starts loading containers...");
        while (!isEmpty() & !isInterrupted()) {
            if (freeSpaceAvailable()) {
                port.storage.loadStorage();
                containersOnTheBoard--;
                System.out.println(this.getName() + " unloaded one container.");
                sleep(100);
            } else
                waitFreeSpaceOrSailAway();
        }
        if (!isInterrupted())
            System.out.println(this.getName() + " now fully unloaded");
    }

    private void loadShip() throws InterruptedException {
        System.out.println(this.getName() + " starts loading containers...");
        while (!isFullyLoaded() & !isInterrupted()) {
            if (containersAvailable()) {
                port.storage.unloadStorage();
                containersOnTheBoard++;
                System.out.println(this.getName() + " loaded one container.");
                sleep(100);
            } else
                waitContainersOrSailAway();
        }
        if (!isInterrupted())
            System.out.println(this.getName() + " now fully loaded");
    }

    private void waitContainersOrSailAway() throws InterruptedException {
        System.out.println("not enough containers " + this.getName() + " start to waiting...");
        for (int i = 0; i < 5; i++) {
            sleep(500);
            if (containersAvailable()) break;
        }
        if (!containersAvailable()) {
            System.out.println(this.getName() + " time is up and the ship needs to return without waiting for the required number of containers");
            interrupt();
        }
    }

    private void waitFreeSpaceOrSailAway() throws InterruptedException {
        System.out.println("not enough free space for containers " + this.getName() + " start to waiting...");
        for (int i = 0; i < 5; i++) {
            sleep(500);
            if (freeSpaceAvailable()) break;
        }
        if (!freeSpaceAvailable()) {
            System.out.println(this.getName() + " time is up and the ship needs to return without waiting for the required free space for containers");
            interrupt();
        }
    }

    private boolean containersAvailable() {
        return port.storage.getContainersOnTheStorage() != 0;
    }

    private boolean freeSpaceAvailable() {
        if (port.storage.getContainersOnTheStorage() == port.storage.getMaxCapacity())
            return false;
        return true;
    }

    private void sailAway() {
        System.out.println(this.getName() + " sail away with " + this.containersOnTheBoard + " containers" + ", freeing the pier " + pier.getPierId() +
                " containers on storage = " + port.storage.getContainersOnTheStorage());
        allPiers.add(pier);
        this.interrupt();
    }
}


