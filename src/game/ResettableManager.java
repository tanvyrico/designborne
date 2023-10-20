package game;

import java.util.ArrayList;

public class ResettableManager {
    private static ArrayList<Resettables> resettableList = new ArrayList<>();

    public static void addResettable(Resettables resettable){
        resettableList.add(resettable);
    }

    public static void removeResettable(Resettables resettable){
        resettableList.remove(resettable);
    }
    public static void executeReset(){
        ArrayList<Resettables> resettableListCopy = new ArrayList<>(resettableList);
        for(Resettables resettable : resettableListCopy){
            resettable.reset();
        }
    }



}
