package me.mirsowasvonegal.dea;

import me.mirsowasvonegal.utils.Utils;

import javax.swing.*;
import java.util.*;

public class DEAMenu {

    public static void open() {
        try {
            int input = Integer.parseInt(JOptionPane.showInputDialog(
                    "Was willst du machen?\n" +
                            "1. Einen statischen Automaten benutzen\n" +
                            "2. Einen neuen Automaten erstellen"));
            if(input == 1) {
                openStaticDEA();
            } else if(input == 2) {
                openVariableDEA();
            } else {
                JOptionPane.showMessageDialog(null, "Bitte gebe nur 1 oder 2 ein!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte gebe nur Ganzzahlen ein!");
        }
    }

    private static void openStaticDEA() {
        JOptionPane.showMessageDialog(null, "Dieser Automat hat die Sprache: L(DEA)=w besteht aus einer geraden Anzahl von a's und b's");
        Set<Integer> endStates = new TreeSet<>();
        endStates.add(0);
        Set<String> terminals = new HashSet<>();
        terminals.add("a");
        terminals.add("b");
        ArrayList<DEAPath> paths = new ArrayList<>();
        paths.add(new DEAPath(0, 1, "a"));
        paths.add(new DEAPath(0, 2, "b"));
        paths.add(new DEAPath(1, 0, "a"));
        paths.add(new DEAPath(1, 3, "b"));
        paths.add(new DEAPath(2, 3, "a"));
        paths.add(new DEAPath(2, 0, "b"));
        paths.add(new DEAPath(3, 2, "a"));
        paths.add(new DEAPath(3, 1, "b"));
        DEA dea = new DEA(4, 0, endStates, terminals, paths);
        while(true) {
            String input = JOptionPane.showInputDialog("Eingabe eines Wortes: ");
            if(input == null || input.equals("")) return;
            try {
                boolean accepted = dea.checkWord(input);
                JOptionPane.showMessageDialog(null, "<html>Dein Wort wird <u><b>" + (accepted ? "" : "nicht") + "</b></u> akzeptiert</html>");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,"Bitte nur Terminal-Symbole eingeben!");
            }
        }
    }

    private static void openVariableDEA() {
        try {
            int stateCount = inputStateCount();
            int startState = inputStartState(stateCount);
            Set<Integer> endStates = inputEndStates(stateCount);
            String[] terminals = JOptionPane.showInputDialog("Bitte gebe deine verschiedenen TerminalSymbole ein. (Mit Leertaste)").split(" ");
            ArrayList<DEAPath> paths = new ArrayList<>();

            for (int i = 0; i < stateCount; i++) {
                for (String terminal : terminals) {
                    paths.add(inputPath(i, terminal));
                }
            }

            DEA dea = new DEA(stateCount, startState, endStates, new HashSet<>(Arrays.asList(terminals)), paths);

            while(true) {
                String input = JOptionPane.showInputDialog("Eingabe eines Wortes: ");
                if(input == null || input.equals("")) return;
                try {
                    boolean accepted = dea.checkWord(input);
                    JOptionPane.showMessageDialog(null, "<html>Dein Wort wird <u><b>" + (accepted ? "" : "nicht") + "</b></u> akzeptiert</html>");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null,"Bitte nur Terminal-Symbole eingeben!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte gebe nur Ganzzahlen ein!");
        }
    }


    private static DEAPath inputPath(int i, String terminal) {
        try {
            int toState = Integer.parseInt(JOptionPane.showInputDialog(
                    "Zustand: " + i + "\n" +
                            "Terminal: " + terminal + "\n" +
                            "Wohin soll man damit gehen?"));
            return new DEAPath(i, toState, terminal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Bitte nur Zahlen eingeben!");
            return inputPath(i, terminal);
        }
    }

    private static int inputStateCount() {
        try {
            String input = JOptionPane.showInputDialog("Wie viele Zustände willst du haben? (Ganzzahl)");
            if(input == null || input.equals("")) System.exit(0);
            int stateCount = Integer.parseInt(input);
            if (stateCount < 1) {
                JOptionPane.showMessageDialog(null, "Du brauchst mindestens einen Zustand!");
                return inputStateCount();
            }
            StringBuilder stateList = new StringBuilder();
            stateList.append("Deine verfügbaren Zustände: \n");
            for (int i = 0; i < stateCount; i++) {
                stateList.append("\n- ").append(i);
            }
            JOptionPane.showMessageDialog(null, stateList.toString());

            return stateCount;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Bitte nur Zahlen eingeben!");
            return inputStateCount();
        }
    }

    private static int inputStartState(int stateCount) {
        try {
            String input = JOptionPane.showInputDialog("Welcher Zustand soll dein Startzustand sein? (Ganzzahl)");
            if(input == null || input.equals("")) System.exit(0);
            int startState = Integer.parseInt(input);
            if (stateCount < startState) {
                JOptionPane.showMessageDialog(null, "Dein Startzustand muss in deiner Zustandsliste vorhanden sein!");
                return inputStartState(stateCount);
            }
            return startState;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Bitte nur Zahlen eingeben!");
            return inputStartState(stateCount);
        }
    }

    private static Set<Integer> inputEndStates(int stateCount) {
        try {
            Set<Integer> endStates = Utils.stringArrayToIntSet(JOptionPane.showInputDialog("Bitte gebe deine Endzustände an. (Mit Leertaste)").split(" "));
            if(stateCount < Collections.max(endStates)) {
                JOptionPane.showMessageDialog(null, "Deine Endzustände müssen eine Teilmenge der Zustände sein! (Ganzzahl)");
                return inputEndStates(stateCount);
            }
            return endStates;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Bitte nur Zahlen eingeben!");
            return inputEndStates(stateCount);
        }
    }

}
