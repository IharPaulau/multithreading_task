package models.bay;

public class Storage {

    public Storage(){

    }

    private  int maxCapacity = 100;
    private  int containersOnTheStorage = 50;

    public int loadStorage(){
        if(containersOnTheStorage>0)
        return containersOnTheStorage-1;
        return containersOnTheStorage;
    }

    public int unloadStorage(){
        if(maxCapacity>containersOnTheStorage)
        return containersOnTheStorage+1;
        return containersOnTheStorage;
    }
}
