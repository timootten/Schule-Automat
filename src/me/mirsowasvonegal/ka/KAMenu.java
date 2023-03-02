package me.mirsowasvonegal.ka;

import me.mirsowasvonegal.dea.DEA;
import me.mirsowasvonegal.dea.DEAPath;
import me.mirsowasvonegal.utils.Utils;

import javax.swing.*;
import java.util.*;

public class KAMenu {

    public static void open() {
        try {
            int input = Integer.parseInt(JOptionPane.showInputDialog(
                            "Was willst du machen?\n" +
                            "1. Einen statischen Automaten benutzen\n" +
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
        JOptionPane.showMessageDialog(null, "Dieser Automat hat die Sprache: L(KA)=w besteht aus gleich vielen a's und b's");
        Set<Integer> endStates = new TreeSet<>();
        endStates.add(1);
        Set<String> terminals = new HashSet<>();
        terminals.add("a");
        terminals.add("b");
        ArrayList<KAPath> paths = new ArrayList<>();
        paths.add(new KAPath(0, 0, "a", "k0", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "b", "k0", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "a", "a", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "a", "b", KAPath.Action.DELETE));
        paths.add(new KAPath(0, 0, "b", "b", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "b", "a", KAPath.Action.DELETE));
        paths.add(new KAPath(0, 1, "", "k0", KAPath.Action.ADD));
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
            int stateCount = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Zustände willst du haben? (Ganzzahl)"));
            if(stateCount < 1) {
                JOptionPane.showMessageDialog(null,"Du brauchst mindestens einen Zustand!");
                return;
            }
            StringBuilder stateList = new StringBuilder();
            stateList.append("Deine verfügbaren Zustände: \n");
            for (int i = 0; i < stateCount; i++) {
                stateList.append("\n- ").append(i);
            }
            JOptionPane.showMessageDialog(null,stateList.toString());
            int startState = Integer.parseInt(JOptionPane.showInputDialog("Welcher Zustand soll dein Startzustand sein? (Ganzzahl)"));
            if(stateCount < startState) {
                JOptionPane.showMessageDialog(null,"Dein Startzustand muss in deiner Zustandsliste vorhanden sein!");
                return;
            }
            Set<Integer> endStates = Utils.stringArrayToIntSet(JOptionPane.showInputDialog("Bitte gebe deine Endzustände an. (Mit Leertaste)").split(" "));
            if(stateCount < Collections.max(endStates)) {
                JOptionPane.showMessageDialog(null, "Deine Endzustände müssen eine Teilmenge der Zustände sein! (Ganzzahl)");
                return;
            }
            String[] terminals = JOptionPane.showInputDialog("Bitte gebe deine verschiedenen TerminalSymbole ein. (Mit Leertaste)").split(" ");
            ArrayList<KAPath> paths = new ArrayList<>();

            int pathCount = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Pfade willst du haben? (Ganzzahl)"));
            if(pathCount < 1) {
                JOptionPane.showMessageDialog(null,"Du brauchst mindestens einen Pfad!");
                return;
            }
            for (int i = 0; i < pathCount; i++) {
                String[] pathString = JOptionPane.showInputDialog(
                                "Deine Eingabe soll so Aussehen: \n" +
                                "Zustand: Aktueller Zustand\n" +
                                "Terminalsymbol: a, b, usw. (Lambda mit X)\n" +
                                "Keller: Oberstes Keller Element. Entweder a, b, usw. oder k0\n" +
                                "Neuer Zustand: In den Zustand soll gegangen werden\n" +
                                "ADD/DELETE: Fügt etwas auf den Stack hinzu oder nimmt etwas runter.\n\n" +
                                "Aktueller Pfad: " + (i + 1) + "/" + pathCount +"\n" +
                                "<Zustand> <Terminalsymbol> <Keller> <Neuer Zustand> <ADD/DELETE>").split(" ");
                if(pathString.length != 5) {
                    JOptionPane.showMessageDialog(null,"Du musst alle Parameter angeben!");
                    return;
                }
                int fromState = Integer.parseInt(pathString[0]);
                String terminal = pathString[1].replace("X", "");
                String top = pathString[2];
                int toState = Integer.parseInt(pathString[3]);
                KAPath.Action action = pathString[4].equals("ADD") ? KAPath.Action.ADD : KAPath.Action.DELETE;
                paths.add(new KAPath(fromState, toState, terminal, top, action));
            }

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

}
