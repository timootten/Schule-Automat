package me.mirsowasvonegal.dea;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class ExpandedMain {

    public static void main(String[] args) {
        try {
            int stateCount = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Zustände willst du haben? (Ganzzahl)"));
            if(stateCount < 1) {
                JOptionPane.showMessageDialog(null,"Du brauchst mindestens einen Zustand!");
                return;
            }
            int startState = Integer.parseInt(JOptionPane.showInputDialog("Welcher Zustand soll dein Startzustand sein? (Ganzzahl)"));
            if(stateCount < startState) {
                JOptionPane.showMessageDialog(null,"Dein Startzustand muss in deiner Zustandsliste vorhanden sein!");
                return;
            }
            Set<Integer> endStates = toIntSet(JOptionPane.showInputDialog("Bitte gebe deine Endzustände an. (Mit Leertaste)").split(" "));
            if(stateCount < Collections.max(endStates)) {
                JOptionPane.showMessageDialog(null, "Deine Endzustände müssen eine Teilmenge der Zustände sein! (Ganzzahl)");
                return;
            }
            String[] terminals = JOptionPane.showInputDialog("Bitte gebe deine verschiedenen TerminalSymbole ein. (Mit Leertaste)").split(" ");
            ArrayList<Path> paths = new ArrayList<>();

            for (int i = 0; i < stateCount; i++) {
                for (String terminal : terminals) {
                    Integer toState = Integer.parseInt(JOptionPane.showInputDialog(
                            "Zustand: " + i + "\n" +
                            "Terminal: " + terminal + "\n" +
                            "Wohin soll man damit gehen?"));
                    paths.add(new Path(i, toState, terminal));
                }
            }

            DEA dea = new DEA(stateCount, startState, endStates, new HashSet<>(Arrays.asList(terminals)), paths);

            while(true) {
                String input = JOptionPane.showInputDialog("Eingabe eines Wortes: ");
                if(input == null || input.equals("")) return;
                boolean accepted = dea.checkWord(input);
                JOptionPane.showMessageDialog(null, "<html>Dein Wort wird <u><b>" + (accepted ? "" : "nicht") + "</b></u> akzeptiert</html>");
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
