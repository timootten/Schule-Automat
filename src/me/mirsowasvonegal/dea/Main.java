package me.mirsowasvonegal.dea;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Set<Integer> endStates = new TreeSet<>();
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
        if(input == null || input.equals("")) return;
        boolean accepted = dea.checkWord(input);
        JOptionPane.showMessageDialog(null, "<html>Dein Wort wird <u><b>" + (accepted ? "" : "nicht") + "</b></u> akzeptiert</html>");
        main(args);
    }

}
