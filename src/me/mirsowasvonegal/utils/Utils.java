package me.mirsowasvonegal.utils;

import java.util.Set;
import java.util.TreeSet;

public class Utils {

    public static Set<Integer> stringArrayToIntSet(String[] list) {
        Set<Integer> intList = new TreeSet<>();
        for (String value : list) {
            intList.add(Integer.parseInt(value));
        }
        return intList;
    }

}
