package models;


import models.ships.Ship;
import java.util.ArrayList;
import java.util.List;

public class Sea {
    private List<Ship> ships = new ArrayList<>();

    public List<Ship> getShips() {
        return ships;
    }

    public void addShips(Ship ship) {
        ships.add(ship);
    }
}