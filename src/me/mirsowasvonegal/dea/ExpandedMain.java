package me.mirsowasvonegal.dea;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class ExpandedMain {

    public static void main(String[] args) {
        try {
            int stateCount = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Zustände willst du haben? (Ganzzahl)"));
            if(stateCount >= 1) {
                JOptionPane.showMessageDialog(null,"Du brauchst mindestens einen Zustand!");
                return;
            }
            int startState = Integer.parseInt(JOptionPane.showInputDialog("Welcher Zustand soll dein Startzustand sein? (Ganzzahl)"));
            if(stateCount < startState) {
                JOptionPane.showMessageDialog(null,"Dein Startzustand muss in deiner Zustandsliste vorhanden sein!");
                return;
            }
            Set<Integer> endStates = toIntSet(JOptionPane.showInputDialog("Bitte gebe deine Endzustände an. (Mit Leertaste)").split(" "));
            if(stateCount >= Collections.max(endStates)) {

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte gebe nur Ganzzahlen ein!");
        }
    }

    public static Set<Integer> toIntSet(String[] list) {
        Set<Integer> intList = new TreeSet<>();
        for (String value : list) {
            intList.add(Integer.parseInt(value));
        }
        return intList;
    }

}
