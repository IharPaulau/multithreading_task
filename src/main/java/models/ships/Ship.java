package models.ships;

import models.bay.Pier;
import models.bay.Port;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Ship extends Thread {
    private Lock lock = new ReentrantLock();
    private ShipClassifier shipClassifier;
    private ShipMission shipMission;
    private int containersOnTheBoard = 0;
    private boolean fullLoaded = false;
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

    public boolean isFullyLoaded() {
        if (containersOnTheBoard == shipClassifier.getCapacity())
            return fullLoaded = true;
        return fullLoaded = false;
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
        doSmth();
        sailAway(pier);
    }

    private void doSmth() {
        lock.lock();
        try {
            if (this.shipMission == ShipMission.FOR_LOAD) {
                loadShip();
            } else unloadShip();
        } finally {
            lock.unlock();
        }
    }

    private void unloadShip() {
    }

    private void loadShip()  {
        try {
            sleep(550);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " loading containers....");
        while (shipClassifier.getCapacity() > containersOnTheBoard) {
            port.storage.unloadStorage();
            containersOnTheBoard++;
        }
    }

    private void sailAway(Pier pier) {

        System.out.println(this.getName() + " floats away, freeing the pier " + pier.getPierId() + " and left the on storage " + port.storage.getContainersOnTheStorage()+ " containers");
        allPiers.add(pier);
    }
}


