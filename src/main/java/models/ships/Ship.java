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
    private int containersOnTheBoard = 0;
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
        if (containersOnTheBoard == shipClassifier.getCapacity())
            return true;
        return false;
    }

    private boolean isEmpty() {
        if (containersOnTheBoard == 0) return true;
        return false;
    }


    @Override
    public void run() {
        this.allPiers = port.getPiers();
        try {
            checkDeadLock();
            semaphore.acquire();
            goToPier(allPiers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    void checkDeadLock() throws InterruptedException {
        while (allPiers.size() == 2) {
            if (shipMission == FOR_LOAD & notEnoughContainers()) {
                sleep(1000);
            } else {
                break;
            }
        }
        while (allPiers.size() == 2) {
                    if (shipMission == FOR_UNLOAD & notEnoughFreeSpace()) {
                sleep(1000);
            } else {
                break;
            }

        }
    }

    private boolean notEnoughFreeSpace() {
        if (this.containersOnTheBoard + port.storage.getContainersOnTheStorage() >= port.storage.getMaxCapacity())
            return true;
        return false;
    }

    private boolean notEnoughContainers() {
        if (port.storage.getContainersOnTheStorage() < this.shipClassifier.getCapacity())
            return true;
        return false;
    }


    private void goToPier(Queue<Pier> allPiers) throws InterruptedException {
        pier = allPiers.poll();
        System.out.println(this.getName() + " find free pier " + pier.getPierId() + " and moored to him");
        doSmth();

    }

    private void doSmth() {
        lock.lock();
        if (this.shipMission == FOR_LOAD) {
            loadShip();
        } else unloadShip();

        sailAway(pier);
        lock.unlock();
    }


    private void unloadShip() {
        System.out.println(this.getName() + " unloading " + containersOnTheBoard + " containers.....");
        while (!isEmpty()) {
            containersOnTheBoard--;
            port.storage.loadStorage();
        }

    }

    private void loadShip() {
        System.out.println(this.getName() + " start loading " + shipClassifier.getCapacity() + " containers.....");
        while (!isFullyLoaded()) {
            port.storage.unloadStorage();
            containersOnTheBoard++;
        }

    }

//    private void unloadShip() throws InterruptedException {
//        System.out.println(this.getName() + " unloading containers....");
//        sleep(500);
//        while (!isEmpty()) {
//            if (port.storage.getContainersOnTheStorage() == port.storage.getMaxCapacity()) {
//                sleep(1000);
//            } else {
//                containersOnTheBoard--;
//                port.storage.loadStorage();
//            }
//        }
//        System.out.println(this.getName() + " left the on storage " + port.storage.getContainersOnTheStorage() + " containers");
//    }
//
//    private void loadShip() throws InterruptedException {
//        System.out.println(this.getName() + " loading containers....");
//        sleep(500);
//        while (!isFullyLoaded()) {
//            if (port.storage.getContainersOnTheStorage() == 0) {
//                sleep(1000);
//            } else {
//                port.storage.unloadStorage();
//                containersOnTheBoard++;
//            }
//        }
//        System.out.println(this.getName() + " left the on storage " + port.storage.getContainersOnTheStorage() + " containers");
//    }


    private void sailAway(Pier pier) {
        System.out.println(this.getName() + " sail away, freeing the pier " + pier.getPierId() +
                " containers on storage = " + port.storage.getContainersOnTheStorage());
        allPiers.add(pier);

    }
}


