package me.mirsowasvonegal.ka;

import me.mirsowasvonegal.dea.DEA;
import me.mirsowasvonegal.dea.DEAPath;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class KAMenu {


    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Dieser Automat hat die Sprache: L(DEA)=w besteht aus einer geraden Anzahl von a's und b's");
        Set<Integer> endStates = new TreeSet<>();
        endStates.add(1);
        Set<String> terminals = new HashSet<>();
        terminals.add("a");
        terminals.add("b");
        ArrayList<KAPath> paths = new ArrayList<>();
        paths.add(new KAPath(0, 0, "a", null, KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "b", null, KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "a", "a", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "a", "b", KAPath.Action.DELETE));
        paths.add(new KAPath(0, 0, "b", "b", KAPath.Action.ADD));
        paths.add(new KAPath(0, 0, "b", "a", KAPath.Action.DELETE));
        paths.add(new KAPath(0, 1, null, null, KAPath.Action.ADD));
        KA ka = new KA(2, 0, endStates, terminals, paths);

    }

}
