package me.mirsowasvonegal.ka;

import me.mirsowasvonegal.utils.Utils;

import javax.swing.*;
import java.util.*;

public class KAMenu {

    public static void open() {
        try {
            int input = Integer.parseInt(JOptionPane.showInputDialog(
                    "Was willst du machen? \n" +
                            "1. Einen statischen Automaten benutzen \n" +
                            "2. Einen neuen Automaten erstellen"));
            if(input == 1) {
                openStaticKA();
            } else if(input == 2) {
                openVariableKA();
            } else {
                JOptionPane.showMessageDialog(null, "Bitte gebe nur 1 oder 2 ein!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte gebe nur Ganzzahlen ein!");
        }
    }

    private static void openStaticKA() {
        JOptionPane.showMessageDialog(null, "Dieser Automat hat die Sprache: L(KA)={e^m d c^n b^n a^m}");
        Set<Integer> endStates = new TreeSet<>();
        endStates.add(1);
        Set<String> terminals = new HashSet<>();
        terminals.add("a");
        terminals.add("b");
        terminals.add("c");
        terminals.add("d");
        terminals.add("e");
        ArrayList<KAPath> paths = new ArrayList<>();
        /*
        paths.add(new KAPath(0, 0, "a", "k0", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "b", "k0", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "a", "a", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "a", "b", KAPath.Action.DELETE)); // gleich viele a's und b's
        paths.add(new KAPath(0, 0, "b", "b", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "b", "a", KAPath.Action.DELETE));
        paths.add(new KAPath(0, 1, "", "k0", KAPath.Action.ADD));
        */
        paths.add(new KAPath(0,0, "e", "k0", KAPath.Action.ADD));
        paths.add(new KAPath(0,0, "e", "e", KAPath.Action.ADD));
        paths.add(new KAPath(0,1, "d", "k0", KAPath.Action.NOTHING));
        paths.add(new KAPath(0,1, "d", "e", KAPath.Action.ADD));
        paths.add(new KAPath(1,1, "c", "k0", KAPath.Action.NOTHING));
        paths.add(new KAPath(1,1, "c", "e", KAPath.Action.ADD));
        KA ka = new KA(2, 0, endStates, terminals, paths);
        while(true) {
            String input = JOptionPane.showInputDialog("Eingabe eines Wortes: ");
            if(input == null || input.equals("")) return;
            try {
                boolean accepted = ka.checkWord(input);
                JOptionPane.showMessageDialog(null, "<html>Dein Wort wird <u><b>" + (accepted ? "" : "nicht") + "</b></u> akzeptiert</html>");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,"Bitte nur Terminal-Symbole eingeben!");
            }
        }
    }

    private static void openVariableKA() {
        try {
            int stateCount = inputStateCount();
            int startState = inputStartState(stateCount);
            Set<Integer> endStates = inputEndStates(stateCount);
            String[] terminals = JOptionPane.showInputDialog("Bitte gebe deine verschiedenen TerminalSymbole ein. (Mit Leertaste)").split(" ");
            if(terminals.length == 0) System.exit(0);
            ArrayList<KAPath> paths = inputPaths();

            KA ka = new KA(stateCount, startState, endStates, new HashSet<>(Arrays.asList(terminals)), paths);

            while(true) {
                String input = JOptionPane.showInputDialog("Eingabe eines Wortes: ");
                if(input == null || input.equals("")) return;
                try {
                    boolean accepted = ka.checkWord(input);
                    JOptionPane.showMessageDialog(null, "<html>Dein Wort wird <u><b>" + (accepted ? "" : "nicht") + "</b></u> akzeptiert</html>");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null,"Bitte nur Terminal-Symbole eingeben!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte gebe nur Ganzzahlen ein!");
        }
    }

    private static ArrayList<KAPath> inputPaths() {
        ArrayList<KAPath> paths = new ArrayList<>();
        String input = JOptionPane.showInputDialog("Wie viele Pfade willst du haben? (Ganzzahl)");
        if(input == null || input.equals("")) System.exit(0);
        int pathCount = Integer.parseInt(input);
        if(pathCount < 1) {
            JOptionPane.showMessageDialog(null,"Du brauchst mindestens einen Pfad!");
            return inputPaths();
        }
        for (int i = 0; i < pathCount; i++) {
            paths.add(inputPath(i, pathCount));
        }
        return paths;
    }

    private static KAPath inputPath(int i, int pathCount) {
        String[] pathString = JOptionPane.showInputDialog(
                "Deine Eingabe soll so Aussehen: \n" +
                        "Zustand: Aktueller Zustand\n" +
                        "Terminalsymbol: a, b, usw. (Lambda mit X)\n" +
                        "Keller: Oberstes Keller Element. Entweder a, b, usw. oder k0\n" +
                        "Neuer Zustand: In den Zustand soll gegangen werden\n" +
                        "ADD: Fügt etwas auf den Stack hinzu.\n" +
                        "DELETE: Nimmt etwas vom Stack runter.\n" +
                        "NOTHING: Es passiert nichts.\n" +
                        "Aktueller Pfad: " + (i + 1) + "/" + pathCount +"\n" +
                        "<Zustand> <Terminalsymbol> <Keller> <Neuer Zustand> <ADD/DELETE/NOTHING>").split(" ");
        if(pathString.length == 0) System.exit(0);
        if(pathString.length != 5) {
            JOptionPane.showMessageDialog(null,"Du musst alle Parameter angeben!");
            return inputPath(i, pathCount);
        }
        int fromState = Integer.parseInt(pathString[0]);
        String terminal = pathString[1].replace("X", "");
        String top = pathString[2];
        int toState = Integer.parseInt(pathString[3]);
        try {
            KAPath.Action action = KAPath.Action.valueOf(pathString[4]);
            return new KAPath(fromState, toState, terminal, top, action);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,"Du musst eine gültige Action verwenden! (ADD/DELETE/NOTHING)");
            return inputPath(i, pathCount);
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
