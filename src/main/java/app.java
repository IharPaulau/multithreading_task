import models.Sea;
import models.bay.Port;
import models.ships.Ship;

import java.util.concurrent.Semaphore;


public class app {


    public static void main(String[] args) {

        Port Wilhelmshaven = new Port();
        Sea northSea = new Sea(Wilhelmshaven);


        for(Ship ship:northSea.ships){
            ship.start();
        }



    }



}
