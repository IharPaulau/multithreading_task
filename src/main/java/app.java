import models.Sea;
import models.bay.Port;
import models.ships.Ship;

public class App {
    public static void main(String[] args) {

        Port wilhelmshaven = new Port();
        Sea northSea = new Sea(wilhelmshaven);

        for (Ship ship : northSea.ships) {
            ship.start();
        }
    }
}
