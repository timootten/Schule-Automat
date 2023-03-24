package me.mirsowasvonegal;

import me.mirsowasvonegal.dea.DEAMenu;
import me.mirsowasvonegal.ka.KAMenu;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            String input = JOptionPane.showInputDialog(
                    "Was willst du verwenden?\n" +
                    "1. DEA (Deterministischer endlicher Automat)\n" +
                    "2. KA (Keller Automat)");
            if(input == null || input.equals("")) return;

            int number = Integer.parseInt(input);
            if(number == 1) {
                DEAMenu.open();
            } else if(number == 2) {
                KAMenu.open();
            } else {
                JOptionPane.showMessageDialog(null, "Bitte gebe nur 1 oder 2 ein!");
                main(args);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte gebe nur Ganzzahlen ein!");
            main(args);
        }
    }

}
