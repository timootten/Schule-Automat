package me.mirsowasvonegal;

import me.mirsowasvonegal.dea.DEAMenu;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            int input = Integer.parseInt(JOptionPane.showInputDialog(
                    "Was willst du verwenden?\n" +
                    "1. DEA (Deterministischer endlicher Automat)\n" +
                    "2. KA (Keller Automat)"));
            if(input == 1) {
                DEAMenu.open();
            } else if(input == 2) {

            } else {
                JOptionPane.showMessageDialog(null, "Bitte gebe nur 1 oder 2 ein!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte gebe nur Ganzzahlen ein!");
        }
    }

}
