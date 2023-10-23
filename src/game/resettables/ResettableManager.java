package game.resettables;

import game.resettables.Resettable;

import java.util.ArrayList;

public class ResettableManager {
    private static ArrayList<Resettable> resettableList = new ArrayList<>();

    public static void addResettable(Resettable resettable){
        resettableList.add(resettable);
    }

    public static void removeResettable(Resettable resettable){
        resettableList.remove(resettable);
    }
    public static void executeReset(){
        ArrayList<Resettable> resettableListCopy = new ArrayList<>(resettableList);
        for(Resettable resettable : resettableListCopy){
            resettable.reset();
        }
    }



}
