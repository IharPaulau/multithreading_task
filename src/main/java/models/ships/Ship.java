package models.ships;

import models.bay.Pier;
import models.bay.Port;

import java.util.List;
import java.util.concurrent.Semaphore;


public class Ship extends Thread {
    private ShipClassifier shipClassifier;
    private int containersOnTheBoard = 0;
    private boolean fullLoaded = false;
    private Port port;
    private Pier pier;
    Semaphore semaphore;

    public Ship(ShipClassifier shipClassifier, int containersOnTheBoard, Port port, Semaphore semaphore) {
        this.shipClassifier = shipClassifier;
        this.containersOnTheBoard = containersOnTheBoard;
        this.port = port;
        this.semaphore = semaphore;
    }

    public boolean isFullyLoaded() {
        if (containersOnTheBoard == shipClassifier.getCapacity())
            return fullLoaded = true;
        return fullLoaded = false;
    }


    @Override
    public void run() {
        int i = 0;
        while (i < 1) {
            List<Pier> allPiers = port.piers;
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                goTopier(allPiers);
                loadShip();
                sailAway();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
            semaphore.release();

        }   
    }

    private void goTopier(List<Pier> allPiers) throws InterruptedException {

        for (Pier pier : allPiers) {
                if(pier.empty = true)
                    System.out.println(this.getName() + " кораблик нашел порт и идет к нему");
                pier.empty = false;
                break;


        }

    }


    private void loadShip() {
        while (shipClassifier.getCapacity() > containersOnTheBoard) {
            port.storage.unloadStorage();
            containersOnTheBoard++;
        }
    }


    private void sailAway() throws InterruptedException {
        sleep(550);
        System.out.println(this.getName() + "уплываю");


    }
}


