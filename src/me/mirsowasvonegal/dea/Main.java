package me.mirsowasvonegal.dea;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> endStates = new ArrayList<>();
        endStates.add(0);
        Set<String> terminals = new HashSet<>();
        terminals.add("a");
        terminals.add("b");
        ArrayList<Path> paths = new ArrayList<>();
        paths.add(new Path(0, 1, "a"));
        paths.add(new Path(0, 2, "b"));
        paths.add(new Path(1, 0, "a"));
        paths.add(new Path(1, 3, "b"));
        paths.add(new Path(2, 3, "a"));
        paths.add(new Path(2, 0, "b"));
        paths.add(new Path(3, 2, "a"));
        paths.add(new Path(3, 1, "b"));
        DEA dea = new DEA(4, 0, endStates, terminals, paths);
        String input = JOptionPane.showInputDialog("Eingabe eines Wortes: ");
        System.out.println(dea.checkWord(input));
    }

}
