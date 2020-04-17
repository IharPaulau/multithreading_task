package models.bay;

public class Storage {
    private  int maxCapacity = 100;
    private  int containersOnTheStorage = 80;

    public Storage(){
    }

    public int unloadStorage(){
        if(containersOnTheStorage>0)
        return containersOnTheStorage--;
        return containersOnTheStorage;
    }

    public int loadStorage(){
        if(maxCapacity>containersOnTheStorage)
        return containersOnTheStorage++;
        return containersOnTheStorage;
    }

    public int getContainersOnTheStorage() {
        return containersOnTheStorage;
    }
}
