package me.mirsowasvonegal.ka;

import me.mirsowasvonegal.dea.DEA;
import me.mirsowasvonegal.dea.DEAPath;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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

    }

}
