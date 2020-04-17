import models.Sea;
import models.bay.Port;
import models.ships.Ship;

import java.util.concurrent.Semaphore;


public class app {


    public static void main(String[] args) {

        Port wilhelmshaven = new Port();
        Sea northSea = new Sea(wilhelmshaven);


        for (Ship ship : northSea.ships) {
            ship.start();
        }



    }


}
